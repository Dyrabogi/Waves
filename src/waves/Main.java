package waves;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setSize(640, 480);

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
		
		Slider slider = new Slider(0,300);	
		
		JPanel sliderLabels = new JPanel();
		Label sliderLabel = new Label(String.valueOf((double) slider.getValue()/10));
		sliderLabels.add(sliderLabel);
		
		JPanel textPanel = new JPanel();
		textPanel.setSize(100, 100);
		DecimalFormat format = new DecimalFormat();
		
		
			
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
		
	    frame.add(slider);
		frame.add(waveLabels);
		frame.add(sliderLabels);
		frame.add(graph);
		frame.add(textPanel);
		frame.setLayout(new GridLayout(2,3));
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
		
	}

}
