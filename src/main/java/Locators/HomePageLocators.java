package Locators;

import org.openqa.selenium.By;

public interface HomePageLocators {

    By searchBox = By.xpath("//input[@id='twotabsearchtextbox']");
    By searchButton = By.id("nav-search-submit-button");
    By searchSubmitButton = By.xpath("//input[@value='Go']");

}
