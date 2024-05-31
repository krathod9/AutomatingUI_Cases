package selenium.tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.base.BaseTest;
import selenium.pages.StorePage;

import java.time.Duration;

public class SearchItemTest extends BaseTest {
    @Test
    public void searchWithPartialMatch(){
        StorePage storePage = new StorePage(getDriver()).
                load().
                search("Blue");
        WebDriverWait webDriverWait=new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.urlContains("Blue&post_type=product"));
        Assert.assertEquals(storePage.getTitleText(), "Search results: “Blue”");
    }
}
