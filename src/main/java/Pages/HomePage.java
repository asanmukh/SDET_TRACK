package Pages;

import Utilities.DriverFactory;
import Utilities.WebActions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static Locators.ElectronicsPageLocators.*;
import static Locators.HomePageLocators.*;
import static Locators.ShoppingCartPageLocators.*;

public class HomePage {

    public WebDriver driver;
    private final WebActions act;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.act = new WebActions(driver);
    }

    public void searchProduct(String anyProductName) {
        act.checkElementIsDisplayed(searchButton);
        act.doEnterText(searchTextBox, anyProductName);
        act.checkElementIsDisplayed(searchSubmitButton);
        act.doClick(searchSubmitButton);
        act.checkElementIsDisplayed(By.xpath("(//*[@value='" + anyProductName + "'])[1]"));
        act.doClick(searchSubmitButton);
    }

    public void testSearchAndFilterFunctionality(String samsung) {
        act.checkElementIsDisplayed(searchTextBox);
        act.doEnterText(searchTextBox, samsung);
        act.checkElementIsDisplayed(searchSubmitButton);
        act.doClick(searchSubmitButton);
        Assert.assertTrue(act.getTitle().contains("Samsung"));
        WebElement brandFilter = DriverFactory.get().findElement(filterBySamsungCheckbox);
        brandFilter.click();
        List<WebElement> itemTitles = act.getListOfWebElements(itemsDisplayedAfterBrandFilter);
        System.out.println("Number of item titles listed after brand filter is selected to Samsung: " + itemTitles.size());
        for (WebElement title : itemTitles) {
            String itemTitle = title.getText().toLowerCase();
            System.out.println("Item title: " + itemTitle);
            Assert.assertTrue("Item title does not contain 'samsung': " + itemTitle, itemTitle.contains("samsung"));
        }
    }

    public void addItemFromBestsellersAndVerifyInCart() {
        act.doClick(openAllCategoriesMenu);
        act.doClick(bestSellers);
        act.checkElementIsDisplayed(bestSellersInBagsWalletsAndLuggage);
        act.checkElementIsDisplayed(bestSellersNextPageButton);
        act.doClick(bestSellersNextPageButton);
        act.doActionsClick(bestSellersNextPageButton);
        act.doActionsClick(eighthItemFromBagsBestSellers);
        WebElement eighthItemFromBestSeller = DriverFactory.get().findElement(eighthItemTitle);
        String selectedItemNameFromBestSellers = eighthItemFromBestSeller.getText().trim();
        System.out.println(selectedItemNameFromBestSellers);
        eighthItemFromBestSeller.click();
        act.checkElementIsDisplayed(eighthItemTitle);
        act.doClick(addItemToCartButton);
        try {
            if (act.checkElementIsDisplayed(skipButtonToSkipAddOnOrder)) {
                act.doActionsClick(skipButtonToSkipAddOnOrder);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Skip button to skip an add-on order is not found");
        }
        Assert.assertTrue(act.checkElementIsDisplayed(addedToCartSuccessMessage));
        act.doJSClick(shoppingCart);
        String cartItemName = DriverFactory.get().findElement(cartItemsList).getText().trim();
        System.out.println(cartItemName);
        Assert.assertEquals(selectedItemNameFromBestSellers, cartItemName);
        act.doClick(deleteItemFromCart);
        act.doActionsClick(deleteItemFromCart);
        Assert.assertTrue(act.checkElementIsDisplayed(itemDeletedSuccessMessage));
    }
}
