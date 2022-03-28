package pizzeria.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeliveryAndPaymentPage extends Page {

    private final String subUrl = "delivery/";

    @FindBy(tagName = "iframe")
    private WebElement iframe;

    @FindBy(css = "li:nth-child(2)")
    private WebElement orderAmountInformationLocator;


    public DeliveryAndPaymentPage(WebDriver browser, WebDriverWait wait) {
        super(browser, wait);
        PageFactory.initElements(browser, this);
        jsExecutor = (JavascriptExecutor)browser;
    }

    @Override
    protected String getPageSubUrl() {
        return subUrl;
    }

    public void switchToFrame() {
        browser.switchTo().frame(iframe);
    }

    public String getOrderAmountInfo() {
        return orderAmountInformationLocator.getText();
    }
}
