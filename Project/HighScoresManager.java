package Project;

import java.io.*;
import java.util.*;

public class HighScoresManager implements Serializable {
    private List<String> highScores; // Store high scores in the format "rank name - score"

    public HighScoresManager() {
        highScores = new ArrayList<>();
    }


    public void addHighScore(String name, long score) {
        // Remove any existing entry for the same player
        removePlayer(name);

        // Add the new high score
        highScores.add(name + " - " + score);

        // Sort the high scores
        sortHighScores();
    }

    public List<String> getHighScores() {
        return highScores;
    }

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

    public void updateHighScores(String path, String name, long score) {
        // Load existing high scores
        loadHighScores(path);

        // Add the new high score
        addHighScore(name, score);

        // Save the updated high scores back to the file
        saveHighScores(path);
    }

    private void removePlayer(String name) {
        Iterator<String> iterator = highScores.iterator();
        while (iterator.hasNext()) {
            String entry = iterator.next();
            if (entry.startsWith(name)) {
                iterator.remove();
            }
        }
    }

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


