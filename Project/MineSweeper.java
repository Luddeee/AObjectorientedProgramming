package Project;

import java.awt.GridLayout;
import javax.swing.*;
import java.util.HashSet;
import java.util.Random;
import java.awt.event.*;

class MineSweeper{
    private JPanel mainPanel = new JPanel();
    private JButton[][] squares;
    private HashSet<String> mines = new HashSet<>();
    private boolean[][] visited;
    private boolean[][] flagged;

    int easymines = 10;
    int easyrows = 8;

    int numMines = easymines; //Numbers of mines to exist in the game
    int row = easyrows; //Number of rows to exist, (the number of squares to exist in the playing field) (the field will be a square thus row and col are equal)

    MineSweeper(){
        initializeGrid();
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    public void restart(){
        //This will restart the whole game and all it's content with the settings currently equiped
        System.out.println("restarted"); //testing var
        try{
            System.out.println("Difficulty is now: " + settings.getDifficulty());
        } catch(Exception e){
            System.out.println("No difficulty is set, reverting to \"Easy\"");
        }
        
        initializeGrid();
    }

    private void initializeGrid(){
        try{
        if((String)settings.getDifficulty()=="Easy"){
            row = easyrows;
            numMines = easymines;
        }
        else if((String)settings.getDifficulty()=="Medium"){
            row = 14;
            numMines = 25;
        }
        else if((String)settings.getDifficulty()=="Hard"){
            row = 24;
            numMines = 99;
        }
        }
        catch(Exception e){
            
        }
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
                squares[i][j].setActionCommand(i + " " + j); //Determines the action the buttons should havea
                final int fi = i; //Final variables as to not tuch i and j (needed for nearBombsCounter())
                final int fj = j;
                squares[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                            toggleFlag(fi, fj);
                        } else if (SwingUtilities.isLeftMouseButton(e)) {
                            showButton(fi, fj);
                        }
                    }
                });
                //squares[i][j].setActionCommand(fi + " " + fj);
                mainPanel.add(squares[i][j]);
            }
        }
        mainPanel.revalidate();
        mainPanel.repaint();
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

        visited[x][y] = true;
        int bombsNearby = nearBombsCounter(x, y);
        JButton button = squares[x][y];
        button.setText(Integer.toString(bombsNearby));
        button.setEnabled(false);

        if (mines.contains(x + " " + y)) {
            button.setText("BOOM");
            JOptionPane.showMessageDialog(mainPanel, "You hit a mine!");
        } else if (bombsNearby == 0) {
            // Recursively reveal adjacent squaress
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    showButton(x + i, y + j);
                }
            }
        }
    }
    private void toggleFlag(int x, int y) {
        if (!visited[x][y]) { // Prevent flagging after revealing
            flagged[x][y] = !flagged[x][y]; // Toggle flag status
            JButton button = squares[x][y];
            if (flagged[x][y]) {
                button.setText("F");
            } else {
                button.setText("");
            }
        }
    }
}