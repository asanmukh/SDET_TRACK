import Pages.ShoppingCartPage;
import Utilities.DriverFactory;
import org.testng.annotations.Test;

public class ShoppingCartPageTests {

    @Test
    public void clickOnShoppingCart() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(DriverFactory.get());
        shoppingCartPage.clickOnShoppingCart();
    }
}
