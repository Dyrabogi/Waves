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

            Main.polski.setText("Polish");
            Main.angielski.setText("English");
            Main.tempo.setText("Speed of animation");
            Main.parametryDzieku.setText("Sound Parameters");
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

            Main.polski.setText("polski");
            Main.angielski.setText("angielski");
            Main.tempo.setText("Tempo animacji");
            Main.parametryDzieku.setText("Parametry dźwięku");
            Main.parametryOsrodka.setText("Parametry ośrodka");

        }
    }
}
