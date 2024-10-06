package Pages;

import Utilities.DriverFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static Locators.ElectronicsPageLocators.*;
import static Locators.HomePageLocators.*;
import static Locators.ShoppingCartPageLocators.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

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

    public void addItemFromBestsellersAndVerifyInCart() {
        DriverFactory.get().findElement(openAllCategoriesMenu).click();
        DriverFactory.get().findElement(bestSellers).click();
        DriverFactory.get().findElement(bestSellersInBagsWalletsAndLuggage).isDisplayed();
        DriverFactory.get().findElement(bestSellersNextPageButton).isDisplayed();
//        DriverFactory.get().findElement(bestSellersNextPageButton).click();

        WebElement nextPageButton = DriverFactory.get().findElement(bestSellersNextPageButton);
        Actions actions = new Actions(driver);
        actions.moveToElement(nextPageButton).click().perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement eighthItem = wait.until(ExpectedConditions.elementToBeClickable(eighthItemFromBagsBestSellers));
        eighthItem.click();

//        DriverFactory.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        DriverFactory.get().findElement(eighthItemFromBagsBestSellers).isDisplayed();
//        DriverFactory.get().findElement(eighthItemFromBagsBestSellers).click();
        WebElement eighthItemFromBestSeller = DriverFactory.get().findElement(eighthItemTitle);
        String selectedItemNameFromBestSellers = eighthItemFromBestSeller.getText().trim();
        System.out.println(selectedItemNameFromBestSellers);
        eighthItemFromBestSeller.click();
        DriverFactory.get().findElement(eighthItemTitle).isDisplayed();
        DriverFactory.get().findElement(addItemToCartButton).click();
        try {
            WebElement skipButton = DriverFactory.get().findElement(skipButtonToSkipAddOnOrder);
            if (skipButton.isDisplayed()) {
                skipButton.click();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Skip button not found");
        }
        Assert.assertTrue(DriverFactory.get().findElement(addedToCartSuccessMessage).isDisplayed());
        DriverFactory.get().findElement(shoppingCart).click();
        String cartItemName = DriverFactory.get().findElement(cartItemsList).getText().trim();
        System.out.println(cartItemName);
        Assert.assertEquals(selectedItemNameFromBestSellers, cartItemName);
    }
}
