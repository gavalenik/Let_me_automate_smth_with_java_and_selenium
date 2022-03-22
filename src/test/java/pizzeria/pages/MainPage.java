package pizzeria.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class MainPage extends Page {

    private final String url = "http://pizzeria.skillbox.cc/";

    private final By visiblePizzaLocator = By.cssSelector("#product1 .slick-active");


    @FindBy(className = "account")
    private WebElement inputLinkLocator;

    @FindBy(css = ".slick-next")
    private WebElement slideRightLocator;

    @FindBy(css = ".slick-prev")
    private WebElement slideLeftLocator;

    @FindBy(css = "#product1 .slick-track[transition]")
    private WebElement sliderTransitionCondition;

    @FindBy(css = "#product1 .slick-active h3")
    private WebElement firstVisiblePizzaTitleLocator;


    public MainPage(WebDriver browser, WebDriverWait wait) {
        super(browser, wait);
        PageFactory.initElements(browser, this);
    }

    public MainPage open() {
        browser.get(url);
        return this;
    }

    public MainPage waitForAllPizzasLoaded() {
        var qnt = browser.findElements(visiblePizzaLocator).size();
        wait.until(visibilityOf(browser.findElements(visiblePizzaLocator).get(qnt-1)));
        return this;
    }

    public void slidePizzasToRight() {
        Actions action = new Actions(browser);
        action.moveToElement(slideRightLocator).click().perform();
        wait.until(ExpectedConditions.invisibilityOf(sliderTransitionCondition));
    }

    public void slidePizzasToLeft() {
        Actions action = new Actions(browser);
        action.moveToElement(slideLeftLocator).click().perform();
        wait.until(ExpectedConditions.invisibilityOf(sliderTransitionCondition));
    }

    public String getFirstPizzaTitle() {
        return firstVisiblePizzaTitleLocator.getText();
    }

    public MainPage clickInputLink() {
        inputLinkLocator.click();
        return this;
    }
}
