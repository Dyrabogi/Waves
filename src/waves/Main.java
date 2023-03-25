package waves;

import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;

public class Main {
	static JButton parametryDzieku, parametryOsrodka;
	static JRadioButton angielski, polski;
	static JFrame frame;
	static JPanel waveLabels, sliderLabels, textPanel, north, east, jezyki;
	static Slider slider, tempoSymulacji;
	static Label sliderLabel;
	static JLabel tempo;
	static JComboBox<String> cb;
	static Menu rozwijane;
	static WaveGraph graph;

	public static void main(String[] args) {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setSize(1280, 720);

		waveLabels = new JPanel();
		ArrayList<Wave> waves = ConfigLoader.readConfig();
	    ArrayList<String> choices = new ArrayList<String>() ;


	    int waveIdx = 0;
		for (Wave wave: waves) {
			Label waveLabel = new Label(wave.toString());
			waveLabels.add(waveLabel);
			choices.add(String.valueOf(waveIdx));
			waveIdx++;
			try {
				SoundGenerator.generate(wave, "wave-freq-" + wave.getFreq() + "-sound");
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
		
		slider = new Slider(0,300, 50);

		sliderLabels = new JPanel();
		sliderLabel = new Label("Amplituda: " +String.valueOf((double) slider.getValue()/10));
		sliderLabels.add(sliderLabel);
		
		textPanel = new JPanel();
		textPanel.setSize(100, 100);
		DecimalFormat format = new DecimalFormat();

		parametryDzieku=new JButton("Parametry dźwięku");
		parametryDzieku.addActionListener(new SoundParameters());
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
			
	    JFormattedTextField field = new JFormattedTextField(format);
	    field.setPreferredSize(new Dimension(500,25));
	    
	    
	    textPanel.add(field);
	    
	    String[] arr = choices.toArray(new String[0]);

	    cb = new JComboBox<String>(arr);
	    graph = new WaveGraph(waves);

	    field.getDocument().addDocumentListener(new WaveTextSynchronizer(waves, waveLabels, field, cb, graph));
	    slider.addChangeListener(new SliderTextSynchronizer(slider, sliderLabels,graph, waveLabels,waves));
	    
	    textPanel.add(cb);
	    // getValue() always returns something valid

		north=new JPanel();
		east=new JPanel();
		jezyki=new JPanel();
		jezyki.setLayout(new GridLayout(1, 2));
		jezyki.add(angielski);
		jezyki.add(polski);
		east.setLayout(new GridLayout(6, 1));
		north.setLayout(new GridLayout(1, 4));
		rozwijane=new Menu();
		north.add(rozwijane);
		north.add(waveLabels);
	    frame.add(slider, BorderLayout.WEST);
		east.add(sliderLabels);
		east.add(parametryDzieku);
		east.add(parametryOsrodka);
		east.add(tempo);
		east.add(tempoSymulacji);
		east.add(jezyki);
		frame.add(graph, BorderLayout.CENTER);
		frame.add(textPanel, BorderLayout.PAGE_END);
		frame.add(east, BorderLayout.EAST);
		frame.add(north, BorderLayout.NORTH);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
		
	}

}
