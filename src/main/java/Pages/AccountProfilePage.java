package Pages;

import Utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;


import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static Locators.LoginPageLocators.*;
import static Locators.accountProfilePageLocators.*;

public class AccountProfilePage {

    public WebDriver driver;

    public AccountProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void addNewAddress(String chooseState) {
        DriverFactory.get().findElement(accountList).click();
        DriverFactory.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        DriverFactory.get().findElement(manageAddresses).isDisplayed();
        DriverFactory.get().findElement(manageAddresses).click();
        DriverFactory.get().findElement(addNewAddressButton).click();
        DriverFactory.get().findElement(addANewAddressPage).isDisplayed();
        DriverFactory.get().findElement(fullName).sendKeys("Akash Test");
        DriverFactory.get().findElement(mobileNumber).sendKeys("8888888888");
        DriverFactory.get().findElement(pinCode).sendKeys("560040");
        DriverFactory.get().findElement(addressLine1).sendKeys("1st A main road Govindraj nagar green building");
        DriverFactory.get().findElement(addressLine2).sendKeys("near mallige idly factory ");
        DriverFactory.get().findElement(landmark).sendKeys("Near Apollo Hospital");
//        DriverFactory.get().findElement(city).sendKeys("Bangalore");
        DriverFactory.get().findElement(state).click();
        DriverFactory.get().findElement(By.xpath("//*[@class='a-dropdown-link a-active' and contains(text(),'" + chooseState + "')]")).click();
        DriverFactory.get().findElement(changeFocus).click();

        WebElement element = DriverFactory.get().findElement(addAddressButton);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();

//        DriverFactory.get().findElement(addAddressButton).click();
        DriverFactory.get().findElement(addAddressSuccessMessage).isDisplayed();
        Assert.assertTrue(DriverFactory.get().findElement(verifyNewAddressFromYourAddress).isDisplayed());
    }

    public void deleteAddress() {
       DriverFactory.get().findElement(deleteAddedAddress).isDisplayed();
       DriverFactory.get().findElement(deleteAddedAddress).click();
       DriverFactory.get().findElement(deleteAddressModalConfirmYesButton).isDisplayed();

       DriverFactory.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//       WebElement element = DriverFactory.get().findElement(deleteAddressModalConfirmYesButton);
//       Actions actions = new Actions(driver);
//       actions.moveToElement(element).click().perform();

        WebElement element = DriverFactory.get().findElement(deleteAddressModalConfirmYesButton);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);

//       DriverFactory.get().findElement(deleteAddressModalConfirmYesButton).click();
       Assert.assertTrue(DriverFactory.get().findElement(addressDeletedSuccessMessage).isDisplayed());
    }
}
