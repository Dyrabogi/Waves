package waves;

import java.awt.Label;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class WaveTextSynchronizer implements DocumentListener {
	ArrayList<Wave> waves;
	JPanel labels;
	JTextField field;
	JComboBox<String> comboBox;
	WaveGraph graph;

	public WaveTextSynchronizer(ArrayList<Wave> waves, JPanel labels, JTextField field, JComboBox<String> comboBox,
			WaveGraph graph) {
		super();
		this.waves = waves;
		this.labels = labels;
		this.field = field;
		this.comboBox = comboBox; 
		this.graph = graph;
	}

	private void update() {
		try {
			double input = (Double.parseDouble(field.getText()));
			waves.get(comboBox.getSelectedIndex()).setAmp(input);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			waves.get(comboBox.getSelectedIndex()).setAmp(0);
		}
		((Label) labels.getComponent(comboBox.getSelectedIndex())).setText(waves.get(comboBox.getSelectedIndex()).toString());
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		update();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		update();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		update();

	}
}
