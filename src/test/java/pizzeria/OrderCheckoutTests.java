package pizzeria;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pizzeria.pages.BasketPage;
import pizzeria.pages.MyAccountPage;
import pizzeria.pages.OrderCheckoutPage;
import pizzeria.pages.PizzaPage;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderCheckoutTests extends TestBase {

    @Test
    @Order(1)
    /*Авторизация, добавление пиццы в корзину, переход к оформлению заказа, ввод персональных данных
      выбор оплаты наличкой, принятие соглашений, размещение заказа
      Проверка отображения сообщения "Заказ получен" */
    public void createOrderWithPaymentByCash() {

        var checkoutPage = authorization_addPizzaToCart_goToCart_StartCheckout_inputCustomerData();
        checkoutPage.choosePaymentByCash();
        checkoutPage.acceptTerms();
        checkoutPage.placeOrder();

        assertNotEquals("Заказ получен", checkoutPage.getOrderConfirmation(),
                        "Order hasn't been confirmed");
    }

    @Test
    @Order(2)
    /*Авторизация, добавление пиццы в корзину, переход к оформлению заказа, ввод персональных данных
      ввод даты заказа - завтрашний день, ввод комментария, выбор оплаты через банковский перевод,
      принятие соглашений, размещение заказа
      Проверка отображения сообщения "Заказ получен" */
    public void createOrderWithPaymentByBankTransfer_orderDateAndOrderComment() {

        var checkoutPage = authorization_addPizzaToCart_goToCart_StartCheckout_inputCustomerData();
        checkoutPage.inputOrderDateWithTomorrowDate();
        checkoutPage.inputOrderComment("Just a comment");
        checkoutPage.choosePaymentByBankTransfer();
        checkoutPage.acceptTerms();
        checkoutPage.placeOrder();

        assertNotEquals("Заказ получен", checkoutPage.getOrderConfirmation(),
                "Order hasn't been confirmed");
    }

    /*Метод - прекондишн для тестов оформления заказов
      Авторизация, добавление пиццы в корзину, переход к оформлению заказа, ввод персональных данных */
    public OrderCheckoutPage authorization_addPizzaToCart_goToCart_StartCheckout_inputCustomerData() {
        var userLogin = "regularUser";
        var userPassword = "Qazwsx1!";
        var streetAndBld = "ул. Ленина 1";
        var city = "Владивосток";
        var region = "Приморский край";
        var zip = "690000";
        var phone = "89401234567";

        var page = new MyAccountPage(browser, wait);
        page.open();
        page.inputLogin(userLogin);
        page.inputPassword(userPassword);
        page.clickSubmitButton();
        var pizzaPage = new PizzaPage(browser, wait);
        pizzaPage.open();
        pizzaPage.addFirstPizzaToBasket();
        pizzaPage.clickCartButton();
        var basketPage = new BasketPage(browser, wait);
        basketPage.clickCheckoutButton();
        var checkoutPage = new OrderCheckoutPage(browser, wait);
        checkoutPage.inputStreetAndBuildingNumber(streetAndBld);
        checkoutPage.inputCity(city);
        checkoutPage.inputRegion(region);
        checkoutPage.inputZip(zip);
        checkoutPage.inputPhone(phone);
        return checkoutPage;
    }
}
