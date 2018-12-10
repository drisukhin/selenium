import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class test2{
    private WebDriver driver;
    private WebDriverWait wait;
    @Before
    public void start() {

        driver= new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

    }
    @Test
    public void Stickers() {
        driver.get("http://localhost/litecart/en/");
        List<WebElement> products = driver.findElements(By.className("box-category"));
        for(WebElement el : products){
            Assert.assertEquals( el.findElements(By.className("sticker")).size(), 1);
        }
    }
    @After
    public void stop () {
        driver.quit();
        driver = null;

    }
}
