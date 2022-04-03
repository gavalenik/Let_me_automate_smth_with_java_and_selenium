package pizzeria.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;

public class DessertPage extends Page {

    private final String subUrl = "product-category/menu/deserts/";

    private final By addToCartButtonLocator = By.cssSelector(".add_to_cart_button");
    private final By loaderLocator = By.cssSelector(".loading");

    @FindBy(css = "#primary h1")
    private WebElement itemTitle;

    @FindBy(css = "li.product.status-publish")
    private List<WebElement> dessertItem;


    public DessertPage(WebDriver browser, WebDriverWait wait) {
        super(browser, wait);
        PageFactory.initElements(browser, this);
        jsExecutor = (JavascriptExecutor)browser;
    }

    @Override
    protected String getPageSubUrl() {
        return subUrl;
    }

    public String getItemTitle() {
        return itemTitle.getText();
    }

    public void addFirstDessertToBasket() {
        dessertItem.get(0).findElement(addToCartButtonLocator).click();
        wait.until(invisibilityOf(dessertItem.get(0).findElement(loaderLocator)));
    }
}
