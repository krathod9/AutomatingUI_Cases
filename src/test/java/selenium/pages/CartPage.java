package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.pom.base.BasePage;

import java.util.List;

public class CartPage extends BasePage {
    @FindBy(css="td[class='product-name'] a") WebElement productName;//PageFactory Implementation
    @FindBy(how= How.XPATH,using="//a[@class='checkout-button button alt wc-forward']") //PageFactory Implementation
    @CacheLookup WebElement checkoutButton;
    //private final By productName=By.cssSelector("td[class='product-name'] a");
    //private final By checkoutButton=By.xpath("//a[@class='checkout-button button alt wc-forward']");
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public String getProductName(){
        return waitShort.until(ExpectedConditions.visibilityOf(productName)).getText();
    }
    public CheckoutPage checkoutCart(){
        waitShort.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
        return new CheckoutPage(driver);

    }
}
