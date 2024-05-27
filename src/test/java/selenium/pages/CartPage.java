package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.pom.base.BasePage;

import java.util.List;

public class CartPage extends BasePage {
    private final By productName=By.cssSelector("td[class='product-name'] a");
    private final By checkoutButton=By.xpath("//a[@class='checkout-button button alt wc-forward']");
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName(){
        return waitShort.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
        //driver.findElement(productName).getText();
    }
    public CheckoutPage checkoutCart(){
        waitShort.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
        //driver.findElement(checkoutButton).click();
        return new CheckoutPage(driver);

    }
}
