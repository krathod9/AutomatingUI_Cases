package selenium.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.utils.ConfigLoader;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait waitLong;
    protected WebDriverWait waitShort;
    public BasePage(WebDriver driver){
        this.driver=driver;
        waitLong=new WebDriverWait(driver,Duration.ofSeconds(15));
        waitShort=new WebDriverWait(driver,Duration.ofSeconds(5));
    }
    public void load(String endPoint){
        driver.get(ConfigLoader.getInstance().getBaseURL()+endPoint);//common method to load URL Endpoint - for specific page navigation
    }

    public void waitForOverlayToDisappear(By checkoutOverlay){
        List<WebElement> overlays=driver.findElements(checkoutOverlay);
        if(overlays.size()>0){
            //new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfAllElements(overlays));
            waitShort.until(ExpectedConditions.invisibilityOfAllElements(overlays));

        }
    }

    public WebElement waitForElementToBeVisible(By element){
        return waitShort.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
}
