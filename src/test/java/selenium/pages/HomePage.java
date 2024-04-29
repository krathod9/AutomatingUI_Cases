package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.pom.base.BasePage;

public class HomePage extends BasePage {
    private final By menulink=By.cssSelector("#menu-item-1227 > a");
    public HomePage(WebDriver driver) {
        super(driver);
    }
    public HomePage load(){ //calling common method to load the URL
        load("/");
        return this;
    }
    public StorePage clickStoreMenulink(){
        driver.findElement(menulink).click();
        return new StorePage(driver);
    }
}
