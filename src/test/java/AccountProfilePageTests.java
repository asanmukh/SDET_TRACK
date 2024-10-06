import org.testng.annotations.Test;
import Utilities.DriverFactory;
import Pages.AccountProfilePage;

public class AccountProfilePageTests extends TestBase{

    @Test
    public void addNewAddress() {
        AccountProfilePage accountProfilePage = new AccountProfilePage(DriverFactory.get());
        accountProfilePage.addNewAddress("KARNATAKA");
    }

    @Test
    public void deleteAddress() {
        AccountProfilePage accountProfilePage = new AccountProfilePage(DriverFactory.get());
        accountProfilePage.deleteAddress();
    }

}
