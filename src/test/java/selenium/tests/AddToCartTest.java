package selenium.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.base.BaseTest;
import selenium.objects.Product;
import selenium.pages.CartPage;
import selenium.pages.HomePage;
import selenium.pages.ProductPage;
import selenium.pages.StorePage;

import java.io.IOException;

public class AddToCartTest extends BaseTest {
    @Test
    public void addItemToCart() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver()).
                load().
                searchWithpartialMatch("Blue").
                addItemToCart(product.getName()).
                viewItemsFromCart();
    }
    @Test
    public void addFeaturedProductToCart() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage=new HomePage(getDriver())
                .load()
                .addFeaturedProductToCart(product.getId())
                .viewItemsFromCart();
        Assert.assertEquals(cartPage.getProductNameFromCart(product),"Blue Shoes");
    }

    @Test
    public void addProductToCartFromProductPage() throws IOException {
        Product product = new Product(1215);
        ProductPage productPage= new HomePage(getDriver())
                .loadProductPage(product)
                .addProuctToCart();
        Assert.assertTrue(productPage.getAlertText().contains("“"+product.getName()+"” has been added to your cart."));
        }
}
