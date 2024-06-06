package selenium.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.base.BasePage;
import selenium.pages.CartPage;
import selenium.pages.HomePage;
import selenium.pages.StorePage;

import java.util.List;

public class ProductThumbnail extends BasePage {
    private final By viewCart=By.xpath("//a[@title='View cart']");
    private final By storePage2=By.xpath("//a[@class='page-numbers' and text()='2']");
    public ProductThumbnail(WebDriver driver) {
        super(driver);
    }

    private By getItemToAddToCart(String item){
        return By.xpath("//a[@aria-label='Add “"+item+"” to your cart']");//Handling dynamic UI elements
    }
    public ProductThumbnail addItemToCart(String item){
        By addToCart=getItemToAddToCart(item);
        if(!isProductVisible(addToCart)){
            waitLong.until(ExpectedConditions.elementToBeClickable(storePage2)).click();
        }
        waitShort.until(ExpectedConditions.elementToBeClickable
                (addToCart)).click();
        return this;
    }
    public CartPage viewItemsFromCart(){
        waitShort.until(ExpectedConditions.elementToBeClickable(viewCart)).click();
        return new CartPage(driver);
    }

    public boolean isProductVisible(By element){
        List<WebElement> webElements =driver.findElements(element);
        return webElements.size() != 0;
    }
}
