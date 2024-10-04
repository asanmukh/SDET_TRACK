package Pages;

import Utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static Locators.HomePageLocators.*;

public class HomePage {

    public WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchProduct(String anyProductName){
        DriverFactory.get().findElement(searchButton).isDisplayed();
        DriverFactory.get().findElement(searchButton).click();
        DriverFactory.get().findElement(searchButton).sendKeys(anyProductName);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        DriverFactory.get().findElement(By.xpath("//*[@value='" + anyProductName + "']")).isDisplayed();
        DriverFactory.get().findElement(searchSubmitButton).isDisplayed();
        DriverFactory.get().findElement(searchSubmitButton).submit();
    }

}
