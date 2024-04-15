package Exercise10;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class PictureComponent extends JComponent {

    public BufferedImage image;
    File[] files;
    int currIndex = 0;

    public PictureComponent(File[] files) {
        this.files = files;
    }

    @Override
    protected void paintComponent(Graphics g) {

        if (files == null) {
            return;
        }

        try {
            image = ImageIO.read(files[currIndex]);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, 400, 400, Color.WHITE, null);
        } else {
            System.out.println("image is null!");
        }

        System.out.println("Went to slide: " + currIndex);

    }

    public void setPictures (File[] files) {
        this.files = files;
    }

    public void prevSlide() {

        if (this.currIndex == 0) {
            System.out.println("No slide before this!");
            return;
        }

        this.currIndex--;
        super.repaint();
    }

    public void nextSlide() {

        if (currIndex == files.length - 1) {
            System.out.println("End of slides!");
            return;
        }

        this.currIndex++;
        super.repaint();
    }
}