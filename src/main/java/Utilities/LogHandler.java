package Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHandler {

    private static Logger log = LogManager.getLogger();

    public static void info(String message) {
        log.info(message);
    }

    public static void error(String message) {
        log.error(message);
    }
}
