package Exercise10;
import java.io.File;

//import javax.swing.JComponent;

public class SlideShow extends Presenter {

	private PictureComponent pictureComponent = null;

	File[] imageFiles;
	String[] texts;
	//int currIndex;
	
	public SlideShow(File[] imageFiles, String[] texts) {
		// read and remember (create instance variables)
		// images from the indicated files
		// and also remember texts in an instance field

		this.imageFiles = imageFiles;
		this.texts = texts;
		centerComponent.setPictures(imageFiles);
		showText(texts[0]);
	}
	
	@Override
	public PictureComponent createCenterComponent() {
		// create picture Component
		// you may want to create a class for that
		// (class PictureComponent extends JComponent { ... } )
		this.pictureComponent = new PictureComponent(imageFiles);
		return pictureComponent;
	}

	@Override
	public void eastButtonPressed() {
		// make pictureComponent display the next picture
		// call showText(...) to update the text associated with picture
		// one possible way of drawing images is
		// in paintComponent
		// BufferedImage image = ImageIO.read(<File object>);
		// Graphics2D g2 = (Graphics2D)g;
		// g2.drawImage(image, null, <posX>, <posY>);
		// Google will give details
		centerComponent.nextSlide();
		showText(texts[centerComponent.currIndex]);
	}
	
	@Override
	public void westButtonPressed() {
		// same as for east button, but for previous picture
		centerComponent.prevSlide();
		showText(texts[centerComponent.currIndex]);
	}

	@Override
	public void southButtonPressed() {
	}

	@Override
	public void northButtonPressed() {
	}
	
	public static void main(String[] args) {
		new SlideShow(
				//You may have to edit file paths to suite your system
				new File[] { new File("Lab2/Exercise10/pic1.png"), new File("Lab2/Exercise10/pic2.png")},
				new String[] {"Descirption 1", "Description 2"});				
	}
}
