package waves;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Color;
import javax.swing.JPanel;


public class WaveGraph extends JPanel {


	protected void paintComponent(Graphics g) {
		super.paintComponent(g);


		Polygon p = new Polygon();

		for (int x = 0; x <= 4*Math.PI * 50; x++) {
			p.addPoint((int) (x) , 100 - (int) (50 * Math.sin((x+ Math.PI/2*50)/50.0)));

		}



		g.setColor(Color.red);
		g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
		

	}
}
