import Pages.*;
import Utilities.DriverFactory;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {

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
        homePage.searchProduct("Gopro Hero12 Black - Waterproof Action Camera With 5.3K60 Ultra Hd Video, 27Mp Photos, Hdr, 1/1.9\" Image Sensor, Live Streaming, Webcam, Stabilization - Digital");
        electronicsPage.addItemToCart("Gopro Hero12 Black - Waterproof Action Camera With 5.3K60 Ultra Hd Video, 27Mp Photos, Hdr, 1/1.9\" Image Sensor, Live Streaming, Webcam, Stabilization - Digital");
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(DriverFactory.get());
        shoppingCartPage.clickOnShoppingCart();
        shoppingCartPage.verifyItemsInCart("Gopro Hero12 Black - Waterproof Action Camera With 5.3K60 Ultra Hd Video, 27Mp Photos, Hdr, 1/1.9\" Image Sensor, Live Streaming, Webcam, Stabilization - Digital", "Samsung Galaxy S20 FE 5G (Cloud Navy, 8GB RAM, 128GB Storage)", "Sony 139 cm (55 inches) BRAVIA 2 4K Ultra HD Smart LED Google TV K-55S25 (Black)");
    }

    @Test
    public void testSearchFunctionality() {
        LoginPage loginPage = new LoginPage(DriverFactory.get());
        loginPage.testValidLogin("akashms4all@gmail.com", "Aki@1717");
        ElectronicsPage electronicsPage = new ElectronicsPage(DriverFactory.get());
        electronicsPage.clickOnElectronics();
        HomePage homePage = new HomePage(DriverFactory.get());
        homePage.testSearchAndFilterFunctionality("Samsung");
        AccountProfilePage accountProfilePage = new AccountProfilePage(DriverFactory.get());
        accountProfilePage.addNewAddress("KARNATAKA");
        accountProfilePage.deleteAddress();
    }

    @Test
    public void testLogoutFunctionality() {
        LoginPage loginPage = new LoginPage(DriverFactory.get());
        loginPage.testValidLogin("akashms4all@gmail.com", "Aki@1717");
        loginPage.testLogOutFunctionality();
    }

    @Test
    public void testAddItemFromBestSellersSectionToCart() {
        LoginPage loginPage = new LoginPage(DriverFactory.get());
        loginPage.testValidLogin("akashms4all@gmail.com", "Aki@1717");
        HomePage homePage = new HomePage(DriverFactory.get());
        homePage.addItemFromBestsellersAndVerifyInCart();
    }
}
