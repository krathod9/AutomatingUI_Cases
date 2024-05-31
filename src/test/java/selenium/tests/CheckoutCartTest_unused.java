package selenium.tests;

import selenium.base.BaseTest;
import selenium.objects.BillingAddress;
import selenium.pages.CheckoutPage;
import selenium.utils.JacksonUtils;

import java.io.IOException;

public class CheckoutCartTest_unused extends BaseTest {
    //@Test //This wont work as checkout page redirects to cart page and cart will be empty everytime
    public void checkoutCart() throws IOException {
        BillingAddress billingAddress1 = JacksonUtils.deserializeJson("myBillingDetails.json", BillingAddress.class);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
                checkoutPage.load().
                enterBillingDetails(billingAddress1).
                selectDirectBankTransfer().
                placeOrder();
    }
}
