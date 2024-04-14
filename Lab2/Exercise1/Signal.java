package Exercise1;

import javax.swing.*;
import javax.swing.Timer;

import java.awt.event.*;
import java.util.*;

public class Signal {
	private final int SAMPLING = 1000;
	private List<SignalObserver> observers = new LinkedList<SignalObserver>();
	
	//This is from stratergy
	Sampler sampler = new DefaultSampler();
	public void setSampler(Sampler s) {
		sampler = s;
	}

	public void addSignalObserver(SignalObserver o) {
		observers.add(o);
	}
	
	public Signal(final JTextArea jta) {
		Timer t = new Timer(SAMPLING, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//double amplitude2 = Math.random() * 10;
				double amplitude = sampler.read();
				for(SignalObserver o : observers)
						o.updateSignal(amplitude);
				// jta.append(""+String.format("%.6f", amplitude)+"\n");
				// inform another view 
				//for(int i=0;i<(int)amplitude2; i++)
				//	System.out.print('*');
				//System.out.println();
				//test
			}
		});
		t.start();
	}
	
}