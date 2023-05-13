package waves;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Random;

import static waves.WaveGraph.*;

public class Menu extends JMenuBar {

	static JMenuItem losowo, zListy, wlasne, nagraj, odtworz, pokaz, zapisz;
	static JMenu dzwiek, detektor;
	Random rand;
	JFileChooser fc = new JFileChooser();
	

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
		WaveGraph graph = new WaveGraph(MainFrame.waves);
		pokaz = new JMenuItem("Pokaż wykres fali");
		pokaz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				graph.setWaves(MainFrame.waves);
				graph.repaint();
				graph.revalidate();
				graph.frame1.setVisible(true);
			}
		});

		detektor.add(pokaz);
		zapisz = new JMenuItem("Zapisz wykres fali");
//		zapisz.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//				int returnVal = fc.showSaveDialog(null);
//	            if (returnVal == JFileChooser.APPROVE_OPTION) {
//	            	File outputFile = fc.getSelectedFile();
//	            
//				
//				try {
//					ImageIO.write(graph.getImage(), "jpg", outputFile);
//				}
//				catch (IOException e2) {
//					System.out.println(e2.getMessage());
//				}
//	            }
//				
//			}});
//		
		detektor.add(zapisz);
		this.add(detektor);
	}}
	

