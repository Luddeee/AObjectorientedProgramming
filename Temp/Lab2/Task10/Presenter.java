// package presenter;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public abstract class Presenter {

	public abstract PictureComponent createCenterComponent();

	public abstract void northButtonPressed();

	public abstract void eastButtonPressed();

	public abstract void southButtonPressed();

	public abstract void westButtonPressed();

	JFrame frame = new JFrame();

	PictureComponent centerComponent;

	TextArea textArea;


	public Presenter() {

		// setup layout manager for frame
		JButton westButton = new JButton("Previous");
		westButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				westButtonPressed();
			}
		});

		JButton eastButton = new JButton("Next");
		eastButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				eastButtonPressed();
			}
		});

		// add northButton to a JPanel, add that panel to frame
		JPanel jPanel = new JPanel();
		jPanel.add(westButton);
		jPanel.add(eastButton);

		frame.add(jPanel, BorderLayout.PAGE_END);

		// add centerComponent to frame
		centerComponent = createCenterComponent();
		frame.add(centerComponent, BorderLayout.CENTER);

		// create other components (text component, e.g.), add them to frame
		textArea = new TextArea("No text", 4, 1);
		textArea.setEditable(false);
		frame.add(textArea, BorderLayout.PAGE_START);
		
		// show frame
		frame.setSize(1000, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // exit app on close
	}

	public void showText(String text) {
		textArea.setText(text);
	}

}
