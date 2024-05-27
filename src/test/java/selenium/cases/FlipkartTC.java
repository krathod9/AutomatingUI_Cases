package selenium.cases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.an.E;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import selenium.pom.base.BaseTest;

import java.time.Duration;

public class FlipkartTC extends BaseTest {
    final By electronic=By.xpath("//span[@class='TSD49J' and contains(text(),'Electronics')]");
    final By cables=By.xpath("//a[@title='Mobile Cables']");
    final By topOffer=By.xpath("//span[@class='_1XjE3T']/span[text()='Top Offer']");
    @Test
    public void findCable() throws InterruptedException {

        ExtentReports extentReports=new ExtentReports();
        ExtentTest extentTest=extentReports.createTest("FlipkartReport");
        extentTest.log(Status.INFO,"Opening flipkart page");
        driver.get("https://www.flipkart.com");
        extentTest.log(Status.PASS,"flipkart page opened");
        driver.findElement(topOffer).click();
        Thread.sleep(5000);
        //new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(electronic));
        Actions actions=new Actions(driver);
        actions.moveToElement(driver.findElement(electronic)).build().perform();
        extentTest.log(Status.SKIP,"skipping hover check in log");
        Thread.sleep(3000);
        driver.findElement(cables).click();
        extentTest.log(Status.FAIL,"fail details");
        Thread.sleep(5000);
        //System.out.println(System.getProperty("user.dir")+"\\src\\test\\java\\selenium\\report\\ExtentReport.html");
    }

}
