import Pages.ElectronicsPage;
import Utilities.DriverFactory;
import org.testng.annotations.Test;

public class ElectronicsPageTests extends TestBase {

    /**
     * Adds an item to the cart, given its name.
     * @param itemName the name of the item to add to the cart
     */
    @Test
    public void addItemToCart(String itemName) {
        ElectronicsPage electronicsPage = new ElectronicsPage(DriverFactory.get());
        electronicsPage.addItemToCart(itemName);
    }

    /**
     * Verifies that the electronics link is clickable.
     */
    @Test
    public void clickOnElectronics() {
        ElectronicsPage electronicsPage = new ElectronicsPage(DriverFactory.get());
        electronicsPage.clickOnElectronics();
    }

    /**
     * Clicks on the 'Mobiles and Accessories' link under the 'Electronics' top level menu.
     */
    @Test
    public void clickOnMobilesAndAccessories() {
        ElectronicsPage electronicsPage = new ElectronicsPage(DriverFactory.get());
        electronicsPage.clickOnMobilesAndAccessories();
    }

    /**
     * Clicks on the 'Cameras' link under the 'Electronics' top level menu.
     */
    @Test
    public void clickOnCameras() {
        ElectronicsPage electronicsPage = new ElectronicsPage(DriverFactory.get());
        electronicsPage.clickOnCameras();
    }
}
