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
                System.out.println("Clicked on the element with locator: " + element);
                break;
            } catch (Exception e) {
                ex = e;
                retryCnt++;
            }
        }
    }

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
                System.out.println("Successfully scrolled the webpage to element with locator: " + element);
                break;
            } catch (NoSuchElementException e) {
                ex = e;
                retryCnt++;
            } catch (Exception e) {
                throw new RuntimeException("Unable to scroll the webpage to element with locator: " + element, e);
            }
        }
    }

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

    public String getBase64Screenshot() {
        ExtentFactory.log(Status.INFO, "Getting base64 screenshot");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

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
                ExtentFactory.log(Status.INFO, "Fetching list of web elements with locator: " + locator);
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
        ExtentFactory.log(Status.INFO, "Getting title");
        return driver.getTitle();
    }

    public void doMoveHoverToElement(By element) {
        ExtentFactory.log(Status.INFO, "Performing mouse hover and click on web element with locator: " + element);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(element)).perform();
    }

    public void refreshPage() {
        ExtentFactory.log(Status.INFO, "Refreshing the page");
        driver.navigate().refresh();
    }

    public void waitForElementToBeInvisible(WebElement element) {
        try {
            ExtentFactory.log(Status.INFO, "Waiting for element to be invisible");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (TimeoutException e) {
            System.out.println("Element is still visible after waiting for 10 seconds");
        }
    }
}
