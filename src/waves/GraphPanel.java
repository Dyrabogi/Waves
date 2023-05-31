package waves;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GraphPanel extends JPanel{
	BufferedImage image;
	GraphPanel(){
		image=MainFrame.graph.chart.createBufferedImage(500, 900);
	}
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
         Graphics2D g2d = (Graphics2D) g;
         if(image!=null)
             g.drawImage(image, 0, 0, this);
    }

}
