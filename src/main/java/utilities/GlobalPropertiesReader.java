package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class GlobalPropertiesReader {

    public static String propertyLocation = "src/main/resources/global.properties";

    public static Properties pro = new Properties();

    static {
        try {
            pro.load(new FileInputStream(propertyLocation));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPropertyValue(String key) {
        return pro.getProperty(key);
    }
}
