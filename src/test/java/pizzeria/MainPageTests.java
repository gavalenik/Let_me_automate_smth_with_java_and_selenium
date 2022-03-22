package pizzeria;

import org.junit.jupiter.api.*;
import pizzeria.pages.MainPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainPageTests extends TestBase {

    @Test
    @Order(1)
    public void slidePizzasToRight() {

        var page = new MainPage(browser, wait)
                .open()
                .waitForAllPizzasLoaded();
        var defaultFirstPizzaTitle = page.getFirstPizzaTitle();
        page.slidePizzasToRight();

        Assertions.assertNotEquals(defaultFirstPizzaTitle, page.getFirstPizzaTitle(),
                "Slide to right is not working");
    }

    @Test
    @Order(2)
    public void slidePizzasToLeft() {

        var page = new MainPage(browser, wait)
                .open()
                .waitForAllPizzasLoaded();
        var defaultFirstPizzaTitle = page.getFirstPizzaTitle();
        page.slidePizzasToLeft();

        Assertions.assertNotEquals(defaultFirstPizzaTitle, page.getFirstPizzaTitle(),
                "Slide to left is not working");
    }

    @Test
    @Order(3)
    public void login() {
        var login = "regularUser";
        var password = "Qazwsx1!";
        var email = "banepa2647@f1xm.com";

        var page = new MainPage(browser, wait)
                .open()
                .waitForAllPizzasLoaded()
                .clickInputLink();
    }
}
