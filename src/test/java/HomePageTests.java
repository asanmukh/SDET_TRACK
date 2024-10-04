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
        homePage.searchProduct("Sony 139 cm (55 inches) BRAVIA 2 4K Ultra HD Smart LED Google TV K-55S25 (Black)");
        electronicsPage.addItemToCart("Sony 139 cm (55 inches) BRAVIA 2 4K Ultra HD Smart LED Google TV K-55S25 (Black)");
        electronicsPage.clickOnMobilesAndAccessories();
        homePage.searchProduct("Samsung Galaxy S20 FE 5G (Cloud Navy, 8GB RAM, 128GB Storage)");
        electronicsPage.addItemToCart("Samsung Galaxy S20 FE 5G (Cloud Navy, 8GB RAM, 128GB Storage)");
        electronicsPage.clickOnCameras();
        homePage.searchProduct("Canon EOS R6 Mark II 24.2 MP Mirrorless Camera-Body Only (Black)");
        electronicsPage.addItemToCart("Canon EOS R6 Mark II 24.2 MP Mirrorless Camera-Body Only (Black)");
    }
}
