package waves;

import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;

public class Main extends JFrame{
	static JButton parametryDziweku, parametryOsrodka;
	static JRadioButton angielski, polski;
	static JPanel waveLabels, sliderLabels, textPanel, north, east, jezyki;
	static Slider tempoSymulacji;
	static JLabel tempo, comboBoxText;
	static JComboBox<String> cb;
	static Menu rozwijane;
	static WaveGraph graph;
	static ArrayList<Wave> waves;
	static ArrayList<Label> waveLabel;
	static int waveIdx=0;
	static ArrayList<String> choices;

	Main() throws HeadlessException{

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(1280, 720);

		waveLabels = new JPanel();
		waveLabels.setLayout(new GridLayout(2, 1));
		waveLabel=new ArrayList<Label>();
		waves = ConfigLoader.readConfig();
		choices = new ArrayList<String>() ;

		for (Wave wave: waves) {
			waveLabel.add(new Label(wave.toString())) ;
			waveLabels.add(waveLabel.get(waveIdx));
			choices.add(String.valueOf(waveIdx+1));
			waveIdx++;
			try {
				SoundGenerator.generate(wave, "wave-freq-" + wave.getFreq() + "-sound");
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
		sliderLabels = new JPanel();
		comboBoxText=new JLabel("Wybierz, parametry którego dźwięku chcesz zmienić");
		textPanel = new JPanel();
		textPanel.setSize(100, 100);

		parametryDziweku =new JButton("Parametry dźwięku nr 1");
		parametryDziweku.addActionListener(new SoundParameters());
		parametryOsrodka=new JButton("Parametry ośrodka");
		parametryOsrodka.addActionListener(new MediumParameters());
		angielski=new JRadioButton("Angielski");
		angielski.addActionListener(new LanguageButton());
		polski=new JRadioButton("Polski");
		polski.addActionListener(new LanguageButton());
		ButtonGroup group=new ButtonGroup();
		group.add(angielski);
		group.add(polski);
		polski.setSelected(true);
		tempo=new JLabel("Tempo Symulacji");
		tempoSymulacji=new Slider(0, 10, 3);

		String[] arr = choices.toArray(new String[0]);

		cb = new JComboBox<String>(arr);
		cb.addActionListener(new ComboBoxListener());

		textPanel.add(comboBoxText);
		textPanel.add(cb);

		north=new JPanel();
		east=new JPanel();
		jezyki=new JPanel();
		jezyki.setLayout(new GridLayout(1, 2));
		jezyki.add(angielski);
		jezyki.add(polski);
		east.setLayout(new GridLayout(6, 1));
		north.setLayout(new GridLayout(1, 3));
		rozwijane=new Menu();
		north.add(rozwijane);
		north.add(waveLabels);
		east.add(sliderLabels);
		east.add(parametryDziweku);
		east.add(parametryOsrodka);
		east.add(tempo);
		east.add(tempoSymulacji);
		east.add(jezyki);
		this.add(textPanel, BorderLayout.PAGE_END);
		this.add(east, BorderLayout.EAST);
		this.add(north, BorderLayout.NORTH);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		Main frame=new Main();
		frame.setVisible(true);
	}
}
