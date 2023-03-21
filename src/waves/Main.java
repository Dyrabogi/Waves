package waves;

import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setSize(1280, 720);

		JPanel waveLabels = new JPanel();
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
		
		Slider slider = new Slider(0,300, 50);

		JPanel sliderLabels = new JPanel();
		Label sliderLabel = new Label("Amplituda: " +String.valueOf((double) slider.getValue()/10));
		sliderLabels.add(sliderLabel);
		
		JPanel textPanel = new JPanel();
		textPanel.setSize(100, 100);
		DecimalFormat format = new DecimalFormat();

		JButton parametryDzieku=new JButton("Parametry dźwięku");
		parametryDzieku.addActionListener(new ParametryDzwieku());
		JButton parametryOsrodka=new JButton("Parametry ośrodka");
		parametryOsrodka.addActionListener(new ParametryOsrodka());
		JButton angielski=new JButton("Angielski");
		JButton polski=new JButton("Polski");
		JLabel tempo=new JLabel("Tempo Symulacji");
		Slider tempoSymulacji=new Slider(0, 10, 3);
			
	    JFormattedTextField field = new JFormattedTextField(format);
	    field.setPreferredSize(new Dimension(500,25));
	    
	    
	    textPanel.add(field);
	    
	    String[] arr = choices.toArray(new String[0]);
	    


	    JComboBox<String> cb = new JComboBox<String>(arr);
	    WaveGraph graph = new WaveGraph(waves);

	    field.getDocument().addDocumentListener(new WaveTextSynchronizer(waves, waveLabels, field, cb, graph));
	    slider.addChangeListener(new SliderTextSynchronizer(slider, sliderLabels,graph, waveLabels,waves));
	    
	    textPanel.add(cb);
	    // getValue() always returns something valid

		JPanel north=new JPanel();
		JPanel east=new JPanel();
		JPanel jezyki=new JPanel();
		jezyki.setLayout(new GridLayout(1, 2));
		jezyki.add(angielski);
		jezyki.add(polski);
		east.setLayout(new GridLayout(6, 1));
		north.setLayout(new GridLayout(1, 4));
		Menu rozwijane=new Menu();
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
