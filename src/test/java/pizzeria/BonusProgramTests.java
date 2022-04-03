package pizzeria;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pizzeria.pages.BonusProgramPage;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BonusProgramTests extends TestBase {

    @Test
    @Order(1)
    /*Открытие страницы бонусная программа, ввод имени и номера телефона, нажатие кнопки "Оформить карту"
      копирование текста из алерта и нажатие кнопки "Ок" в алерте, установка таймута 8 сек. (лоадер)
      Проверка, что текст алерта верный и надпись "Ваша карта оформлена!" отобразилась */
    public void bonusProgram_positiveCase() {
        var bonusUsername = "Stepan";
        var bonusPhoneNumber = "89801234567";

        var page = new BonusProgramPage(browser, wait);
        page.open();
        page.inputBonusUsername(bonusUsername);
        page.inputBonusPhoneNumber(bonusPhoneNumber);
        page.clickSubmitButton();
        var alert = browser.switchTo().alert();
        var alertText = alert.getText();
        alert.accept();
        setTimeout(8);

        assertAll(
                () -> assertEquals("Заявка отправлена, дождитесь, пожалуйста, оформления карты!", alertText,
                        "Joining to bonus program is not working"),
                () -> assertEquals("Ваша карта оформлена!", page.getBonusProgramText(),
                        "Bonus program card is not prepared")
        );
    }

    @Test
    @Order(2)
    /*Открытие страницы бонусная программа, поля остаются пустыми, нажатие кнопки "Оформить карту"
      Проверка текста ошибки */
    public void bonusProgram_emptyFields_checkErrorMessage() {

        var page = new BonusProgramPage(browser, wait);
        page.open();
        page.clickSubmitButton();

        assertEquals("Поле \"Имя\" обязательно для заполнения\nПоле \"Телефон\" обязательно для заполнения",
                page.getErrorMessageText(), "Wrong error message");
    }

    @Test
    @Order(3)
    /*Открытие страницы бонусная программа, ввод имени и некорректного номера телефона, нажатие кнопки "Оформить карту"
      Проверка текста ошибки */
    public void bonusProgram_wrongPhoneFormat_checkErrorMessage() {
        var bonusUsername = "Stepan";
        var bonusPhoneNumber = "555";

        var page = new BonusProgramPage(browser, wait);
        page.open();
        page.inputBonusUsername(bonusUsername);
        page.inputBonusPhoneNumber(bonusPhoneNumber);
        page.clickSubmitButton();

        assertEquals("Введен неверный формат телефона", page.getErrorMessageText(),
                "Wrong error message");
    }
}
