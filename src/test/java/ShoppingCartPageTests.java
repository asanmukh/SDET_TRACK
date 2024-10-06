import Pages.ShoppingCartPage;
import Utilities.DriverFactory;
import org.testng.annotations.Test;

public class ShoppingCartPageTests extends TestBase {

    @Test
    public void clickOnShoppingCart() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(DriverFactory.get());
        shoppingCartPage.clickOnShoppingCart();
    }

    @Test
   public void verifyItemsInCart(String electronicsProduct1Name, String electronicsProduct2Name, String electronicsProduct3Name) {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(DriverFactory.get());
        shoppingCartPage.verifyItemsInCart(electronicsProduct1Name, electronicsProduct2Name, electronicsProduct3Name);
    }
}
