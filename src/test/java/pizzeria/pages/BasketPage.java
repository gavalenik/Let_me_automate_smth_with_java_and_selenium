package pizzeria.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasketPage extends Page {

    @FindBy(css = ".cart_item")
    private List<WebElement> cartItemLocator;

    @FindBy(css = ".qty")
    private WebElement itemQuantityLocator;

    @FindBy(css = "button[name='update_cart']")
    private WebElement updateBasketButtonLocator;

    @FindBy(css = "#coupon_code")
    private WebElement couponInputLocator;

    @FindBy(css = "button[name='apply_coupon']")
    private WebElement applyCouponButtonLocator;

    @FindBy(css = ".woocommerce-error")
    private WebElement couponErrorLocator;

    @FindBy(css = ".order-total")
    private WebElement totalAmountLocator;

    @FindBy(css = ".cart-empty")
    private WebElement basketIsEmptyTitleLocator;

    @FindBy(css = ".blockUI")
    private List<WebElement> loader;


    public BasketPage(WebDriver browser, WebDriverWait wait) {
        super(browser, wait);
        PageFactory.initElements(browser, this);
        jsExecutor = (JavascriptExecutor)browser;
    }

    public Integer getBasketItemQuantity() {
        return cartItemLocator.size();
    }

    public void increaseQuantityByOne() {
        itemQuantityLocator.sendKeys(Keys.ARROW_UP);
    }

    public void reduceQuantityByOne() {
        itemQuantityLocator.sendKeys(Keys.ARROW_DOWN);
    }

    public void updateBasket() {
        updateBasketButtonLocator.click();
        wait.until(ExpectedConditions.invisibilityOfAllElements(loader));
    }

    public String getItemQuantity() {
        return itemQuantityLocator.getAttribute("value");
    }

    public String getTotalAmount() {
        return totalAmountLocator.getText();
    }

    public String getEmptyBasketTitle() {
        return basketIsEmptyTitleLocator.getText();
    }
    
    public void inputCoupon(String promocode) {
        couponInputLocator.sendKeys(promocode);
    }

    public void applyCoupon() {
        applyCouponButtonLocator.click();
    }

    public String getCouponErrorText() {
        return couponErrorLocator.getText();
    }
}
