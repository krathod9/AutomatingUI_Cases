package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.base.BasePage;
import selenium.objects.Product;

import java.io.IOException;

public class HomePage extends BasePage {
    private final By menulink=By.cssSelector("#menu-item-1227 > a");
    private final By viewCart=By.xpath("//a[@title='View cart']");
    public HomePage(WebDriver driver) {
        super(driver);
    }
    public HomePage load(){ //calling common method to load the URL
        load("/");
        return this;
    }
    public StorePage clickStoreMenulink(){
        waitShort.until(ExpectedConditions.elementToBeClickable(menulink)).click();
        return new StorePage(driver);
    }
    public ProductPage navigateToProductPage(Integer id) throws IOException {
        waitShort.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[text()='"+new Product(id).getName() +"']"))).click();
        return new ProductPage(driver);
    }

    private By getItemToAddToCart(String item){
        return By.xpath("//a[@aria-label='Add “"+item+"” to your cart']");//Handling dynamic UI elements
    }
    public HomePage addItemToCart(String item){
        By addToCart=getItemToAddToCart(item);
        driver.findElement(addToCart).click();
        return this;
    }

    public CartPage viewItemsFromCart(){
        waitShort.until(ExpectedConditions.elementToBeClickable(viewCart)).click();
        return new CartPage(driver);
    }

    public ProductPage loadProductPage(Product product){
        String finalProduct=product.getName().toLowerCase().replaceAll(" ","-");
        load("/"+finalProduct+"/");
        return new ProductPage(driver);
    }

}
