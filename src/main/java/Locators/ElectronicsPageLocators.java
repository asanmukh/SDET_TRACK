package Locators;

import org.openqa.selenium.By;

public interface ElectronicsPageLocators {

    By electronics = By.xpath("//a[@class='nav-a  ' and text()=' Electronics ']");

    By mobilesAndAccessories = By.xpath("//*[@class='nav-a-content' and contains(text(),'Mobiles & Accessories')]");

    By cameras = By.xpath("//*[@class='nav-a-content' and contains(text(),'Camera')]");
}
