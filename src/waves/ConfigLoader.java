package waves;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
			Path path = Paths.get("config.json");
			Gson g = new Gson();
			String raw = Files.readString(path);
			
			Config config = g.fromJson(raw, Config.class);
			return config.getWavesList();
		} catch (IOException|JsonSyntaxException e) {

			ArrayList<Wave> defaultConfig = new ArrayList<Wave>();
			defaultConfig.add(new Wave(1, 0, 1));
			return defaultConfig;
		}
	}
}
