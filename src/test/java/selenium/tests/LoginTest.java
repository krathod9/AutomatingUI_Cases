
package selenium.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import selenium.api.actions.CartApi;
import selenium.api.actions.SignUpApi;
import selenium.base.BaseTest;
import selenium.objects.Product;
import selenium.objects.UserInfo;
import selenium.pages.CheckoutPage;
import selenium.utils.FakerUtils;

import java.io.IOException;

public class LoginTest extends BaseTest {
    @Test
    public void loginDuringCheckout() throws IOException, InterruptedException {
        String username="demouser"+new FakerUtils().generateRandongNumber();
        UserInfo userInfo =new UserInfo().
                setUserID(username).
                setEmail(username+"@email.com").
                setPassword(username+"demopwd");
        Product product=new Product(1215);

        SignUpApi sapi=new SignUpApi();
        sapi.register(userInfo);
        CartApi cartApi=new CartApi();
        cartApi.addToCart(product.getId(),1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage.load();
        checkoutPage.userLogin(userInfo);//.clickLoginButton(); this method is already called in userLogin()

        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));

    }
}
