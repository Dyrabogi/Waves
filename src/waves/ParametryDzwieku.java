package waves;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParametryDzwieku implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog dialog=new JDialog();
        dialog.setLayout(new GridLayout(13, 1));
        JLabel amplituda=new JLabel("Amplituda");
        JLabel przesuniecie=new JLabel("Przesunięcie fazowe");
        JLabel czestotliwosc=new JLabel("Częstotliwość");
        Slider ampSlider=new Slider(0, 100, 10);
        Slider czeSlider=new Slider(0, 100, 10);
        Slider przesSlider=new Slider(0, 100, 10);
        JTextField ampText=new JTextField();
        JTextField czeText=new JTextField();
        JTextField przeText=new JTextField();
        dialog.add(amplituda);
        dialog.add(ampSlider);
        dialog.add(ampText);
        dialog.add(czestotliwosc);
        dialog.add(czeSlider);
        dialog.add(czeText);
        dialog.add(przesuniecie);
        dialog.add(przesSlider);
        dialog.add(przeText);

        JLabel moc=new JLabel("Moc akustyczna");
        Slider mocSlider=new Slider(0, 10, 3);
        JTextField mocText=new JTextField();
        dialog.add(moc);
        dialog.add(mocSlider);
        dialog.add(mocText);

        dialog.setSize(500,700);
        dialog.setVisible(true);


    }
}
