package selenium.tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.base.BaseTest;
import selenium.objects.Product;
import selenium.pages.HomePage;
import selenium.pages.ProductPage;
import selenium.pages.StorePage;

import java.io.IOException;
import java.time.Duration;

public class NavigationTest extends BaseTest {
    @Test
    public void navigateFromHomeToStorePage(){
        StorePage storePage = new HomePage(getDriver()).
                load().getHeaders().//composition
                clickStoreMenulink();
        WebDriverWait wait= new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("store"));
        Assert.assertEquals(storePage.getTitleText(),"Store");
    }

    @Test
    public void navigateFromStoreToProductPage() throws IOException {
        Product product=new Product(1215);
        ProductPage productPage= new StorePage(getDriver())
                .load()
                .navigateToProductPage(product.getId());

        Assert.assertEquals(productPage.getProductTitle(),product.getName());
    }

    @Test
    public void navigateFromHomePageToProductPage() throws IOException {
        Product product = new Product(1215);
        ProductPage productPage= new ProductPage(getDriver())
                .loadProductPage(product);

        Assert.assertEquals(productPage.getProductTitle(),product.getName());
    }
}
