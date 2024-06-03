package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.base.BasePage;
import selenium.objects.Product;

public class ProductPage extends BasePage {
    private final By addToCart = By.xpath("//button[@class='single_add_to_cart_button button alt']");
    private final By productTitle = By.cssSelector(".product_title.entry-title");
    private final By alertText=By.xpath("//div[@role='alert']");
    public ProductPage(WebDriver driver){
        super(driver);
    }
    public ProductPage addProuctToCart(){
        waitShort.until(ExpectedConditions.elementToBeClickable(addToCart)).click();
        return this;
    }
    public String getProductTitle(){
        return waitShort.until(ExpectedConditions.presenceOfElementLocated(productTitle)).getText();
    }

    public ProductPage loadProduct(){
        load("/product/"+getProductTitle());
        return this;
    }

    public String getAlertText(){
        return waitShort.until(ExpectedConditions.visibilityOfElementLocated(alertText)).getText();
    }

}
