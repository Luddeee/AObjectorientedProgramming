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

    // The constructor `public SoundNotification(List<String> soundFilePaths)` in the
    // `SoundNotification` class is initializing the `SoundNotification` object with a list of sound
    // file paths.
    public SoundNotification(List<String> soundFilePaths) {
        this.soundFilePaths.addAll(soundFilePaths);
    }

    /**
     * The `playSoundNotification` method randomly selects a sound file path from a list and plays the
     * corresponding sound.
     */
    @Override
    public void playSoundNotification() {
        if (soundFilePaths.isEmpty()) {
            System.out.println("No sound file paths provided.");
            return;
        }
        int index = random.nextInt(soundFilePaths.size());
        String selectedFilePath = soundFilePaths.get(index);
        playSound(selectedFilePath);
    }

    /**
     * The function `playSoundNotification` plays a sound notification based on the provided sound
     * type.
     * 
     * @param soundType The `soundType` parameter is a string that specifies the type of sound
     * notification to be played. It can have values "boomsound" or "plingsound" based on the switch
     * cases in the `playSoundNotification` method.
     */
    public void playSoundNotification(String soundType) {
        if (soundFilePaths.isEmpty()) {
            System.out.println("No sound file paths provided.");
            return;
        }
        String soundFileName = "";
        switch (soundType) {
            case "boomsound":
                soundFileName = "Project/soundFiles/boomsound.wav";
                break;
            case "plingsound":
                soundFileName = "Project/soundFiles/plingsound.wav";
                break;
            default:
                System.out.println("Invalid sound type.");
                return;
        }

        playSound(soundFileName);
    }

    /**
     * The `playSound` function plays a sound from a specified file path in a Java application,
     * handling potential exceptions related to audio playback.
     * 
     * @param filePath The `filePath` parameter is a `String` that represents the file path to the
     * sound file that you want to play. This method attempts to load the sound file from the specified
     * path and play it using Java's `Clip` class.
     */
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



