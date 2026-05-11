package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	
	private static Properties properties = new Properties();

    static {
        try {
            // Load config.properties from classpath
            InputStream input = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException("config.properties not found in resources folder!");
            }

            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file: " + e.getMessage());
        }
    }

    // Fetch property by key
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
