package Utilities;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentFactory {

    public static ThreadLocal <ExtentTest> extent = new ThreadLocal<>();

    /**
     * Set the current {@link ExtentTest} thread-local instance.
     * @param test the {@link ExtentTest} to set
     */
    public static void set(ExtentTest test) {
        extent.set(test);
    }

    /**
     * Get the current {@link ExtentTest} thread-local instance.
     * @return the current {@link ExtentTest}
     */
    public static ExtentTest get() {
        return extent.get();
    }

    /**
     * Log the given message and status to the current {@link ExtentTest},
     * and also log it to the console with the given status.
     * @param s the status to log
     * @param message the message to log
     */
    public static void log(Status s, String message) {
        get().info(message);
        if (s == Status.FAIL) {
            LogHandler.error(message);
        }
        else
            LogHandler.info(message);
    }
}
