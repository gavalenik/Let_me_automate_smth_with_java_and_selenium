package pizzeria.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage extends Page {

    private final String subUrl = "my-account/";

    @FindBy(css = "#username")
    private WebElement loginFieldLocator;

    @FindBy(css = "#password")
    private WebElement passwordFieldLocator;

    @FindBy(css = ".woocommerce-button")
    private WebElement submitButtonLocator;

    @FindBy(css = ".woocommerce-MyAccount-navigation-link--edit-account")
    private WebElement accountDataButtonLocator;

    @FindBy(css = "#uploadFile")
    private WebElement uploadAvatarLocator;

    @FindBy(css = ".woocommerce-Button")
    private WebElement saveChangesButtonLocator;

    @FindBy(css = ".woocommerce-message")
    private WebElement messageFieldLocator;


    public MyAccountPage(WebDriver browser, WebDriverWait wait) {
        super(browser, wait);
        PageFactory.initElements(browser, this);
        jsExecutor = (JavascriptExecutor)browser;
    }

    @Override
    protected String getPageSubUrl() {
        return subUrl;
    }

    public void inputLogin(String login) {
        loginFieldLocator.sendKeys(login);
    }

    public void inputPassword(String password) {
        passwordFieldLocator.sendKeys(password);
    }

    public void clickSubmitButton() {
        submitButtonLocator.click();
    }

    public void clickAccountDataButton() {
        accountDataButtonLocator.click();
    }

    public void uploadAvatar(String pathToPicture) {
        uploadAvatarLocator.sendKeys(pathToPicture);
    }

    public void clickSaveChangesButton() {
        saveChangesButtonLocator.click();
    }

    public String getMessage() {
        return messageFieldLocator.getText();
    }
}
