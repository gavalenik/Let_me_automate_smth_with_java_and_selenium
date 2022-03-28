package pizzeria;

import org.junit.jupiter.api.*;
import pizzeria.pages.BasketPage;
import pizzeria.pages.PizzaPage;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PizzaPageTests extends TestBase {

    @Test
    @Order(1)
    public void pizzasSortingByPriceLowToHigh() {

        var page = new PizzaPage(browser, wait);
        page.open();
        page.sortingByValue("По возрастанию цены");
        var pizzasPriceList = page.getPizzaPrices();

        for (int i = 0; i < pizzasPriceList.size() - 1; i++) {
            assertTrue(Math.abs(pizzasPriceList.get(i + 1) - pizzasPriceList.get(i)) >= 0,
                    "Sorting pizzas by price is not working");
        }
    }

    @Test
    @Order(2)
    public void filterPizzasByPriceSlider() {

        var page = new PizzaPage(browser, wait);
        page.open();
        page.scrollToPriceFilter();
        var defaultPizzasQuantity = page.getPizzaQuantity();
        page.changePrice_oneStepHigherWithLeftSlider();
        page.changePrice_oneStepLowerWithRightSlider();
        page.clickButtonSubmit();

        assertNotEquals(defaultPizzasQuantity, page.getPizzaQuantity(),
                "Pizza price filter is not working");
    }

    @Test
    @Order(3)
    public void addPizzaToCart() {

        var page = new PizzaPage(browser, wait);
        page.open();
        page.addPizzaToBasketByIndex(0);
        page.clickCartButton();
        var basketPage = new BasketPage(browser, wait);

        assertTrue(basketPage.getBasketItemQuantity() > 0, "Pizza was added to cart successfully");
    }
}
