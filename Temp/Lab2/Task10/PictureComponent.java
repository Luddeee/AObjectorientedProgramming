import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class PictureComponent extends JComponent {

    public BufferedImage image;
    File[] images;
    int slide_index = 0;

    public PictureComponent(File[] files) {
        this.images = files;
    }

    @Override
    protected void paintComponent(Graphics g) {

        if (images == null) {
            return;
        }

        try {
            image = ImageIO.read(images[slide_index]);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, 400, 400, Color.WHITE, null);
        } else {
            System.out.println("image is null!");
        }

        System.out.println("Went to slide: " + slide_index);

    }

    public void setPictures (File[] files) {
        this.images = files;
    }

    public void prevSlide() {

        if (this.slide_index == 0) {
            System.out.println("No slide before this!");
            return;
        }

        this.slide_index--;
        super.repaint();
    }

    public void nextSlide() {

        if (slide_index == images.length - 1) {
            System.out.println("End of slides!");
            return;
        }

        this.slide_index++;
        super.repaint();
    }
}
