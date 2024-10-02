import Pages.LoginPage;
import Utilities.DriverFactory;
import org.testng.annotations.Test;

public class LoginPageTests extends TestBase {

    @Test
    public void loginTest() {
        LoginPage loginPage = new LoginPage(DriverFactory.get());
        loginPage.testValidLogin("akashms4all@gmail.com", "Aki@1717");
    }

    @Test
    public void invalidEmailLoginTest() {
        LoginPage loginPage = new LoginPage(DriverFactory.get());
        loginPage.testInvalidEmailLogin("23424545345");
    }

    @Test
    public void invalidPasswordLoginTest() {
        LoginPage loginPage = new LoginPage(DriverFactory.get());
        loginPage.testInvalidPasswordLogin("akashms4all@gmail.com","password123");
    }
}
