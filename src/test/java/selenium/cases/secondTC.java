package selenium.cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.pages.CartPage;
import selenium.pages.CheckoutPage;
import selenium.pages.HomePage;
import selenium.pages.StorePage;
import selenium.pom.base.BasePage;
import selenium.pom.base.BaseTest;

public class secondTC extends BaseTest {
    @Test
    public void guestLoginCheckoutDirectBankTransfer() throws InterruptedException {
        String [] details={"Rafael","Nadal","Rafael's Address in San Francisco","San Francisco","94188","rafael@test.com"};
        StorePage storePage = new HomePage(driver).
                load().
                clickStoreMenulink().
                search("Blue");

        Assert.assertEquals(storePage.getTitleText(),"Search results: “Blue”");
        storePage.addItemToCart("Blue Shoes");
        Thread.sleep(5000);
        CartPage cartPage=storePage.viewItemsFromCart();
        Assert.assertEquals(cartPage.getProductName(),"Blue Shoes");
        CheckoutPage checkoutPage=cartPage.checkoutCart();
        //checkoutPage.userLogin("testdemo","test123");
        checkoutPage.addShippingDetails(details);
        Thread.sleep(8000);
        Assert.assertEquals(checkoutPage.getOrderSuccessText(),"Thank you. Your order has been received.");
        System.out.println(checkoutPage.getOrderID());

    }
}
