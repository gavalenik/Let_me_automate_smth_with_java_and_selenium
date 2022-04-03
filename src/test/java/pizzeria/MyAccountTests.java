package pizzeria;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pizzeria.pages.MyAccountPage;

import static java.lang.System.getProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MyAccountTests extends TestBase {

    private final String userLogin = "regularUser";
    private final String userPassword = "Qazwsx1!";
    //private final String userEmail = "banepa2647@f1xm.com";

    @Test
    @Order(1)
    /*Открытие страницы мой аккаунт, авторизация, переход к данным аккаунта, загрузка аватара, сохранение изменений
      Проверка отображения сообщения, что изменения успешно сохранены */
    public void accountData_uploadAvatar_checkSuccessfulMessage() {
        var filePath = getProperty("user.dir") + "\\src\\main\\resources\\avatar.jpg";

        var page = new MyAccountPage(browser, wait);
        page.open();
        page.inputLogin(userLogin);
        page.inputPassword(userPassword);
        page.clickSubmitButton();
        page.clickAccountDataButton();
        page.uploadAvatar(filePath);
        page.clickSaveChangesButton();

        assertEquals("Account details changed successfully.", page.getMessage(),
                "Avatar upload is not working");
    }

    @Test
    @Order(2)
    /*Открытие страницы мой аккаунт, нажатие кнопки "Выйти"
      Проверка отображения линки с текстом "Войти" */
    public void accountData_logout() {

        var page = new MyAccountPage(browser, wait);
        page.open();
        page.inputLogin(userLogin);
        page.inputPassword(userPassword);
        page.clickSubmitButton();
        page.clickLogoutButton();

        assertEquals("Войти", page.getLoginLogoutLinkLocator(), "Logout is not working");
    }
}
