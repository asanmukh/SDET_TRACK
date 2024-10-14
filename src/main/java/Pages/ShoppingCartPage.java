package Pages;

import Utilities.DriverFactory;
import Utilities.LogHandler;
import Utilities.WebActions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static Locators.ShoppingCartPageLocators.*;

public class ShoppingCartPage {

    public WebDriver driver;
    private final WebActions act;
    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        this.act = new WebActions(driver);
    }


    /**
     * Clicks on the "Shopping Cart" button in the top navigation bar.
     */
    public void clickOnShoppingCart() {
        act.checkElementIsDisplayed(shoppingCart);
        act.doClick(shoppingCart);
    }

    /**
     * Verifies that the three electronics products added in the test scenario are present in the shopping cart.
     * @param electronicsProduct1Name the name of the first electronics product
     * @param electronicsProduct2Name the name of the second electronics product
     * @param electronicsProduct3Name the name of the third electronics product
     */
    public void verifyItemsInCart(String electronicsProduct1Name, String electronicsProduct2Name, String electronicsProduct3Name) {
        act.refreshPage();
        List<WebElement> cartItems = act.getListOfWebElements(cartItemsList);
        DriverFactory.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String[] expectedTexts = {electronicsProduct1Name, electronicsProduct2Name, electronicsProduct3Name};
        boolean[] found = new boolean[expectedTexts.length];
        for (WebElement item : cartItems) {
            LogHandler.info(item.getText());
            for (int i = 0; i < expectedTexts.length; i++) {
                if (item.getText().contains(expectedTexts[i])) {
                    found[i] = true;
                }
            }
        }
        Assert.assertTrue("Electronic Item 1 not found in cart", found[0]);
        Assert.assertTrue("Electronic 2 not found in cart", found[1]);
        Assert.assertTrue("Electronic 3 not found in cart", found[2]);
        LogHandler.info("Asserted items:");
        for (int i = 0; i < expectedTexts.length; i++) {
            LogHandler.info((i + 1) + ". " + expectedTexts[i]);
        }
    }

    /**
     * Deletes all items added to the cart.
     */
    public void deleteAddedItemsFromCart() {
        act.refreshPage();
        List<WebElement> cartItems = act.getListOfWebElements(cartItemsList);
        DriverFactory.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        for (int i = 0; i < cartItems.size(); i++) {
            try {
                WebElement deleteButton = driver.findElement(By.xpath("(//*[@data-feature-id='delete']/span/input[@data-action='delete'])[" + (i + 1) + "]"));
                LogHandler.info("Clicking on delete button for item " + (i + 1));
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
                deleteButton.click();
                LogHandler.info("Delete button clicked for item " + (i + 1));
            } catch (Exception e) {
                LogHandler.info("Error clicking on delete button: " + e.getMessage());
            }
        }
        LogHandler.info("Waiting for cart items to be deleted...");
        act.refreshPage();
        List<WebElement> cartItemsAfterDeletion = act.getListOfWebElements(cartItemsList);
        Assert.assertTrue("Cart is not empty after deletion", cartItemsAfterDeletion.isEmpty());
    }
}
