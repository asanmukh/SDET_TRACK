package Locators;

import org.openqa.selenium.By;

public interface ShoppingCartPageLocators {

    By shoppingCart = By.xpath("//*[@id='nav-cart']");

    By shoppingCartItems = By.xpath("//div[@class='cart_item']");

    By checkoutButton = By.xpath("//a[@class='btn_action checkout_button']");

}
