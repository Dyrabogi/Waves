package waves;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JPanel;

public class WaveGraph extends JPanel {

	ArrayList<Wave> waves;
	static ChartFrame frame1;
	JFreeChart chart;
	XYSeriesCollection dataset = new XYSeriesCollection();
	BufferedImage image;
	public WaveGraph(ArrayList<Wave> waves) {
		super();
		XYSeries series = new XYSeries(" f(x) ");
		
		if (MainFrame.polski.isSelected()) {
			chart = ChartFactory.createXYLineChart("Wykres fali wynikowej", // Tytul
					"t (s)", // opisy osi
					" ", dataset, // Dane
					PlotOrientation.VERTICAL, // Orjentacja wykresu
					true, // legenda
					true, // tooltips
					false);
		}
		if (MainFrame.angielski.isSelected()) {
			chart = ChartFactory.createXYLineChart("Wave graph", // Tytul
					"t (s)", // opisy osi
					" ", dataset, // Dane
					PlotOrientation.VERTICAL, // Orjentacja wykresu
					true, // legenda
					true, // tooltips
					false);
		}

		frame1 = new ChartFrame("Chart", chart);
		frame1.setSize(1400, 600);
		image = chart.createBufferedImage(500, 500);
		this.waves = waves;
	}

	public void setWaves(ArrayList<Wave> waves) {
		this.waves = waves;
		
		XYSeries series = new XYSeries(" f(x) ");
		double y;
		for (double x = 0.1; x <= 1000; x++) {
			y = 0;
						
			for (int i = 0; i <= waves.size() - 1; i++) {
				double lambda=1/waves.get(i).getFreq();
				double 	d=Math.pow(Visualisation.detector.getxPos()-Visualisation.speakers.get(i).getxPos(), 2);
				d+=Math.pow(Visualisation.detector.getyPos()-Visualisation.speakers.get(i).getyPos(), 2);
				d=Math.sqrt(d);
				y += waves.get(i).getAmp() * Math.sin(2 * 3.14 * waves.get(i).getFreq() * x + waves.get(i).getPhase()
						+2 * 3.14 *d/lambda);
			}
			series.add(x, y);
		}
		
		dataset.removeAllSeries();
		dataset.addSeries(series);
		
	}

	public BufferedImage getImage() {
		image = chart.createBufferedImage(500, 500);
		return image;
	}
	
}
