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
    /*Открытие главной страницы, ожидание загрузки всех продуктов, скрол ассортимента пиц вправо на одну
      Проверка, что имя первой пиццы до скрола и после не одинаковые */
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
    /*Открытие главной страницы, ожидание загрузки всех продуктов, скрол ассортимента пиц влево на одну
      Проверка, что имя первой пиццы до скрола и после не одинаковые */
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
    /*Открытие главной страницы, переход к секции напитков и ожидание загрузки всех продуктов
      наведение курсора мыши на первый напиток
      Проверка, что при наведении курсора отображается кнопка "В корзину" */
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
    /*Открытие главной страницы, переход к секции десертов и ожидание загрузки всех продуктов, клик по первому десерту
      Проверка, что открылась страница десерта */
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
    /*Открытие главной страницы, ожидание загрузки всех пицц, скролл к футеру страницы
      Проверка, что отобразилась стрелка "вверх" в правом нижнем углу */
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
    /*Открытие главной страницы, скролл к футеру страницы, клик по ссылке на социальную сеть
      Проверка, что социальная сеть открылась в новой вкладке */
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

    @Order(7)
    @ParameterizedTest
    @MethodSource("menuItems")
    /*Открытие главной страницы, переход по меню
      Проверка, что переход по пунктам меню отрабатывает */
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

    @Order(8)
    @ParameterizedTest
    @MethodSource("submenuItems")
    /*Открытие главной страницы, переход по подпунктам пункта "Меню"
      Проверка, что переход по подпунктам меню отрабатывает */
    public void mainMenu_submenuRedirect(String itemName) {

        var page = new MainPage(browser, wait);
        page.open();
        page.goToSubmenu(itemName);

        assertEquals(itemName.toLowerCase(), page.getPageTitle_redirectViaSubmenu().toLowerCase(),
                format("Redirect to submenu '%s' is not working", itemName));
    }

    private static Stream<Arguments> searchItems() {
        return Stream.of (
                arguments("рай"),
                arguments("шок")
        );
    }

    @Order(9)
    @ParameterizedTest
    @MethodSource("searchItems")
    /*Открытие главной страницы, поиск товара
      Проверка перехода на страницу товара */
    public void searchForItem_checkItemTitle(String searchItem) {

        var page = new MainPage(browser, wait);
        page.open();
        page.searchFor(searchItem);

        assertTrue(page.itemTitlesContainsSearchItem(searchItem),
                format("Redirect to submenu '%s' is not working", searchItem));
    }
}
