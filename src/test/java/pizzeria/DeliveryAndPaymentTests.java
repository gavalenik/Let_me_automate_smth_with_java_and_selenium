package pizzeria;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pizzeria.pages.DeliveryAndPaymentPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeliveryAndPaymentTests extends TestBase {

    @Test
    @Order(1)
    public void deliveryAndPayment_checkOrderAmountInformation() {

        var page = new DeliveryAndPaymentPage(browser, wait);
        page.open();
        page.switchToFrame();

        assertEquals("Минимальная сумма заказа 800 рублей.", page.getOrderAmountInfo(),
                "Order Amount Information is incorrect");
    }
}
