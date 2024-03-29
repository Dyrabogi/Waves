package waves;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LanguageButton implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		if (MainFrame.angielski.isSelected()) {

			Menu.losowo.setText("Random");
			Menu.dzwiek.setText("Add sound source");
			Menu.nagraj.setText("Save the sound");
			Menu.zatrzymaj.setText("Stop the sound");
			Menu.odtworz.setText("Play the sound");
			Menu.pokaz.setText("Show the wave graph");
			Menu.wlasne.setText("Select parameters");
			Menu.json.setText("Add sound source from json file");
			Menu.zapisz.setText("Save the graph");
			Menu.zListy.setText("Select from the list");
			Menu.detektor.setText("Detector");
			MainFrame.highDensity.setText("High Density");
			MainFrame.lowDensity.setText("Low Density");
			MainFrame.midDensity.setText("Medium Density");
			MainFrame.comboBoxText.setText("Choose which sound parameters you want to change");
			MainFrame.tempo.setText("Speed of animation");
			MainFrame.legendLabel.setText("Legend of simulation");
			MainFrame.parametryDziweku.setText("Parameters of sound number " + (MainFrame.cb.getSelectedIndex() + 1));
			MainFrame.parametryOsrodka.setText("Medium Parameters");
		}
		if (MainFrame.polski.isSelected()) {

			Menu.losowo.setText("Losowo");
			Menu.dzwiek.setText("Dodaj źródło dźwięku");
			Menu.nagraj.setText("Zapisz dźwięk");
			Menu.odtworz.setText("Odtwórz dźwięk");
			Menu.pokaz.setText("Pokaż wykres fali");
			Menu.zatrzymaj.setText("Zatrzymaj dźwięk");
			Menu.wlasne.setText("Wybierz własne parametry");
			Menu.zapisz.setText("Zapisz wykres");
			Menu.zListy.setText("Wybierz z listy");
			Menu.detektor.setText("Detektor");
			Menu.json.setText("Wczytaj z pliku json");
			MainFrame.highDensity.setText("Duża gęstość");
			MainFrame.legendLabel.setText("Legenda symulacji");
			MainFrame.lowDensity.setText("Mała gęstość");
			MainFrame.midDensity.setText("Średnia gęstość");
			MainFrame.comboBoxText.setText("Wybierz, parametry którego dźwięku chcesz zmienić");
			MainFrame.tempo.setText("Tempo animacji");
			MainFrame.parametryDziweku.setText("Parametry dzwięku nr " + (MainFrame.cb.getSelectedIndex() + 1));
			MainFrame.parametryOsrodka.setText("Parametry ośrodka");

		}
	}
}
