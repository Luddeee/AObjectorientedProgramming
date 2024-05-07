package Project;

import java.util.ArrayList;
import java.util.List;

public class PrevGameState implements Observer {
    private MineSweeper mineSweeper;
    private List<String[][]> gameStates = new ArrayList<>();

    public PrevGameState(MineSweeper mineSweeper) {
        this.mineSweeper = mineSweeper;
        mineSweeper.registerObserver(this);
    }
    int oldrcounter;
    @Override
    public void update() {
        
        int rcounter = mineSweeper.getrestartcount();
        // Capture current state of the grid
        String[][] currentState = new String[mineSweeper.getRow()][mineSweeper.getRow()];
        for (int i = 0; i < mineSweeper.getRow(); i++) {
            for (int j = 0; j < mineSweeper.getRow(); j++) {
                if(rcounter != oldrcounter){
                    currentState[i][j] = "Restarted";
                }
                else{
                    currentState[i][j] = mineSweeper.getCellStatus(i, j);
                }
            }
        }
        gameStates.add(currentState);
        oldrcounter = rcounter;
    }

    public List<String[][]> getGameStates() {
        return gameStates;
    }
    
    public void printCapturedStates() {
        int gameStateNumber = 1;
        boolean once = true;
        for (String[][] state : gameStates) {
            System.out.println("Game State #" + gameStateNumber + ":");
            for (int i = 0; i < state.length; i++) {
                for (int j = 0; j < state[i].length; j++) {
                    if(state[0][0] == "Restarted"){
                        if(once){
                            System.out.println("Restarted");
                            once = false;
                        }
                    }
                    else{
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
    
}
