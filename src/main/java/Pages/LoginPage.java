package Pages;

import Utilities.DriverFactory;
import net.sourceforge.tess4j.Tesseract;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import static Locators.HomePageLocators.*;
import static Locators.LoginPageLocators.*;


public class LoginPage {

    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void testValidLogin(String username, String password) {
        try {
            DriverFactory.get().findElement(accountList).click();
            DriverFactory.get().findElement(email).sendKeys(username);
            DriverFactory.get().findElement(continueButton).click();
            DriverFactory.get().findElement(passwordLocator).sendKeys(password);
            DriverFactory.get().findElement(signInButton).click();
            Thread.sleep(10000);
            DriverFactory.get().findElement(searchBox).isDisplayed();
            if (DriverFactory.get().findElement(searchBox).isDisplayed()) {
                System.out.println("Login successful, search box is displayed.");
            }
            List<WebElement> captchaElements = DriverFactory.get().findElements(captchaImage);
            if (!captchaElements.isEmpty() && captchaElements.get(0).isDisplayed()) {
                File screenshot = ((TakesScreenshot) DriverFactory.get()).getScreenshotAs(OutputType.FILE);
                BufferedImage image = ImageIO.read(screenshot);

                Tesseract tesseract = new Tesseract();
                String captchaText = tesseract.doOCR(image);

                DriverFactory.get().findElement(captchaInputBox).sendKeys(captchaText);
                DriverFactory.get().findElement(captchaContinueButton).click();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to login", e);
        }
    }

    public void testInvalidEmailLogin(String username) {
        try {
            DriverFactory.get().findElement(accountList).click();
            DriverFactory.get().findElement(email).sendKeys(username);
            DriverFactory.get().findElement(continueButton).click();
            DriverFactory.get().findElement(authenticationErrorMessage).isDisplayed();
        } catch (Exception e) {
           throw new RuntimeException("User is not able to see the error message", e);
        }
    }

    public void testInvalidPasswordLogin(String username, String password) {
        try {
            DriverFactory.get().findElement(accountList).click();
            DriverFactory.get().findElement(email).sendKeys(username);
            DriverFactory.get().findElement(continueButton).click();
            DriverFactory.get().findElement(passwordLocator).sendKeys(password);
            DriverFactory.get().findElement(signInButton).click();
            DriverFactory.get().findElement(authenticationErrorMessage).isDisplayed();
        } catch (Exception e) {
           throw new RuntimeException("User is not able to see the error message", e);
        }
    }
}

