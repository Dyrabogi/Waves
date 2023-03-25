package waves;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SoundParameters implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog dialog=new JDialog();
        dialog.setLayout(new GridLayout(13, 1));
        JLabel amplituda=new JLabel();
        JLabel przesuniecie=new JLabel();
        JLabel czestotliwosc=new JLabel();
        JLabel moc=new JLabel();
        if(Main.polski.isSelected()){
            amplituda.setText("Amplituda");
            przesuniecie.setText("Przesunięcie fazowe");
            czestotliwosc.setText("Częstotliwość");
            moc.setText("Moc akustyczna");
        }
        else{
            amplituda.setText("Amplitude");
            przesuniecie.setText("Phase shift");
            czestotliwosc.setText("Frequency");
            moc.setText("Acustic power");
        }

        Slider ampSlider=new Slider(0, 100, 10);
        ampSlider.addChangeListener(new ChangeListener() {
            @Override
                public void stateChanged(ChangeEvent e) {
                    Main.waves.get(Main.cb.getSelectedIndex()).setAmp(ampSlider.getValue());
                    Main.waveLabel.get(Main.cb.getSelectedIndex()).setText(Main.waves.get(Main.cb.getSelectedIndex()).toString());
            }
        });
        Slider czeSlider=new Slider(0, 100, 10);
        czeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Main.waves.get(Main.cb.getSelectedIndex()).setFreq(czeSlider.getValue());
                Main.waveLabel.get(Main.cb.getSelectedIndex()).setText(Main.waves.get(Main.cb.getSelectedIndex()).toString());
            }
        });
        Slider przesSlider=new Slider(0, 100, 10);
        przesSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Main.waves.get(Main.cb.getSelectedIndex()).setPhase(przesSlider.getValue());
                Main.waveLabel.get(Main.cb.getSelectedIndex()).setText(Main.waves.get(Main.cb.getSelectedIndex()).toString());
            }
        });
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
        Slider mocSlider=new Slider(0, 10, 3);
        JTextField mocText=new JTextField();
        dialog.add(moc);
        dialog.add(mocSlider);
        dialog.add(mocText);

        dialog.setSize(500,700);
        dialog.setVisible(true);


    }
}
