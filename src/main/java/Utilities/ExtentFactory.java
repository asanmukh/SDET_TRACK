package Utilities;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentFactory {

    public static ThreadLocal <ExtentTest> extent = new ThreadLocal<>();

    public static void set(ExtentTest test) {
        extent.set(test);
    }

    public static ExtentTest get() {
        return extent.get();
    }

    public static void log(Status s, String message) {
        get().info(message);
        if (s == Status.FAIL) {
            LogHandler.error(message);
        }
        else
            LogHandler.info(message);
    }
}
