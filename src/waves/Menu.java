package waves;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static waves.WaveGraph.*;

public class Menu extends JMenuBar {

	static JMenuItem losowo, zListy, wlasne, nagraj, odtworz, pokaz, zapisz;
	static JMenu dzwiek, detektor;
	Random rand;

	Menu() {
		super();
		rand = new Random();
		dzwiek = new JMenu("Dodaj źródło dźwięku");
		this.add(dzwiek);
		losowo = new JMenuItem("Losowo");
		losowo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Wave waveTemp = new Wave(rand.nextInt(100), rand.nextInt(100), rand.nextInt(100));
				MainFrame.waves.add(waveTemp);
				MainFrame.waveLabel.add(new Label(waveTemp.toString()));
				MainFrame.waveLabels.setLayout(new GridLayout(MainFrame.waveIdx + 1, 1));
				MainFrame.waveLabels.add(MainFrame.waveLabel.get(MainFrame.waveIdx));
				MainFrame.cb.addItem(String.valueOf(MainFrame.waveIdx + 1));
				revalidate();
				MainFrame.waveIdx++;
			}
		});

		zListy = new JMenuItem("Z listy");
		wlasne = new JMenuItem("Własne parametry");
		wlasne.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Wave waveTemp = new Wave(0, 0, 0);
				MainFrame.waves.add(waveTemp);
				MainFrame.waveLabel.add(new Label(waveTemp.toString()));
				MainFrame.waveLabels.setLayout(new GridLayout(MainFrame.waveIdx + 1, 1));
				MainFrame.waveLabels.add(MainFrame.waveLabel.get(MainFrame.waveIdx));
				MainFrame.cb.addItem(String.valueOf(MainFrame.waveIdx + 1));
				revalidate();
				MainFrame.waveIdx++;
			}
		});
		dzwiek.add(losowo);
		dzwiek.add(zListy);
		dzwiek.add(wlasne);

		detektor = new JMenu("Detektor");
		nagraj = new JMenuItem("Nagraj dźwięk");
		detektor.add(nagraj);
		odtworz = new JMenuItem("Odtwórz dźwięk");
		detektor.add(odtworz);
		pokaz = new JMenuItem("Pokaż wykres fali");
		pokaz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WaveGraph graph = new WaveGraph(MainFrame.waves);
				graph.repaint();
				graph.revalidate();
				graph.frame1.setVisible(true);
			}
		});

		detektor.add(pokaz);
		zapisz = new JMenuItem("Zapisz wykres fali");
		detektor.add(zapisz);
		this.add(detektor);
	}
}
