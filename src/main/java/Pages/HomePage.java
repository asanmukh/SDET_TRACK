package Pages;

import Utilities.DriverFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static Locators.ElectronicsPageLocators.*;
import static Locators.HomePageLocators.*;

public class HomePage {

    public WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchProduct(String anyProductName){
        DriverFactory.get().findElement(searchButton).isDisplayed();
        DriverFactory.get().findElement(searchTextBox).click();
        DriverFactory.get().findElement(searchTextBox).sendKeys(anyProductName);
        DriverFactory.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        DriverFactory.get().findElement(searchSubmitButton).isDisplayed();
        DriverFactory.get().findElement(searchSubmitButton).click();
        DriverFactory.get().findElement(By.xpath("(//*[@value='" + anyProductName + "'])[1]")).isDisplayed();
        DriverFactory.get().findElement(searchSubmitButton).submit();
    }

    public void testSearchAndFilterFunctionality(String samsung) {
        DriverFactory.get().findElement(searchTextBox).sendKeys("Samsung");
        DriverFactory.get().findElement(searchSubmitButton).click();
        DriverFactory.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertTrue(DriverFactory.get().getTitle().contains("Samsung"));
        WebElement brandFilter = DriverFactory.get().findElement(filterBySamsungCheckbox);
        brandFilter.click();
        DriverFactory.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<WebElement> itemTitles = DriverFactory.get().findElements(itemsDisplayedAfterBrandFilter);
        System.out.println("Number of item titles listed after brand filter is selected to Samsung: " + itemTitles.size());
        for (WebElement title : itemTitles) {
            String itemTitle = title.getText().toLowerCase();
            System.out.println("Item title: " + itemTitle);
            Assert.assertTrue("Item title does not contain 'samsung': " + itemTitle, itemTitle.contains("samsung"));
        }
    }
}
