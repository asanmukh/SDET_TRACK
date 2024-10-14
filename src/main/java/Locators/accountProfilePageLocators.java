package Locators;

import org.openqa.selenium.By;

public interface accountProfilePageLocators {

    By manageAddresses = By.xpath("//*[@data-card-identifier='AddressesAnd1Click']");
    By addNewAddressButton = By.xpath("//*[text()='Add address']");
    By addANewAddressPage = By.xpath("//*[text()='Add a new address']");
    By fullName = By.xpath("//*[@id='address-ui-widgets-enterAddressFullName']");
    By mobileNumber = By.xpath("//*[@id='address-ui-widgets-enterAddressPhoneNumber']");
    By pinCode = By.xpath("//*[@id='address-ui-widgets-enterAddressPostalCode']");
    By addressLine1 = By.xpath("//*[@id='address-ui-widgets-enterAddressLine1']");
    By addressLine2 = By.xpath("//*[@id='address-ui-widgets-enterAddressLine2']");
    By landmark = By.xpath("//*[@id='address-ui-widgets-landmark']");
    By state = By.xpath("//*[@id='address-ui-widgets-enterAddressStateOrRegion']");
    By addAddressButton = By.xpath("//*[text()='Add address']");
    By addAddressSuccessMessage = By.xpath("//*[text()='Address saved']");
    By verifyNewAddressFromYourAddress = By.xpath("(//*[@id='address-ui-widgets-FullName' and contains(text(),'Akash Test')])[1]");
    By deleteAddedAddress = By.xpath("//*[text()=('Akash Test')]/parent::span/parent::li/parent::ul/parent::div/parent::div/parent::div/parent::div/following-sibling::div/span/a[@id='ya-myab-address-delete-btn-2']");
    By deleteAddressModalConfirmYesButton = By.xpath("//*[@id='deleteAddressModal-2-submit-btn']//input[@type='submit']");
    By addressDeletedSuccessMessage = By.xpath("//*[text()='Address deleted']");
    By changeFocus = By.xpath("//*[text()='Add a new address']");

}
