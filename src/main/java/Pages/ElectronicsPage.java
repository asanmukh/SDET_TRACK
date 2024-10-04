package Pages;

import Utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static Locators.ElectronicsPageLocators.*;
public class ElectronicsPage {

    public WebDriver driver;

    public ElectronicsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnElectronics() {
        DriverFactory.get().findElement(electronics).click();
    }

    public void addItemToCart(String itemName) {
        DriverFactory.get().findElement(By.xpath("//*[@class='a-size-medium a-color-base a-text-normal' and text()='" + itemName + "']")).isDisplayed();
        DriverFactory.get().findElement(By.xpath("(//*[text()='" + itemName + "'])[1]/parent::a/parent::h2/parent::div/following-sibling::div/div/div/div/div/div/div/span/div/span/span")).click();
    }
}
