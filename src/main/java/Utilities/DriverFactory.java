package Utilities;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Gets the current WebDriver thread-local instance.
     *
     * @return the current WebDriver thread-local instance
     */
    public static WebDriver get() {
        return driver.get();
    }

    /**
     * Sets the current WebDriver thread-local instance.
     *
     * @param Driver the WebDriver to set for the current thread
     */
    public static void set(WebDriver Driver) {
        driver.set(Driver);
    }
}
