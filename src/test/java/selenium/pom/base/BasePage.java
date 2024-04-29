package selenium.pom.base;

import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;
    public BasePage(WebDriver driver){
        this.driver=driver;
    }
    public void load(String endPoint){
        driver.get("https://askomdch.com"+endPoint);//common method to load URL Endpoint - for specific page navigation
    }
}
