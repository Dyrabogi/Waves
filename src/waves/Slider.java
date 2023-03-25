package waves;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Slider extends JSlider {
	
	private JSlider slider = new JSlider();

	public Slider(int minValue, int maxValue, int majorTick) {
		super(JSlider.HORIZONTAL, minValue, maxValue, 0);
		setMinorTickSpacing(majorTick/5);
		setMajorTickSpacing(majorTick);
		setPaintLabels(true);
		setPaintTicks(true);
		setPaintTrack(true);
	}
}
