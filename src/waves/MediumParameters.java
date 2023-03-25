package waves;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MediumParameters implements ActionListener {

    MediumParameters(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog dialog=new JDialog();
        dialog.setLayout(new GridLayout(4, 1));
        if(Main.polski.isSelected()){
            JLabel cisnienie=new JLabel("Ciśnienie");
            JButton lista=new JButton("Wybierz z listy");
            dialog.add(cisnienie);
            dialog.add(lista);
        }
        else{
            JLabel cisnienie=new JLabel("Pressure");
            JButton lista=new JButton("Choose from a list");
            dialog.add(cisnienie);
            dialog.add(lista);
        }
        Slider cisSlider=new Slider(0, 10, 3);
        JTextField cisText=new JTextField();

        dialog.add(cisSlider);
        dialog.add(cisText);
        dialog.setSize(300,300);
        dialog.setVisible(true);
    }
}
