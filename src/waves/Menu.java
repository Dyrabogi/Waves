package waves;

import javax.swing.*;


public class Menu extends JMenuBar {

    static JMenuItem losowo, zListy, wlasne, nagraj, odtworz, pokaz, zapisz;
    static JMenu dzwiek, detektor;
    Menu(){
        super();
        dzwiek=new JMenu("Dodaj źródło dźwięku");
        this.add(dzwiek);
        losowo=new JMenuItem("Losowo");
        zListy=new JMenuItem("Z listy");
        wlasne=new JMenuItem("Własne parametry");
        dzwiek.add(losowo);
        dzwiek.add(zListy);
        dzwiek.add(wlasne);

        detektor=new JMenu("Detektor");
        nagraj=new JMenuItem("Nagraj dźwięk");
        detektor.add(nagraj);
        odtworz=new JMenuItem("Odtwórz dźwięk");
        detektor.add(odtworz);
        pokaz=new JMenuItem("Pokaż wykres fali");
        detektor.add(pokaz);
        zapisz=new JMenuItem("Zapisz wykres fali");
        detektor.add(zapisz);
        this.add(detektor);
    }
}
