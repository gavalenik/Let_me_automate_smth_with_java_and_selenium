package pizzeria.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class MyAccountPage extends Page {

    private final String subUrl = "my-account/";

    @FindBy(css = "#username")
    private WebElement loginFieldLocator;

    @FindBy(css = "#password")
    private WebElement passwordFieldLocator;

    @FindBy(css = ".woocommerce-button")
    private WebElement submitButtonLocator;

    @FindBy(css = ".woocommerce-MyAccount-navigation-link--edit-account > a")
    private WebElement accountDataButtonLocator;

    @FindBy(css = ".woocommerce-MyAccount-navigation-link--customer-logout > a")
    private WebElement logoutButtonLocator;

    @FindBy(css = "#uploadFile")
    private WebElement uploadAvatarLocator;

    @FindBy(css = ".woocommerce-Button")
    private WebElement saveChangesButtonLocator;

    @FindBy(css = ".woocommerce-message")
    private WebElement messageFieldLocator;

    @FindBy(css = ".login-woocommerce")
    private WebElement loginLogoutLinkLocator;


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

    public void clickLogoutButton() {
        logoutButtonLocator.click();
        wait.until(visibilityOf(loginFieldLocator));
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

    public String getLoginLogoutLinkLocator() {
        return loginLogoutLinkLocator.getText();
    }
}
