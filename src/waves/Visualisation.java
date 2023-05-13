package waves;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.*;  
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

public class Visualisation extends JPanel implements MouseMotionListener{
	static Detector detector;
	static ArrayList <Speaker> speakers;
	int doZmiany, podniesienieX, podniesienieY;
	boolean zmiana;
	
	Visualisation(){
	zmiana=false;
	 speakers=new ArrayList<>();
	 detector=new Detector();
	 this.setBackground(Color.white);
	 this.addMouseListener(myMouseListener);
	 this.addMouseMotionListener(this);
     repaint();
     setVisible(true);
	}
	void addSpeaker() {
		Speaker speak=new Speaker();
		speakers.add(speak);
		repaint();
	}
	
	public void paintComponent(Graphics g) {
	       super.paintComponent(g);
	        detector.draw(g);
	        for(Speaker i:speakers)
	        	i.draw(g);
	    }
	
	 MouseListener myMouseListener=new MouseListener() {
	 public void mousePressed(MouseEvent e) {
		 
		 for(int i=0; i<speakers.size(); i++) {
			 if(e.getX()>=speakers.get(i).getxPos() && e.getX()<=speakers.get(i).getxWidth()
					 && e.getY()>=speakers.get(i).getyPos() && e.getY()<=speakers.get(i).getyWidth()) 
			 {
				 zmiana=true;
				 doZmiany=i;
				 podniesienieX=e.getX()-speakers.get(doZmiany).getxPos();
				 podniesienieY=e.getY()-speakers.get(i).getyPos();
			 }
		 }
		 if(e.getX()>=detector.getxPos() && e.getX()<=detector.getxWidth()
					 && e.getY()>=detector.getyPos() && e.getY()<=detector.getyWidth()) {
			 zmiana=true;
			 doZmiany=10000;
			 podniesienieX=e.getX()-detector.getxPos();
			 podniesienieY=e.getY()-detector.getyPos();
		 }
		 
	 }
	 public void mouseReleased(MouseEvent e) {
		 zmiana=false;}
	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			for(int i=0; i<Visualisation.speakers.size(); i++) {
				 if(e.getX()>=Visualisation.speakers.get(i).getxPos() && e.getX()<=Visualisation.speakers.get(i).getxWidth()
						 && e.getY()>=Visualisation.speakers.get(i).getyPos() && e.getY()<=Visualisation.speakers.get(i).getyWidth()) {
	
			JPopupMenu popupmenu = new JPopupMenu("Zmień źódło dźwięku");   
	         JMenuItem change = new JMenuItem("Zmień parametry");
	         MainFrame.cb.setSelectedIndex(i);
	         change.addActionListener(new SoundParameters());
	         popupmenu.add(change);   
	         popupmenu.show(MainFrame.center, e.getX(), e.getY()); 
	         
	}}}}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	 };
	
	public void mouseDragged(MouseEvent e) {
		if(zmiana==true) {
			if(doZmiany<10000) {
			speakers.get(doZmiany).setxPos(e.getX()-podniesienieX);
			speakers.get(doZmiany).setyPos(e.getY()-podniesienieY);
			speakers.get(doZmiany).setxWidth();
			speakers.get(doZmiany).setyWidth();
			this.repaint();}
			else {
				detector.setxPos(e.getX()-podniesienieX);
				detector.setyPos(e.getY()-podniesienieY);
				detector.setxWidth();
				detector.setyWidth();
				this.repaint();
			}
				
		}
	}
	

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	ActionListener changeAction=new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	};
}
