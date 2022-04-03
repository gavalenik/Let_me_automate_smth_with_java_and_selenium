package pizzeria;

import org.junit.jupiter.api.Test;
import pizzeria.pages.DeliveryAndPaymentPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryAndPaymentTests extends TestBase {

    @Test
    /*Открытие страницы доставка и оплата
      Проверка присутствия текста на странице */
    public void deliveryAndPayment_checkOrderAmountInformation() {

        var page = new DeliveryAndPaymentPage(browser, wait);
        page.open();
        page.switchToFrame();

        assertEquals("Минимальная сумма заказа 800 рублей.", page.getOrderAmountInfo(),
                "Order Amount Information is incorrect");
    }
}
