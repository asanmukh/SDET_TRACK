import Pages.LoginPage;
import Utilities.DriverFactory;
import org.testng.annotations.Test;

public class LoginPageTests extends TestBase {

    @Test
    public void loginTest() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.testValidLogin();
    }
}
