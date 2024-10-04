import Pages.ElectronicsPage;
import Pages.HomePage;
import Utilities.DriverFactory;
import org.testng.annotations.Test;

public class ElectronicsPageTests {

    @Test
    public void addItemToCart(String itemName) {
        ElectronicsPage electronicsPage = new ElectronicsPage(DriverFactory.get());
        electronicsPage.addItemToCart(itemName);
    }
}
