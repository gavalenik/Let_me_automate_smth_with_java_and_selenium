package pizzeria;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pizzeria.pages.BonusProgramPage;
import pizzeria.pages.MyAccountPage;

import static java.lang.System.getProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BonusProgramTests extends TestBase {

    @Test
    @Order(1)
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

        assertEquals("Заявка отправлена, дождитесь, пожалуйста, оформления карты!", alertText,
                "Bonus program joining is not working");
    }
}
