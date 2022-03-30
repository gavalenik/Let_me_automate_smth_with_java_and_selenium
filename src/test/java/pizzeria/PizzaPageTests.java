package pizzeria;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pizzeria.pages.BasketPage;
import pizzeria.pages.PizzaPage;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PizzaPageTests extends TestBase {

    @Test
    @Order(1)
    public void pizzasSortingByPriceLowToHigh() {

        var page = new PizzaPage(browser, wait);
        page.open();
        page.sortingItemsByValue("По возрастанию цены");
        var pizzasPriceList = page.getPizzaPrices();

        for (int i = 0; i < pizzasPriceList.size() - 1; i++) {
            assertTrue(Math.abs(pizzasPriceList.get(i + 1) - pizzasPriceList.get(i)) >= 0,
                    "Sorting pizzas by price 'low to high' is not working");
        }
    }

    @Test
    @Order(2)
    public void pizzasSortingByPriceHighToLow() {

        var page = new PizzaPage(browser, wait);
        page.open();
        var defaultOrderPizzasPriceList = page.getPizzaPrices();
        page.sortingItemsByValue("По убыванию цены");
        var newOrderPizzasPriceList = page.getPizzaPrices();
        page.sortListHighToLow(defaultOrderPizzasPriceList);

        assertEquals(defaultOrderPizzasPriceList, newOrderPizzasPriceList,
                    "Sorting pizzas by price 'high to low' is not working");
    }

    @Test
    @Order(3)
    public void filterPizzasByPriceSlider() {

        var page = new PizzaPage(browser, wait);
        page.open();
        page.scrollToPriceFilter();
        var defaultPizzasQuantity = page.getPizzaQuantity();
        page.changePrice_oneStepHigherWithLeftSlider();
        page.changePrice_oneStepLowerWithRightSlider();
        page.applyPriceFilter();

        assertNotEquals(defaultPizzasQuantity, page.getPizzaQuantity(),
                "Pizza price filter is not working");
    }

    @Test
    @Order(4)
    public void addPizzaToCart() {

        var page = new PizzaPage(browser, wait);
        page.open();
        page.addFirstPizzaToBasket();
        page.clickCartButton();
        var basketPage = new BasketPage(browser, wait);

        assertTrue(basketPage.getBasketItemQuantity() > 0, "Pizza was not added to cart");
    }
}
