package waves;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import javax.swing.JFileChooser;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ConfigLoader {
	class Config {
		Wave[] waves;

		public Config(Wave[] waves) {
			super();
			this.waves = waves;
		}

		public ArrayList<Wave> getWavesList() {
			return new ArrayList<Wave>(Arrays.asList(this.waves));
		}

	}

	public static ArrayList<Wave> readConfig() {

		try {
			JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Wybierz plik");
            int result = chooser.showDialog(null, "Wybierz");   
            if (JFileChooser.APPROVE_OPTION == result){
            File inputFile = new File(chooser.getSelectedFile().toURI());
            Path path=Paths.get(inputFile.toURI());
			Gson g = new Gson();
			String raw = Files.readString(path);
			Config config = g.fromJson(raw, Config.class);
			return config.getWavesList();
            }
            else {
            	ArrayList<Wave> defaultConfig = new ArrayList<Wave>();
    			return defaultConfig;
            }
            	
           
		} catch (IOException | JsonSyntaxException e) {

			ArrayList<Wave> defaultConfig = new ArrayList<Wave>();
			return defaultConfig;
		}
	}
}
