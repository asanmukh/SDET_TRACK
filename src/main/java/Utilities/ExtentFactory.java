package Utilities;

import com.aventstack.extentreports.ExtentTest;

public class ExtentFactory {

    public static ThreadLocal <ExtentTest> extent = new ThreadLocal<>();

    public static void set(ExtentTest test) {
        extent.set(test);
    }

    public static ExtentTest get() {
        return extent.get();
    }
}
