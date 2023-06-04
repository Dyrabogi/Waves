package waves;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

public class Circle {

		protected int x;
	    protected int y;
	    protected double radius;
	    protected Color color;
	    protected int i;

	    public Circle(int i){
	    	this.i=i;	    	
	    }
	    
	    public void setOrigin(int x, int y) {
	    	this.x=x;
	    	this.y=y;
	    }
	    public void setRadius(double d) {
	    	this.radius=d;
	    }
	    public double getRadius() {
	    	return this.radius;
	    }
	    
	    public void setColor(double x) {
	    	
	    	double amp=0;	    	
			amp= Math.sin(MainFrame.waves.get(i).getFreq()*1000*x+MainFrame.waves.get(i).getPhase())+1;
			amp/=2;
			float famp=(float) amp;
			if(famp <0.2)
				color=Color.getHSBColor((float) 0.3, (float) 0.8, (float) 0.8);
			else if(famp <0.4)
				color=Color.getHSBColor((float) 0.5, (float) 0.8, (float) 0.8);
			else if(famp <0.6)
				color=Color.getHSBColor((float) 0.7, (float) 0.8, (float) 0.8);
			else if(famp <0.8)
				color=Color.getHSBColor((float) 0.9, (float) 0.8, (float) 0.8);
			else 
				color=Color.getHSBColor(1, 1, (float) 0.8);
	    }
	
	public void draw(Graphics2D g2d) {
		
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(5));
        int iradius=(int) radius;
        g2d.drawOval(x-iradius/2, y-iradius/2, iradius, iradius);
        
    }
}
