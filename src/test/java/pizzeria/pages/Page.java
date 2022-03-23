package pizzeria.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Page {

    protected WebDriver browser;
    protected WebDriverWait wait;
    protected JavascriptExecutor jsExecutor;

    protected Integer defaultTimeout = 4;


    public Page(WebDriver browser, WebDriverWait wait) {
        this.browser = browser;
        this.wait = wait;
        PageFactory.initElements(browser, this);
    }

    public void setTimeout(Integer timeout) {
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }
}