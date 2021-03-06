package pizzeria.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.String.format;
import static org.openqa.selenium.Keys.ENTER;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class MainPage extends Page {

    Actions action = new Actions(browser);

    public final By pizzaLocator = By.cssSelector("#product1 .slick-active");
    public final By dessertLocator = By.cssSelector("#product2 .slick-active");
    public final By beverageLocator = By.cssSelector("#accesspress_store_product-7 .slick-active");
    private final By addToBasketButtonLocator = By.cssSelector(".add_to_cart_button");

    @FindBy(css = ".slick-next")
    private WebElement slideRightLocator;

    @FindBy(css = ".slick-prev")
    private WebElement slideLeftLocator;

    @FindBy(css = "#product1 .slick-track[transition]")
    private WebElement sliderTransitionCondition;

    @FindBy(css = "#product1 .slick-active h3")
    private List<WebElement> pizzaTitleLocator;

    @FindBy(css = "div#ak-top")
    private WebElement arrowUpLocator;

    @FindBy(css = "html")
    private WebElement page;

    @FindBy(xpath = "//*[@id='menu']")
    private WebElement menu;

    @FindBy(css = "#menu-item-389")
    private WebElement pointMenuOfMainMenu;

    @FindBy(css = "#accesspress-breadcrumb .current")
    private WebElement pageTitle;

    @FindBy(css = ".accesspress-breadcrumb span")
    private WebElement subCategoryPageTitle;

    @FindBy(css = ".search-field")
    private WebElement searchFieldLocator;

    @FindBy(css = ".product_title")
    private WebElement itemTitleLocator;


    public MainPage(WebDriver browser, WebDriverWait wait) {
        super(browser, wait);
        PageFactory.initElements(browser, this);
        jsExecutor = (JavascriptExecutor)browser;
    }

    public void waitForAllItemsLoaded(By item) {
        var qnt = browser.findElements(item).size();
        wait.until(visibilityOf(browser.findElements(item).get(qnt - 1)));
    }

    private void moveAndWait(WebElement element) {
        action.moveToElement(element).click().perform();
        wait.until(invisibilityOf(sliderTransitionCondition));
    }

    public void oneTimeSlidePizzasToRight() {
        moveAndWait(slideRightLocator);
    }

    public void oneTimeSlidePizzasToLeft() {
        moveAndWait(slideLeftLocator);
    }

    public String getFirstPizzaTitle() {
        return pizzaTitleLocator.get(0).getText();
    }

    public void scrollToSection(By item) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", browser.findElement(item));
    }

    public void moveCursorToFirstBeverage() {
        action.moveToElement(browser.findElement(beverageLocator)).perform();
    }

    public String getAddToButtonText() {
        return browser.findElement(beverageLocator).findElement(addToBasketButtonLocator).getText();
    }

    public Boolean addToBasketButtonIsDisplayed() {
        return browser.findElement(beverageLocator).findElement(addToBasketButtonLocator).isDisplayed();
    }

    public Boolean arrowUpIsDisplayed() {
        return arrowUpLocator.isDisplayed();
    }

    public void clickFirstDessert() {
        browser.findElements(dessertLocator).get(0).click();
    }

    public void goToPageBottom() {
        for (var i = 0; i < 40; i++) {
            page.sendKeys(Keys.DOWN);
        }
    }

    public void clickSocialNetworkLink(String socialNetwork) {
        var element = By.cssSelector(format("p a[href *= '%s']", socialNetwork));
        browser.findElement(element).click();
    }

    public Integer getTabsQuantity() {
        return browser.getWindowHandles().size();
    }

    public void goToMenu(String menuItem) {
        menu.findElement(By.xpath(format("//a[text()='%s']", menuItem))).click();
    }

    public void goToSubmenu(String menuItem) {
        action.moveToElement(pointMenuOfMainMenu)
                .moveToElement(browser.findElement(By.xpath(format("//a[text()='%s']", menuItem)))).click().perform();
    }

    public String getPageTitle_redirectViaMenu() {
        return pageTitle.getText();
    }

    public String getPageTitle_redirectViaSubmenu() {
        return subCategoryPageTitle.getText();
    }

    public void searchFor(String item) {
        searchFieldLocator.sendKeys(item);
        searchFieldLocator.sendKeys(ENTER);
        wait.until(visibilityOf(itemTitleLocator));
    }

    public Boolean itemTitlesContainsSearchItem(String item) {
        return itemTitleLocator.getText().toLowerCase().contains(item.toLowerCase());
    }
}
