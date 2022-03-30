package pizzeria.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;

public class OrderCheckoutPage extends Page {

    @FindBy(css = "input#billing_address_1")
    private WebElement streetNameAndBuildingFieldLocator;

    @FindBy(css = "input#billing_city")
    private WebElement cityFieldLocator;

    @FindBy(css = "input#billing_state")
    private WebElement regionFieldLocator;

    @FindBy(css = "input#billing_postcode")
    private WebElement zipFieldLocator;

    @FindBy(css = "input#billing_phone")
    private WebElement phoneFieldLocator;

    @FindBy(css = "#payment_method_bacs")
    private WebElement paymentByBankTransferRadioLocator;

    @FindBy(css = "#payment_method_cod")
    private WebElement paymentByCashRadioLocator;

    @FindBy(css = "#terms")
    private WebElement termsCheckboxLocator;

    @FindBy(css = "#place_order")
    private WebElement placeOrderButtonLocator;

    @FindBy(css = "h2.post-title")
    private WebElement orderConfirmedLocator;

    @FindBy(css = ".blockOverlay")
    private WebElement loaderLocator;

    @FindBy(css = "#order_date")
    private WebElement orderDateLocator;

    @FindBy(css = "#order_comments")
    private WebElement orderCommentLocator;


    public OrderCheckoutPage(WebDriver browser, WebDriverWait wait) {
        super(browser, wait);
        PageFactory.initElements(browser, this);
        jsExecutor = (JavascriptExecutor)browser;
    }

    public void inputStreetAndBuildingNumber(String address) {
        streetNameAndBuildingFieldLocator.clear();
        streetNameAndBuildingFieldLocator.sendKeys(address);
    }

    public void inputCity(String city) {
        cityFieldLocator.clear();
        cityFieldLocator.sendKeys(city);
    }

    public void inputRegion(String region) {
        regionFieldLocator.clear();
        regionFieldLocator.sendKeys(region);
    }

    public void inputZip(String zip) {
        zipFieldLocator.clear();
        zipFieldLocator.sendKeys(zip);
    }

    public void inputPhone(String phoneNumber) {
        phoneFieldLocator.clear();
        phoneFieldLocator.sendKeys(phoneNumber);
    }

    public void choosePaymentByBankTransfer() {
        wait.until(invisibilityOf(loaderLocator));
        paymentByBankTransferRadioLocator.click();
    }

    public void choosePaymentByCash() {
        wait.until(invisibilityOf(loaderLocator));
        paymentByCashRadioLocator.click();
    }

    public void acceptTerms() {
        wait.until(invisibilityOf(loaderLocator));
        termsCheckboxLocator.click();
    }

    public void placeOrder() {
        placeOrderButtonLocator.click();
    }

    public String getOrderConfirmation() {
        return orderConfirmedLocator.getText();
    }

    public void inputOrderDateWithTomorrowDate() {
        var tomorrow = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("MMddyyyy"));
        orderDateLocator.sendKeys(tomorrow);
    }

    public void inputOrderComment(String comment) {
        orderCommentLocator.sendKeys(comment);
    }
}
