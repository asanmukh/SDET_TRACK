import org.testng.annotations.Test;
import Utilities.DriverFactory;
import Pages.AccountProfilePage;

public class AccountProfilePageTests extends TestBase{

    /**
     * Test that adds a new address with state as "KARNATAKA".
     */
    @Test
    public void addNewAddress() {
        AccountProfilePage accountProfilePage = new AccountProfilePage(DriverFactory.get());
        accountProfilePage.addNewAddress("KARNATAKA");
    }

    /**
     * Test that deletes the newly added address from the account profile page.
     */
    @Test
    public void deleteAddress() {
        AccountProfilePage accountProfilePage = new AccountProfilePage(DriverFactory.get());
        accountProfilePage.deleteAddress();
    }

}
