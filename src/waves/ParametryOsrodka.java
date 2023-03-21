package waves;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParametryOsrodka implements ActionListener {

    ParametryOsrodka(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog dialog=new JDialog();
        dialog.setLayout(new GridLayout(4, 1));
        JLabel cisnienie=new JLabel("Ciśnienie");
        Slider cisSlider=new Slider(0, 10, 3);
        JTextField cisText=new JTextField();
        dialog.add(cisnienie);
        dialog.add(cisSlider);
        dialog.add(cisText);
        JButton lista=new JButton("Wybierz z listy");
        dialog.add(lista);
        dialog.setSize(300,300);
        dialog.setVisible(true);
    }
}
