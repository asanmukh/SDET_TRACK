import Pages.ShoppingCartPage;
import Utilities.DriverFactory;
import org.testng.annotations.Test;

public class ShoppingCartPageTests extends TestBase {

    /**
     * Tests that clicking on the "Shopping Cart" button in the top navigation bar takes user to the shopping cart page.
     */
    @Test
    public void clickOnShoppingCart() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(DriverFactory.get());
        shoppingCartPage.clickOnShoppingCart();
    }

    /**
     * Verifies that the given electronics products are present in the shopping cart.
     * @param electronicsProduct1Name the name of the first electronics product
     * @param electronicsProduct2Name the name of the second electronics product
     * @param electronicsProduct3Name the name of the third electronics product
     */
    @Test
   public void verifyItemsInCart(String electronicsProduct1Name, String electronicsProduct2Name, String electronicsProduct3Name) {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(DriverFactory.get());
        shoppingCartPage.verifyItemsInCart(electronicsProduct1Name, electronicsProduct2Name, electronicsProduct3Name);
    }
}
