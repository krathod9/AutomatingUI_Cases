package selenium.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.base.BaseTest;
import selenium.dataproviders.DataProviders;
import selenium.objects.Product;
import selenium.pages.CartPage;
import selenium.pages.HomePage;
import selenium.pages.ProductPage;
import selenium.pages.StorePage;
import selenium.utils.JacksonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AddToCartTest extends BaseTest {
    @Test(dataProvider = "getFeaturedProducts", dataProviderClass = DataProviders.class)
    public void addItemFromStorePageToCartPage(Product product) throws IOException {
        CartPage cartPage = new StorePage(getDriver()).
                load().
                getProductThumbnail().addItemToCart(product.getName()).
                viewItemsFromCart();
        Assert.assertTrue(cartPage.getProductNameFromCart(product).contains(product.getName()));

    }
    @Test
    public void addProductToCartFromProductPage() throws IOException {
        Product product = new Product(1215);
        ProductPage productPage= new ProductPage(getDriver())
                .loadProductPage(product)
                .addProuctToCart();
        Assert.assertTrue(productPage.getAlertText().contains("“"+product.getName()+"” has been added to your cart."));
        }
    @Test(dataProvider = "getFeaturedProducts", dataProviderClass = DataProviders.class)
    public void addFeaturedProductToCart(Product product) throws IOException {
        CartPage cartPage=new HomePage(getDriver())
                .load()
                .getProductThumbnail().addItemToCart(product.getName())
                .viewItemsFromCart();
        Assert.assertEquals(cartPage.getProductNameFromCart(product),product.getName());
    }

}
