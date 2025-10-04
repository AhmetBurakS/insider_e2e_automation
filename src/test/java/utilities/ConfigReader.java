package utilities;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        try {
            properties = new Properties();
            InputStream stream = ConfigReader.class.getClassLoader()
                    .getResourceAsStream("configuration.properties");
            properties.load(stream);
            stream.close();
        } catch (Exception e) {
            throw new RuntimeException("Configuration yüklenemedi", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
