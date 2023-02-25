package waves;

import java.awt.BorderLayout;
import java.awt.Label;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(640, 480);


		JPanel labels = new JPanel();
		ArrayList<Wave> waves = ConfigLoader.readConfig();
		waves.forEach((wave) -> {
			Label label = new Label(wave.toString());
			labels.add(label);

		});
		
		SoundGenerator.generate(waves.get(0));


		frame.add(labels, BorderLayout.PAGE_START);
		frame.add(new WaveGraph(), BorderLayout.CENTER);

		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
	}

}
