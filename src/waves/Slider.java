package waves;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Slider extends JSlider {
	
	private JSlider slider = new JSlider();

	public Slider(int minValue, int maxValue) {
		super();
		this.slider = new JSlider(minValue, maxValue);
		slider.setPaintLabels(true);
		slider.setPaintTicks(false);
		slider.setPaintTrack(true);
		
		slider.setMinorTickSpacing((maxValue - minValue)/100);
		slider.setMajorTickSpacing((maxValue - minValue)/10);
					
	}
}
