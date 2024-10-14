package Utilities;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class WebActions {

    private final WebDriverWait wait;
    private final WebDriver driver;
    private final int maxRetryCount = 3;
    private final JavascriptExecutor js;

    public WebActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.js = (JavascriptExecutor) driver;
    }

    /**
     * Clicks on the web element with the given locator.
     * If the element is not clickable after {@link #maxRetryCount} attempts, a RuntimeException is thrown.
     * @param locator the locator of the element to click.
     * @throws RuntimeException if the element is not clickable after max retries.
     */
    public void doClick(By locator) {
        int retryCount = 0;
        while (retryCount < maxRetryCount) {
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                if (element.isEnabled() && element.isDisplayed()) {
                    if (element.isDisplayed() && element.isEnabled()) {
                        ExtentFactory.log(Status.INFO, "Clicking on element with locator: " + locator);
                        element.click();
                        break;
                    } else {
                        throw new ElementNotInteractableException("Element is not visible or intractable");
                    }
                } else {
                    throw new ElementNotInteractableException("Element is not visible or intractable");
                }
            } catch (Exception e) {
                retryCount++;
                if (retryCount == maxRetryCount) {
                    throw new RuntimeException("Unable to click on element with locator: " + locator, e);
                }
            }
        }
    }

    /**
     * Clicks on the web element with the given locator using JavaScript.
     * If the element is not clickable after {@link #maxRetryCount} attempts, a RuntimeException is thrown.
     * @param element the locator of the element to click.
     * @param msg Optional message to log when attempting to click the element.
     * @throws RuntimeException if the element is not clickable after max retries.
     */
    public void doJSClick(By element, String... msg) {
        int retryCnt = 0;
        Exception ex = null;
        if (msg.length > 0)
            ExtentFactory.log(Status.INFO, msg[0]);
        while (true) {
            if (retryCnt > maxRetryCount)
                throw new RuntimeException("Unable to click the element with locator: " + element, ex);
            try {
                ExtentFactory.log(Status.INFO, "Clicking on the element with locator: " + element);
                wait.until(ExpectedConditions.elementToBeClickable(element));
                js.executeScript("arguments[0].click();", driver.findElement(element));
                LogHandler.info("Clicked on the element with locator: " + element);
                break;
            } catch (Exception e) {
                ex = e;
                retryCnt++;
            }
        }
    }

    /**
     * Scrolls the webpage to the element with the given locator.
     * If the element is not found after {@link #maxRetryCount} attempts, a RuntimeException is thrown.
     * @param element the locator of the element to scroll to.
     * @param msg Optional message to log when attempting to scroll to the element.
     * @throws RuntimeException if the element is not clickable after max retries.
     */
    public void scrollToElement(By element, String... msg) {
        int retryCnt = 0;
        Exception ex = null;
        if (msg.length > 0)
            ExtentFactory.log(Status.INFO, msg[0]);
        while (true) {
            if (retryCnt > maxRetryCount)
                throw new RuntimeException("Unable to scroll the webpage to element with locator: " + element, ex);
            try {
                ExtentFactory.log(Status.INFO, "Scrolling the webpage to element with locator: " + element);
                wait.until(ExpectedConditions.presenceOfElementLocated(element));
                js.executeScript(
                        "arguments[0].scrollIntoView({behavior: \"smooth\", block: \"end\", inline: \"nearest\"})",
                        driver.findElement(element));
                LogHandler.info("Successfully scrolled the webpage to element with locator: " + element);
                break;
            } catch (NoSuchElementException e) {
                ex = e;
                retryCnt++;
            } catch (Exception e) {
                throw new RuntimeException("Unable to scroll the webpage to element with locator: " + element, e);
            }
        }
    }

    /**
     * Performs a mouse hover and click on the web element with the given locator.
     * If the element is not clickable after {@link #maxRetryCount} attempts, a RuntimeException is thrown.
     * @param locator the locator of the element to perform mouse hover and click on.
     * @param msg Optional message to log when attempting to perform mouse hover and click on the element.
     * @throws RuntimeException if the element is not clickable after max retries.
     */
    public void doActionsClick(By locator, String... msg) {
        int retryCnt = 0;
        Exception ex = null;
        if (msg.length > 0)
            ExtentFactory.log(Status.INFO, msg[0]);
        Actions acts = new Actions(driver);
        while (true) {
            if (retryCnt > maxRetryCount)
                throw new RuntimeException("Unable to perform mouse hover and click on web element with locator: " + locator, ex);
            try {
                ExtentFactory.log(Status.INFO, "Performing mouse hover and click on web element with locator: " + locator);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                acts.moveToElement(element).click().build().perform();
                break;
            } catch (StaleElementReferenceException e) {
                retryCnt++;
            } catch (Exception e) {
                ex = e;
                retryCnt++;
            }
        }
    }

    /**
     * Enters the given text into the web element with the given locator.
     * If the element is not present after {@link #maxRetryCount} attempts, a RuntimeException is thrown.
     * @param locator the locator of the element to enter text into.
     * @param text the text to enter into the element.
     * @throws RuntimeException if the element is not present after max retries.
     */
    public void doEnterText(By locator, String text) {
        int retryCount = 0;
        while (retryCount < maxRetryCount) {
            try {
                ExtentFactory.log(Status.INFO, "Entering text into element with locator: " + locator + " with text: " + text);
                wait.until(ExpectedConditions.presenceOfElementLocated(locator)).sendKeys(text);
                break;
            } catch (Exception e) {
                retryCount++;
            }
        }
    }

    /**
     * Gets the base64 representation of the current screenshot.
     * @return the base64 representation of the current screenshot.
     */
    public String getBase64Screenshot() {
        ExtentFactory.log(Status.INFO, "Getting base64 screenshot");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

    /**
     * Checks if the element with the given locator is displayed.
     * @param locator the locator of the element to check.
     * @return true if the element is displayed, false otherwise.
     */
    public boolean checkElementIsDisplayed(By locator) {
        try {
            ExtentFactory.log(Status.INFO, "Checking if element is displayed with locator: " + locator);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Gets the list of web elements with the given locator.
     * If the elements are not present after {@link #maxRetryCount} attempts, an empty list is returned.
     * @param locator the locator of the elements to fetch.
     * @param msg Optional message to log when attempting to fetch the elements.
     * @return the list of web elements with the given locator.
     */
    public List<WebElement> getListOfWebElements(By locator, String... msg) {
        List<WebElement> elements = new ArrayList<>();
        int retryCnt = 0;
        Exception ex = null;
        if (msg.length > 0)
            LogHandler.info(msg[0]);
        while (true) {
            try {
                if (retryCnt > maxRetryCount) {
                    try {
                        throw new RuntimeException("Unable to fetch list of web elements with locator: " + locator, ex);
                    } catch (RuntimeException fw) {
                        return Collections.emptyList();
                    }
                }
                ExtentFactory.log(Status.INFO, "Fetching list of web elements with locator: " + locator);
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
                elements = driver.findElements(locator);
                LogHandler.info("Successfully found web elements: " + locator);
                break;
            } catch (Exception e) {
                ex = e;
                retryCnt++;
            }
        }
        return elements;
    }

    /**
     * Gets the title of the currently active page.
     * @return the title of the currently active page.
     */
    public String getTitle() {
        ExtentFactory.log(Status.INFO, "Getting title");
        return driver.getTitle();
    }

    /**
     * Performs a mouse hover over the web element with the given locator.
     * @param element the locator of the element to perform mouse hover on.
     */
    public void doMoveHoverToElement(By element) {
        ExtentFactory.log(Status.INFO, "Performing mouse hover and click on web element with locator: " + element);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(element)).perform();
    }

    /**
     * Refreshes the currently active page.
     */
    public void refreshPage() {
        ExtentFactory.log(Status.INFO, "Refreshing the page");
        driver.navigate().refresh();
    }

    /**
     * Waits for the given web element to become invisible.
     * @param element the web element to wait for.
     */
    public void waitForElementToBeInvisible(WebElement element) {
        try {
            ExtentFactory.log(Status.INFO, "Waiting for element to be invisible");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (TimeoutException e) {
            LogHandler.info("Element is still visible after waiting for 10 seconds");
        }
    }
}
