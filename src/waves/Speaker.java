package waves;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;

public class Speaker implements Runnable{
	
	static BufferedImage glosnik;
	private int xPos, yPos;
	private int xWidth, yWidth;
	private CopyOnWriteArrayList <Circle> animation;
	static int nrSpeakers=0;
	private int whichSpeaker;
	static int speed;
	static int circleDensity = 1;
	Boolean czynny=true;
Speaker(){
		animation=new CopyOnWriteArrayList<Circle>();
		 File inputFile = new File("glosnik.png");
	     try {
	         glosnik = ImageIO.read(inputFile);
	     } catch(IOException ex) {
	         System.out.println(ex.getMessage());
	     }
	    xPos=100;
	    yPos=300;
	    speed=100;
	    
	    xWidth=xPos+glosnik.getWidth();
	    yWidth=yPos+glosnik.getHeight();
	    
	    for(int i=0; i<10; i++) {
			Circle c=new Circle(nrSpeakers);
			c.setOrigin(xPos/2+xWidth/2, yPos/2+yWidth/2);
			c.setRadius(70+i);
			animation.add(c);
		}
	    whichSpeaker=nrSpeakers;
	    nrSpeakers++;
}
void setSpeed(int i) {
	if(i==0)
		czynny=false;
	else
	{
		speed=1000/i;
		czynny=true;
	}
		
}

public void draw(Graphics g2d) {
  
    for (Circle crr:animation) {
    	if(crr.getRadius()>1270)
    		animation.remove(crr);
    }
    for(Circle cr:animation) {
    	Graphics2D g2=(Graphics2D) g2d;
    	cr.draw(g2);
    }
    Circle c=new Circle(whichSpeaker);
    c.setRadius(70);
    c.setOrigin(xPos/2+xWidth/2, yPos/2+yWidth/2);
    animation.add(c);
    g2d.drawImage(glosnik, xPos, yPos, MainFrame.center);
    }
    	
public int getyPos() {
	return yPos;
}
public void setyPos(int yPos) {
	for(Circle c: animation) {
		c.setOrigin(xPos/2+xWidth/2, yPos/2+yWidth/2);
	}
	this.yPos = yPos;
}
public int getxPos() {
	return xPos;
}
public void setxPos(int xPos) {
	for(Circle c: animation) {
		c.setOrigin(xPos/2+xWidth/2, yPos/2+yWidth/2);
	}
	this.xPos = xPos;
}

public int getxWidth() {
	return xWidth;
}

public void setxWidth() {
	this.xWidth = xPos+glosnik.getWidth();
}

public int getyWidth() {
	return yWidth;
}

public void setyWidth() {
	this.yWidth = yPos+glosnik.getHeight();
}

public void setCircleDensity(int i) {
	circleDensity = i;
}

@Override
public void run() {
	while(czynny){
		{
			  for (Circle cr : animation) {
              cr.setRadius(cr.getRadius()+circleDensity*10);
              cr.setColor(cr.getRadius()); 
          } 
			  MainFrame.center.repaint(); 
			 
        try {
        	
            Thread.sleep(speed);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }	
} }     

}
	
}
