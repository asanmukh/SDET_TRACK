package Pages;

import Utilities.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.Assert;
import java.time.Duration;
import java.util.List;

import static Locators.ShoppingCartPageLocators.*;

public class ShoppingCartPage {

    public WebDriver driver;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnShoppingCart() {
        DriverFactory.get().findElement(shoppingCart).isDisplayed();
        DriverFactory.get().findElement(shoppingCart).click();
    }

    public void verifyItemsInCart(String electronicsProduct1Name, String electronicsProduct2Name, String electronicsProduct3Name) {

        DriverFactory.get().navigate().refresh();

        List<WebElement> cartItems = driver.findElements(cartItemsList);

        DriverFactory.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        for (WebElement item : cartItems) {
            System.out.println(item.getText());
        }
        boolean electronicsItem1Found = false;
        boolean electronicsItem2Found = false;
        boolean electronicsItem3Found = false;
        for (WebElement item : cartItems) {
            System.out.println("Expected text: " + electronicsProduct1Name);
            System.out.println("Actual text: " + item.getText());
            if (item.getText().contains(electronicsProduct1Name)) {
                electronicsItem1Found = true;
            } else if (item.getText().contains(electronicsProduct2Name)) {
                electronicsItem2Found = true;
            } else if (item.getText().contains(electronicsProduct3Name)) {
                electronicsItem3Found = true;
            }
        }
        Assert.assertTrue("Electronic Item 1 not found in cart", electronicsItem1Found);
        Assert.assertTrue("Electronic 2 not found in cart", electronicsItem2Found);
        Assert.assertTrue("Electronic 3 not found in cart", electronicsItem3Found);
    }

}
