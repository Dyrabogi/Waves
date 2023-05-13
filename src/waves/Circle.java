package waves;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

public class Circle {

		protected int x;
	    protected int y;
	    protected int radius;
	    protected Color color;
	    protected int i;
	    Circle(){

	    }
	    
	    public void setOrigin(int x, int y) {
	    	this.x=x;
	    	this.y=y;
	    }
	    public void setRadius(int d) {
	    	this.radius=d;
	    }
	    public void setColor(int x) {
	    	double amp=0;	    	
	    	double lambda=Math.sqrt(MediumParameters.getCisnienie())/MainFrame.waves.get(i).getFreq();
			double 	d=Math.pow(Visualisation.detector.getxPos()-Visualisation.speakers.get(i).getxPos(), 2);
			d+=Math.pow(Visualisation.detector.getyPos()-Visualisation.speakers.get(i).getyPos(), 2);
			d=Math.sqrt(d);
			amp=MainFrame.waves.get(i).getAmp()/Math.pow(d, 2) * Math.sin(2 * 3.14 * MainFrame.waves.get(i).getFreq() * x + MainFrame.waves.get(i).getPhase()
					+2 * 3.14 *d/lambda)+ MainFrame.waves.get(i).getAmp();

			float famp=(float) amp/255;
			float a=(float) 0.5;
	    	color=new Color(famp, famp, famp, a);
	    }
	
	public void draw(Graphics g2d) {
		
        g2d.setColor(color);
        g2d.drawOval(x-radius/2, y-radius/2, radius, radius);
    }
}
