package waves;

import javax.swing.*;

public class Menu extends JMenuBar {
    Menu(){
        super();
        JMenu dzwiek=new JMenu("Dodaj źródło dźwięku");
        this.add(dzwiek);
        JMenuItem losowo=new JMenuItem("Losowo");
        JMenuItem zListy=new JMenuItem("Z listy");
        JMenuItem wlasne=new JMenuItem("Własne parametry");
        dzwiek.add(losowo);
        dzwiek.add(zListy);
        dzwiek.add(wlasne);

        JMenu detektor=new JMenu("Detektor");
        JMenuItem nagraj=new JMenuItem("Nagraj dźwięk");
        detektor.add(nagraj);
        JMenuItem odtworz=new JMenuItem("Odtwórz dźwięk");
        detektor.add(odtworz);
        JMenuItem pokaz=new JMenuItem("Pokaż wykres fali");
        detektor.add(pokaz);
        JMenuItem zapisz=new JMenuItem("Zapisz wykres fali");
        detektor.add(zapisz);
        this.add(detektor);
    }
}
