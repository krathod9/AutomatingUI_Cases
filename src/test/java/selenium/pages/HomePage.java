package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.base.BasePage;
import selenium.objects.Product;
import selenium.pages.components.Headers;
import selenium.pages.components.ProductThumbnail;

import java.io.IOException;

public class HomePage extends BasePage {
    private ProductThumbnail productThumbnail;
    private Headers headers;

    public HomePage(WebDriver driver) {
        super(driver);
        productThumbnail=new ProductThumbnail(driver);
        headers=new Headers(driver);
    }
    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    public Headers getHeaders() {
        return headers;
    }
    public HomePage load(){ //calling common method to load the URL
        load("/");
        return this;
    }
    public ProductPage navigateToProductPage(Integer id) throws IOException {
        waitShort.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[text()='"+new Product(id).getName() +"']"))).click();
        return new ProductPage(driver);
    }



}
