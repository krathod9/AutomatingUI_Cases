package selenium.cases;

import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.pages.CartPage;
import selenium.pages.CheckoutPage;
import selenium.pages.HomePage;
import selenium.pages.StorePage;
import selenium.pom.base.BaseTest;

public class firstTestCase extends BaseTest {
    @Test
    public void guestCheckoutDirectBankTransfer() throws InterruptedException {
//        HomePage homePage = new HomePage(driver).load();
//        StorePage storePage = homePage.clickStoreMenulink();        //this is fluent interface we couple homepage with store page
//        storePage.search("Blue");                               //Functional Page Object example
//        storePage.enterTextInSearchField("Blue").clickSearchButton();//this is builder patter
//        storePage.enterTextInSearchField("Blue")
//        storePage.clickSearchButton();
        StorePage storePage=new HomePage(driver).
                load().
                clickStoreMenulink().
                search("Blue"); //Builder Pattern
        Assert.assertEquals(storePage.getTitleText(),"Search results: “Blue”");
        storePage.addItemToCart("Blue Shoes");
        Thread.sleep(5000);
        CartPage cartPage=storePage.viewItemsFromCart();
        Assert.assertEquals(cartPage.getProductName(),"Blue Shoes");
        CheckoutPage checkoutPage=cartPage.checkoutCart();
        checkoutPage.enterFirstName("Roger").
                enterLastName("Federrer").
                enterBillingAddress("San Francisco Street 1").
                enterBillingCity("San Francisco").
                enterBillingPostCode("94188").
                enterBillingEmail("rogertest@test.com").
                placeOrder();
        Thread.sleep(8000);
        Assert.assertEquals(checkoutPage.getOrderSuccessText(),"Thank you. Your order has been received.");
        System.out.println(checkoutPage.getOrderID());
    }
}
