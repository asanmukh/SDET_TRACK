import Pages.ElectronicsPage;
import Utilities.DriverFactory;
import org.testng.annotations.Test;

public class ElectronicsPageTests extends TestBase {

    @Test
    public void addItemToCart(String itemName) {
        ElectronicsPage electronicsPage = new ElectronicsPage(DriverFactory.get());
        electronicsPage.addItemToCart(itemName);
    }

    @Test
    public void clickOnElectronics() {
        ElectronicsPage electronicsPage = new ElectronicsPage(DriverFactory.get());
        electronicsPage.clickOnElectronics();
    }

    @Test
    public void clickOnMobilesAndAccessories() {
        ElectronicsPage electronicsPage = new ElectronicsPage(DriverFactory.get());
        electronicsPage.clickOnMobilesAndAccessories();
    }

    @Test
    public void clickOnCameras() {
        ElectronicsPage electronicsPage = new ElectronicsPage(DriverFactory.get());
        electronicsPage.clickOnCameras();
    }
}
