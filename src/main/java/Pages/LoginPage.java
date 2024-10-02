package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.time.Duration;

public class LoginPage {

    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void testValidLogin() {
        System.out.println("Valid login");
        // Login to the application
        String currentUrl = driver.getCurrentUrl();
        driver.findElement(By.xpath("")).sendKeys();
    }


}
