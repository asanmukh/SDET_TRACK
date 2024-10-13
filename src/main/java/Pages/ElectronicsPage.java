package Pages;

import Utilities.DriverFactory;
import Utilities.WebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static Locators.ElectronicsPageLocators.*;

public class ElectronicsPage {

    public WebDriver driver;
    private final WebActions act;
    public ElectronicsPage(WebDriver driver) {
        this.driver = driver;
        this.act = new WebActions(driver);
    }

    /**
     * Clicks on the 'Electronics' link at the top of the page.
     */
    public void clickOnElectronics() {
        DriverFactory.get().findElement(electronics).sendKeys(Keys.RETURN);
    }

    /**
     * Clicks on the 'Mobiles and Accessories' link under the 'Electronics' top level menu.
     */
    public void clickOnMobilesAndAccessories() {
        act.doClick(mobilesAndAccessories);
    }

    /**
     * Clicks on the 'Cameras' link under the 'Electronics' top level menu.
     */
    public void clickOnCameras() {
        act.doClick(cameras);
    }

    /**
     * Adds an item to the cart, given its name.
     * @param itemName the name of the item to add to the cart
     */
    public void addItemToCart(String itemName) {
        act.checkElementIsDisplayed(By.xpath("//*[@class='a-size-medium a-color-base a-text-normal' and contains(text(),'" + itemName + "')]"));
        act.doClick(By.xpath("(//*[text()='" + itemName + "'])[1]/parent::a/parent::h2/parent::div/following-sibling::div/div/div/div/div/div/div/span/div/span/span"));
    }

}
