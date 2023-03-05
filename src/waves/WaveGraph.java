package waves;

import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JPanel;

public class WaveGraph extends JPanel {

	ArrayList<Wave> waves;

	public WaveGraph(ArrayList<Wave> waves) {
		super();
		this.waves = waves;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Polygon p = new Polygon();

		for (int x = 0; x <= 4 * Math.PI * 50; x++) {
//			double bob1 = (x*waves.get(0).getFreq()*2*Math.PI)/50 ;
//			double bob2 = (x*waves.get(1).getFreq()*2*Math.PI)/50 ;
//			double amp1 = waves.get(0).getAmp();
//			double amp2 = waves.get(1).getAmp();
//			double ph1 = waves.get(0).getPhase();
//			double ph2 = waves.get(1).getPhase()/2;
			// System.out.println(Math.sin(bob1)-Math.cos(bob2));
			p.addPoint((int) (x), 100 - (int) (waves.get(0).getAmp() * Math.sin(50 * x)));
			// System.out.println(Math.sin(bob1) + " " + Math.sin(100 * x));
		}

		g.setColor(Color.red);
		g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
	}


}
