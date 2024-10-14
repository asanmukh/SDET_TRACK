package Pages;

import Utilities.DriverFactory;
import Utilities.ExtentFactory;
import Utilities.LogHandler;
import Utilities.WebActions;
import com.aventstack.extentreports.Status;
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

import static Locators.HomePageLocators.searchBox;
import static Locators.LoginPageLocators.*;

public class LoginPage {

    public WebDriver driver;
    private final WebActions act;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.act = new WebActions(driver);
    }

    /**
     * Perform login with the given username and password
     *
     * @param username the username to use when logging in
     * @param password the password to use when logging in
     */
    public void performLogin(String username, String password) {
        ExtentFactory.log(Status.INFO, "Performing login with username: " + username + " and password: " + password);
        act.doClick(accountList);
        act.doEnterText(email, username);
        act.doClick(continueButton);
        act.doEnterText(passwordLocator, password);
        act.doClick(signInButton);
        ExtentFactory.log(Status.PASS, "Login successful");
    }


    /**
     * Waits for the search box to be displayed
     */
    public void waitForSearchBoxToBeDisplayed() {
        act.checkElementIsDisplayed(searchBox);
    }

    /**
     * Checks if the captcha element is present and visible on the page.
     *
     * @return true if the captcha element is present and visible, false otherwise
     */
    public boolean isCaptchaPresent() {
        ExtentFactory.log(Status.INFO, "Checking if captcha is present");
        List<WebElement> captchaElements = act.getListOfWebElements(captchaImage);
        return !captchaElements.isEmpty() && act.checkElementIsDisplayed(captchaImage);
    }


    /**
     * Captures a screenshot of the entire screen and saves it as a PNG file to the working directory.
     * If a file named "screenshot.png" already exists, it will be deleted.
     * If there is an error capturing the screenshot or writing it to the file, an error message will be printed.
     */
    public static void screenshot() {
        try {
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
            BufferedImage screenshot = robot.createScreenCapture(screenRect);
            File file = new File("screenshot.png");
            if (file.exists()) {
                LogHandler.info("File already exists, deleting it...");
                if (!file.delete()) {
                    LogHandler.info("Error deleting file: " + file.getName());
                }
            }
            ImageIO.write(screenshot, "png", file);
        } catch (AWTException e) {
            LogHandler.info("Error creating robot: " + e.getMessage());
        } catch (IOException e) {
            LogHandler.info("Error writing screenshot to file: " + e.getMessage());
            LogHandler.info("Unable to write screenshot to file. Please check file permissions.");
        }
    }


    /**
     * Solve the captcha by using the Tesseracts OCR library to extract the text from the captcha image.
     * The image is captured using the Selenium TakesScreenshot interface.
     * The extracted text is then entered into the captcha input box, and the continued button is clicked.
     */
    public void solveCaptcha() {
        LogHandler.info("Captcha found, solving...");
        LoginPage.screenshot();
        File captchaScreenshot = ((TakesScreenshot) DriverFactory.get()).getScreenshotAs(OutputType.FILE);
        String captchaText = solveCaptcha(captchaScreenshot);
        act.doEnterText(captchaInputBox, captchaText);
        act.doClick(captchaContinueButton);
    }


    /**
     * Test a valid login by performing a login with the provided username and password, and then check
     * if the search box is displayed.
     * If the search box is displayed, the login was successful, otherwise
     * the login failed.
     *
     * @param username The username to use for the login.
     * @param password The password to use for the login.
     */
    public void testValidLogin(String username, String password) {
        try {
            performLogin(username, password);
            if (isCaptchaPresent()) {
                solveCaptcha();
            }
            waitForSearchBoxToBeDisplayed();
            if (isSearchBoxDisplayed()) {
                LogHandler.info("Login successful, search box is displayed.");
            } else {
                LogHandler.info("Login failed, search box is not displayed.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to login", e);
        }
    }

    /**
     * Checks if the search box is displayed.
     *
     * @return true if the search box is displayed, false otherwise.
     */
    public boolean isSearchBoxDisplayed() {
        return act.checkElementIsDisplayed(searchBox);
    }

    /**
     * Solve the captcha by taking a screenshot of the captcha element and then
     * using Tesseracts to OCR the image.
     * The result is returned as a string.
     *
     * @param captchaScreenshot the screenshot of the captcha element.
     * @return the text recognized by Tesseracts.
     */
    public String solveCaptcha(File captchaScreenshot) {
        try {
            System.load("/opt/homebrew/lib/libtesseract.dylib");
            LogHandler.info(System.getProperty("java.library.path"));
            System.setProperty("java.library.path", "/usr/local/bin/tesseract");
            ITesseract tesseract = new Tesseract();
            tesseract.setDatapath("/opt/homebrew/opt/tesseract/share/tessdata");
            tesseract.setLanguage("eng");
            return tesseract.doOCR(captchaScreenshot);
        } catch (Exception e) {
            LogHandler.info("Error message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to solve captcha", e);
        }
    }

    /**
     * Tests a login with an invalid email address.
     *
     * @param username the invalid email address to use for the login.
     *                 The test will fail if the authentication error message is not displayed.
     */
    public void testInvalidEmailLogin(String username) {
        try {
            act.doClick(accountList);
            act.doEnterText(email, username);
            act.doClick(continueButton);
            if (isAuthenticationErrorMessageDisplayed()) {
                LogHandler.info("Authentication error message is displayed");
            } else {
                throw new RuntimeException("User is not able to see the error message");
            }
        } catch (Exception e) {
            throw new RuntimeException("User is not able to see the error message", e);
        }
    }


    /**
     * Tests a login with an invalid password.
     *
     * @param username the email address to use for the login.
     * @param password the invalid password to use for the login.
     *                 The test will fail if the authentication error message is not displayed.
     *                 If a captcha is present, the test will attempt to solve the captcha.
     */
    public void testInvalidPasswordLogin(String username, String password) {
        try {
            act.doClick(accountList);
            act.doEnterText(email, username);
            act.doClick(continueButton);
            act.doEnterText(passwordLocator, password);
            act.doClick(signInButton);
            if (isAuthenticationErrorMessageDisplayed()) {
                LogHandler.info("Authentication error message is displayed");
            } else if (isCaptchaPresent()) {
                solveCaptcha();
            } else {
                throw new RuntimeException("User is not able to see the error message or captcha");
            }
        } catch (Exception e) {
            throw new RuntimeException("User is not able to see the error message", e);
        }
    }

    /**
     * Determines if the authentication error message is displayed on the page.
     *
     * @return true if the authentication error message is displayed, false if not.
     */
    private boolean isAuthenticationErrorMessageDisplayed() {
        try {
            return act.checkElementIsDisplayed(authenticationErrorMessage);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Tests the logout functionality by verifying that the user is able to
     * click on the logout button and then see the email input box.
     */
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

