package Utilities;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void set(WebDriver Driver) {
        driver.set(Driver);
    }
}
