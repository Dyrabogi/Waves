package waves;

import java.awt.Label;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderTextSynchronizer implements ChangeListener {
	
	Slider slider;
	JPanel sliderLabels;
	WaveGraph graph;
	JPanel waveLabels;
	ArrayList<Wave> waves;

	JComboBox comboBox;
	public SliderTextSynchronizer(Slider slider, JPanel sliderLabels, WaveGraph graph, JPanel waveLabels, ArrayList<Wave> waves, JComboBox comboBox) {
		super();
		this.slider = slider;
		this.sliderLabels = sliderLabels;
		this.graph = graph;
		this.waveLabels = waveLabels;
		this.waves = waves;
		this.comboBox=comboBox;
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		waves.get(comboBox.getSelectedIndex()).setAmp(slider.getValue());

		((Label) Main.sliderLabels.getComponent(0)).setText(String.valueOf((double) slider.getValue()/10));
		Main.waveLabel.get(comboBox.getSelectedIndex()).setText(waves.get(comboBox.getSelectedIndex()).toString());
	}
		
}
