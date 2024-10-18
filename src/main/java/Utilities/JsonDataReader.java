package Utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonDataReader {

    private static JsonNode jsonNode;

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonNode = objectMapper.readTree(new File("src/main/resources/testData.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the test data from the src/main/resources/testData.json file.
     * @param page  the page to retrieve test data for
     * @param key   the key of the test data to retrieve
     * @return      the test data retrieved from the JSON file
     */
    public static String getTestData(String page, String key) {
        return jsonNode.path(page).path(key).asText();
    }
}
