package selenium.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.api.actions.CartApi;
import selenium.api.actions.SignUpApi;
import selenium.base.BaseTest;
import selenium.objects.BillingAddress;
import selenium.objects.Product;
import selenium.objects.UserInfo;
import selenium.pages.CheckoutPage;
import selenium.utils.FakerUtils;
import selenium.utils.JacksonUtils;

import java.io.IOException;

public class CheckoutCartTest extends BaseTest {
    @Test
    public void guestCheckoutwithDirectBanktransfer() throws IOException {
        BillingAddress billingAddress1 = JacksonUtils.deserializeJson("myBillingDetails.json", BillingAddress.class);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();

        CartApi cartApi=new CartApi();
        cartApi.addToCart(1215,1);
        injectCookiesToBrowser(cartApi.getCookies());

        checkoutPage
                .load()
                .enterBillingDetails(billingAddress1)
                .selectDirectBankTransfer()
                .placeOrder();

        Assert.assertEquals(checkoutPage.getOrderSuccessText(),"Thank you. Your order has been received.");
        System.out.println(checkoutPage.getOrderID());

    }

    @Test
    public void loginAndCheckoutwithDirectBanktransfer() throws IOException, InterruptedException {
        BillingAddress billingAddress1 = JacksonUtils.deserializeJson("myBillingDetails.json", BillingAddress.class);
        String username="demouser"+new FakerUtils().generateRandongNumber();
        UserInfo userInfo =new UserInfo().
                setUserID(username).
                setEmail(username+"@email.com").
                setPassword(username+"demopwd");
        Product product=new Product(1215);

        SignUpApi signUpApi=new SignUpApi();
        signUpApi.register(userInfo);
        CartApi cartApi=new CartApi(signUpApi.getCookies());
        cartApi.addToCart(product.getId(),1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiesToBrowser(signUpApi.getCookies());
        checkoutPage.load();
        Thread.sleep(5000);

                checkoutPage.enterBillingDetails(billingAddress1)
                .selectDirectBankTransfer()
                .placeOrder();

        Assert.assertEquals(checkoutPage.getOrderSuccessText(),"Thank you. Your order has been received.");
        System.out.println(checkoutPage.getOrderID());
    }
}
