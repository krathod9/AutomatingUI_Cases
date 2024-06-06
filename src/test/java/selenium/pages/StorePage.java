package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.base.BasePage;
import selenium.objects.Product;
import selenium.pages.components.ProductThumbnail;

import java.io.IOException;
import java.util.List;

public class StorePage extends BasePage {

    private final By searchField=By.id("woocommerce-product-search-field-0");
    private final By searchButton=By.xpath("//button[@value='Search']");
    private final By title=By.cssSelector(".woocommerce-products-header__title.page-title");
    private final By infoTextForSeach = By.cssSelector(".woocommerce-info.woocommerce-no-products-found");

    private ProductThumbnail productThumbnail;
    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    public StorePage(WebDriver driver) {
        super(driver);
        productThumbnail=new ProductThumbnail(driver);
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

    public String getTitleText(){
        return driver.findElement(title).getText();
    }

    public ProductPage navigateToProductPage(Integer id) throws IOException {
        waitShort.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[text()='"+new Product(id).getName() +"']"))).click();
        return new ProductPage(driver);
    }

    public String getInfoForSearchedItem(){
        return waitShort.until(ExpectedConditions.visibilityOfElementLocated(infoTextForSeach)).getText();
    }
}
