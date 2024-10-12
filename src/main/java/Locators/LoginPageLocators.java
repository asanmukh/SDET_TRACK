package Locators;

import org.openqa.selenium.By;

public interface LoginPageLocators {

    By accountList = By.id("nav-link-accountList");
    By email = By.id("ap_email");
    By passwordLocator = By.id("ap_password");
    By signInButton = By.id("signInSubmit");
    By continueButton = By.id("continue");
    By authenticationErrorMessage = By.id("auth-error-message-box");
    By captchaImage = By.xpath("//img[@alt='captcha']");
    By captchaInputBox = By.xpath("//*[@name='cvf_captcha_input']");
    By captchaContinueButton = By.xpath("//*[@name='cvf_captcha_captcha_action']");
    By logOutButton = By.xpath("//a[@id='nav-item-signout']");

}
