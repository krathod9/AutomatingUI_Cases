package selenium.pom;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import selenium.factory.DriverManager;

public class BaseTest {
protected WebDriver driver;
@BeforeMethod
public void startDriver(){
    driver=new DriverManager().initializeDriver();
}
@AfterMethod
public void quitDriver(){
    driver.quit();
}

}
