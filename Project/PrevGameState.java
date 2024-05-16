package Project;

import java.util.ArrayList;
import java.util.List;

public class PrevGameState implements Observer {
    private MineSweeper mineSweeper;
    private List<String[][]> gameStates = new ArrayList<>();

    public PrevGameState(MineSweeper mineSweeper) {
        this.mineSweeper = mineSweeper;
        this.mineSweeper.registerObserver(this);
    }

    int oldrcounter;

    /**
     * This Java function updates the game state based on the current cell status and restart count in
     * a Minesweeper game.
     */
    @Override
    public void update() {
        int rcounter = mineSweeper.getrestartcount();
        String[][] currentState = new String[mineSweeper.getRow()][mineSweeper.getRow()];
        for (int i = 0; i < mineSweeper.getRow(); i++) {
            for (int j = 0; j < mineSweeper.getRow(); j++) {
                if (rcounter != oldrcounter) {
                    currentState[i][j] = "Restarted";
                } else {
                    currentState[i][j] = mineSweeper.getCellStatus(i, j);
                }
            }
        }
        gameStates.add(currentState);
        oldrcounter = rcounter;
    }

    /**
     * The function `getGameStates` returns a list of 2D string arrays representing game states.
     * 
     * @return A List of two-dimensional String arrays representing game states is being returned.
     */
    public List<String[][]> getGameStates() {
        return gameStates;
    }

    /**
     * This Java function iterates through a 2D array of game states, printing each state with a
     * specific format and handling a special case for the "Restarted" state.
     */
    public void printCapturedStates() {
        int gameStateNumber = 1;
        boolean once = true;
        for (String[][] state : gameStates) {
            System.out.println("Game State #" + gameStateNumber + ":");
            for (int i = 0; i < state.length; i++) {
                for (int j = 0; j < state[i].length; j++) {
                    if (state[0][0].equals("Restarted")) {
                        if (once) {
                            System.out.println("Restarted");
                            once = false;
                        }
                    } else {
                        System.out.print(state[i][j] + " ");
                    }
                }
                if (once) {
                    System.out.println();
                }
            }
            once = true;
            gameStateNumber++;
        }
    }

    @Override
    public void updateGame(int x, int y, int flag){
    }

    @Override
    public void updateSounds(String soundType) {
    }
    
}    
