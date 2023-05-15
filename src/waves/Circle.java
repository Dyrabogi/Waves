package waves;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.util.List;

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
			amp= Math.sin(MainFrame.waves.get(i).getFreq()*x+MainFrame.waves.get(i).getPhase())+1;
			amp=amp/2;
			float famp=(float) amp;
			float a=(float) 0.5;
			if(famp<0.33)
				color=new Color(famp, famp/2, 0, a);
			else if(famp<0.66)
				color=new Color(0, 0, famp, a);
			else
				color=new Color(0, famp, 0, a);
	    }
	
	public void draw(Graphics g2d) {
		
        g2d.setColor(color);
        int iradius=(int) radius;
        g2d.drawOval(x-iradius/2, y-iradius/2, iradius, iradius);
    }
}
