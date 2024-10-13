package Locators;

import org.openqa.selenium.By;

public interface HomePageLocators {

    By searchBox = By.xpath("//input[@id='twotabsearchtextbox']");
    By searchButton = By.id("nav-search-submit-button");
    By searchTextBox = By.xpath("//input[@id='twotabsearchtextbox']");
    By searchSubmitButton = By.xpath("//input[@value='Go']");
    By openAllCategoriesMenu = By.xpath("//*[@id='nav-hamburger-menu']");
    By bestSellers = By.xpath("(//*[@class='hmenu-item' and contains(text(),'Best Sellers')])[1]");
    By bestSellersInBagsWalletsAndLuggage = By.xpath("//*[text()='Bestsellers in Bags, Wallets and Luggage']");
    By bestSellersNextPageButton = By.xpath("//*[text()='Bestsellers in Bags, Wallets and Luggage']/parent::div/parent::div/following-sibling::div/div/div/a/span/i[@class='a-icon a-icon-next']");
    By eighthItemFromBagsBestSellers = By.xpath("//*[text()='Bestsellers in Bags, Wallets and Luggage']/parent::div/parent::div//div[@id='gridItemRoot']/div[@id='p13n-asin-index-7']/div//div/a/span/div");
    By eighthItemTitle = By.xpath("//*[@id='productTitle' and contains(text(),'American Tourister Liftoff')]");
    By addItemToCartButton = By.xpath("//*[@title='Add to Shopping Cart']");
    By skipButtonToSkipAddOnOrder = By.xpath("//*[@id='attachSiNoCoverage-announce']");
    By addedToCartSuccessMessage = By.xpath("//*[@class='a-size-medium-plus a-color-base sw-atc-text a-text-bold' and contains(text(),'Added to Cart')]");
    By bagsWalletsAndLuggage =By.xpath("//*[text()='Bags, Wallets and Luggage']");
}

