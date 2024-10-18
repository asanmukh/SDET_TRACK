package Pages;

import Utilities.DriverFactory;
import Utilities.WebActions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static Locators.LoginPageLocators.accountList;
import static Locators.accountProfilePageLocators.*;

public class AccountProfilePage {

    private final WebActions act;
    public WebDriver driver;

    public AccountProfilePage(WebDriver driver) {
        this.driver = driver;
        this.act = new WebActions(driver);
    }

    /**
     * Adds a new address on the account profile page.
     *
     * @param chooseState The state name to be selected.
     */
    public void addNewAddress(String chooseState) {
        act.doClick(accountList);
        act.checkElementIsDisplayed(manageAddresses);
        act.doClick(manageAddresses);
        act.doClick(addNewAddressButton);
        act.checkElementIsDisplayed(addANewAddressPage);
        act.doEnterText(fullName, "Akash Test");
        act.doEnterText(mobileNumber, "8888888888");
        act.doEnterText(pinCode, "560040");
        act.doEnterText(addressLine1, "1st A main road Govindraj nagar green building");
        act.doEnterText(addressLine2, "near mallige idly factory ");
        act.doEnterText(landmark, "Near Apollo Hospital");
        act.doClick(state);
        WebElement stateName = DriverFactory.get().findElement(By.xpath("//*[@class='a-dropdown-link a-active' and contains(text(),'" + chooseState + "')]"));
        stateName.click();
        act.doClick(changeFocus);
        act.doActionsClick(addAddressButton);
        act.checkElementIsDisplayed(addAddressSuccessMessage);
        Assert.assertTrue(act.checkElementIsDisplayed(verifyNewAddressFromYourAddress));
    }

    /**
     * Deletes the newly added address from the account profile page.
     */
    public void deleteAddress() {
        act.checkElementIsDisplayed(deleteAddedAddress);
        act.doClick(deleteAddedAddress);
        act.checkElementIsDisplayed(deleteAddressModalConfirmYesButton);
        DriverFactory.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        act.doActionsClick(deleteAddressModalConfirmYesButton);
        Assert.assertTrue(act.checkElementIsDisplayed(addressDeletedSuccessMessage));
    }
}
