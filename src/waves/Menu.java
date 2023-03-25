package waves;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static waves.WaveGraph.*;


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
        pokaz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               WaveGraph graph= new WaveGraph(Main.waves);
                     graph.repaint();
                     graph.revalidate();
                     graph.frame1.setVisible(true);
            }
        });

        detektor.add(pokaz);
        zapisz=new JMenuItem("Zapisz wykres fali");
        detektor.add(zapisz);
        this.add(detektor);
    }
}
