package Pages;

import Utilities.DriverFactory;
import net.sourceforge.tess4j.Tesseract;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import net.sourceforge.tess4j.*;
import java.io.File;
import java.time.Duration;
import java.util.List;
import static Locators.HomePageLocators.searchBox;
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

            // Wait for the page to load
            WebDriverWait wait = new WebDriverWait(DriverFactory.get(), Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));

            // Check if the captcha is displayed
            List<WebElement> captchaElements = DriverFactory.get().findElements(captchaImage);
            if (!captchaElements.isEmpty() && DriverFactory.get().findElement(captchaImage).isDisplayed()) {
                System.out.println("Captcha found, solving...");
                File captchaScreenshot = ((TakesScreenshot) DriverFactory.get()).getScreenshotAs(OutputType.FILE);
                String captchaText = solveCaptcha(captchaScreenshot);
                DriverFactory.get().findElement(captchaInputBox).sendKeys(captchaText);
                DriverFactory.get().findElement(captchaContinueButton).click();

                // Wait for the captcha to be solved and the page to load
                wait = new WebDriverWait(DriverFactory.get(), Duration.ofSeconds(5));
                wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
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

    public String solveCaptcha(File captchaScreenshot) {
        try {
            // Solve the CAPTCHA using Tesseracts
            ITesseract tesseract = new Tesseract();
            tesseract.setDatapath("/opt/homebrew/opt/tesseract/share/tessdata");
            tesseract.setLanguage("eng");
            // Perform OCR on the captcha screenshot
            String captchaText = tesseract.doOCR(captchaScreenshot);
            return captchaText;
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to solve captcha", e);
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

            // Check if the authentication error message is displayed
            try {
                DriverFactory.get().findElement(authenticationErrorMessage).isDisplayed();
            } catch (NoSuchElementException e) {
                // If the error message is not displayed, check if the captcha is displayed
                List<WebElement> captchaElements = DriverFactory.get().findElements(captchaImage);
                if (!captchaElements.isEmpty() && DriverFactory.get().findElement(captchaImage).isDisplayed()) {
                    System.out.println("Captcha found, solving...");
                    File captchaScreenshot = ((TakesScreenshot) DriverFactory.get()).getScreenshotAs(OutputType.FILE);
                    String captchaText = solveCaptcha(captchaScreenshot);
                    DriverFactory.get().findElement(captchaInputBox).sendKeys(captchaText);
                    DriverFactory.get().findElement(captchaContinueButton).click();
                } else {
                    throw new RuntimeException("User is not able to see the error message or captcha", e);
                }
            }
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

