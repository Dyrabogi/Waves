package waves;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Speaker {
static BufferedImage glosnik;
private int xPos, yPos;
private int xWidth, yWidth;
ArrayList <Circle> animation;
Speaker(){
	animation=new ArrayList<Circle>();
	
	
	 File inputFile = new File("glosnik.png");
     try {
         glosnik = ImageIO.read(inputFile);
     } catch(IOException ex) {
         System.out.println(ex.getMessage());
     }
    xPos=100;
    yPos=300;
    
    xWidth=xPos+glosnik.getWidth();
    yWidth=yPos+glosnik.getHeight();
    
    for(int i=0; i<500; i++) {
		Circle c=new Circle();
		c.setOrigin(xPos/2+xWidth/2, yPos/2+yWidth/2);
		animation.add(c);
	}
}

public void draw(Graphics g2d) {
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
}
