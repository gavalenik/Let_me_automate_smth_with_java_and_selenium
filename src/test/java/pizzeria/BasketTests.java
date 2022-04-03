package pizzeria;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pizzeria.pages.BasketPage;
import pizzeria.pages.DessertPage;
import pizzeria.pages.PizzaPage;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BasketTests extends TestBase {

    @Test
    @Order(1)
    /*Открытие страницы с пиццами, добавление первой пиццы в козину, переход в корзину
      увеличение количества товаров в корзине на 1, обновление корзины.
      Проверка, что количество товаров и сумма заказа до изменения не равны
      количеству товаров и сумме после изменения */
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
    /*Открытие страницы с пиццами, добавление первой пиццы в козину, переход в корзину
      уменьшение количества товаров в корзине на 1, обновление корзины.
      Проверка, что корзина пуста */
    public void reduceItemQuantityInBasket_basketIsEmpty() {

        var basketPage = openPizzasPage_addPizzaToCart_goToCart();
        basketPage.reduceQuantityByOne();
        basketPage.updateBasket();

        assertEquals("Корзина пуста.", basketPage.getEmptyBasketTitle(),
                        "Item quantity reduction is not working");
    }

    @Test
    @Order(3)
    /*Открытие страницы с пиццами, добавление первой пиццы в козину, переход в корзину
      ввод купона "GIVEMEHALYAVA".
      Проверка, что отображается сообщение "Coupon code applied successfully." и купон присутствует в расчетах */
    public void applyCouponInBasket_couponIsFound() {

        var basketPage = openPizzasPage_addPizzaToCart_goToCart();
        basketPage.inputCoupon("GIVEMEHALYAVA");
        basketPage.applyCoupon();

        assertAll(
                () -> assertEquals("Coupon code applied successfully.",
                        basketPage.getCouponApplyingMessageText(), "Coupon applying is not working"),
                () -> assertTrue(basketPage.promoCodExistsInCartTotal(),
                        "Coupon is not displayed in cart total")
        );
    }

    @Test
    @Order(4)
    /*Открытие страницы с пиццами, добавление первой пиццы в козину, переход в корзину
      ввод купона "promo".
      Проверка, что отображается сообщение "неверный купон" */
    public void applyCouponInBasket_couponIsNotFound() {

        var basketPage = openPizzasPage_addPizzaToCart_goToCart();
        basketPage.inputCoupon("promo");
        basketPage.applyCoupon();

        assertEquals("Неверный купон.", basketPage.getCouponApplyingErrorText(),
                "Coupon applying is not working");
    }

    @Test
    @Order(5)
    /*Добавление в корзину двух разных товаров, удаление первого товара и восстановление его в корзине
      Проверка что в корзине 2 вида товаров */
    public void applyCouponInBasket_couponIsNotFound123123() {

        var dessertPage = new DessertPage(browser, wait);
        dessertPage.open();
        dessertPage.addFirstDessertToBasket();
        var pizzaPage = new PizzaPage(browser, wait);
        pizzaPage.open();
        pizzaPage.addFirstPizzaToBasket();
        pizzaPage.clickCartButton();
        var basketPage =  new BasketPage(browser, wait);
        basketPage.removeFirstBasketItem();
        basketPage.restoreBasketItem();

        assertEquals(2, basketPage.getBasketItemQuantity(), "Basket doesn't contain 2 items");
    }

    /*Метод - прекондишн для тестов корзины
      Открытие страницы с пиццами, добавление первой пиццы в козину, переход в корзину */
    public BasketPage openPizzasPage_addPizzaToCart_goToCart() {

        var pizzaPage = new PizzaPage(browser, wait);
        pizzaPage.open();
        pizzaPage.addFirstPizzaToBasket();
        pizzaPage.clickCartButton();
        return new BasketPage(browser, wait);
    }
}
