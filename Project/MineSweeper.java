package Project;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class MineSweeper implements Subject{
// The above code snippet is declaring three private fields in a Java class:
// 1. `observers`: A list of `Observer` objects initialized with an empty `ArrayList`.
// 2. `highScoresManager`: An instance of `HighScoresManager` class.
// 3. `gameStrategy`: An instance of `GameStrategy` class.
    private List<Observer> observers = new ArrayList<>();
    private HighScoresManager highScoresManager;
    private GameStrategy gameStrategy;
    
    public boolean gridActive = false;
    public String currdifficulty;
    int discoverBombs = 0;
    int currBombReveal = 1;

    private long startTime;
    private long endTime;

    private JPanel mainPanel = new JPanel();
    private JButton[][] squares;
    HashSet<String> mines = new HashSet<>();
    boolean[][] visited;
    private boolean[][] flagged;
    BufferedImage originalImage,bombImage2,flag2;
    Image scaledImage,bombImage,flag;

    int easymines = 10;
    int easyrows = 8;
    int wincounter;
    boolean isLost = false;

    public int numMines = easymines; //Numbers of mines to exist in the game
    int row = easyrows; //Number of rows to exist, (the number of squares to exist in the playing field) (the field will be a square thus row and col are equal)

    private int currXPlace = 0, currYPlace = 0;

    // The above code is a constructor for a `MineSweeper` class in Java. It sets the strategy for the
    // MineSweeper game to an instance of `EasyStrategy`, initializes a `HighScoresManager`, and sets
    // global listeners for the game.
    public MineSweeper(){
        setStrategy(new EasyStrategy());
        highScoresManager = new HighScoresManager();
        setGlobalListeners();
    }

    /**
     * The `registerObserver` function adds an observer to a list of observers if it is not already
     * present.
     * 
     * @param o The parameter "o" in the method "registerObserver" is an object of type Observer. This
     * object represents an observer that will be registered to receive updates from the subject.
     */
    @Override
    public void registerObserver(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

   /**
    * The function removes an observer from a list of observers.
    * 
    * @param o The parameter `o` in the `removeObserver` method is an object of type `Observer` that
    * you want to remove from the list of observers.
    */
    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    /**
     * The `notifyObservers` function iterates through a list of observers and calls the `update`
     * method on each observer.
     */
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    /**
     * The `setStrategy` method sets a new game strategy, applies settings, and restarts the game.
     * 
     * @param strategy The `strategy` parameter in the `setStrategy` method is an object of type
     * `GameStrategy`.
     */
    public void setStrategy(GameStrategy strategy) {
        this.gameStrategy = strategy;
        this.gameStrategy.applySettings(this);
        //notifyObservers();
        restart();
    }

    /**
     * The function `getMainPanel()` returns the main panel in a Java program.
     * 
     * @return The method `getMainPanel()` is returning a `JPanel` object named `mainPanel`.
     */
    public JPanel getMainPanel(){
        return mainPanel;
    }

    int restartcounter = 0;

    /**
     * The `restart` function resets the game, increments a counter, sets the start time, initializes
     * the grid, and notifies observers.
     */
    public void restart(){
        restartcounter++;
        startTime = System.currentTimeMillis();
        System.out.println("restarted");
        isLost = false;
        currBombReveal = 1;
        initializeGrid();
        notifyObservers();
    }

    /**
     * The function `getrestartcount` returns the value of the `restartcounter` variable.
     * 
     * @return The method `getrestartcount` is returning the value of the variable `restartcounter`.
     */
    public int getrestartcount(){
        return restartcounter;
    }

    /**
     * The `initializeGrid` function sets up a grid layout, adds buttons with mouse click event
     * listeners, and refreshes the main panel for a Minesweeper game.
     */
    void initializeGrid(){
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
                final int fi = i;
                final int fj = j;;
                squares[i][j].setContentAreaFilled(false);
                squares[i][j].addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        handleMouseClick(e, fi, fj);
                    }
                });
                mainPanel.add(squares[i][j]);
            }
        }
        updateFocus();
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    /**
     * The function `handleMouseClick` processes mouse clicks by toggling flags or showing buttons
     * based on the mouse button pressed, and notifies observers if the game is not lost.
     * 
     * @param e The parameter `e` is of type `MouseEvent` and represents the mouse event that occurred.
     * It contains information about the event such as the type of mouse button clicked, the
     * coordinates of the mouse click, etc.
     * @param x The parameter `x` in the `handleMouseClick` method represents the x-coordinate of the
     * mouse click location on the screen. It is used to determine the position where the mouse click
     * occurred within the application window or component.
     * @param y The 'y' parameter in the `handleMouseClick` method represents the vertical coordinate
     * of the mouse click event. It is used to determine the position where the mouse click occurred on
     * the screen.
     */
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

    /**
     * The `updateFocus` function iterates through a 2D array of squares and highlights the square at
     * the current position with a red border while setting all other squares to have a black border.
     */
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

    /**
     * The `setGlobalListeners` function in Java sets up keyboard event listeners to handle arrow key
     * movements and button interactions in a game interface.
     */
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
    

    /**
     * The function sets random mine locations on a grid
     * until the desired number of mines is reached.
     */
    private void setMines() {
        Random rand = new Random();
        while (mines.size() < numMines) {
            int i = rand.nextInt(row);
            int j = rand.nextInt(row);
            mines.add(i + " " + j);
        }
    }

    /**
     * The function `nearBombsCounter` calculates the number of mines surrounding a given cell in a
     * minesweeper game grid.
     * 
     * @param x The parameter `x` represents the x-coordinate of a cell on a grid where you are trying
     * to count the number of neighboring bombs. The function `nearBombsCounter` calculates the number
     * of bombs in the neighboring cells of the cell at coordinates (x, y) on the grid.
     * @param y The `y` parameter in the `nearBombsCounter` function represents the y-coordinate of a
     * cell in a grid. The function calculates the number of bombs in the neighboring cells of the
     * specified cell at coordinates (x, y).
     * @return The function `nearBombsCounter` returns the count of neighboring bombs around the cell
     * at coordinates (x, y).
     */
    int nearBombsCounter(int x, int y) {
        int count = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < row && j >= 0 && j < row) {
                    if (mines.contains(i + " " + j)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

   /**
    * The function `showButton` in Java handles the display of buttons in a Minesweeper game, including
    * setting icons based on nearby bombs, handling clicks on mines, and checking for win conditions.
    * 
    * @param x The `x` parameter in the `showButton` method represents the x-coordinate of the button
    * in a grid. It is used to determine the position of the button within the grid and to perform
    * various operations based on its value.
    * @param y The parameter `y` in the `showButton` method represents the y-coordinate of the button
    * in a grid. It is used to determine the position of the button within the grid and to perform
    * operations based on its location, such as checking nearby bombs and updating the button's
    * appearance.
    */
    void showButton(int x, int y) {
        if (x < 0 || x >= row || y < 0 || y >= row || visited[x][y]) {
            return; //Error handling if we are out of bounds
        }
        String fileName;
        visited[x][y] = true;
        int bombsNearby = nearBombsCounter(x, y);
        JButton button = squares[x][y];
        if (bombsNearby == 0) {
            fileName = "blaa.png";
        } else if (bombsNearby >= 1 && bombsNearby <= 8) {
            fileName = "number-" + bombsNearby + ".png";
        } else {
            return;
        }
        try {
            originalImage = ImageIO.read(new File("Project/interfaceIcons/" + fileName));
            scaledImage = originalImage.getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mines.contains(x + " " + y)) {
            try {
                bombImage2 = ImageIO.read(new File("Project/interfaceIcons/bomb.png"));
                bombImage = bombImage2.getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(bombImage));
            } catch (Exception e) {
                e.printStackTrace();
            }
            isLost = true;
            JOptionPane.showMessageDialog(mainPanel, "You hit a mine!");
            for(int i = 0; i < row; i++){
                for(int j = 0; j < row; j++){
                    if (mines.contains(i + " " + j)){
                        button = squares[i][j];
                        button.setIcon(new ImageIcon(bombImage));
                    }
                }
            }
        } else if (bombsNearby == 0) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    showButton(x + i, y + j);
                }
            }
        }
        wincounter++;
        if(isLost == false){
            if(wincounter == ((row*row)-numMines)){
                try {
                    bombImage2 = ImageIO.read(new File("Project/interfaceIcons/bomb.png"));
                    bombImage = bombImage2.getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
                        }
                    }
                }
            }
            notifyObservers();
        }
    }
    /**
     * The `toggleFlag` function toggles the flag status of a square on a game board and updates the
     * corresponding button icon accordingly.
     * 
     * @param x The `x` parameter in the `toggleFlag` method represents the x-coordinate of the square
     * on the game board where the flag is being toggled.
     * @param y The `y` parameter in the `toggleFlag` method represents the vertical position or the
     * column index of the cell in a grid. It is used to specify the location of the cell where the
     * flag status is being toggled.
     */
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

    /**
     * The function `setDifficulty` sets the game difficulty level, number of rows, and mines, and
     * reloads high scores if the grid is active.
     * 
     * @param difficulty The `difficulty` parameter in the `setDifficulty` method is a String that
     * represents the level of difficulty for the game. It could be values like "easy", "medium", or
     * "hard".
     * @param rows The `rows` parameter in the `setDifficulty` method represents the number of rows in
     * the game grid. It determines the vertical size of the grid where the game will be played.
     * @param mines The `mines` parameter in the `setDifficulty` method represents the number of mines
     * that will be present in the game grid for the specified difficulty level. This parameter
     * determines the level of challenge and risk in the game.
     */
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

    /**
     * The `getRow` function in Java returns the value of the `row` variable.
     * 
     * @return The `row` variable is being returned.
     */
    public int getRow(){
        return row;
    }

    /**
     * The `getCellStatus` function returns the status of a cell on a grid based on whether it has been
     * visited, contains a mine, is flagged, or has not been opened.
     * 
     * @param x The parameter `x` represents the x-coordinate of the cell in a grid.
     * @param y The `y` parameter represents the y-coordinate of the cell for which you want to get the
     * status. The `getCellStatus` method takes two parameters `x` and `y`, which are the coordinates
     * of a cell in a grid. The method checks the status of the cell at the specified coordinates
     * @return The `getCellStatus` method returns the status of a cell at coordinates (x, y) on a grid.
     * The possible return values are:
     * - "M" if the cell is a mine
     * - A number indicating the number of bombs nearby if the cell has been visited
     * - "F" if the cell is flagged
     * - "U" if the cell has not been opened
     */
    public String getCellStatus(int x, int y) {
        if (visited[x][y]) {
            if (mines.contains(x + " " + y)) {
                return "M";
            } else {
                int bombs = nearBombsCounter(x, y);
                return Integer.toString(bombs);
            }
        } else if (flagged[x][y]) {
            return "F";
        }
        return "U";
    }
    /**
     * The `revealBombPosition` function reveals the positions of bombs on a grid by updating the icons
     * of specific buttons with a bomb image.
     */
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

    /**
     * The `saveHighscore` method saves the player's high score with their name and elapsed time, and
     * reloads the high scores grid if the highScoresManager is properly initialized.
     * 
     * @param elapsedTime The `elapsedTime` parameter in the `saveHighscore` method represents the time
     * taken to achieve a certain score or complete a task in the game. It is a measure of the time
     * duration in milliseconds that the player took to complete a particular level or game session.
     * @param name The `name` parameter in the `saveHighscore` method represents the name of the player
     * who achieved the high score. This name will be associated with the elapsed time in the high
     * scores list.
     */
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