package selenium.base;

import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import selenium.factory.DriverManager;
import selenium.utils.CookieUtil;

import java.util.List;

public class BaseTest {
private ThreadLocal<WebDriver> driver=new ThreadLocal<>();
private void setDriver(WebDriver driver){
    this.driver.set(driver);
}
protected WebDriver getDriver(){
    return this.driver.get();
}
@Parameters("browser")
@BeforeMethod
public void startDriver(@Optional String browser){//this is mark the param as optional if we are using testNG parameter
    browser=System.getProperty("browser",browser);//2nd param is default and now 1st will be taken from testNG
    if(browser==null) browser="CHROME";
    setDriver(new DriverManager().initializeDriver(browser));
    System.out.println("CURRENT THREAD :" +Thread.currentThread().getId());
}
@AfterMethod
public void quitDriver(){
    getDriver().quit();
}
public void injectCookiesToBrowser(Cookies cookies){
    List<Cookie> seleniumCookies= new CookieUtil().convertRESTToSeleniumCookies(cookies);
    for(Cookie cookie:seleniumCookies){
        getDriver().manage().addCookie(cookie);
    }
}

}


