package Locators;

import org.openqa.selenium.By;

public interface ShoppingCartPageLocators {

    By shoppingCart = By.xpath("//*[@id='nav-cart']");
    By cartItemsList = By.xpath("//div[@id='sc-active-cart']/div/form/div/div/div/div/div/ul/li/span/a/span/h4/span");

    By deleteItemFromCart = By.xpath("(//*[@data-feature-id='delete'])[1]");

    By itemDeletedSuccessMessage = By.xpath("//*[text()=' was removed from Shopping Cart. ']");


}
