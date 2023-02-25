package waves;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ConfigLoader {
	public static List<String> readConfig() {
		Path path = Paths.get("src/config.txt") ;
		try {
			return Files.readAllLines(path);
		} catch (IOException e) {
			List<String> defaultConfig =  new ArrayList<String>();
			defaultConfig.add("1,0,1");
			return defaultConfig;
		}
	}
}
