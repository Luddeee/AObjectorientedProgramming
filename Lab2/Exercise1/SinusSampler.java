package Exercise1;

public class SinusSampler implements Sampler {
	private double t, dt;
	
	public SinusSampler(double start, double delta) {
		t = start;
		dt = delta;
	}
	
	@Override
	public double read() {
		double x = t;
		t += dt;
		return (1+Math.sin(x))*30;
	}

}
