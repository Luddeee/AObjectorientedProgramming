package Project;

import java.io.*;
import java.util.*;

public class HighScoresManager implements Serializable {
    private List<String> highScores;
    // The `public HighScoresManager()` constructor in the `HighScoresManager` class is initializing
    // the `highScores` instance variable. It creates a new `ArrayList` object and assigns it to the
    // `highScores` variable. This ensures that when an instance of `HighScoresManager` is created, it
    // starts with an empty list of high scores.
    public HighScoresManager() {
        highScores = new ArrayList<>();
    }


    /**
     * The `addHighScore` function in Java adds a new high score entry for a player, removing any
     * existing entry for the same player and then sorting the high scores.
     * 
     * @param name Name of the player whose high score is being added.
     * @param score The `score` parameter in the `addHighScore` method represents the numerical score
     * achieved by a player in a game. It is a `long` data type, which is a 64-bit signed integer in
     * Java. This score is added to the list of high scores for the game along with
     */
    public void addHighScore(String name, long score) {
        // Remove any existing entry for the same player
        removePlayer(name);

        // Add the new high score
        highScores.add(name + " - " + score);

        // Sort the high scores
        sortHighScores();
    }

    /**
     * This Java function returns a list of high scores.
     * 
     * @return A List of Strings containing high scores is being returned.
     */
    public List<String> getHighScores() {
        return highScores;
    }

    /**
     * The function `saveHighScores` writes the updated high scores to a file specified by the given
     * path in Java.
     * 
     * @param path The `path` parameter in the `saveHighScores` method is a `String` that represents
     * the file path where the high scores will be saved. This parameter specifies the location and
     * name of the file where the high scores data will be written to.
     */
    /**
     * The `saveHighScores` function writes the updated high scores to a file specified by the given
     * path in Java.
     * 
     * @param path The `path` parameter in the `saveHighScores` method is the file path where the high
     * scores will be saved. It specifies the location and name of the file where the high scores will
     * be written to.
     */
    public void saveHighScores(String path) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, false))) {
            // Write the updated high scores to the file
            for (String highScore : highScores) {
                writer.println(highScore);
            }
        } catch (IOException e) {
            System.out.println("Error saving high scores: " + e.getMessage());
        }
    }

    /**
     * The `loadHighScores` function clears existing high scores, reads high scores from a file
     * specified by the path, adds them to a list, sorts the high scores, and handles any exceptions
     * that occur during the process.
     * 
     * @param path The `path` parameter in the `loadHighScores` method is a string that represents the
     * file path from which the high scores will be loaded. This path should point to the location of
     * the file containing the high scores data that needs to be loaded into the program.
     */
    public void loadHighScores(String path) {
        highScores.clear(); // Clear existing high scores
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String item = scanner.nextLine();
                highScores.add(item);
                System.out.println(item);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading or loading file");
            System.out.println("Current working directory: " + new File(".").getAbsolutePath());
        }
        sortHighScores(); // Sort the high scores after loading
    }

    /**
     * The function `updateHighScores` updates the high scores by loading existing scores, adding a new
     * score, and saving the updated scores back to a file.
     * 
     * @param path The `path` parameter in the `updateHighScores` method represents the file path where
     * the high scores data is stored or where you want to save the updated high scores. It is a string
     * that specifies the location of the file in the file system.
     * @param name The `name` parameter in the `updateHighScores` method represents the name of the
     * player who achieved the high score.
     * @param score The `score` parameter in the `updateHighScores` method represents the score that a
     * player achieved in a game. This score will be added to the list of high scores and saved in a
     * file for future reference.
     */
    public void updateHighScores(String path, String name, long score) {
        // Load existing high scores
        loadHighScores(path);

        // Add the new high score
        addHighScore(name, score);

        // Save the updated high scores back to the file
        saveHighScores(path);
    }

    /**
     * The `removePlayer` function removes all entries in the `highScores` list that start with the
     * specified `name`.
     * 
     * @param name The `name` parameter in the `removePlayer` method is a `String` representing the
     * name of the player that you want to remove from the `highScores` list. The method iterates
     * through the list of high scores and removes any entry that starts with the specified player
     * name.
     */
    private void removePlayer(String name) {
        Iterator<String> iterator = highScores.iterator();
        while (iterator.hasNext()) {
            String entry = iterator.next();
            if (entry.startsWith(name)) {
                iterator.remove();
            }
        }
    }

    /**
     * The `sortHighScores` method sorts a list of high scores based on score value in descending order
     * and assigns rankings to handle ties.
     */
    private void sortHighScores() {
        // Sort the high scores based on score value
        Collections.sort(highScores, (s1, s2) -> {
            long score1 = Long.parseLong(s1.substring(s1.lastIndexOf("-") + 1).trim());
            long score2 = Long.parseLong(s2.substring(s2.lastIndexOf("-") + 1).trim());
            return Long.compare(score1, score2); // Sort in descending order
        });

        // Assign rankings to the sorted high scores
        List<String> rankedHighScores = new ArrayList<>();
        long prevScore = Long.MAX_VALUE; // Store the previous score to handle ties
        int rank = 0;
        for (String entry : highScores) {
            long score = Long.parseLong(entry.substring(entry.lastIndexOf("-") + 1).trim());
            String rankString = "[" + rank + "]";
            if (entry.contains("[")) {
                // If the entry already contains a rank, remove it
                entry = entry.substring(entry.indexOf("]") + 1).trim();
            }
            rankedHighScores.add(rankString + " " + entry);
            if (score != prevScore) {
                // If the score is different from the previous one, update the rank
                rank++;
            }
            prevScore = score; // Update the previous score
        }

        // Update the high scores list with the ranked high scores
        highScores.clear();
        highScores.addAll(rankedHighScores);
    }
}


