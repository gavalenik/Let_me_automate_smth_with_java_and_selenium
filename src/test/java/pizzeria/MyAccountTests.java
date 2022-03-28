package pizzeria;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pizzeria.pages.MyAccountPage;

import static java.lang.System.getProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MyAccountTests extends TestBase {

    @Test
    @Order(1)
    public void accountData_uploadAvatar_checkSuccessfulMessage() {
        var userLogin = "regularUser";
        var userPassword = "Qazwsx1!";
        //var userEmail = "banepa2647@f1xm.com";
        var filePath = getProperty("user.dir") + "\\src\\test\\java\\pizzeria\\avatar.jpg";

        var page = new MyAccountPage(browser, wait);
        page.open();
        page.inputLogin(userLogin);
        page.inputPassword(userPassword);
        page.clickSubmitButton();
        page.clickAccountDataButton();
        page.uploadAvatar(filePath);
        page.clickSaveChangesButton();

        assertEquals("Account details changed successfully.", page.getMessage(),
                "Upload avatar is not working");
    }
}
