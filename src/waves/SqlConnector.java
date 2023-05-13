package waves;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class SqlConnector {

ArrayList<Wave> importedWaves;
ArrayList<String> soundNames;
	
	public SqlConnector() {
	
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://db4free.net/wavestemplate", "dyrabog", "Dajmito123");

			Statement statement = conn.createStatement();
			
			statement.execute("SELECT Id, name, frequency FROM dzwieki");
			
			
			ResultSet rs = statement.getResultSet();
			
			ResultSetMetaData md  = rs.getMetaData();
					
			importedWaves = new ArrayList();
			soundNames = new ArrayList();
			for (int ii = 1; ii <= md.getColumnCount(); ii++){
				System.out.print(md.getColumnName(ii)+ " | ");						
				
			}
			System.out.println();
			int tr;
			int j = 0;
			while (rs.next()) {
				
					//for (int ii = 1; ii <= md.getColumnCount(); ii++){
						tr =  rs.getInt("Id");
						importedWaves.add(new Wave(1,0,0));
						soundNames.add(rs.getString("name"));
						importedWaves.get(j).setFreq(rs.getDouble("frequency"));
						j++;
				
			}
			for(int jj = 0 ;jj<importedWaves.size(); jj++) {
				System.out.println(soundNames.get(jj) + " -> czestotliwosc:" + importedWaves.get(jj).getFreq() );

			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			if (conn!= null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		

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
	
	public String printParameters(int i) {
		String pars = "Amplituda -> " + importedWaves.get(i).getAmp() + ", czestotliwosc -> " 
				+ importedWaves.get(i).getFreq() + ", faza -> " + importedWaves.get(i).getPhase();
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
	


	}


