package Pages;

import Utilities.DriverFactory;
import org.openqa.selenium.WebDriver;

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

}
