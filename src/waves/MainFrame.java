package waves;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainFrame extends JFrame {
	static JButton parametryDziweku, parametryOsrodka, lowDensity, highDensity, midDensity;
	static JRadioButton angielski, polski;
	static JPanel waveLabels, sliderLabels, textPanel, east, jezyki,labelsPane;
	static Slider tempoSymulacji;
	static JLabel tempo, comboBoxText, legendLabel;
	static JComboBox<String> cb;
	static Menu rozwijane;
	private JMenuBar menuBar;
	static WaveGraph graph;
	static ArrayList<Wave> waves;
	static ArrayList<Label> waveLabel;
	static int waveIdx = 0;
	static ArrayList<String> choices;
	static Visualisation center;
	static JDialog dialog, dialog2;
	JScrollPane scrollPane;
	static Object selectedValue;
	static Object[] possibleValues ={ "Korzystaj z aplikacji bez internetu", "Spróbuj ponownie się połączyć" };
	public Legend legend;
	MainFrame() throws HeadlessException {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(1280, 720);
		
		center=new Visualisation();
		waveLabels = new JPanel();
		scrollPane = new JScrollPane(waveLabels);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(15,10,325,75);
        labelsPane = new JPanel(null);
        labelsPane.setPreferredSize(new Dimension(40,100));
        labelsPane.add(scrollPane);
       
		waveLabels.setLayout(new GridLayout(2, 1));
		waveLabel = new ArrayList<Label>();
		waves=new ArrayList<>();
		choices = new ArrayList<String>();
		comboBoxText = new JLabel("Wybierz, parametry którego dźwięku chcesz zmienić");
		textPanel = new JPanel();
		textPanel.setSize(100, 100);

		parametryDziweku = new JButton("Parametry dźwięku nr 1");
		parametryDziweku.addActionListener(new SoundParameters());
		parametryOsrodka = new JButton("Parametry ośrodka");
		parametryOsrodka.addActionListener(new MediumParameters());

		ImageIcon ang = new ImageIcon("angielski.jpg");
	    angielski = new JRadioButton(ang);
		angielski.addActionListener(new LanguageButton());
		angielski.setText("English");
		ImageIcon pl = new ImageIcon("polski.jpg");
	    polski = new JRadioButton(pl);
	    polski.setText("Polski");
		polski.addActionListener(new LanguageButton());
		ButtonGroup group = new ButtonGroup();
		group.add(angielski);
		group.add(polski);
		polski.setSelected(true);
		tempo = new JLabel("Tempo Symulacji");
		tempoSymulacji = new Slider(0, 100, 25);
		tempoSymulacji.setValue(10);
		tempoSymulacji.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				for(Speaker s: center.speakers)
					s.setSpeed(tempoSymulacji.getValue());
			}
			
		});

		String[] arr = choices.toArray(new String[0]);
		cb = new JComboBox<String>(arr);
		cb.addActionListener(new ComboBoxListener());
		textPanel.add(comboBoxText);
		textPanel.add(cb);
		east = new JPanel();
		jezyki = new JPanel();
		jezyki.setLayout(new GridLayout(1, 2));
		jezyki.add(angielski);
		jezyki.add(polski);	
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		menuBar=new JMenuBar();
		JOptionPane pane = new JOptionPane("Trwa łączenie z bazą danych");
	    dialog = pane.createDialog(this, "Uwaga");
	    this.setVisible(true);
		rozwijane = new Menu();	
		menuBar.add(rozwijane);
		this.add(menuBar, BorderLayout.NORTH);
		JPanel parametry=new JPanel();
		parametry.add(parametryDziweku);
		parametry.add(parametryOsrodka);
		
		graph = new WaveGraph(MainFrame.waves);
		east.add(graph.panel);
		graph.panel.setPreferredSize(new Dimension(340, 210));
		east.add(parametry);
		east.add(labelsPane);
		JPanel densityPanel = new JPanel();
		lowDensity = new JButton("Mała gęstość");
		lowDensity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				center.changeCircleDensity(7);
				
			}
		});
		
		midDensity = new JButton("Średnia gęstość");
		midDensity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				center.changeCircleDensity(4);
				
			}
		});
		highDensity = new JButton("Duża gęstość");
		highDensity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				center.changeCircleDensity(1);
				
			}
		});



		densityPanel.add(lowDensity);
		densityPanel.add(midDensity);
		densityPanel.add(highDensity);
		legend=new Legend();
		east.add(densityPanel);
		legendLabel=new JLabel("Legenda symulacji");
		east.add(legendLabel);
		east.add(legend);
		east.add(tempo);
		east.add(tempoSymulacji);
		east.add(jezyki);
		this.add(textPanel, BorderLayout.PAGE_END);
		this.add(east, BorderLayout.EAST);
		this.add(center, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		this.setSize(1280, 720);
	}
}
