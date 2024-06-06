package selenium.base;

import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import selenium.factory.DriverManager;
import selenium.utils.CookieUtil;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
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
public synchronized void startDriver(@Optional String browser){//this is mark the param as optional if we are using testNG parameter
    browser=System.getProperty("browser",browser);//2nd param is default and now 1st will be taken from testNG
    if(browser==null) browser="CHROME";
    setDriver(new DriverManager().initializeDriver(browser));
    System.out.println("CURRENT THREAD :" +Thread.currentThread().getId());
}
@Parameters("browser")
@AfterMethod
public synchronized void quitDriver(@Optional String browser,ITestResult result) throws InterruptedException, IOException {
    Thread.sleep(300);
    if(result.getStatus()==ITestResult.FAILURE){
        File file= new File("scr"+File.separator+"FailedScreenshots"+File.separator+
                result.getTestClass().getRealClass().getSimpleName()
                +"_"+result.getMethod().getMethodName()+".png");
        takeScreenshotUsingAshot(file);
    }
    getDriver().quit();
}
public void injectCookiesToBrowser(Cookies cookies){
    List<Cookie> seleniumCookies= new CookieUtil().convertRESTToSeleniumCookies(cookies);
    for(Cookie cookie:seleniumCookies){
        getDriver().manage().addCookie(cookie);
    }
}
private void takeScreenshot(File destFile) throws IOException {
    TakesScreenshot takesScreenshot= (TakesScreenshot) getDriver();
    File srcFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(srcFile,destFile);
}

private void takeScreenshotUsingAshot(File destFile) throws IOException {
    Screenshot screenshot=new AShot()
            .shootingStrategy(ShootingStrategies.viewportPasting(100))
            .takeScreenshot(getDriver());
    ImageIO.write(screenshot.getImage(),"PNG",destFile);
}

}


