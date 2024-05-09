package Project;

import java.util.ArrayList;
import java.util.List;

public class PrevGameState implements Observer {
    private MineSweeper mineSweeper;
    private List<String[][]> gameStates = new ArrayList<>();
    private SoundNotification soundNotification; // Store SoundNotification instance

    public PrevGameState(MineSweeper mineSweeper) {
        this.mineSweeper = mineSweeper;
        this.mineSweeper.registerObserver(this);

        // Initialize SoundNotification with sound files
        List<String> soundFilePaths = new ArrayList<>();
        soundFilePaths.add("Project/soundFiles/plingsound.wav"); // Sound for revealing squares
        soundFilePaths.add("Project/soundFiles/boomsound.wav"); // Sound for hitting a mine
        soundFilePaths.add("Project/soundFiles/winsound.wav"); // Sound for winning
        this.soundNotification = new SoundNotification(soundFilePaths);
    }

    int oldrcounter;

    @Override
    public void update() {
        int rcounter = mineSweeper.getrestartcount();
        // Capture current state of the grid
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
        // Play sound notification based on game action
        playSoundNotification();
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

    public void playSoundNotification() {
        // Get the index of the last recorded game state
        int lastIndex = gameStates.size() - 1;
    
        // Check if there is a previous game state
        if (lastIndex > 0) {
            String[][] previousState = gameStates.get(lastIndex - 1);
            String[][] currentState = gameStates.get(lastIndex);
    
            boolean bombHit = false; // Flag to track if a bomb was hit
            boolean squareRevealed = false; // Flag to track if a square was revealed
    
            // Loop through the game states to compare changes
            for (int i = 0; i < currentState.length; i++) {
                for (int j = 0; j < currentState[i].length; j++) {
                    // Check if there's a change from the previous state
                    if (!currentState[i][j].equals(previousState[i][j])) {
                        // Check if a bomb was hit
                        if (currentState[i][j].equals("M")) {
                            bombHit = true;
                        }
                        // Check if a square was revealed
                        else if (!currentState[i][j].equals("U") 
                        && !currentState[i][j].equals("F") 
                        && !currentState[i][j].equals("Restarted")) {
                            squareRevealed = true;
                        }
                    }
                }
            }
    
            // Play the corresponding sound based on the detected changes
            if (bombHit) {
                soundNotification.playSoundNotification("boomsound"); // Play the boom sound
            } else if (squareRevealed) {
                soundNotification.playSoundNotification("plingsound"); // Play the pling sound
            }
        }
    }
    
}    
