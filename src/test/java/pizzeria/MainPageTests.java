package pizzeria;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pizzeria.pages.DessertPage;
import pizzeria.pages.MainPage;

import java.util.stream.Stream;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainPageTests extends TestBase {

    @Test
    @Order(1)
    public void slidePizzasToRight() {

        var page = new MainPage(browser, wait);
        page.open();
        page.waitForAllItemsLoaded(page.pizzaLocator);
        var defaultFirstPizzaTitle = page.getFirstPizzaTitle();
        page.oneTimeSlidePizzasToRight();

        assertNotEquals(defaultFirstPizzaTitle, page.getFirstPizzaTitle(),
                "Slide pizzas to right is not working");
    }

    @Test
    @Order(2)
    public void slidePizzasToLeft() {

        var page = new MainPage(browser, wait);
        page.open();
        page.waitForAllItemsLoaded(page.pizzaLocator);
        var defaultFirstPizzaTitle = page.getFirstPizzaTitle();
        page.oneTimeSlidePizzasToLeft();

        assertNotEquals(defaultFirstPizzaTitle, page.getFirstPizzaTitle(),
                "Slide pizzas to left is not working");
    }

    @Test
    @Order(3)
    public void moveCursorToFirstBeverage_checkAddToBasketButtonVisibility() {

        var page = new MainPage(browser, wait);
        page.open();
        page.scrollToSection(page.beverageLocator);
        page.waitForAllItemsLoaded(page.beverageLocator);
        page.moveCursorToFirstBeverage();

        assertAll(
                () -> assertTrue(page.addToBasketButtonIsDisplayed(), "Button 'Add to basket' is not displayed"),
                () -> assertEquals("В КОРЗИНУ", page.getAddToButtonText(),
                        "Title of button 'Add to basket' is incorrect")
        );
    }

    @Test
    @Order(4)
    public void clickDessert_checkDessertPage() {

        var page = new MainPage(browser, wait);
        page.open();
        page.scrollToSection(page.dessertLocator);
        page.waitForAllItemsLoaded(page.dessertLocator);
        page.clickFirstDessert();
        var itemPage = new DessertPage(browser, wait);

        assertEquals("Десерт «Булочка с корицей»", itemPage.getItemTitle(),
                "Dessert Page is not opened");
    }

    @Test
    @Order(5)
    public void scrollToPageBottom_arrowUpIsDisplayed() {

        var page = new MainPage(browser, wait);
        page.open();
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

    @Order(6)
    @ParameterizedTest
    @MethodSource("socialNetworkLinks")
    public void socialNetworkLinksIsOpenedInNewWindow(String socialNetwork) {

        var page = new MainPage(browser, wait);
        page.open();
        page.goToPageBottom();
        page.clickSocialNetworkLink(socialNetwork);

        assertEquals(2, page.getTabsQuantity(), format("New window wasn't opened for '%s'", socialNetwork));
    }

    private static Stream<Arguments> menuItems() {
        return Stream.of (
                arguments("Доставка и оплата"),
                arguments("Акции"),
                arguments("О нас"),
                arguments("Корзина"),
                arguments("Мой аккаунт"),
                //arguments("Оформление заказа"),
                arguments("Бонусная программа")
        );
    }

    @Order(6)
    @ParameterizedTest
    @MethodSource("menuItems")
    public void mainMenuRedirect(String itemName) {

        var page = new MainPage(browser, wait);
        page.open();
        page.goToMenu(itemName);

        assertEquals(itemName.toLowerCase(), page.getPageTitle_redirectViaMenu().toLowerCase(),
                format("Redirect to menu '%s' is not working", itemName));
    }

    private static Stream<Arguments> submenuItems() {
        return Stream.of (
                arguments("Пицца"),
                arguments("Десерты"),
                arguments("Напитки")
        );
    }

    @Order(7)
    @ParameterizedTest
    @MethodSource("submenuItems")
    public void mainMenu_submenuRedirect(String itemName) {

        var page = new MainPage(browser, wait);
        page.open();
        page.goToSubmenu(itemName);

        assertEquals(itemName.toLowerCase(), page.getPageTitle_redirectViaSubmenu().toLowerCase(),
                format("Redirect to submenu '%s' is not working", itemName));
    }
}
