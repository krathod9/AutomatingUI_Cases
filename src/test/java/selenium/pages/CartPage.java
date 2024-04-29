package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.pom.base.BasePage;

public class CartPage extends BasePage {
    private final By productName=By.cssSelector("td[class='product-name'] a");
    private final By checkoutButton=By.xpath("//a[@class='checkout-button button alt wc-forward']");
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName(){
        return driver.findElement(productName).getText();
    }
    public CheckoutPage checkoutCart(){
        driver.findElement(checkoutButton).click();
        return new CheckoutPage(driver);
    }
}
