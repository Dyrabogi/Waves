package waves;

import java.awt.BorderLayout;
import java.awt.Label;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(640, 480);
		
	
		
	    JPanel labels = new JPanel();
		List<String> wavesConfig = ConfigLoader.readConfig();
		ArrayList<Wave> waves = new ArrayList<Wave>();
		wavesConfig.forEach((line) -> {
			String[] params = line.split(",");
			waves.add(new Wave(Double.parseDouble(params[0]), Double.parseDouble(params[1]), Double.parseDouble(params[2])));
					
		});

		waves.forEach((wave) -> {
			Label label = new Label(wave.toString());
			labels.add(label);

		});
		
		frame.add(labels, BorderLayout.PAGE_START);
	    frame.add(new WaveGraph(), BorderLayout.CENTER);

		
	    
	    frame.setLocationRelativeTo(null);

		frame.setVisible(true);
	}

}
