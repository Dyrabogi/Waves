package waves;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBoxListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		if (MainFrame.polski.isSelected())
			MainFrame.parametryDziweku.setText("Parametry dzwięku nr " + (MainFrame.cb.getSelectedIndex() + 1));
		else
			MainFrame.parametryDziweku.setText("Parameters of sound number " + (MainFrame.cb.getSelectedIndex() + 1));

	}
}
