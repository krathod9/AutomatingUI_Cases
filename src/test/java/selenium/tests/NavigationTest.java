package selenium.tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.base.BaseTest;
import selenium.pages.HomePage;
import selenium.pages.StorePage;

import java.time.Duration;

public class NavigationTest extends BaseTest {
    @Test
    public void navigateFromHomeToStorePage(){
        StorePage storePage = new HomePage(getDriver()).
                load().
                clickStoreMenulink();
        WebDriverWait wait= new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("store"));
        Assert.assertEquals(storePage.getTitleText(),"Store");
    }
}
