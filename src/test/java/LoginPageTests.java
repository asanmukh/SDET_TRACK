import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class LoginPageTests extends TestBase {

    public WebDriver driver;
    @Test
    public void loginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.testValidLogin();
    }
}
