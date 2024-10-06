package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class GlobalPropertiesReader {

    public static String propertyLocation = "src/main/resources/global.properties";

    public static Properties prop = new Properties();

    static {
        try {
            prop.load(new FileInputStream(propertyLocation));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPropertyValue(String key) {
        String value = prop.getProperty(key,"");
        return value;
    }
}
