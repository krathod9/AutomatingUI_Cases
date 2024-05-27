package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.pom.base.BasePage;

import java.time.Duration;

public class StorePage extends BasePage {

    private final By searchField=By.id("woocommerce-product-search-field-0");
    private final By searchButton=By.xpath("//button[@value='Search']");
    private final By title=By.cssSelector(".woocommerce-products-header__title.page-title");
    private final By viewCart=By.xpath("//a[@title='View cart']");
    public StorePage(WebDriver driver) {
        super(driver);
    }

    private StorePage enterTextInSearchField(String str){
        driver.findElement(searchField).sendKeys(str);
        return this;
    }
    private StorePage clickSearchButton(){
        driver.findElement(searchButton).click();
        return this;
    }

    public StorePage search(String str){
        enterTextInSearchField(str).clickSearchButton();
        return this;
    }
    public String getTitleText(){
        return driver.findElement(title).getText();
    }

    private By getItemToAddToCart(String item){
        return By.xpath("//a[@aria-label='Add “"+item+"” to your cart']");//Handling dynamic UI elements
    }
    public StorePage addItemToCart(String item){
        By addToCart=getItemToAddToCart(item);
        driver.findElement(addToCart).click();
        return this;
    }

    public CartPage viewItemsFromCart(){
        waitShort.until(ExpectedConditions.elementToBeClickable(viewCart));
        driver.findElement(viewCart).click();
        return new CartPage(driver);
    }

}
