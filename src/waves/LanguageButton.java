package waves;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LanguageButton implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(Main.angielski.isSelected()){

            Menu.losowo.setText("Random");
            Menu.dzwiek.setText("Add sound source");
            Menu.nagraj.setText("Record the sound");
            Menu.odtworz.setText("Play the sound");
            Menu.pokaz.setText("Show the wave graph");
            Menu.wlasne.setText("Select parameters");
            Menu.zapisz.setText("Save the graph");
            Menu.zListy.setText("Select from the list");
            Menu.detektor.setText("Detector");
            Main.comboBoxText.setText("Choose which sound parameters you want to change");
            Main.polski.setText("Polish");
            Main.angielski.setText("English");
            Main.tempo.setText("Speed of animation");
            Main.parametryDziweku.setText("Parameters of sound number "+ (Main.cb.getSelectedIndex()+1) );
            Main.parametryOsrodka.setText("Medium Parameters");
        }
        if(Main.polski.isSelected()){

            Menu.losowo.setText("Losowo");
            Menu.dzwiek.setText("Dodaj źródło dźwięku");
            Menu.nagraj.setText("Nagraj dźwięk");
            Menu.odtworz.setText("Odtwórz dźwięk");
            Menu.pokaz.setText("Pokaż wykres fali");
            Menu.wlasne.setText("Wybierz własne parametry");
            Menu.zapisz.setText("Zapisz wykres");
            Menu.zListy.setText("Wybierz z listy");
            Menu.detektor.setText("Detektor");
            Main.comboBoxText.setText("Wybierz, parametry którego dźwięku chcesz zmienić");
            Main.polski.setText("polski");
            Main.angielski.setText("angielski");
            Main.tempo.setText("Tempo animacji");
            Main.parametryDziweku.setText("Parametry dzwięku nr "+ (Main.cb.getSelectedIndex()+1) );
            Main.parametryOsrodka.setText("Parametry ośrodka");

        }
    }
}
