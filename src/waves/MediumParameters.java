package waves;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MediumParameters implements ActionListener {

	static double cisnienie;
	Slider cisSlider;
	MediumParameters() {
		cisnienie=100;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JDialog dialog = new JDialog();
		dialog.setLayout(new GridLayout(4, 1));
		if (MainFrame.polski.isSelected()) {
			JLabel cisnienie = new JLabel("Ciśnienie");
			JButton lista = new JButton("Wybierz z listy");
			dialog.add(cisnienie);
			dialog.add(lista);
		} else {
			JLabel cisnienie = new JLabel("Pressure");
			JButton lista = new JButton("Choose from a list");
			dialog.add(cisnienie);
			dialog.add(lista);
		}
		cisSlider = new Slider(0, 1000, 100);
		cisSlider.addChangeListener(slider);
		JFormattedTextField cisText = new JFormattedTextField();
		dialog.add(cisSlider);
		dialog.add(cisText);
		dialog.setSize(300, 300);
		dialog.setVisible(true);
	}

	ChangeListener slider=new ChangeListener() {

		@Override
		public void stateChanged(ChangeEvent e) {
			cisnienie=cisSlider.getValue();
			
		}
		
	};
	public static double getCisnienie() {
		
		return cisnienie;
	}
}
