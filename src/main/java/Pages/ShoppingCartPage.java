package Pages;

import Utilities.DriverFactory;
import Utilities.WebActions;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public void clickOnShoppingCart() {
        act.checkElementIsDisplayed(shoppingCart);
        act.doClick(shoppingCart);
    }

    public void verifyItemsInCart(String electronicsProduct1Name, String electronicsProduct2Name, String electronicsProduct3Name) {
        act.refreshPage();
        List<WebElement> cartItems = act.getListOfWebElements(cartItemsList);
        DriverFactory.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String[] expectedTexts = {electronicsProduct1Name, electronicsProduct2Name, electronicsProduct3Name};
        boolean[] found = new boolean[expectedTexts.length];
        for (WebElement item : cartItems) {
            System.out.println(item.getText());
            for (int i = 0; i < expectedTexts.length; i++) {
                if (item.getText().contains(expectedTexts[i])) {
                    found[i] = true;
                }
            }
        }
        Assert.assertTrue("Electronic Item 1 not found in cart", found[0]);
        Assert.assertTrue("Electronic 2 not found in cart", found[1]);
        Assert.assertTrue("Electronic 3 not found in cart", found[2]);
        System.out.println("Asserted items:");
        for (int i = 0; i < expectedTexts.length; i++) {
            System.out.println((i + 1) + ". " + expectedTexts[i]);
        }
    }

    public void deleteAddedItemsFromCart() {
        act.refreshPage();
        List<WebElement> cartItems = act.getListOfWebElements(cartItemsList);
        DriverFactory.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        for (int i = 0; i < cartItems.size(); i++) {
            try {
                WebElement deleteButton = driver.findElement(deleteAllItemsFromCart);
                deleteButton.click();
            } catch (Exception e) {
                System.out.println("Error clicking on delete button: " + e.getMessage());
            }
        }
        act.refreshPage();
        List<WebElement> cartItemsAfterDeletion = act.getListOfWebElements(cartItemsList);
        Assert.assertTrue("Cart is not empty after deletion", cartItemsAfterDeletion.isEmpty());
    }
}
