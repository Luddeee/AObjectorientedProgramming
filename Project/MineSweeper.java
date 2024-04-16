package Project;

import java.awt.GridLayout;

import javax.swing.*;

public class MineSweeper extends grid{
    private JPanel mainPanel = new JPanel();
    private JButton[][] squares;

    private int numMines = 1; //Numbers of mines to exist in the game
    int row = 8; //Number of rows to exist, (the number of squares to exist in the playing field) (the field will be a square thus row and col are equal)

    public MineSweeper(){
        mainPanel.setLayout(new GridLayout(row, row));

        squares = new JButton[row][row];
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares.length; j++) {
                squares[i][j] = new JButton();
                squares[i][j].setActionCommand(i + " " + j); //Determines the action the buttons should have
                mainPanel.add(squares[i][j]);
            }
        }
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    public static void restart(){
        //This will restart the whole game and all it's content with the settings currently equiped
        System.out.println("restarted"); //testing var
        System.out.println("Difficulty is now: " + settings.getDifficulty());
    }

    public static void load(){
        //Loads and starts the game, if game is already running the game instead restarts using the restart method and then calls itself to begin again
        System.out.println("Loaded"); //Testing var
    }

    void saveHighscore(){
        //Saves the time of completion and difficulty when winning to a text file, (the text file can then be loaded in)
        //This also needs to add the highscore in the correct order in the text file so that the best is at the top
        //This is to be updated after you win
    }
}
