package Pages;

import Utilities.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import static Locators.LoginPageLocators.*;


public class LoginPage {

    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void testValidLogin(String username, String password) {
        try {
            DriverFactory.get().findElement(By.id("nav-link-accountList")).click();
            DriverFactory.get().findElement(By.id("ap_email")).sendKeys(username);
            DriverFactory.get().findElement(By.id("continue")).click();
            DriverFactory.get().findElement(By.id("ap_password")).sendKeys(password);
            DriverFactory.get().findElement(By.id("signInSubmit")).click();
            DriverFactory.get().findElement(By.id("twotabsearchtextbox")).isDisplayed();
        } catch (Exception e) {
           throw new RuntimeException("Failed to login", e);
        }
    }

    public void testInvalidEmailLogin(String username) {
        try {
            DriverFactory.get().findElement(By.id("nav-link-accountList")).click();
            DriverFactory.get().findElement(By.id("ap_email")).sendKeys(username);
            DriverFactory.get().findElement(By.id("continue")).click();
            DriverFactory.get().findElement(By.id("auth-error-message-box")).isDisplayed();
        } catch (Exception e) {
           throw new RuntimeException("User is not able to see the error message", e);
        }
    }

    public void testInvalidPasswordLogin(String username, String password) {
        try {
            DriverFactory.get().findElement(accountList).click();
            DriverFactory.get().findElement(By.id("ap_email")).sendKeys(username);
            DriverFactory.get().findElement(By.id("continue")).click();
            DriverFactory.get().findElement(By.id("ap_password")).sendKeys(password);
            DriverFactory.get().findElement(By.id("signInSubmit")).click();
            DriverFactory.get().findElement(By.id("auth-error-message-box")).isDisplayed();
        } catch (Exception e) {
           throw new RuntimeException("User is not able to see the error message", e);
        }
    }
}

