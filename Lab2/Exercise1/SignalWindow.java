package Exercise1;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class SignalWindow extends JFrame implements SignalObserver {

	private JTextArea jta;
	public SignalWindow() {
		jta = new JTextArea(100, 20);
		jta.append("The signal amplitude.\n");
		add(jta);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public JTextArea getTextArea() {
		return jta;
	}

	@Override
	public void updateSignal(double value) {
		jta.append(""+String.format("%.6f", value)+"\n");
	}

}