package selenium.cases;

import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.objects.BillingAddress;
import selenium.objects.Product;
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
    public void guestCheckoutDirectBankTransfer() throws InterruptedException, IOException {
        //BillingAddress billing =new BillingAddress("Raj","Kothrapalli","St123",
                //"94188","San Francisco","raj@test.com");//passing the values in parameterized constructor
        BillingAddress billingAddress1= JacksonUtils.deserializeJson("myBillingDetails.json",BillingAddress.class);
        Product product=new Product(1215);

        StorePage storePage=new HomePage(driver).
                load().
                clickStoreMenulink().
                search("Blue"); //Builder Pattern
        Assert.assertEquals(storePage.getTitleText(),"Search results: “Blue”");
        storePage.addItemToCart(product.getName());
        Thread.sleep(5000);
        CartPage cartPage=storePage.viewItemsFromCart();
        Assert.assertEquals(cartPage.getProductName(),product.getName());
        CheckoutPage checkoutPage=cartPage.checkoutCart().
                enterBillingDetails(billingAddress1).
                placeOrder();
        Thread.sleep(8000);
        Assert.assertEquals(checkoutPage.getOrderSuccessText(),"Thank you. Your order has been received.");
        System.out.println(checkoutPage.getOrderID());
    }
}
//        HomePage homePage = new HomePage(driver).load();
//        StorePage storePage = homePage.clickStoreMenulink();        //this is fluent interface we couple homepage with store page
//        storePage.search("Blue");                               //Functional Page Object example
//        storePage.enterTextInSearchField("Blue").clickSearchButton();//this is builder patter
//        storePage.enterTextInSearchField("Blue")
//        storePage.clickSearchButton();
//        checkoutPage.enterFirstName("Roger").///replaced by data objects with POJO
//                enterLastName("Federrer").
//                enterBillingAddress("San Francisco Street 1").
//                enterBillingCity("San Francisco").
//                enterBillingPostCode("94188").
//                enterBillingEmail("rogertest@test.com").
//        BillingAddress billing=new BillingAddress().//setting data using builder pattern
//                setFistName("Ron").
//                setLastName("Mary").
//                setAddress("SF St1").
//                setCity("San Francisco").
//                setPostalCode("94188").
//                setEmail("ron@mary.com");
//