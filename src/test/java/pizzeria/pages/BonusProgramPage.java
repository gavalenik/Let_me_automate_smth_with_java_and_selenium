package pizzeria.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BonusProgramPage extends Page {

    private final String subUrl = "bonus/";

    @FindBy(css = "#bonus_username")
    private WebElement bonusUsernameFieldLocator;

    @FindBy(css = "#bonus_phone")
    private WebElement bonusPhoneNumberFieldLocator;

    @FindBy(css = "#bonus_main button")
    private WebElement submitButtonLocator;


    public BonusProgramPage(WebDriver browser, WebDriverWait wait) {
        super(browser, wait);
        PageFactory.initElements(browser, this);
        jsExecutor = (JavascriptExecutor)browser;
    }

    @Override
    protected String getPageSubUrl() {
        return subUrl;
    }

    public void inputBonusUsername(String username) {
        bonusUsernameFieldLocator.sendKeys(username);
    }

    public void inputBonusPhoneNumber(String phoneNumber) {
        bonusPhoneNumberFieldLocator.sendKeys(phoneNumber);
    }

    public void clickSubmitButton() {
        submitButtonLocator.click();
    }
}
