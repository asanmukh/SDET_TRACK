import Pages.*;
import Utilities.DriverFactory;
import Utilities.JsonDataReader;
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
        String username = JsonDataReader.getTestData("loginPage", "username");
        String password = JsonDataReader.getTestData("loginPage", "password");
        String searchProduct1 = JsonDataReader.getTestData("homePage","searchProduct1");
        String searchProduct2 = JsonDataReader.getTestData("homePage","searchProduct2");
        String searchProduct3 = JsonDataReader.getTestData("homePage","searchProduct3");
        String searchProduct4 = JsonDataReader.getTestData("homePage","searchProduct4");
        String electronicsItem1 = JsonDataReader.getTestData("electronicsPage","electronicAddToCartItem1");
        String electronicsItem2 = JsonDataReader.getTestData("electronicsPage","electronicAddToCartItem2");
        String electronicsItem3 = JsonDataReader.getTestData("electronicsPage","electronicAddToCartItem3");
        String state = JsonDataReader.getTestData("accountProfilePage", "state");

        LoginPage loginPage = new LoginPage(DriverFactory.get());
        loginPage.testValidLogin(username,password);
        HomePage homePage = new HomePage(DriverFactory.get());
        ElectronicsPage electronicsPage = new ElectronicsPage(DriverFactory.get());
        electronicsPage.clickOnElectronics();
        homePage.searchProduct(searchProduct1);
        electronicsPage.addItemToCart(electronicsItem1);
        electronicsPage.clickOnMobilesAndAccessories();
        homePage.searchProduct(searchProduct2);
        electronicsPage.addItemToCart(electronicsItem2);
        electronicsPage.clickOnCameras();
        homePage.searchProduct(searchProduct3);
        electronicsPage.addItemToCart(electronicsItem3);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(DriverFactory.get());
        shoppingCartPage.clickOnShoppingCart();
        shoppingCartPage.verifyItemsInCart(searchProduct3, searchProduct2, searchProduct1);
        homePage.testSearchAndFilterFunctionality(searchProduct4);
        AccountProfilePage accountProfilePage = new AccountProfilePage(DriverFactory.get());
        accountProfilePage.addNewAddress(state);
        loginPage.testLogOutFunctionality();
//        accountProfilePage.deleteAddress();
//        shoppingCartPage.deleteAddedItemsFromCart();
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
        String username = JsonDataReader.getTestData("loginPage", "username");
        String password = JsonDataReader.getTestData("loginPage", "password");
        LoginPage loginPage = new LoginPage(DriverFactory.get());
        loginPage.testValidLogin(username,password);
        HomePage homePage = new HomePage(DriverFactory.get());
        homePage.addItemFromBestsellersAndVerifyInCart();
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
        String username = JsonDataReader.getTestData("loginPage", "username");
        String password = JsonDataReader.getTestData("loginPage", "password");
        String searchProduct4 = JsonDataReader.getTestData("homePage","searchProduct4");
        String state = JsonDataReader.getTestData("accountProfilePage", "state");
        LoginPage loginPage = new LoginPage(DriverFactory.get());
        loginPage.testValidLogin(username,password);
        ElectronicsPage electronicsPage = new ElectronicsPage(DriverFactory.get());
        electronicsPage.clickOnElectronics();
        HomePage homePage = new HomePage(DriverFactory.get());
        homePage.testSearchAndFilterFunctionality(searchProduct4);
        AccountProfilePage accountProfilePage = new AccountProfilePage(DriverFactory.get());
        accountProfilePage.addNewAddress(state);
        accountProfilePage.deleteAddress();
    }

    /**
     * Tests the logout functionality by verifying that the user is able to
     * click on the logout button and then see the email input box.
     */
    @Test
    public void testLogoutFunctionality() {
        String username = JsonDataReader.getTestData("loginPage", "username");
        String password = JsonDataReader.getTestData("loginPage", "password");
        LoginPage loginPage = new LoginPage(DriverFactory.get());
        loginPage.testValidLogin(username,password);
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
        String username = JsonDataReader.getTestData("loginPage", "username");
        String password = JsonDataReader.getTestData("loginPage", "password");
        LoginPage loginPage = new LoginPage(DriverFactory.get());
        loginPage.testValidLogin(username,password);
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
        String username = JsonDataReader.getTestData("loginPage", "username");
        String password = JsonDataReader.getTestData("loginPage", "password");
        LoginPage loginPage = new LoginPage(DriverFactory.get());
        loginPage.testValidLogin(username,password);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(DriverFactory.get());
        shoppingCartPage.clickOnShoppingCart();
        shoppingCartPage.deleteAddedItemsFromCart();
    }

    @Test
    public void deleteAddress() {
        String username = JsonDataReader.getTestData("loginPage", "username");
        String password = JsonDataReader.getTestData("loginPage", "password");
        String state = JsonDataReader.getTestData("accountProfilePage", "state");
        LoginPage loginPage = new LoginPage(DriverFactory.get());
        loginPage.testValidLogin(username,password);
        AccountProfilePage accountProfilePage = new AccountProfilePage(DriverFactory.get());
        accountProfilePage.addNewAddress(state);
        accountProfilePage.deleteAddress();
    }
}
