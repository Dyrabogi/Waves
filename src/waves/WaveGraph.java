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


		for (double x = 0.001; x <= 1000; x++) {
			int y=0;
			for(int i=0; i< waves.size(); i++){
				y+=(int) 200-(waves.get(i).getAmp() * Math.sin(2*3.14*waves.get(i).getFreq() * x+waves.get(i).getPhase()));
			}
			p.addPoint((int) x, y);
		}
		g.setColor(Color.red);
		g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
	}


}
