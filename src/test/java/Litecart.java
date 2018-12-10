import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Litecart {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

    }

    @Test
    public void litecartTest() {
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }


    @Test
        public void testLeftMenuLinks() throws InterruptedException {
        litecartTest();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("name")));

        List<WebElement> leftMenuItems = driver.findElements(By.className("name"));

        List<String> leftMenuItemNames = getElementNames(leftMenuItems);

                for(String s : leftMenuItemNames){

            driver.findElement(By.xpath("//span[text()='" + s + "']")).click();
            Assert.assertTrue(driver.findElement(By.tagName("h1")).isDisplayed());

            List<WebElement> menuSubItems = driver.findElements(By.cssSelector(("[id^=doc-]")));

            List<String> leftSubMenuItemNames = getElementNames(menuSubItems);
            for(String se : leftSubMenuItemNames){

                driver.findElement(By.xpath("//span[text()='" + se + "']")).click();
                Assert.assertTrue(driver.findElement(By.tagName("h1")).isDisplayed());
            }
        }
    }
    @Test
    public void testLogoutAsAdmin() throws InterruptedException {
        litecartTest();
        driver.findElement(By.className("fa-sign-out")).click();
    }

    private List<String> getElementNames(List<WebElement> elements){
        List<String> names = new ArrayList<String>();
        for(WebElement e : elements){
            names.add(e.getText());
        }
        return names;

}

    @After
    public void stop() {
        driver.quit();
        driver = null;

    }


    }

