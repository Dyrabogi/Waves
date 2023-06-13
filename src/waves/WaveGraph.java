package waves;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

public class WaveGraph extends JPanel {

	ArrayList<Wave> waves;
	static ChartFrame frame1;
	static ChartPanel panel;
	JFreeChart chart;
	XYSeriesCollection dataset = new XYSeriesCollection();
	BufferedImage image;
	
	public WaveGraph(ArrayList<Wave> waves) {
		super();
		XYSeries series = new XYSeries(" f(x) ");
		
		if (MainFrame.polski.isSelected()) {
			chart = ChartFactory.createXYLineChart("Wykres fali wynikowej",
					"t (s)", 
					" ", dataset,
					PlotOrientation.VERTICAL, 
					true, 
					true, 
					false);
		}
		if (MainFrame.angielski.isSelected()) {
			chart = ChartFactory.createXYLineChart("Wave graph",
					"t (s)", 
					" ", dataset,
					PlotOrientation.VERTICAL, 
					true, 
					true, 
					false);
		}

		frame1 = new ChartFrame("Chart", chart);
		panel=new ChartPanel(chart);
		frame1.setSize(1400, 600);
		image = chart.createBufferedImage(500, 500);
		this.waves = waves;
	}

	public void setWaves(ArrayList<Wave> waves) {
		this.waves = waves;
		if(waves.size() != 0) {
		ArrayList<Double> freqs = new ArrayList();
		for(Wave wav : waves)
			freqs.add(wav.getFreq());
		double minFreq = Collections.min(freqs);
		XYSeries series = new XYSeries(" f(x) ");
		double y;
		for (double x = 0 ; x <= 2*(1/minFreq); x+=(1/minFreq)/200) {
			y = 0;
						
			for (int i = 0; i < waves.size() ; i++) {
				double lambda=Math.sqrt(MediumParameters.getCisnienie())/waves.get(i).getFreq();
				double 	d=Math.pow(Visualisation.detector.getxPos()-Visualisation.speakers.get(i).getxPos(), 2);
				d+=Math.pow(Visualisation.detector.getyPos()-Visualisation.speakers.get(i).getyPos(), 2);
				d=Math.sqrt(d);
				y += waves.get(i).getAmp()/Math.pow(d, 2) * Math.sin(2 * 3.14 * waves.get(i).getFreq() * x + waves.get(i).getPhase()
						+2 * 3.14 *d/lambda);
			}
			series.add(x, y);
		}
		
		
		dataset.removeAllSeries();
		dataset.addSeries(series);
		}
		panel.repaint();
		frame1.repaint();
		
	}

	public BufferedImage getImage() {
		image = chart.createBufferedImage(500, 500);
		return image;
	}
	
}
