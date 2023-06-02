package waves;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import javax.swing.*;
import javax.swing.JPanel;

public class Legend extends JPanel {
	GradientPaint gradient1, gradient2, gradient3, gradient4;
	Color c1, c2, c3, c4, c5;
	Legend(){
		this.setPreferredSize(new Dimension(80, 100));
		c1 =Color.getHSBColor((float) 0.3, 1, (float) 0.8);
		c2=Color.getHSBColor((float) 0.5, 1, (float) 0.8);
		c3=Color.getHSBColor((float) 0.7, 1, (float) 0.8);
		c4=Color.getHSBColor((float) 0.9, 1, (float) 0.8);
		c5=Color.getHSBColor(1, 1, (float) 0.8);
	    gradient1 = new GradientPaint(0, 0, c1, 90, 0, c2);
	    this.setLayout(new GridLayout(1, 2, 250, 0));
	    JLabel min= new JLabel("Minimum");
	    this.add(min);
	    gradient2 = new GradientPaint(90, 0, c2, 180, 0, c3);
	    gradient3 = new GradientPaint(180, 0, c3, 270, 0, c4);
	    JLabel max=new JLabel("Maximum");
	    gradient4 = new GradientPaint(270, 0, c4, 360, 0, c5);
	    this.add(max);
	    this.setBackground(Color.white);
	    repaint();
	}
	
	protected void paintComponent(Graphics g) {
	     super.paintComponent(g);
		
	     Graphics2D g2d=(Graphics2D) g;
	     g2d.setPaint(gradient1);
		 g2d.fill(new Rectangle(0,0,90,300));
		 g2d.setPaint(gradient2);
		g2d.fill(new Rectangle(90,0,90,300));
		 g2d.setPaint(gradient3);
		 g2d.fill(new Rectangle(180,0,90,300));
		 g2d.setPaint(gradient4);
		 g2d.fill(new Rectangle(270,0,90,300));
	}


}
