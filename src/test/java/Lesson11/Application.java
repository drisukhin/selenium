package Lesson11;

import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Application {
    private WebDriver driver;
    private WebDriverWait wait;
    public MainPage mainPage;
    public CartPage cartPage;
    public ItemPage itemPage;

    public Application() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);
        itemPage = new ItemPage(driver);
    }

    @After
    public void quit() {
        driver.quit();
    }

}
