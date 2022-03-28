package pizzeria;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pizzeria.pages.BasketPage;
import pizzeria.pages.PizzaPage;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BasketTests extends TestBase {

    @Test
    @Order(1)
    public void increaseItemQuantityInBasket_totalAmountIsChanged() {

        var basketPage = openPizzasPage_addPizzaToCart_goToCart();

        var quantityBeforeChange = basketPage.getItemQuantity();
        var amountBeforeChange = basketPage.getTotalAmount();
        basketPage.increaseQuantityByOne();
        basketPage.updateBasket();

        assertAll(
                () -> assertNotEquals(quantityBeforeChange, basketPage.getItemQuantity(),
                        "Quantity the same after change"),
                () -> assertNotEquals(amountBeforeChange, basketPage.getTotalAmount(),
                        "Amount the same after item quantity change")
        );
    }

    @Test
    @Order(2)
    public void reduceItemQuantityInBasket_basketIsEmpty() {

        var basketPage = openPizzasPage_addPizzaToCart_goToCart();

        basketPage.reduceQuantityByOne();
        basketPage.updateBasket();

        assertEquals("Корзина пуста.", basketPage.getEmptyBasketTitle(),
                        "Item quantity reduction is not working");
    }

    @Test
    @Order(3)
    public void applyCouponInBasket_couponIsNotFound() {

        var basketPage = openPizzasPage_addPizzaToCart_goToCart();

        basketPage.inputCoupon("promo");
        basketPage.applyCoupon();

        assertEquals("Неверный купон.", basketPage.getCouponErrorText(),
                "Coupon applying is not working");
    }

    public BasketPage openPizzasPage_addPizzaToCart_goToCart() {

        var page = new PizzaPage(browser, wait);
        page.open();
        page.addPizzaToBasketByIndex(0);
        page.clickCartButton();
        return new BasketPage(browser, wait);
    }
}
