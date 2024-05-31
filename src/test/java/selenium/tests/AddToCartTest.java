package selenium.tests;

import org.testng.annotations.Test;
import selenium.base.BaseTest;
import selenium.objects.Product;
import selenium.pages.CartPage;
import selenium.pages.StorePage;

import java.io.IOException;

public class AddToCartTest extends BaseTest {
    @Test
    public void addItemToCart() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver()).
                load().
                search("Blue").
                addItemToCart(product.getName()).
                viewItemsFromCart();
    }
}
