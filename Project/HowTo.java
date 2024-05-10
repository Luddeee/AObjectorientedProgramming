package Project;

import java.awt.*;
import java.io.File;
import javax.swing.*;

public class HowTo {
    JPanel entryPanel = new JPanel();
    private void loadHowTo1(double framehight1) {
        File[] imageFiles;
        String[] texts;
        imageFiles = new File[] { new File("Project/interfaceIcons/left-click.png"),
            new File("Project/interfaceIcons/right-click.png"),
                new File("Project/interfaceIcons/letter-x.png"),
                new File("Project/interfaceIcons/letter-z.png"),
                new File("Project/interfaceIcons/buttons.png"),};
            texts = new String[] {"Left click = Open block", "Right click = Flag block", "X=Open block",
                 "Z=Flag block", "arrows=movement"};
    
            // Adding images and text
            for (int i = 0; i < imageFiles.length; i++) {
                ImageIcon originalIcon = new ImageIcon(imageFiles[i].getPath());
                Image originalImage = originalIcon.getImage();
        
                // Define the maximum width and height for the resized image
                int maxWidth = 80; // Adjust this value to your preference
                int maxHeight = 60; // Adjust this value to your preference
        
                // Calculate the scaled width and height while preserving aspect ratio
                int scaledWidth = originalImage.getWidth(null);
                int scaledHeight = originalImage.getHeight(null);
                double aspectRatio = (double) scaledWidth / scaledHeight;
        
                if (scaledWidth > maxWidth) {
                    scaledWidth = maxWidth;
                    scaledHeight = (int) (scaledWidth / aspectRatio);
                }
                if (scaledHeight > maxHeight) {
                    scaledHeight = maxHeight;
                    scaledWidth = (int) (scaledHeight * aspectRatio);
                }
        
                // Resize the image
                Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        
                // Create the ImageIcon with the resized image
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
                JLabel imageLabel = new JLabel(scaledIcon);
                JLabel descriptionLabel = new JLabel(texts[i]);
    
                entryPanel.setLayout(new BoxLayout(entryPanel, BoxLayout.Y_AXIS));
        
                // Set maximum size for each entry panel
                entryPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int)framehight1 / 2));
        
                imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
                entryPanel.add(imageLabel);
                entryPanel.add(descriptionLabel);
    
                //westTextPanel.add(entryPanel);
            
        }
    }

    JPanel getHowToPanel(double framehight){
        loadHowTo1(framehight);
        return entryPanel;
    } 
}
