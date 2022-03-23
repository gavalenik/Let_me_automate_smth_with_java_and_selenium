package pizzeria;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pizzeria.pages.DessertPage;
import pizzeria.pages.MainPage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainPageTests extends TestBase {

    @Test
    @Order(1)
    public void slidePizzasToRight() {

        var page = new MainPage(browser, wait)
                .open();
        page.waitForAllItemsLoaded(page.pizzaLocator);
        var defaultFirstPizzaTitle = page.getPizzaTitleByIndex(0);
        page.slidePizzasTo(page.slideRightLocator);

        assertNotEquals(defaultFirstPizzaTitle, page.getPizzaTitleByIndex(0),
                "Slide pizzas to right is not working");
    }

    @Test
    @Order(2)
    public void slidePizzasToLeft() {

        var page = new MainPage(browser, wait)
                .open();
        page.waitForAllItemsLoaded(page.pizzaLocator);
        var defaultFirstPizzaTitle = page.getPizzaTitleByIndex(0);
        page.slidePizzasTo(page.slideLeftLocator);

        assertNotEquals(defaultFirstPizzaTitle, page.getPizzaTitleByIndex(0),
                "Slide Pizzas to left is not working");
    }

    @Test
    @Order(3)
    public void moveCursorToFirstBeverage_checkAddToBasketButtonVisibility() {

        var page = new MainPage(browser, wait)
                .open();
        page.scrollToSection(page.beverageLocator);
        page.waitForAllItemsLoaded(page.beverageLocator)
                .moveCursorToFirstBeverage();

        assertAll(
                () -> assertEquals("В КОРЗИНУ", page.getAddToButtonText(),
                "Title of button 'Add to basket' is incorrect"),
                () -> assertTrue(page.addToBasketButtonIsDisplayed(), "Button 'Add to basket' is not displayed")
        );
    }

    @Test
    @Order(4)
    public void clickDessert_checkDessertPage() {

        var page = new MainPage(browser, wait)
                .open();
        page.scrollToSection(page.dessertLocator);
        page.clickDessertByIndex(0);
        var itemPage = new DessertPage(browser, wait);

        assertEquals("Десерт «Булочка с корицей»", itemPage.getItemTitle(),
                "Dessert Page is not opened");
    }

    @Test
    @Order(5)
    public void scrollToPageBottom_arrowUpIsDisplayed() {

        var page = new MainPage(browser, wait)
                .open();
        page.waitForAllItemsLoaded(page.pizzaLocator);
        page.goToPageBottom();

        assertTrue(page.arrowUpIsDisplayed(), "Arrow 'Scroll to up' is not displayed");
    }

    private static Stream<Arguments> socialNetworkLinks() {
        return Stream.of (
                arguments("facebook.com"),
                arguments("vk.com"),
                arguments("instagram.com")
        );
    }

    @ParameterizedTest
    @Order(6)
    @MethodSource("socialNetworkLinks")
    public void socialNetworkLinksIsOpenedInNewWindow(String socialNetworkLink) {

        var page = new MainPage(browser, wait)
                .open()
                .goToPageBottom()
                .clickSocialNetworkLink(socialNetworkLink);

        assertEquals(2, page.getWindowsQuantity(),
                String.format("New window wasn't opened for '%s'", socialNetworkLink));
    }
}
