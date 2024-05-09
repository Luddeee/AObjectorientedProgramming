package Project;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SoundNotification implements SoundObserver {
    private List<String> soundFilePaths = new ArrayList<>();
    private Random random = new Random();

    public SoundNotification(List<String> soundFilePaths) {
        this.soundFilePaths.addAll(soundFilePaths);
    }

    @Override
    public void playSoundNotification() {
        // Implement the logic to play a sound without any arguments
        if (soundFilePaths.isEmpty()) {
            System.out.println("No sound file paths provided.");
            return;
        }

        // Randomly select a sound file
        int index = random.nextInt(soundFilePaths.size());
        String selectedFilePath = soundFilePaths.get(index);
        playSound(selectedFilePath);
    }

    // Additional method to play sound with a specified type
    public void playSoundNotification(String soundType) {
        if (soundFilePaths.isEmpty()) {
            System.out.println("No sound file paths provided.");
            return;
        }

        // Determine the file path based on the sound type
        String soundFileName = "";
        switch (soundType) {
            case "boomsound":
                soundFileName = "Project/soundFiles/boomsound.wav";
                break;
            case "plingsound":
                soundFileName = "Project/soundFiles/plingsound.wav";
                break;
            // Add cases for other sound types if needed
            default:
                System.out.println("Invalid sound type.");
                return;
        }

        playSound(soundFileName);
    }

    private void playSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            // Handle sound playback exceptions gracefully
        }
    }
}



