import Pages.*;
import Utilities.DriverFactory;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {

    /**
     * Tests the following scenario:
     * 1. Login to the application using valid credentials
     * 2. Search and add the following items to the cart
     *    - Sony 139 cm (55 inches) BRAVIA 2 4K Ultra HD Smart LED Google TV K-55S25 (Black)
     *    - Samsung Galaxy S20 FE 5G (Cloud Navy, 8GB RAM, 128GB Storage)
     *    - Gopro Hero12 Black - Waterproof Action Camera With 5.3K60 Ultra Hd Video, 27Mp Photos, Hdr, 1/1.9\" Image Sensor, Live Streaming, Webcam, Stabilization - Digital
     * 3. Verify all the items in cart
     * 4. Test search and filter functionality
     * 5. Add a new address and delete the same
     * 6. Delete all the items from the cart
     */
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
        homePage.testSearchAndFilterFunctionality("Samsung");
        AccountProfilePage accountProfilePage = new AccountProfilePage(DriverFactory.get());
        accountProfilePage.addNewAddress("KARNATAKA");
        accountProfilePage.deleteAddress();
        shoppingCartPage.deleteAddedItemsFromCart();
    }

    /**
     * Tests the following scenario:
     * 1. Login to the application using valid credentials
     * 2. Search and filter for Samsung products
     * 3. Verify items in cart
     * 4. Delete all the items from the cart
     */
    @Test
    public void testScenario2() {
        LoginPage loginPage = new LoginPage(DriverFactory.get());
        loginPage.testValidLogin("akashms4all@gmail.com", "Aki@1717");
        HomePage homePage = new HomePage(DriverFactory.get());
        homePage.testSearchAndFilterFunctionality("Samsung");
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(DriverFactory.get());
        shoppingCartPage.clickOnShoppingCart();
        shoppingCartPage.deleteAddedItemsFromCart();
    }

    /**
     * Tests the following scenario:
     * 1. Login to the application using valid credentials
     * 2. Search and filter for Samsung products
     * 3. Add a new address to account profile page
     * 4. Delete the newly added address from the account profile page
     */
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

    /**
     * Tests the logout functionality by verifying that the user is able to
     * click on the logout button and then see the email input box.
     */
    @Test
    public void testLogoutFunctionality() {
        LoginPage loginPage = new LoginPage(DriverFactory.get());
        loginPage.testValidLogin("akashms4all@gmail.com", "Aki@1717");
        loginPage.testLogOutFunctionality();
    }

    /**
     * Tests the following scenario:
     * 1. Logs in to the application using valid credentials
     * 2. Adds the eighth item from the bestseller page to cart
     * 3. Verifies that the item is added to cart
     * 4. Verifies that the item is deleted from the cart after clicking delete button
     */
    @Test
    public void testAddItemFromBestSellersSectionToCart() {
        LoginPage loginPage = new LoginPage(DriverFactory.get());
        loginPage.testValidLogin("akashms4all@gmail.com", "Aki@1717");
        HomePage homePage = new HomePage(DriverFactory.get());
        homePage.addItemFromBestsellersAndVerifyInCart();
    }

    /**
     * Tests the following scenario:
     * 1. Logs in to the application using valid credentials
     * 2. Delete all items from the cart
     */
    @Test
    public void deleteItemFromCart() {
        LoginPage loginPage = new LoginPage(DriverFactory.get());
        loginPage.testValidLogin("akashms4all@gmail.com", "Aki@1717");
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(DriverFactory.get());
        shoppingCartPage.clickOnShoppingCart();
        shoppingCartPage.deleteAddedItemsFromCart();
    }
}
