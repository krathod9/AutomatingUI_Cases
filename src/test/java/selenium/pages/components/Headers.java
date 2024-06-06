package selenium.pages.components;

import org.bouncycastle.util.Store;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.base.BasePage;
import selenium.pages.StorePage;

public class Headers extends BasePage {
    private final By menulink=By.cssSelector("#menu-item-1227 > a");

    public Headers(WebDriver driver) {
        super(driver);
    }

    public StorePage clickStoreMenulink(){
        waitShort.until(ExpectedConditions.elementToBeClickable(menulink)).click();
        return new StorePage(driver);
    }
}
