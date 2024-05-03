package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.objects.BillingAddress;
import selenium.objects.Product;
import selenium.objects.UserInfo;
import selenium.pages.CartPage;
import selenium.pages.CheckoutPage;
import selenium.pages.HomePage;
import selenium.pages.StorePage;
import selenium.pom.base.BaseTest;
import selenium.utils.JacksonUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MySecondTestCase extends BaseTest {
    @Test
    public void guestCheckoutDirectBankTransferwithLogin() throws InterruptedException, IOException {
        String searchItem="Blue";
        BillingAddress billingAddress1= JacksonUtils.deserializeJson("myBillingDetails.json",BillingAddress.class);
        UserInfo userinfo=JacksonUtils.deserializeJson("userInfo.json",UserInfo.class);
        Product product=new Product(1215);

        StorePage storePage=new HomePage(driver).
                load().
                clickStoreMenulink().
                search(searchItem); //Builder Pattern
        Assert.assertEquals(storePage.getTitleText(),"Search results: “Blue”");

        storePage.addItemToCart(product.getName());
        Thread.sleep(5000);
        CartPage cartPage=storePage.viewItemsFromCart();
        Assert.assertEquals(cartPage.getProductName(),product.getName());

        CheckoutPage checkoutPage=cartPage.checkoutCart().userLogin(userinfo).
                enterBillingDetails(billingAddress1).
                placeOrder();
        Thread.sleep(8000);
        Assert.assertEquals(checkoutPage.getOrderSuccessText(),"Thank you. Your order has been received.");
        System.out.println(checkoutPage.getOrderID());
    }

}
