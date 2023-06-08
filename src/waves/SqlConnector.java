package waves;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;


public class SqlConnector implements Runnable{

ArrayList<Wave> importedWaves;
ArrayList<Double> importedMediumVals;
ArrayList<String> importedMediumNames;
ArrayList<String> soundNames;
Connection conn;
	public SqlConnector() {
	
		conn = null;
		importedWaves = new ArrayList();
		soundNames = new ArrayList();
		importedMediumVals = new ArrayList();
		importedMediumNames = new ArrayList();
	}
	
	public int getWaveindex(String name) {
		int index = 0;
		
		for(String str : soundNames) {
			if(str == name)
				return index;
			else
				index ++;
		
		}
		return index;
	}
	public int getMediumindex(String name) {
		int index = 0;
		
		for(String str : importedMediumNames) {
			if(str == name)
				return index;
			else
				index ++;
		
		}
		return index;
	}
	
	public String printParameters(int i) {
		String pars = "Amplituda -> " + importedWaves.get(i).getAmp() + ", czestotliwosc -> " 
				+ importedWaves.get(i).getFreq() + ", faza -> " + importedWaves.get(i).getPhase();
		return pars;
	}
	
	public String printMediumParameters(int i) {
		String pars = "Cisnienie -> " + importedMediumVals.get(i);
		return pars;
	}
	

	public ArrayList<Wave> getImportedWaves() {
		return importedWaves;
	}

	public ArrayList<String> getSoundNames() {
		return soundNames;
	}
	
	double getFreq(int i) {
		return importedWaves.get(i).getFreq();
	}
	double getAmp(int i) {
		return importedWaves.get(i).getAmp();
	}
	double getPhase(int i) {
		return importedWaves.get(i).getPhase();
	}
	public ArrayList<Double> getMediumPressures(){
		return importedMediumVals;
	}
	public ArrayList<String> getMediumNames(){
		return importedMediumNames;
	}

	@Override
	public void run() {

		
		Menu.zListy.setEnabled(false);
	try {
		conn = DriverManager.getConnection("jdbc:mysql://db4free.net/wavestemplate", "dyrabog", "Dajmito123");
		Statement statement = conn.createStatement();
		statement.execute("SELECT Id, name, frequency FROM dzwieki");
		ResultSet rs = statement.getResultSet();
		ResultSetMetaData md  = rs.getMetaData();
		System.out.println();
		int tr;
		int j = 0;
		while (rs.next()) {
					tr =  rs.getInt("Id");
					importedWaves.add(new Wave(1,0,0));
					soundNames.add(rs.getString("name"));
					importedWaves.get(j).setFreq(rs.getDouble("frequency"));
					j++;
		}
		
		
	}
	catch (CommunicationsException e1) {
			  int dialogButton = JOptionPane.YES_NO_OPTION;
			  int dialogResult = JOptionPane.showConfirmDialog (null, "Brak internetu. Czy chcesz spróbować się połączyć jeszcze raz?","Uwaga",dialogButton);
			  if(dialogResult == JOptionPane.YES_OPTION){
			    MainFrame.rozwijane.exec.execute(MainFrame.rozwijane.soundsDatabase);
			  }
		} 
	catch (SQLException e) {
		e.printStackTrace();
	}
	
	finally {
		MainFrame.dialog.setVisible(true);
		if (conn!= null){
			try {
				conn.close();
				Menu.zListy.setEnabled(true);
				MainFrame.dialog.setVisible(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	MediumParameters.lista.setEnabled(false);
	try {
		conn = DriverManager.getConnection("jdbc:mysql://db4free.net/wavestemplate", "dyrabog", "Dajmito123");
		Statement statement = conn.createStatement();
		statement.execute("SELECT Id, NAME, PRESSURE FROM osrodki");
		ResultSet rs = statement.getResultSet();
		ResultSetMetaData md  = rs.getMetaData();
		
		int tr;
		int j = 0;
		while (rs.next()) {
					tr =  rs.getInt("Id");
					importedMediumNames.add(rs.getString("name"));
					importedMediumVals.add(rs.getDouble("pressure"));
					j++;
		}
		
		
	}
	catch (CommunicationsException e1) {
			  int dialogButton = JOptionPane.YES_NO_OPTION;
			  int dialogResult = JOptionPane.showConfirmDialog (null, "Brak internetu. Czy chcesz spróbować się połączyć jeszcze raz?","Uwaga",dialogButton);
		} 
	catch (SQLException e) {
		e.printStackTrace();
	}
	
	finally {
		if (conn!= null){
			try {
				conn.close();
				MediumParameters.lista.setEnabled(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	}



	}


