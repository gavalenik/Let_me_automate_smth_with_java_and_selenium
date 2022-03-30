package pizzeria;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class TestBase {

    protected WebDriver browser;
    protected WebDriverWait wait;
    protected Integer defaultTimeout = 4;


    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        var options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        browser = new ChromeDriver(options);
        wait = new WebDriverWait(browser, Duration.ofSeconds(defaultTimeout));
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(defaultTimeout));
    }

    @AfterEach
    public void tearDown() throws IOException {
        try{
            takeScreenshot();
        } catch (UnhandledAlertException alertException) {
            Alert alert = browser.switchTo().alert();
            alert.accept();
            takeScreenshot();
        }
        browser.quit();
    }

    private void takeScreenshot() throws IOException {
        var sourceFile = ((TakesScreenshot) browser).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File("c:\\tmp\\screenshot.png"));
    }

    public void setTimeout(Integer timeout) {
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }
}
