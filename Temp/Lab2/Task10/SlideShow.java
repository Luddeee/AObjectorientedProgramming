// package presenter;

import java.io.File;

public class SlideShow extends Presenter {

	private PictureComponent pictureComponent = null;

	File[] imageFiles;
	String[] texts;
	int current_pic_index = 0;

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

	// prev button
	@Override
	public void westButtonPressed() {
		// same as for east button, but for previous picture
		centerComponent.prevSlide();
		showText(texts[centerComponent.slide_index]);
	}

	// next button
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
		showText(texts[centerComponent.slide_index]);
	}

	@Override
	public void southButtonPressed() {
		// do nothing
	}

	@Override
	public void northButtonPressed() {
		// do nothing
	}

	public static void main(String[] args) {
		SlideShow app = new SlideShow(
				new File[] { new File("./pic1.png"), new File("./pic2.jpg") },
				new String[] { "Descirption 1", "Description 2" });
	}

}
