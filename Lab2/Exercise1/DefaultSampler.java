package Exercise1;

public class DefaultSampler implements Sampler {

	@Override
	public double read() {
		return Math.random() * 10;
	}

}
