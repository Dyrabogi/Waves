package waves;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class Detector {
private BufferedImage detektor;
private int xPos, yPos;
private int xWidth, yWidth;

	
Detector(){
	File inputFile = new File("detektor.png");
     try {
         detektor = ImageIO.read(inputFile);
     } catch(IOException ex) {
         System.out.println(ex.getMessage());
     }
     xPos=1000;
     yPos=300;
     this.setxWidth();
     this.setyWidth();
}

public void draw(Graphics g) {
    g.drawImage(detektor, xPos, yPos, MainFrame.center);
}

public int getxPos() {
	return xPos;
}
public void setxPos(int xPos) {
	this.xPos = xPos;
}

public int getyPos() {
	return yPos;
}

public void setyPos(int yPos) {
	this.yPos = yPos;
}
public int getxWidth() {
	return xWidth;
}

public void setxWidth() {
	this.xWidth = xPos+detektor.getWidth();
}

public int getyWidth() {
	return yWidth;
}

public void setyWidth() {
	this.yWidth = yPos+detektor.getHeight();
}
}
