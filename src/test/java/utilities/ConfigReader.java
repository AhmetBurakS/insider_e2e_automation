package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream stream = ConfigReader.class.getClassLoader()
                .getResourceAsStream("configuration.properties")) {

            if (stream == null) {
                throw new IllegalStateException("configuration.properties dosyası bulunamadı");
            }

            properties.load(stream);

        } catch (IOException e) {
            throw new RuntimeException("Configuration yüklenemedi", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
