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

    By eighthItemFromBagsBestSellers = By.xpath("//*[@class='a-carousel-heading a-inline-block' and contains(text(),'Bestsellers in Bags, Wallets and Luggage')]/parent::div/parent::div/following-sibling::div//li[4]/div//span/div");

    By eighthItemTitle = By.xpath("//*[@id='productTitle' and contains(text(),'American Tourister Liftoff 79 Cms Large Check-in Polypropylene Hard Sided Double Spinner 4 Wheel Luggage')]");

    By addItemToCartButton = By.xpath("//*[@title='Add to Shopping Cart']");

    By skipButtonToSkipAddOnOrder = By.xpath("//*[@id='attachSiNoCoverage-announce']");

    By addedToCartSuccessMessage = By.xpath("//*[@role='status' and contains(text(),'Added to Cart')]");
}

