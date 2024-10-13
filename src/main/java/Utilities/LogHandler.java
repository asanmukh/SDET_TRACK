package Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHandler {

    private static final Logger log = LogManager.getLogger();

    /**
     * Logs the given message as an INFO message.
     *
     * @param message The message to be logged.
     */
    public static void info(String message) {
        log.info(message);
    }

    /**
     * Logs the given message as an ERROR message.
     *
     * @param message The message to be logged.
     */
    public static void error(String message) {
        log.error(message);
    }
}
