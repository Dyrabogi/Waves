package waves;

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
	String whatToChange;

	public WaveTextSynchronizer(ArrayList<Wave> waves, JPanel labels, JTextField field, JComboBox<String> comboBox,
			WaveGraph graph, String whatToChange) {
		super();
		this.waves = waves;
		this.labels = labels;
		this.field = field;
		this.comboBox = comboBox;
		this.graph = graph;
		this.whatToChange = whatToChange;
	}

	private void update() {
		try {
			double input = (Double.parseDouble(field.getText()));
			if (whatToChange == "amplituda") {
				waves.get(comboBox.getSelectedIndex()).setAmp(input);
			} else if (whatToChange == "czestotliwosc") {
				waves.get(comboBox.getSelectedIndex()).setFreq(input);
			} else if (whatToChange == "faza") {
				waves.get(comboBox.getSelectedIndex()).setPhase(input);
			}

		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block

			if (whatToChange == "amplituda") {
				waves.get(comboBox.getSelectedIndex()).setAmp(0);
			} else if (whatToChange == "czestotliwosc") {
				waves.get(comboBox.getSelectedIndex()).setFreq(0);
			} else if (whatToChange == "faza") {
				waves.get(comboBox.getSelectedIndex()).setPhase(0);
			}
		}
		MainFrame.waveLabel.get(comboBox.getSelectedIndex()).setText(waves.get(comboBox.getSelectedIndex()).toString());
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
