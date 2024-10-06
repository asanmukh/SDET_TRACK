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
import java.util.Map;
import static Locators.HomePageLocators.*;
import static Locators.LoginPageLocators.*;
import org.openqa.selenium.interactions.Actions;

public class LoginPage {

    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void testValidLogin(String username, String password) {
        try {
            System.setProperty("jna.library.path", "/Users/asanmukh/Downloads/Tess4J/dist");
            ProcessBuilder processBuilder = new ProcessBuilder();
            Map<String, String> environment = processBuilder.environment();
            environment.put("TESSDATA_PREFIX", "/Users/asanmukh/Downloads/Tess4J/dist");

            System.out.println("Tesseracts path: " + System.getProperty("jna.library.path"));
            DriverFactory.get().findElement(accountList).click();
            DriverFactory.get().findElement(email).sendKeys(username);
            DriverFactory.get().findElement(continueButton).click();
            DriverFactory.get().findElement(passwordLocator).sendKeys(password);
            DriverFactory.get().findElement(signInButton).click();
            Thread.sleep(10000);
            // Check if the captcha is displayed
            List<WebElement> captchaElements = DriverFactory.get().findElements(captchaImage);
            if (!captchaElements.isEmpty() && DriverFactory.get().findElement(captchaImage).isDisplayed()) {
                System.out.println("Captcha found, solving...");

                // Solve the captcha
                File screenshot = ((TakesScreenshot) DriverFactory.get()).getScreenshotAs(OutputType.FILE);
                BufferedImage image = ImageIO.read(screenshot);

                Tesseract tesseract = new Tesseract();
                tesseract.setDatapath("/Users/asanmukh/Downloads/Tess4J/dist");
                tesseract.setLanguage("eng");
                String captchaText = tesseract.doOCR(image);

                DriverFactory.get().findElement(captchaInputBox).sendKeys(captchaText);
                DriverFactory.get().findElement(captchaContinueButton).click();

                // Wait for the captcha to be solved and the page to load
                Thread.sleep(5000);
            }

            // Check if the login is successful (search box is displayed)
            if (DriverFactory.get().findElement(searchBox).isDisplayed()) {
                System.out.println("Login successful, search box is displayed.");
            } else {
                System.out.println("Login failed, search box is not displayed.");
            }
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
            e.printStackTrace();
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

    public void testLogOutFunctionality() {
        try {
            WebElement accountListElement = DriverFactory.get().findElement(accountList);
            Actions actions = new Actions(driver);
            actions.moveToElement(accountListElement).perform();
            DriverFactory.get().findElement(logOutButton).isDisplayed();
            DriverFactory.get().findElement(logOutButton).click();
            DriverFactory.get().findElement(email).isDisplayed();
        } catch (Exception e) {
            throw new RuntimeException("User is not able to see the error message", e);
        }
    }
}

