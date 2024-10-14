package Pages;

import Utilities.DriverFactory;
import Utilities.LogHandler;
import Utilities.WebActions;
import org.junit.Assert;
import org.openqa.selenium.*;

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

    /**
     * This method performs a search for a given product name on the HomePage,
     * and validates that the expected product is displayed
     * @param anyProductName the name of the product to search for
     */
    public void searchProduct(String anyProductName) {
        act.checkElementIsDisplayed(searchButton);
        act.doEnterText(searchTextBox, anyProductName);
        act.checkElementIsDisplayed(searchSubmitButton);
        act.doClick(searchSubmitButton);
        act.checkElementIsDisplayed(By.xpath("(//*[@value='" + anyProductName + "'])[1]"));
        act.doClick(searchSubmitButton);
    }

    /**
     * This method tests the search functionality by searching for a product name and then selecting the brand filter.
     * It then verifies that all items displayed have the brand name in their title.
     * @param samsung the name of the product to search for
     */
    public void testSearchAndFilterFunctionality(String samsung) {
        act.checkElementIsDisplayed(searchTextBox);
        act.doEnterText(searchTextBox, samsung);
        act.checkElementIsDisplayed(searchSubmitButton);
        act.doClick(searchSubmitButton);
        Assert.assertTrue(act.getTitle().contains("Samsung"));
        WebElement brandFilter = DriverFactory.get().findElement(filterBySamsungCheckbox);
        brandFilter.click();
        List<WebElement> itemTitles = act.getListOfWebElements(itemsDisplayedAfterBrandFilter);
        LogHandler.info("Number of item titles listed after brand filter is selected to Samsung: " + itemTitles.size());
        for (WebElement title : itemTitles) {
            String itemTitle = title.getText().toLowerCase();
            LogHandler.info("Item title: " + itemTitle);
            Assert.assertTrue("Item title does not contain 'samsung': " + itemTitle, itemTitle.contains("samsung"));
        }
    }

    /**
     * This method adds the eighth item from the bestseller page to the cart,
     * and verifies that the item is added to the cart
     * It also verifies that the item is deleted from the cart after clicking the deleted button
     */
    public void addItemFromBestsellersAndVerifyInCart() {
        act.doActionsClick(openAllCategoriesMenu);
        act.doActionsClick(bestSellers);
        act.checkElementIsDisplayed(bagsWalletsAndLuggage);
        act.doActionsClick(bagsWalletsAndLuggage);
        act.checkElementIsDisplayed(eighthItemFromBagsBestSellers);
        WebElement eighthItemTitle = DriverFactory.get().findElement(eighthItemFromBagsBestSellers);
        String selectedItemNameFromBestSellers = eighthItemTitle.getText().trim();
        LogHandler.info(selectedItemNameFromBestSellers);
        eighthItemTitle.click();
        act.doActionsClick(bestSellerrsCartButton);
        try {
            if (act.checkElementIsDisplayed(skipButtonToSkipAddOnOrder)) {
                act.doActionsClick(skipButtonToSkipAddOnOrder);
            }
        } catch (NoSuchElementException e) {
            LogHandler.info("Skip button to skip an add-on order is not found");
        }
        Assert.assertTrue(act.checkElementIsDisplayed(addedToCartSuccessMessage));
        act.doClick(goToCartButton);
        act.doActionsClick(shoppingCart);
        String cartItemName = DriverFactory.get().findElement(cartItemsList).getText().trim();
        LogHandler.info(cartItemName);
        Assert.assertEquals(selectedItemNameFromBestSellers, cartItemName);
        act.doClick(deleteItemFromCart);
//        act.doActionsClick(deleteItemFromCart);
        Assert.assertTrue(act.checkElementIsDisplayed(itemDeletedSuccessMessage));
    }
}
