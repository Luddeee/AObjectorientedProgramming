package Project;

import java.awt.*;
import java.io.File;
import javax.swing.*;

public class HowTo {
    JPanel entryPanel = new JPanel();
    /**
     * The `loadHowTo1` function loads images and corresponding text descriptions, resizes the images
     * while preserving aspect ratio, and adds them to a panel for display.
     * 
     * @param framehight1 The `framehight1` parameter in the `loadHowTo1` method represents the height
     * of the frame where the images and descriptions will be displayed. This height is used to
     * calculate the maximum size for each entry panel within the frame.
     */
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
    
            for (int i = 0; i < imageFiles.length; i++) {
                ImageIcon originalIcon = new ImageIcon(imageFiles[i].getPath());
                Image originalImage = originalIcon.getImage();
        
                int maxWidth = 80;
                int maxHeight = 60;

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
        
                Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
                JLabel imageLabel = new JLabel(scaledIcon);
                JLabel descriptionLabel = new JLabel(texts[i]);
    
                entryPanel.setLayout(new BoxLayout(entryPanel, BoxLayout.Y_AXIS));
        
                entryPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int)framehight1 / 2));
        
                imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
                entryPanel.add(imageLabel);
                entryPanel.add(descriptionLabel);
    
                //westTextPanel.add(entryPanel);
            
        }
    }

    /**
     * The function `getHowToPanel` returns a JPanel after loading content based on the provided frame
     * height.
     * 
     * @param framehight It looks like the method `getHowToPanel` is designed to return a `JPanel`
     * based on the provided `framehight` parameter. The method likely loads some content into the
     * panel before returning it.
     * @return The method `getHowToPanel` is returning a `JPanel` named `entryPanel`.
     */
    JPanel getHowToPanel(double framehight){
        loadHowTo1(framehight);
        return entryPanel;
    } 
}
