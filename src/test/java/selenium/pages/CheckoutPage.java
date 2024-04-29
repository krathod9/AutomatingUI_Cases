package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.pom.base.BasePage;

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
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterFirstName(String fName){
        driver.findElement(firstName).sendKeys(fName);
        return this;
    }

    public CheckoutPage enterLastName(String lName){
        driver.findElement(lastName).sendKeys(lName);
        return this;
    }
    public CheckoutPage enterBillingAddress(String adr){
        driver.findElement(billingAddress).sendKeys(adr);
        return this;
    }
    public CheckoutPage enterBillingCity(String city){
        driver.findElement(billingCity).sendKeys(city);
        return this;
    }
    public CheckoutPage enterBillingPostCode(String postCode){
        driver.findElement(billingPostCode).sendKeys(postCode);
        return this;
    }
    public CheckoutPage enterBillingEmail(String email){
        driver.findElement(billingEmail).sendKeys(email);
        return this;
    }

    public void placeOrder(){
        driver.findElement(placeOrderButton).click();
    }
    public String getOrderSuccessText(){
        return driver.findElement(orderConfirmation).getText();
    }
    public String getOrderID(){
        return driver.findElement(orderID).getText();
    }

}
