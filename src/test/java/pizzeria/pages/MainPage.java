package pizzeria.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class MainPage extends Page {

    private final String url = "http://pizzeria.skillbox.cc/";

    public final By pizzaLocator = By.cssSelector("#product1 .slick-active");
    public final By dessertLocator = By.cssSelector("#product2 .slick-active");
    public final By beverageLocator = By.cssSelector("#accesspress_store_product-7 .slick-active");
    private final By addToBasketButtonLocator = By.cssSelector(".add_to_cart_button");


    @FindBy(className = "account")
    private WebElement inputLinkLocator;

    @FindBy(css = ".slick-next")
    public WebElement slideRightLocator;

    @FindBy(css = ".slick-prev")
    public WebElement slideLeftLocator;

    @FindBy(css = "#product1 .slick-track[transition]")
    private WebElement sliderTransitionCondition;

    @FindBy(css = "#product1 .slick-active h3")
    private List<WebElement> pizzaTitleLocator;

    @FindBy(css = "div#ak-top")
    private WebElement arrowUp;

    @FindBy(css = "html")
    private WebElement page;


    public MainPage(WebDriver browser, WebDriverWait wait) {
        super(browser, wait);
        PageFactory.initElements(browser, this);
        jsExecutor = (JavascriptExecutor)browser;
    }

    public MainPage open() {
        browser.get(url);
        return this;
    }

    public MainPage waitForAllItemsLoaded(By item) {
        var qnt = browser.findElements(item).size();
        wait.until(visibilityOf(browser.findElements(item).get(qnt - 1)));
        return this;
    }

    public void slidePizzasTo(WebElement element) {
        Actions action = new Actions(browser);
        action.moveToElement(element).click().perform();
        wait.until(ExpectedConditions.invisibilityOf(sliderTransitionCondition));
    }

    public String getPizzaTitleByIndex(Integer index) {
        return pizzaTitleLocator.get(index).getText();
    }

    public void scrollToSection(By item) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", browser.findElement(item));
    }

    public void moveCursorToFirstBeverage() {
        Actions action = new Actions(browser);
        action.moveToElement(browser.findElement(beverageLocator)).perform();
    }

    public String getAddToButtonText() {
        return browser.findElement(beverageLocator).findElement(addToBasketButtonLocator).getText();
    }

    public Boolean addToBasketButtonIsDisplayed() {
        return browser.findElement(beverageLocator).findElement(addToBasketButtonLocator).isDisplayed();
    }

    public Boolean arrowUpIsDisplayed() {
        return arrowUp.isDisplayed();
    }

    public void clickDessertByIndex(Integer index) {
        browser.findElements(dessertLocator).get(index).click();
    }

    public MainPage goToPageBottom() {
        for (var i = 0; i < 40; i++) {
            page.sendKeys(Keys.DOWN);
        }
        return this;
    }

    public MainPage clickSocialNetworkLink(String socialNetwork) {
        var element = By.cssSelector(String.format("p a[href *= '%s']", socialNetwork));
        browser.findElement(element).click();
        return this;
    }

    public Integer getWindowsQuantity() {
        return browser.getWindowHandles().size();
    }
}
