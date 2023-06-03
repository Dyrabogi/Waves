package waves;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SoundParameters implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JDialog dialog = new JDialog();
		dialog.setLayout(new GridLayout(10, 1));
		JLabel amplituda = new JLabel();
		JLabel przesuniecie = new JLabel();
		JLabel czestotliwosc = new JLabel();
		if (MainFrame.polski.isSelected()) {
			amplituda.setText("Amplituda");
			przesuniecie.setText("Przesunięcie fazowe");
			czestotliwosc.setText("Częstotliwość");
		} else {
			amplituda.setText("Amplitude");
			przesuniecie.setText("Phase shift");
			czestotliwosc.setText("Frequency");
		}

		Slider ampSlider = new Slider(0, 100, 10);
		ampSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				MainFrame.waves.get(MainFrame.cb.getSelectedIndex()).setAmp(ampSlider.getValue());
				MainFrame.waveLabel.get(MainFrame.cb.getSelectedIndex())
						.setText(MainFrame.waves.get(MainFrame.cb.getSelectedIndex()).toString());
				 MainFrame.graph.setWaves(MainFrame.waves);
				 MainFrame.graph.panel.repaint();
			}
		});
		Slider czeSlider = new Slider(0, 100, 10);
		czeSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				MainFrame.waves.get(MainFrame.cb.getSelectedIndex()).setFreq(czeSlider.getValue());
				MainFrame.waveLabel.get(MainFrame.cb.getSelectedIndex())
						.setText(MainFrame.waves.get(MainFrame.cb.getSelectedIndex()).toString());
				 MainFrame.graph.setWaves(MainFrame.waves);
				 MainFrame.graph.panel.repaint();
			}
		});
		Slider przesSlider = new Slider(0, 100, 10);
		przesSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				MainFrame.waves.get(MainFrame.cb.getSelectedIndex()).setPhase(przesSlider.getValue());
				MainFrame.waveLabel.get(MainFrame.cb.getSelectedIndex())
						.setText(MainFrame.waves.get(MainFrame.cb.getSelectedIndex()).toString());
				 MainFrame.graph.setWaves(MainFrame.waves);
				 MainFrame.graph.panel.repaint();
			}
		});
		JFormattedTextField ampText = new JFormattedTextField();
		ampText.getDocument().addDocumentListener(
				new WaveTextSynchronizer(MainFrame.waves, MainFrame.waveLabels, ampText, MainFrame.cb, MainFrame.graph, "amplituda"));
		JFormattedTextField czeText = new JFormattedTextField();
		czeText.getDocument().addDocumentListener(
				new WaveTextSynchronizer(MainFrame.waves, MainFrame.waveLabels, czeText, MainFrame.cb, MainFrame.graph, "czestotliwosc"));
		JFormattedTextField przeText = new JFormattedTextField();
		przeText.getDocument().addDocumentListener(
				new WaveTextSynchronizer(MainFrame.waves, MainFrame.waveLabels, przeText, MainFrame.cb, MainFrame.graph, "faza"));
		dialog.add(amplituda);
		dialog.add(ampSlider);
		dialog.add(ampText);
		dialog.add(czestotliwosc);
		dialog.add(czeSlider);
		dialog.add(czeText);
		dialog.add(przesuniecie);
		dialog.add(przesSlider);
		dialog.add(przeText);
		dialog.setSize(500, 700);
		dialog.setVisible(true);

	}
}
