package Exercise10;
import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
//import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public abstract class Presenter {

	public abstract PictureComponent createCenterComponent();
	
	public abstract void northButtonPressed();
	public abstract void eastButtonPressed();
	public abstract void southButtonPressed();
	public abstract void westButtonPressed();

	PictureComponent centerComponent;
	TextArea textArea;
	
	JFrame frame = new JFrame();
	public Presenter() {
		// setup layout manager for frame
		JButton westButton = new JButton("Pre");
		westButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				northButtonPressed();				
			}
		});

		JButton eastButton = new JButton("Next");
		eastButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				northButtonPressed();				
			}
		});
		// add northButton to a JPanel, add that panel to frame
		JPanel panel = new JPanel();
		panel.add(westButton);
		panel.add(eastButton);

		frame.add(panel, BorderLayout.PAGE_END);

		//JComponent centerComponent = createCenterComponent();

		// add centerComponent to frame
		centerComponent = createCenterComponent();
		frame.add(centerComponent, BorderLayout.CENTER);

		// create other components (text component, e.g.), add them to frame
		textArea = new TextArea("Nah", 4, 1);
		textArea.setEditable(false);
		frame.add(textArea, BorderLayout.PAGE_START);

		// show frame
		frame.setSize(1000, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);		
	}
	
	public void showText(String text) {
		// update the text component
		textArea.setText(text);
	}
	
}
