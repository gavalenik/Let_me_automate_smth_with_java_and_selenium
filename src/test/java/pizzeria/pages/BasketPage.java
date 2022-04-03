package pizzeria.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfAllElements;

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

    @FindBy(css = ".woocommerce-message")
    private WebElement couponApplyingMessageLocator;

    @FindBy(css = ".woocommerce-error")
    private WebElement couponApplyingErrorLocator;

    @FindBy(css = ".order-total")
    private WebElement totalAmountLocator;

    @FindBy(css = ".cart-empty")
    private WebElement basketIsEmptyTitleLocator;

    @FindBy(css = ".blockUI")
    private List<WebElement> loaderLocator;

    @FindBy(css = ".checkout-button")
    private WebElement checkoutButtonLocator;

    @FindBy(css = ".cart-discount")
    private WebElement promoCodInCartTotalLocator;

    @FindBy(css = ".product-remove .remove")
    private List<WebElement> removeItemIconLocator;

    @FindBy(css = ".restore-item")
    private WebElement restoreBasketItemLocator;


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
        wait.until(invisibilityOfAllElements(loaderLocator));
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

    public String getCouponApplyingMessageText() {
        return couponApplyingMessageLocator.getText();
    }

    public String getCouponApplyingErrorText() {
        return couponApplyingErrorLocator.getText();
    }

    public void clickCheckoutButton() {
        checkoutButtonLocator.click();
    }

    public Boolean promoCodExistsInCartTotal() {
        return promoCodInCartTotalLocator.isDisplayed();
    }

    public void removeFirstBasketItem() {
        removeItemIconLocator.get(0).click();
        wait.until(invisibilityOfAllElements(loaderLocator));
    }

    public void restoreBasketItem() {
        restoreBasketItemLocator.click();
        wait.until(invisibilityOfAllElements(loaderLocator));
    }
}
