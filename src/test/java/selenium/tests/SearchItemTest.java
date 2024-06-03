package selenium.tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.base.BaseTest;
import selenium.pages.StorePage;
import selenium.utils.FakerUtils;

import java.time.Duration;

public class SearchItemTest extends BaseTest {
    @Test
    public void searchWithPartialMatch(){
        String searchItem="Bl";
        StorePage storePage = new StorePage(getDriver()).
                load().
                searchWithpartialMatch(searchItem);
        WebDriverWait webDriverWait=new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.urlContains(searchItem+"&post_type=product"));
        Assert.assertEquals(storePage.getTitleText(), "Search results: “"+searchItem+"”");
    }

    @Test
    public void searchNonExistingProduct(){
        StorePage storePage = new StorePage(getDriver()).
                load().
                searchWithpartialMatch(new FakerUtils().generateRandomName());

        WebDriverWait webDriverWait=new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.urlContains("&post_type=product"));
        Assert.assertTrue(storePage.getInfoForSearchedItem().contains("No products were found matching your selection."));
    }
}
