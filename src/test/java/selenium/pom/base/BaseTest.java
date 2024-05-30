package selenium.pom.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import selenium.factory.DriverManager;

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
public void startDriver(String browser){
    browser=System.getProperty("browser",browser);//2nd param is default and now 1st will be taken from testNG
    setDriver(new DriverManager().initializeDriver(browser));
    System.out.println("CURRENT THREAD :" +Thread.currentThread().getId());
}
@AfterMethod
public void quitDriver(){
    getDriver().quit();
}

}
