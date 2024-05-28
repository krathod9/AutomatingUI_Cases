package selenium.tests;

import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
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
import java.io.InputStream;

public class firstTestCase extends BaseTest {
    @Test
    public void guestCheckoutDirectBankTransfer() throws IOException {
        BillingAddress billingAddress1 = JacksonUtils.deserializeJson("myBillingDetails.json", BillingAddress.class);
        Product product = new Product(1215);
        Actions actions = new Actions(driver);
        StorePage storePage = new HomePage(driver).
                load().
                clickStoreMenulink().
                search("Blue"); //Builder Pattern
        Assert.assertEquals(storePage.getTitleText(), "Search results: “Blue”");
        storePage.addItemToCart(product.getName());

        CartPage cartPage = storePage.viewItemsFromCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        CheckoutPage checkoutPage = cartPage.checkoutCart().
                enterBillingDetails(billingAddress1).
                selectDirectBankTransfer().
                placeOrder();

        Assert.assertEquals(checkoutPage.getOrderSuccessText(), "Thank you. Your order has been received.");
        System.out.println(checkoutPage.getOrderID());
    }

//    @Test
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

        CartPage cartPage=storePage.viewItemsFromCart();
        Assert.assertEquals(cartPage.getProductName(),product.getName());

        CheckoutPage checkoutPage=cartPage.checkoutCart().userLogin(userinfo).
                enterBillingDetails(billingAddress1).
                placeOrder();

        Assert.assertEquals(checkoutPage.getOrderSuccessText(),"Thank you. Your order has been received.");
        System.out.println(checkoutPage.getOrderID());
    }
}