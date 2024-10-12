package Pages;

import Utilities.DriverFactory;
import Utilities.WebActions;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static Locators.HomePageLocators.*;
import static Locators.LoginPageLocators.*;

public class LoginPage {

    public WebDriver driver;
    private final WebActions act;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.act = new WebActions(driver);
    }

    public void performLogin(String username, String password) {
        act.doClick(accountList);
        act.doEnterText(email, username);
        act.doClick(continueButton);
        act.doEnterText(passwordLocator, password);
        act.doClick(signInButton);
    }

    public void waitForSearchBoxToBeDisplayed() {
        act.checkElementIsDisplayed(searchBox);
    }

    public boolean isCaptchaPresent() {
        List<WebElement> captchaElements = act.getListOfWebElements(captchaImage);
        return !captchaElements.isEmpty() && act.checkElementIsDisplayed(captchaImage);
    }

    public static void screenshot() {
        try {
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
            BufferedImage screenshot = robot.createScreenCapture(screenRect);
            File file = new File("screenshot.png");
            if (file.exists()) {
                System.out.println("File already exists, deleting it...");
                if (!file.delete()) {
                    System.out.println("Error deleting file: " + file.getName());
                }
            }
            ImageIO.write(screenshot, "png", file);
        } catch (AWTException e) {
            System.out.println("Error creating robot: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error writing screenshot to file: " + e.getMessage());
            System.out.println("Unable to write screenshot to file. Please check file permissions.");
        }
    }

    public void solveCaptcha() {
        System.out.println("Captcha found, solving...");
        LoginPage.screenshot();
        File captchaScreenshot = ((TakesScreenshot) DriverFactory.get()).getScreenshotAs(OutputType.FILE);
        String captchaText = solveCaptcha(captchaScreenshot);
        act.doEnterText(captchaInputBox, captchaText);
        act.doClick(captchaContinueButton);
    }

    public void testValidLogin(String username, String password) {
        try {
            performLogin(username, password);
            if (isCaptchaPresent()) {
                solveCaptcha();
            }
            waitForSearchBoxToBeDisplayed();
            if (isSearchBoxDisplayed()) {
                System.out.println("Login successful, search box is displayed.");
            } else {
                System.out.println("Login failed, search box is not displayed.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to login", e);
        }
    }

    public boolean isSearchBoxDisplayed() {
        return act.checkElementIsDisplayed(searchBox);
    }

    public String solveCaptcha(File captchaScreenshot) {
        try {
            System.load("/opt/homebrew/lib/libtesseract.dylib");
            System.out.println(System.getProperty("java.library.path"));
            System.setProperty("java.library.path", "/usr/local/bin/tesseract");
            ITesseract tesseract = new Tesseract();
            tesseract.setDatapath("/opt/homebrew/opt/tesseract/share/tessdata");
            tesseract.setLanguage("eng");
            return tesseract.doOCR(captchaScreenshot);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to solve captcha", e);
        }
    }

    public void testInvalidEmailLogin(String username) {
        try {
            act.doClick(accountList);
            act.doEnterText(email, username);
            act.doClick(continueButton);
            if (isAuthenticationErrorMessageDisplayed()) {
                System.out.println("Authentication error message is displayed");
            } else {
                throw new RuntimeException("User is not able to see the error message");
            }
        } catch (Exception e) {
            throw new RuntimeException("User is not able to see the error message", e);
        }
    }

    public void testInvalidPasswordLogin(String username, String password) {
        try {
            act.doClick(accountList);
            act.doEnterText(email, username);
            act.doClick(continueButton);
            act.doEnterText(passwordLocator, password);
            act.doClick(signInButton);
            if (isAuthenticationErrorMessageDisplayed()) {
                System.out.println("Authentication error message is displayed");
            } else if (isCaptchaPresent()) {
                solveCaptcha();
            } else {
                throw new RuntimeException("User is not able to see the error message or captcha");
            }
        } catch (Exception e) {
            throw new RuntimeException("User is not able to see the error message", e);
        }
    }

    private boolean isAuthenticationErrorMessageDisplayed() {
        try {
            return act.checkElementIsDisplayed(authenticationErrorMessage);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void testLogOutFunctionality() {
        try {
            act.doMoveHoverToElement(accountList);
            act.checkElementIsDisplayed(logOutButton);
            act.doClick(logOutButton);
            act.checkElementIsDisplayed(email);
        } catch (Exception e) {
            throw new RuntimeException("User is not able to see the error message", e);
        }
    }
}

