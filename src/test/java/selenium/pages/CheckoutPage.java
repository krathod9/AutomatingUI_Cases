package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import selenium.objects.BillingAddress;
import selenium.objects.UserInfo;
import selenium.base.BasePage;

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
    private final By countryDropDown = By.id("billing_country");
    private final By stateDropDown = By.id("billing_state");
    private final By directBankTransferRadioButton=By.id("payment_method_bacs");

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
        WebElement e=waitForElementToBeVisible(lastName);
        e.clear();
        e.sendKeys(lName);
        return this;
    }
    public CheckoutPage enterBillingAddress(String adr){
        WebElement e=waitForElementToBeVisible(billingAddress);
        e.clear();
        e.sendKeys(adr);
        return this;
    }
    public CheckoutPage enterBillingCity(String city){
        WebElement e=waitForElementToBeVisible(billingCity);
        e.clear();
        e.sendKeys(city);
        return this;
    }
    public CheckoutPage enterBillingPostCode(String postCode){
        WebElement e=waitForElementToBeVisible(billingPostCode);
        e.clear();
        e.sendKeys(postCode);
        return this;
    }
    public CheckoutPage enterBillingEmail(String email){
        WebElement e=waitForElementToBeVisible(billingEmail);
        e.clear();
        e.sendKeys(email);
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
                selectCountry(billing.getCountry()).
                enterBillingAddress(billing.getAddress()).
                enterBillingCity(billing.getCity()).
                selectState(billing.getState()).
                enterBillingEmail(billing.getEmail()).
                enterBillingPostCode(billing.getPostalCode());
    }
    public CheckoutPage enterUserName(String userName){
        waitLong.until(ExpectedConditions.visibilityOfElementLocated(userID)).sendKeys(userName);
        return this;
    }
    public CheckoutPage enterPassword(String pwd){
        waitLong.until(ExpectedConditions.visibilityOfElementLocated(password)).sendKeys(pwd);
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
        enterUserName(userInfo.getUserID()).enterPassword(userInfo.getPassword()).clickLoginButton();
        return this;
    }

    public CheckoutPage placeOrder(){
        waitForOverlayToDisappear(checkoutOverlay);
        waitLong.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
        return this;
    }

    public CheckoutPage selectCountry(String countryName){
        Select select=new Select(driver.findElement(countryDropDown));
        select.selectByVisibleText(countryName);
        return this;
    }

    public CheckoutPage selectState(String stateName){
        Select select=new Select(driver.findElement(stateDropDown));
        select.selectByVisibleText(stateName);
        return this;
    }
    public CheckoutPage selectDirectBankTransfer(){
        WebElement e=waitShort.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioButton));
        if(!e.isSelected()){
            e.click();
        }
        return this;
    }
    public String getOrderSuccessText(){
        return waitLong.until(ExpectedConditions.presenceOfElementLocated(orderConfirmation)).getText();
    }
    public String getOrderID(){
        return waitShort.until(ExpectedConditions.presenceOfElementLocated(orderID)).getText();
    }
}

