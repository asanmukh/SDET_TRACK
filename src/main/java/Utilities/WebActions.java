package Utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WebActions {

    private WebDriverWait wait;
    private WebDriver driver;

    private int maxRetryCount = 3;

    public WebActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public List<WebElement> getListOfWebElements(By locator, String... msg) {
        List<WebElement> elements = new ArrayList<>();
        int retryCnt = 0;
        Exception ex = null;
        if (msg.length > 0)
            System.out.println(msg[0]);
        while (true) {
            try {
                if (retryCnt > maxRetryCount) {
                    try {
                        throw new RuntimeException("Unable to fetch list of web elements with locator: " + locator, ex);
                    } catch (RuntimeException fw) {
                        return Collections.emptyList();
                    }
                }
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
                elements = driver.findElements(locator);
                System.out.println("Successfully found web elements: " + locator);
                break;
            } catch (Exception e) {
                ex = e;
                retryCnt++;
            }
        }
        return elements;
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
