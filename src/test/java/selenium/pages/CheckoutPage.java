package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import selenium.objects.BillingAddress;
import selenium.objects.UserInfo;
import selenium.pom.base.BasePage;

import java.time.Duration;
import java.util.List;

public class CheckoutPage extends BasePage {
    private final By firstName = By.xpath("//input[@name='billing_first_name']");
    private final By lastName = By.id("billing_last_name");
    private final By billingAddress = By.xpath("//input[@name='billing_address_1']");
    private final By billingCity = By.xpath("//input[@name='billing_city']");
    private final By billingPostCode=By.id("billing_postcode");
    private final By billingEmail = By.cssSelector("#billing_email");
    private final By placeOrderButton = By.xpath("//button[@name='woocommerce_checkout_place_order']");
    private final By orderConfirmation = By.xpath("//p[@class='woocommerce-notice woocommerce-notice--success woocommerce-thankyou-order-received']");
    private final By orderID = By.cssSelector("li.woocommerce-order-overview__order.order");
    private final By userID = By.id("username");
    private final By password = By.id("password");
    private final By loginButton = By.xpath("//button[@name='login']");
    private final By enableLoginButton = By.xpath("//a[@class='showlogin']");
    private final By checkoutOverlay= By.cssSelector(".blockUI.blockOverlay");


        public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterFirstName(String fName){
            WebElement e=waitForElementToBeVisible(firstName);
            e.clear();
            e.sendKeys(fName);
            return this;
    }

    public CheckoutPage enterLastName(String lName){
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(lName);
        return this;
    }
    public CheckoutPage enterBillingAddress(String adr){
        driver.findElement(billingAddress).clear();
        driver.findElement(billingAddress).sendKeys(adr);
        return this;
    }
    public CheckoutPage enterBillingCity(String city){
        driver.findElement(billingCity).clear();
        driver.findElement(billingCity).sendKeys(city);
        return this;
    }
    public CheckoutPage enterBillingPostCode(String postCode){
        driver.findElement(billingPostCode).clear();
        driver.findElement(billingPostCode).sendKeys(postCode);
        return this;
    }
    public CheckoutPage enterBillingEmail(String email){
        driver.findElement(billingEmail).clear();
        driver.findElement(billingEmail).sendKeys(email);
        return this;
    }

    public void addShippingDetails(String [] details){
        enterFirstName(details[0]).
                enterLastName(details[1]).
                enterBillingAddress(details[2]).
                enterBillingCity(details[3]).
                enterBillingPostCode(details[4]).
                enterBillingEmail(details[5]).placeOrder();

    }

    public CheckoutPage enterBillingDetails(BillingAddress billing){
        return  enterFirstName(billing.getFistName()).
                enterLastName(billing.getLastName()).
                enterBillingAddress(billing.getAddress()).
                enterBillingCity(billing.getCity()).
                enterBillingEmail(billing.getEmail()).
                enterBillingPostCode(billing.getPostalCode());
    }
    public CheckoutPage enterUserName(String userName){
        driver.findElement(userID).sendKeys(userName);
        return this;
    }
    public CheckoutPage enterPassword(String pwd){
        driver.findElement(password).sendKeys(pwd);
        return this;
    }
    public void enableLoginButton(){
        driver.findElement(enableLoginButton).click();
    }
    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    public CheckoutPage userLogin(UserInfo userInfo) throws InterruptedException {
        enableLoginButton();
        Thread.sleep(2000);
        enterUserName(userInfo.getUserID()).enterPassword(userInfo.getPassword()).clickLoginButton();
        return this;
    }


    public CheckoutPage placeOrder(){
        waitForOverlayToDisappear(checkoutOverlay);
        driver.findElement(placeOrderButton).click();
        return this;
    }
    public String getOrderSuccessText(){
        new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(orderConfirmation));
        return driver.findElement(orderConfirmation).getText();
    }
    public String getOrderID(){
            new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(orderID));
            return driver.findElement(orderID).getText();
    }
}

