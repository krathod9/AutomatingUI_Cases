package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.base.BasePage;
import selenium.objects.UserInfo;

public class AccountPage extends BasePage {
    private final By userName=By.id("username");
    private final By password=By.id("password");
    private final By loginButton=By.xpath("//button[@name='login']");
    private final By alertMsg=By.xpath("//ul[@role='alert']");

    public AccountPage(WebDriver driver){
        super(driver);
    }
    public AccountPage load() {
        load("/account/");
        return this;
    }
    public AccountPage userLogin(String uName,String pwd){
        waitShort.until(ExpectedConditions.visibilityOfElementLocated(userName)).sendKeys(uName);
        waitShort.until(ExpectedConditions.visibilityOfElementLocated(password)).sendKeys(pwd);
        driver.findElement(loginButton).click();
        return this;
    }

    public String getMessage(){
        return waitShort.until(ExpectedConditions.visibilityOfElementLocated(alertMsg)).getText();
    }

}
