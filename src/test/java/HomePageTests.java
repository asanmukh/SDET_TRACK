import Pages.ElectronicsPage;
import Pages.HomePage;
import Pages.LoginPage;
import Utilities.DriverFactory;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase{

    @Test
    public void testScenario1() {
        LoginPage loginPage = new LoginPage(DriverFactory.get());
        loginPage.testValidLogin("akashms4all@gmail.com", "Aki@1717");
        HomePage homePage = new HomePage(DriverFactory.get());
        ElectronicsPage electronicsPage = new ElectronicsPage(DriverFactory.get());
        electronicsPage.clickOnElectronics();
        homePage.searchProduct("Sony 139 cm (55 inches) BRAVIA 2 4K Ultra HD Smart LED Google TV K-55S25 ");
        electronicsPage.addItemToCart("Sony 139 cm (55 inches) BRAVIA 2 4K Ultra HD Smart LED Google TV K-55S25 ");
    }
}
