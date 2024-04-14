package Exercise1;

public class TestSignal {

	public static void main(String[] args) {
		SignalWindow window = new SignalWindow();
		Signal s = new Signal(window.getTextArea());
		s.addSignalObserver(new PrintStarObserver());
		s.addSignalObserver(window);
		s.setSampler(new SinusSampler(0.0, 0.1));
		//test
	}

}
