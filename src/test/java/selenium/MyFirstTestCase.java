package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class MyFirstTestCase {

    @Test
    public void guestCheckoutDirectBankTransfer() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("autofill.profile_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        WebDriver driver =new ChromeDriver(options);
        driver.get("https://askomdch.com/");
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("#menu-item-1227 > a")).click();
        driver.findElement(By.id("woocommerce-product-search-field-0")).sendKeys("Blue");
        driver.findElement(By.xpath("//button[@value='Search']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".woocommerce-products-header__title.page-title")).getText(),"Search results: “Blue”");
        driver.findElement(By.xpath("//a[@aria-label='Add “Blue Shoes” to your cart']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[@title='View cart']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("td[class='product-name'] a")).getText(),"Blue Shoes");
        driver.findElement(By.xpath("//a[@class='checkout-button button alt wc-forward']")).click();
        driver.findElement(By.xpath("//input[@name='billing_first_name']")).sendKeys("fName");
        driver.findElement(By.id("billing_last_name")).sendKeys("lName");
        driver.findElement(By.xpath("//input[@name='billing_address_1']")).sendKeys("My address at San Francisco");
        driver.findElement(By.xpath("//input[@name='billing_city']")).sendKeys("San Francisco");
        driver.findElement(By.id("billing_postcode")).sendKeys("94188");
        driver.findElement(By.cssSelector("#billing_email")).sendKeys("testemail@mymail.com");
        driver.findElement(By.xpath("//button[@name='woocommerce_checkout_place_order']")).click();
        Thread.sleep(8000);
        Assert.assertEquals(driver.findElement(By.xpath("//p[@class='woocommerce-notice woocommerce-notice--success woocommerce-thankyou-order-received']")).getText(),"Thank you. Your order has been received.");
        String orderId=driver.findElement(By.cssSelector("li.woocommerce-order-overview__order.order")).getText();
        System.out.println("Order Id - "+orderId);
        driver.quit();
    }
}
