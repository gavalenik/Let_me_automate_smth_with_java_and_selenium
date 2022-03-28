package pizzeria.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;

public class PizzaPage extends Page {

    private final String subUrl = "product-category/menu/pizza/";

    private final By priceLocator = By.cssSelector("bdi");
    private final By addToCartButtonLocator = By.cssSelector(".add_to_cart_button");
    private final By loaderLocator = By.cssSelector(".loading");

    @FindBy(css = "#primary h1")
    private WebElement itemTitleLocator;

    @FindBy(css = ".ui-slider-handle:nth-of-type(1)")
    private WebElement leftPriceSliderLocator;

    @FindBy(css = ".ui-slider-handle:nth-of-type(2)")
    private WebElement rightPriceSliderLocator;

    @FindBy(css = ".price_slider_amount .button")
    private WebElement priceSliderButtonSubmitLocator;

    @FindBy(css = "li.product.status-publish")
    private List<WebElement> pizzaItem;

    @FindBy(css = ".orderby")
    private WebElement sortingFieldLocator;

    @FindBy(css = ".cart-contents")
    private WebElement cartButtonLocator;


    public PizzaPage(WebDriver browser, WebDriverWait wait) {
        super(browser, wait);
        PageFactory.initElements(browser, this);
        jsExecutor = (JavascriptExecutor)browser;
    }

    @Override
    protected String getPageSubUrl() {
        return subUrl;
    }

    public void scrollToPriceFilter() {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", leftPriceSliderLocator);
    }

    public void changePrice_oneStepHigherWithLeftSlider() {
        leftPriceSliderLocator.click();
        leftPriceSliderLocator.sendKeys(Keys.RIGHT);
    }

    public void changePrice_oneStepLowerWithRightSlider() {
        rightPriceSliderLocator.click();
        rightPriceSliderLocator.sendKeys(Keys.LEFT);
    }

    public void clickButtonSubmit() {
        priceSliderButtonSubmitLocator.click();
    }

    public Integer getPizzaQuantity() {
        return pizzaItem.size();
    }

    public void sortingByValue(String value) {
        new Select(sortingFieldLocator).selectByVisibleText(value);
    }

    public List<Double> getPizzaPrices() {
        List<Double> pizzasPriceList = new ArrayList<>();
        for(WebElement pizza : pizzaItem) {
            pizzasPriceList.add(Double.valueOf(pizza.findElement(priceLocator).getText()
                    .replace("₽","").replace(",",".")));
        }
        return pizzasPriceList;
    }

    public void addPizzaToBasketByIndex(Integer index) {
        pizzaItem.get(index).findElement(addToCartButtonLocator).click();
        wait.until(invisibilityOf(pizzaItem.get(index).findElement(loaderLocator)));
    }

    public void clickCartButton() {
        cartButtonLocator.click();
    }
}
