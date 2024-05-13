package Project;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class MineSweeper implements Subject{
    private List<Observer> observers = new ArrayList<>();
    private HighScoresManager highScoresManager;
    private GameStrategy gameStrategy;
    
    public boolean gridActive = false;
    String currdifficulty;
    int discoverBombs = 0;
    int currBombReveal = 1;

    private long startTime;
    private long endTime;

    private JPanel mainPanel = new JPanel();
    private JButton[][] squares;
    private HashSet<String> mines = new HashSet<>();
    private boolean[][] visited;
    private boolean[][] flagged;
    BufferedImage originalImage,bombImage2,flag2;
    Image scaledImage,bombImage,flag;

    int easymines = 10;
    int easyrows = 8;
    int wincounter;
    boolean isLost = false;

    int numMines = easymines; //Numbers of mines to exist in the game
    int row = easyrows; //Number of rows to exist, (the number of squares to exist in the playing field) (the field will be a square thus row and col are equal)

    private int currXPlace = 0, currYPlace = 0;

    public MineSweeper(){
        setStrategy(new EasyStrategy());
        highScoresManager = new HighScoresManager();
        setGlobalListeners();
    }

    @Override
    public void registerObserver(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void setStrategy(GameStrategy strategy) {
        this.gameStrategy = strategy;
        this.gameStrategy.applySettings(this);
        //notifyObservers();
        restart();
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    int restartcounter = 0;

    public void restart(){
        //This will restart the whole game and all it's content with the settings currently equiped
        restartcounter++;
        startTime = System.currentTimeMillis();
        System.out.println("restarted"); //testing var
        try{
            //System.out.println("Difficulty is now: " + settings.getDifficulty());
        } catch(Exception e){
            System.out.println("No difficulty is set, reverting to \"Easy\"");
        }
        isLost = false;
        currBombReveal = 1;
        initializeGrid();
        notifyObservers();
    }

    public int getrestartcount(){
        return restartcounter;
    }

    private void initializeGrid(){
        wincounter = 0;
        mainPanel.removeAll();
        mines.clear();
        mainPanel.setLayout(new GridLayout(row, row));

        setMines();

        squares = new JButton[row][row];
        visited = new boolean[row][row];
        flagged = new boolean[row][row];
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares.length; j++) {
                squares[i][j] = new JButton();
                squares[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                //squares[i][j].setActionCommand(i + " " + j); //Determines the action the buttons should havea
                final int fi = i; //Final variables as to not tuch i and j (needed for nearBombsCounter())
                final int fj = j;;
                squares[i][j].setContentAreaFilled(false);
                squares[i][j].addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        handleMouseClick(e, fi, fj);
                    }
                });
                //squares[i][j].setActionCommand(fi + " " + fj);
                mainPanel.add(squares[i][j]);
            }
        }
        updateFocus();
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void handleMouseClick(MouseEvent e, int x, int y) {
        if(isLost==false){
            if (SwingUtilities.isRightMouseButton(e)) {
                toggleFlag(x, y);
            } else if (SwingUtilities.isLeftMouseButton(e)) {
                showButton(x, y);
            }
                notifyObservers();
        }
    }

    private void updateFocus() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                if (i == currXPlace && j == currYPlace) {
                    squares[i][j].setBorder(BorderFactory.createLineBorder(Color.RED, 2)); // Highlight border for focus
                } else {
                    squares[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
            }
        }
    }

    private void setGlobalListeners() {
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyEventDispatcher() {
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                            currYPlace = Math.max(0, currYPlace - 1); break;
                        case KeyEvent.VK_RIGHT:
                            currYPlace = Math.min(row - 1, currYPlace + 1); break;
                        case KeyEvent.VK_UP:
                            currXPlace = Math.max(0, currXPlace - 1); break;
                        case KeyEvent.VK_DOWN:
                            currXPlace = Math.min(row - 1, currXPlace + 1); break;
                        case KeyEvent.VK_X:
                        if(isLost==false){
                            showButton(currXPlace, currYPlace); break;}
                        case KeyEvent.VK_Z:
                        if(isLost==false){
                            toggleFlag(currXPlace, currYPlace); break;}
                    }
                }
                    updateFocus();
                return false;
            }
        });
    }
    

    void saveHighscore(){
        //Saves the time of completion and difficulty when winning to a text file, (the text file can then be loaded in)
        //This also needs to add the highscore in the correct order in the text file so that the best is at the top
        //This is to be updated after you win
    }

    private void setMines() {
        Random rand = new Random();
        while (mines.size() < numMines) {
            int i = rand.nextInt(row);
            int j = rand.nextInt(row);
            mines.add(i + " " + j);
        }
    }

    private int nearBombsCounter(int x, int y) {
        int count = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < row && j >= 0 && j < row) { // Ensure indices are within grid bounds
                    if (mines.contains(i + " " + j)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private void showButton(int x, int y) {
        if (x < 0 || x >= row || y < 0 || y >= row || visited[x][y]) {
            return; //Error handling if we are out of bounds
        }
        String fileName;
        visited[x][y] = true;
        int bombsNearby = nearBombsCounter(x, y);
        JButton button = squares[x][y];
        if (bombsNearby == 0) {
            //if(!mines.contains(x + " " + y)){playSound("Project/soundFiles/plingsound.wav");}
            fileName = "blaa.png";
        } else if (bombsNearby >= 1 && bombsNearby <= 8) {
            //if(!mines.contains(x + " " + y)){playSound("Project/soundFiles/plingsound.wav");}
            fileName = "number-" + bombsNearby + ".png";
        } else {
            // Handle invalid number of nearby bombs
            return;
        }
        try {
            originalImage = ImageIO.read(new File("Project/interfaceIcons/" + fileName));
            scaledImage = originalImage.getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception gracefully, maybe show an error message
        }
        //button.setText(Integer.toString(bombsNearby));
        //button.setEnabled(false);
        if (mines.contains(x + " " + y)) {
            //button.setText("BOOM");
            try {
                bombImage2 = ImageIO.read(new File("Project/interfaceIcons/bomb.png"));
                bombImage = bombImage2.getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(bombImage));
            } catch (Exception e) {
                e.printStackTrace();
                // Handle the exception gracefully, maybe show an error message
            }
            //playSound("Project/soundFiles/boomsound.wav");
            JOptionPane.showMessageDialog(mainPanel, "You hit a mine!");
            isLost = true;
            for(int i = 0; i < row; i++){
                for(int j = 0; j < row; j++){
                    if (mines.contains(i + " " + j)){
                        button = squares[i][j];
                        button.setIcon(new ImageIcon(bombImage));
                        //button.setEnabled(false); //same principle.
                    }
                }
            }
        } else if (bombsNearby == 0) {
            // Recursively reveal adjacent squaress
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    showButton(x + i, y + j);
                }
            }
        }
        wincounter++;
        if(isLost == false){
            if(wincounter == ((row*row)-numMines)){
                //button.setText("BOOM");
                try {
                    bombImage2 = ImageIO.read(new File("Project/interfaceIcons/bomb.png"));
                    bombImage = bombImage2.getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
                } catch (Exception e) {
                    e.printStackTrace();
                    // Handle the exception gracefully, maybe show an error message
                }
                //playSound("Project/soundFiles/winsound.wav");
                endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                String name = JOptionPane.showInputDialog("YOU Win! Enter your name:");
                if (name == null) {
                    name = "Pelle svanslös";
                }
                saveHighscore(elapsedTime,name);
                isLost = true;
                for(int i = 0; i < row; i++){
                    for(int j = 0; j < row; j++){
                        if (mines.contains(i + " " + j)){
                            button = squares[i][j];
                            button.setIcon(new ImageIcon(bombImage));
                            //button.setEnabled(false); //same principle.
                        }
                    }
                }
            }
            notifyObservers();
        }
    }
    private void toggleFlag(int x, int y) {
        if (!visited[x][y]) { // Prevent flagging after revealing
            flagged[x][y] = !flagged[x][y]; // Toggle flag status
            JButton button = squares[x][y];
            if (flagged[x][y]) {
                try {
                    flag2 = ImageIO.read(new File("Project/interfaceIcons/flag.png"));
                    flag = flag2.getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
                    button.setIcon(new ImageIcon(flag));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Failed to create a flag");
                }
            } else {
                //button.setText("");
                button.setIcon(null);
            }
        }
        notifyObservers();
    }

    public void setDifficulty(String difficulty, int rows, int mines) {
        currdifficulty = difficulty;
        String test ="Project/Highscores" + currdifficulty + ".txt";
        System.out.println(test);
        if(true == gridActive){
            grid.getInstance().reloadHighScores("Project/Highscores" + currdifficulty + ".txt");
        }
        this.row = rows;
        this.numMines = mines;
        System.out.println("Difficulty is now: " + difficulty + " Rows:" + rows + " Mines:" + mines);
    }

    /*public void playSound(String soundFileName) {
        try {
            File soundFile = new File(soundFileName);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    } */

    public int getRow(){
        return row;
    }

    public String getCellStatus(int x, int y) {
        if (visited[x][y]) {
            if (mines.contains(x + " " + y)) {
                return "M"; //if cell is a mine
            } else {
                int bombs = nearBombsCounter(x, y);
                return Integer.toString(bombs); // Return the number of bombs nearby
            }
        } else if (flagged[x][y]) {
            return "F"; //if cell is flagged
        }
        return "U"; //if cell has not been opened
    }
    public void revealBombPosition() {
        int x = 0;
        int y = 0;
        JButton button = squares[x][y];
        boolean OneBomb = true;
        discoverBombs = 0;
        try {
            bombImage2 = ImageIO.read(new File("Project/interfaceIcons/bomb.png"));
            bombImage = bombImage2.getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception gracefully, maybe show an error message
        }
        for(int i = 0; i < row; i++){
            for(int j = 0; j < row; j++){
                if (mines.contains(i + " " + j) && (OneBomb == true)){
                    discoverBombs++;
                    if(discoverBombs == currBombReveal){
                        OneBomb = false;
                    }
                    button = squares[i][j];
                    button.setIcon(new ImageIcon(bombImage));
                    //button.setEnabled(false); //same principle.
                }
            }
        }
        OneBomb = false;
        currBombReveal++;
    }

    private void saveHighscore(long elapsedTime, String name) {
        // Ensure highScoresManager is properly initialized
        if (highScoresManager != null) {
            highScoresManager.updateHighScores("Project/Highscores" + currdifficulty + ".txt", name, elapsedTime);
            System.err.println(elapsedTime);
            // Reload high scores in the grid
            String test ="Project/Highscores" + currdifficulty + ".txt";
            System.out.println(test);
            grid.getInstance().reloadHighScores("Project/Highscores" + currdifficulty + ".txt");
        } else {
            System.err.println("Error: highScoresManager is not initialized.");
        }
    }
}