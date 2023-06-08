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
	static JButton lista;
	static SqlConnector mediumDatabase;
	MediumParameters(SqlConnector mediums) {
		cisnienie=100;
		lista = new JButton("Wybierz z listy");
		this.mediumDatabase = mediums;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JDialog dialog = new JDialog();
		dialog.setLayout(new GridLayout(4, 1));
		if (MainFrame.polski.isSelected()) {
			JLabel cisnienie = new JLabel("Ciśnienie");
			dialog.add(cisnienie);
			dialog.add(lista);
			System.out.println(mediumDatabase.getMediumNames().size());
		} else {
			JLabel cisnienie = new JLabel("Pressure");
			JButton lista = new JButton("Choose from a list");
			dialog.add(cisnienie);
			dialog.add(lista);
		}
		lista.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new JDialog();
				dialog.setSize(500, 800);
				dialog.setLayout(new GridLayout(mediumDatabase.getMediumNames().size(), 3));
				for(int j = 0; j < mediumDatabase.getMediumNames().size();j++) {
					JLabel mediumName = new JLabel(mediumDatabase.getMediumNames().get(j));
					JLabel mediumParameters = new JLabel(mediumDatabase.printMediumParameters(j));
					JButton okButton = new JButton("Dodaj");
					okButton.setName(mediumDatabase.getMediumNames().get(j));
					
					okButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							int jj = mediumDatabase.getMediumindex(okButton.getName());
							cisnienie = mediumDatabase.getMediumPressures().get(jj);
							MainFrame.graph.repaint();
						}
					
					});
					dialog.add(mediumName);
					dialog.add(mediumParameters);
					dialog.add(okButton);
				}
				
				dialog.setVisible(true);
				
			}
		});
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
			 MainFrame.graph.setWaves(MainFrame.waves);
			 MainFrame.graph.panel.repaint();
			
		}
		
	};

	public static double getCisnienie() {
		
		return cisnienie;
	}
}
