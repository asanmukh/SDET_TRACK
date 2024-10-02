package Locators;

import org.openqa.selenium.By;

public interface LoginPageLocators {

    By accountList = By.id("nav-link-accountList");
    By email = By.id("ap_email");
    By password = By.id("ap_password");
    By signInButton = By.id("signInSubmit");
    By continueButton = By.id("continue");
    By authenticationErrorMessage = By.id("auth-error-message-box");

}
