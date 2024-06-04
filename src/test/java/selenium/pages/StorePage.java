package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.base.BasePage;
import selenium.objects.Product;

import java.io.IOException;
import java.util.List;

public class StorePage extends BasePage {

    private final By searchField=By.id("woocommerce-product-search-field-0");
    private final By searchButton=By.xpath("//button[@value='Search']");
    private final By title=By.cssSelector(".woocommerce-products-header__title.page-title");
    private final By viewCart=By.xpath("//a[@title='View cart']");
    private final By infoTextForSeach = By.cssSelector(".woocommerce-info.woocommerce-no-products-found");
    private final By storePage2=By.xpath("//a[@class='page-numbers' and text()='2']");
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
    public StorePage load(){
        load("/store/");
        return this;
    }
    public StorePage searchWithpartialMatch(String str){
        enterTextInSearchField(str).clickSearchButton();
        return this;
    }

    public StorePage addProductsFromStorePage(String str){
        if(!isProductVisible(str)){
            waitShort.until(ExpectedConditions.elementToBeClickable(storePage2)).click();
        }
        waitShort.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[@aria-label='Add “"+str+"” to your cart']"))).click();
        return this;
    }
    public boolean isProductVisible(String str){
        List<WebElement> webElements =driver.findElements(By.xpath
                ("//a[@aria-label='Add “"+str+"” to your cart']"));
        return webElements.size()!=0?true:false;
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
        waitShort.until(ExpectedConditions.elementToBeClickable(viewCart)).click();
        return new CartPage(driver);
    }
    public ProductPage navigateToProductPage(Integer id) throws IOException {
        waitShort.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[text()='"+new Product(id).getName() +"']"))).click();
        return new ProductPage(driver);
    }

    public String getInfoForSearchedItem(){
        return waitShort.until(ExpectedConditions.visibilityOfElementLocated(infoTextForSeach)).getText();
    }
}
