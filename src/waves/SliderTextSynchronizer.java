package waves;

import java.awt.Label;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderTextSynchronizer implements ChangeListener {
	
	Slider slider;
	JPanel sliderLabels;
	WaveGraph graph;
	JPanel waveLabels;
	ArrayList<Wave> waves;

	public SliderTextSynchronizer(Slider slider, JPanel sliderLabels, WaveGraph graph, JPanel waveLabels,ArrayList<Wave> waves) {
		super();
		this.slider = slider;
		this.sliderLabels = sliderLabels;
		this.graph = graph;
		this.waveLabels = waveLabels;
		this.waves = waves;
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		((Label) sliderLabels.getComponent(0)).setText(String.valueOf((double) slider.getValue()/10));
		waves.get(0).setAmp(slider.getValue());
		((Label) waveLabels.getComponent(0)).setText(waves.get(0).toString());
		WaveGraph.frame1.revalidate();
		WaveGraph.frame1.repaint();
	}
		
}
