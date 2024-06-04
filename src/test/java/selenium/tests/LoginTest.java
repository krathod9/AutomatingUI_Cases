
package selenium.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import selenium.api.actions.CartApi;
import selenium.api.actions.SignUpApi;
import selenium.base.BaseTest;
import selenium.objects.Product;
import selenium.objects.UserInfo;
import selenium.pages.AccountPage;
import selenium.pages.CheckoutPage;
import selenium.utils.FakerUtils;

import java.io.IOException;

public class LoginTest extends BaseTest {
    @Test
    public void loginDuringCheckout() throws IOException, InterruptedException {
        String username="demouser"+new FakerUtils().generateRandomNumber();
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
    @Test
    public void unregisteredUserLoginOnAccountPage(){
        String username="demouser"+new FakerUtils().generateRandomNumber();
        UserInfo userInfo =new UserInfo().
                setUserID(username).
                setEmail(username+"@email.com").
                setPassword(username+"demopwd");

        AccountPage accountPage=new AccountPage(getDriver())
                .load()
                .userLogin(userInfo.getUserID(),userInfo.getPassword());

        Assert.assertTrue(accountPage.getMessage().contains("The username "+userInfo.getUserID()+" is not registered on this site. If you are unsure of your username, try your email address instead."));
    }

    @Test
    public void invalidPasswordOnAccountPage() throws IOException {
        String username="demouser"+new FakerUtils().generateRandomNumber();
        UserInfo userInfo =new UserInfo().
                setUserID(username).
                setEmail(username+"@email.com").
                setPassword(username+"demopwd");

        SignUpApi sapi=new SignUpApi();
        sapi.register(userInfo);

        AccountPage accountPage=new AccountPage(getDriver())
                .load()
                .userLogin(userInfo.getUserID(),"demopwd");

        Assert.assertTrue(accountPage.getMessage()
                .contains("The password you entered for the username "
                + userInfo.getUserID() + " is incorrect. Lost your password?"));

    }
}
