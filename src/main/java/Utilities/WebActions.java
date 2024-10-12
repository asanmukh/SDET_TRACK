package Utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static java.rmi.server.LogStream.log;

public class WebActions {

    private final WebDriverWait wait;
    private final WebDriver driver;
    private final int maxRetryCount = 3;
    private JavascriptExecutor js;

    public WebActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void doClick(By locator) {
        int retryCount = 0;
        while (retryCount < maxRetryCount) {
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                if (element.isEnabled() && element.isDisplayed()) {
                    if (element.isDisplayed() && element.isEnabled()) {
                        element.click();
                        break;
                    } else {
                        throw new ElementNotInteractableException("Element is not visible or interactable");
                    }
                } else {
                    throw new ElementNotInteractableException("Element is not visible or interactable");
                }
            } catch (Exception e) {
                retryCount++;
                if (retryCount == maxRetryCount) {
                    throw new RuntimeException("Unable to click on element with locator: " + locator, e);
                }
            }
        }
    }

    public void doJSClick(By element, String... msg) {
        int retryCnt = 0;
        Exception ex = null;
        if (msg.length > 0)
            System.out.println(log(msg[0]));
        while (true) {
            if (retryCnt > maxRetryCount)
                throw new RuntimeException("Unable to click the element with locator: " + element, ex);
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element));
                js.executeScript("arguments[0].click();", driver.findElement(element));
                System.out.println("Clicked on the element with locator: " + element);
                break;
            } catch (Exception e) {
                ex = e;
                retryCnt++;
            }
        }
    }

    public void doActionsClick(By locator, String... msg) {
        int retryCnt = 0;
        Exception ex = null;
        if (msg.length > 0)
            System.out.println(log(msg[0]));
        Actions acts = new Actions(driver);
        while (true) {
            if (retryCnt > maxRetryCount)
                throw new RuntimeException("Unable to perform mouse hover and click on web element with locator: " + locator, ex);
            try {
                WebElement element = driver.findElement(locator);
                if (element.isDisplayed() && element.isEnabled()) {
                    acts.moveToElement(element).click().build().perform();
                    break;
                } else {
                    throw new NoSuchElementException("Element not visible or interactable");
                }
            } catch (Exception e) {
                ex = e;
                retryCnt++;
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

    public void doMoveHoverToElement(By element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(element)).perform();
    }
}
