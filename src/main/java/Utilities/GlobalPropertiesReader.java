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


    /**
     * Get the value of the specified property key from the global properties file.
     *
     * @param key the key of the property to retrieve
     * @return the value of the specified property, or an empty string if the key is not found in the properties file
     */
    public static String getPropertyValue(String key) {
        return prop.getProperty(key, "");
    }
}
