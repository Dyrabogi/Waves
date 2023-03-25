package waves;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBoxListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(Main.polski.isSelected())
            Main.parametryDziweku.setText("Parametry dzwięku nr "+ (Main.cb.getSelectedIndex()+1) );
        else
            Main.parametryDziweku.setText("Parameters of sound number "+ (Main.cb.getSelectedIndex()+1) );

    }
}
