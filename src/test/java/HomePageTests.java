import Pages.*;
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
        homePage.searchProduct("GoPro Hero12 Waterproof Digital Action Camera with Front&Rear LCD Screens,5.3K60 Ultra Hd Video,Hypersmooth 6.0+Autoboost,Live Streaming with Enduro Battery(1-Yr +1-Yr India Warranty),Black");
        electronicsPage.addItemToCart("GoPro Hero12 Waterproof Digital Action Camera with Front&Rear LCD Screens,5.3K60 Ultra Hd Video,Hypersmooth 6.0+Autoboost,Live Streaming with Enduro Battery(1-Yr +1-Yr India Warranty),Black");
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(DriverFactory.get());
        shoppingCartPage.clickOnShoppingCart();
        shoppingCartPage.verifyItemsInCart("GoPro Hero12 Waterproof Digital Action Camera with Front&Rear LCD Screens,5.3K60 Ultra Hd Video,Hypersmooth 6.0+Autoboost,Live Streaming with Enduro Battery(1-Yr +1-Yr India Warranty),Black","Samsung Galaxy S20 FE 5G (Cloud Navy, 8GB RAM, 128GB Storage)","Sony 139 cm (55 inches) BRAVIA 2 4K Ultra HD Smart LED Google TV K-55S25 (Black)");
    }

    @Test
    public void testSearchFunctionality() {
        LoginPage loginPage = new LoginPage(DriverFactory.get());
        loginPage.testValidLogin("akashms4all@gmail.com", "Aki@1717");
//        ElectronicsPage electronicsPage = new ElectronicsPage(DriverFactory.get());
//        electronicsPage.clickOnElectronics();
//        HomePage homePage = new HomePage(DriverFactory.get());
//        homePage.testSearchAndFilterFunctionality("Samsung");
        AccountProfilePage accountProfilePage = new AccountProfilePage(DriverFactory.get());
        accountProfilePage.addNewAddress("KARNATAKA");
        accountProfilePage.deleteAddress();
    }
}
