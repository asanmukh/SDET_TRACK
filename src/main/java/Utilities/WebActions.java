package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebActions {

    private WebDriverWait wait;
    private WebDriver driver;

    private int maxRetryCount = 3;

    public WebActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void doClick(By locator) {
        int retryCount = 0;
        while (retryCount < maxRetryCount) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
                break;
            } catch (Exception e) {
                retryCount++;
            }
        }
    }

    public void doEnterText(By locator, String text) {
        int retryCount = 0;
        while (retryCount < maxRetryCount) {
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(locator)).sendKeys(text);
                break;
            } catch (Exception e) {
                retryCount++;
            }
        }
    }

    public String getBase64Screenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

    public boolean checkElementIsDisplayed(By locator) {
        int retryCount = 0;
        while (retryCount < maxRetryCount) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
                break;
            } catch (Exception e) {
                retryCount++;
            }
        }
        return false;
    }
}
