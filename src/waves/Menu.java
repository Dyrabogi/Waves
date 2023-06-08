package waves;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static waves.WaveGraph.*;

public class Menu extends JMenuBar {

	static JMenuItem losowo, zListy, wlasne, nagraj, odtworz, pokaz, zapisz, json, zatrzymaj;
	static JMenu dzwiek, detektor;
	Random rand;
	JFileChooser fc = new JFileChooser();
	static SqlConnector soundsDatabase;
	static ExecutorService exec;
	static SoundGenerator sound;

	Menu(SqlConnector sounds) {
		super();
		
		rand = new Random();
		dzwiek = new JMenu("Dodaj źródło dźwięku");
		this.add(dzwiek);
		this.soundsDatabase = sounds;
		losowo = new JMenuItem("Losowo");
		losowo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Wave waveTemp = new Wave(rand.nextInt(100), rand.nextInt(100), rand.nextInt(100));
				MainFrame.waves.add(waveTemp);
				MainFrame.waveLabel.add(new Label((MainFrame.waveIdx +1)+". " + waveTemp.toString()));
				MainFrame.waveLabels.setLayout(new GridLayout(MainFrame.waveIdx + 1, 1));
				MainFrame.waveLabels.add(MainFrame.waveLabel.get(MainFrame.waveIdx));
				MainFrame.cb.addItem(String.valueOf(MainFrame.waveIdx + 1));
				MainFrame.center.addSpeaker();
				revalidate();
				MainFrame.waveIdx++;
				MainFrame.graph.setWaves(MainFrame.waves);
				MainFrame.graph.repaint();
			}
		});

		zListy = new JMenuItem("Z listy");
		exec=Executors.newFixedThreadPool(2);
		exec.execute(soundsDatabase);

		zListy.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new JDialog();
				dialog.setSize(500, 800);
				dialog.setLayout(new GridLayout(soundsDatabase.getImportedWaves().size(), 3));
				for(int j = 0; j < soundsDatabase.getImportedWaves().size();j++) {
					JLabel soundName = new JLabel(soundsDatabase.getSoundNames().get(j));
					JLabel soundParameters = new JLabel(soundsDatabase.printParameters(j));
					JButton okButton = new JButton("Dodaj");
					okButton.setName(soundsDatabase.getSoundNames().get(j));
					
					okButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							int jj = soundsDatabase.getWaveindex(okButton.getName());
							Wave waveTemp = new Wave(soundsDatabase.getAmp(jj),
									soundsDatabase.getPhase(jj),soundsDatabase.getFreq(jj));
							MainFrame.waves.add(waveTemp);
							
							MainFrame.waveLabel.add(new Label((MainFrame.waveIdx +1)+". " + waveTemp.toString()));
							MainFrame.waveLabels.setLayout(new GridLayout(MainFrame.waveIdx + 1, 1));
							MainFrame.waveLabels.add(MainFrame.waveLabel.get(MainFrame.waveIdx));
							MainFrame.cb.addItem(String.valueOf(MainFrame.waveIdx + 1));
							MainFrame.center.addSpeaker();
							revalidate();
							MainFrame.waveIdx++;
							MainFrame.graph.setWaves(MainFrame.waves);
							MainFrame.graph.repaint();
						}
					
					});
					dialog.add(soundName);
					dialog.add(soundParameters);
					dialog.add(okButton);
				}
				
				dialog.setVisible(true);
				
				
			}
		});
		
		wlasne = new JMenuItem("Własne parametry");
		wlasne.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Wave waveTemp = new Wave(0, 0, 1);
				MainFrame.waves.add(waveTemp);
				MainFrame.waveLabel.add(new Label((MainFrame.waveIdx +1) +". " + waveTemp.toString()));
				MainFrame.waveLabels.setLayout(new GridLayout(MainFrame.waveIdx + 1, 1));
				MainFrame.waveLabels.add(MainFrame.waveLabel.get(MainFrame.waveIdx));
				MainFrame.cb.addItem(String.valueOf(MainFrame.waveIdx + 1));
				MainFrame.center.addSpeaker();
				revalidate();
				MainFrame.waveIdx++;
				MainFrame.graph.setWaves(MainFrame.waves);
				MainFrame.graph.repaint();
			}
		});
		
		json=new JMenuItem("Wczytaj z pliku json");
		json.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList <Wave> json=new ArrayList<>();
				json=ConfigLoader.readConfig();
				for(Wave wa:json)
					MainFrame.waves.add(wa) ;
	
				for(Wave w: json) {
				MainFrame.waveLabel.add(new Label((MainFrame.waveIdx +1) +". " + w.toString()));
				MainFrame.waveLabels.setLayout(new GridLayout(MainFrame.waveIdx + 1, 1));
				MainFrame.waveLabels.add(MainFrame.waveLabel.get(MainFrame.waveIdx));
				MainFrame.cb.addItem(String.valueOf(MainFrame.waveIdx + 1));
				MainFrame.center.addSpeaker();
				MainFrame.waveIdx++;
				}
				MainFrame.graph.setWaves(MainFrame.waves);
				MainFrame.graph.repaint();
				revalidate();
			}});
		
		dzwiek.add(losowo);
		dzwiek.add(zListy);
		dzwiek.add(wlasne);
		dzwiek.add(json);

		detektor = new JMenu("Detektor");
		nagraj = new JMenuItem("Zapisz dźwięk");
		nagraj.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
//				 JFileChooser chooser = new JFileChooser();
//		         chooser.setDialogTitle("Wybierz plik");
//		         int result = chooser.showDialog(null, "Wybierz");
//		         for (Wave wave : MainFrame.waves) {
//						try {
//							sound.generate(wave, "wave-freq-" + wave.getFreq() + "-sound",true);
//						} catch (IOException e1) {
//							e1.printStackTrace();
//						}
//		         }
//		         try {
//		        	 
//					Path temp = Files.move
//						        (Paths.get(sound.doZapisu.toURI()),
//						        Paths.get(chooser.getSelectedFile().toURI()));
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//				for (Wave wave : MainFrame.waves) {
//					try {
//						sound.generate(wave, "wave-freq-" + wave.getFreq() + "-sound",true);
////						exec.execute(sound);
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}
					try {
						sound.saveSounds(MainFrame.waves);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//				}
		
			
				
				
			}
		});
		detektor.add(nagraj);
		odtworz = new JMenuItem("Odtwórz dźwięk");
		detektor.add(odtworz);
		zatrzymaj = new JMenuItem("Zatrzymaj dźwięk");
		detektor.add(zatrzymaj);
		zatrzymaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.czynny=false;
			}
		});
		sound=new SoundGenerator();
		odtworz.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sound.czynny=true;
				try {
					sound.playSounds(MainFrame.waves);
					exec.execute(sound);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		});
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
		
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
	
		zapisz = new JMenuItem("Zapisz wykres fali");
		zapisz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int returnVal = fc.showSaveDialog(null);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	            	File outputFile = fc.getSelectedFile();
	            	if (!outputFile.getAbsolutePath().endsWith(".png")) {
	            	    outputFile = new File(outputFile.toString() + ".png");
	            	}    
	            	
				try {
					ImageIO.write(graph.getImage(), "png", outputFile);
				}
				catch (IOException e2) {
					System.out.println(e2.getMessage());
				}
	            }

				
			}});
		
		detektor.add(zapisz);

		this.add(detektor);
	}
}
	
	

