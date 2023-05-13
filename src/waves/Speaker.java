package waves;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Speaker {
private BufferedImage glosnik;
private int xPos, yPos;
private int xWidth, yWidth;

Speaker(){
	
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
}

public void draw(Graphics g2d) {
    g2d.drawImage(glosnik, xPos, yPos, MainFrame.center);
}
public int getyPos() {
	return yPos;
}
public void setyPos(int yPos) {
	this.yPos = yPos;
}
public int getxPos() {
	return xPos;
}
public void setxPos(int xPos) {
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
