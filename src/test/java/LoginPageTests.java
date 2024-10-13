import Pages.LoginPage;
import Utilities.DriverFactory;
import org.testng.annotations.Test;

public class LoginPageTests extends TestBase {


    /**
     * Verifies that a valid login can be performed.
     *
     * Logs in with a valid email and password and verifies that the login was successful.
     */
    @Test
    public void loginTest() {
        LoginPage loginPage = new LoginPage(DriverFactory.get());
        loginPage.testValidLogin("akashms4all@gmail.com", "Aki@1717");
    }

    /**
     * Verifies that an invalid email address is not accepted during login.
     *
     * Logs in with an invalid email address and verifies that the login was not successful.
     */
    @Test
    public void invalidEmailLoginTest() {
        LoginPage loginPage = new LoginPage(DriverFactory.get());
        loginPage.testInvalidEmailLogin("23424545345");
    }

    /**
     * Verifies that an invalid password is not accepted during login.
     *
     * Logs in with a valid email address and invalid password and verifies that the login was not successful.
     */
    @Test
    public void invalidPasswordLoginTest() {
        LoginPage loginPage = new LoginPage(DriverFactory.get());
        loginPage.testInvalidPasswordLogin("akashms4all@gmail.com","Aki@1616");
    }

    /**
     * Verifies that the log-out functionality is working as expected.
     *
     * Logs in with a valid email and password, then logs out and verifies that the email input box is displayed.
     */
    @Test
    public void logOutTest() {
        LoginPage loginPage = new LoginPage(DriverFactory.get());
        loginPage.testLogOutFunctionality();
    }
}
