package waves;

public class Wave {
	private double amp = 1;
	private double phase = 0;
	private double freq = 1;



	public Wave(double amp, double phase, double freq) {
		this.amp = amp;
		this.phase = phase;
		this.freq = freq;

	}

	public void setAmp(double amp) {
		this.amp = amp;
	}

	public void setPhase(double phase) {
		this.phase = phase;
	}

	public void setFreq(double freq) {
		this.freq = freq;
	}

	public double getFreq() {
		return freq;
	}

	public double getAmp() {
		return amp;
	}

	public double getPhase() {
		return phase;
	}
	
	@Override
	public String toString() {
		return "Fala [amp=" + amp + ", phase=" + phase + ", freq=" + freq + "]";
	}

}
