import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class OpenLink_Test {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

    }

    @Test
    public void OpeningLink(){


        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[name='countries_form']"))));

        driver.findElement(By.cssSelector("[title='Edit']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[contains(text(), 'Edit Country')]"))));

        String existingWindow = driver.getWindowHandle();
        System.out.println(existingWindow);

        List<WebElement> links = driver.findElements(By.className("fa-external-link"));
        int numberOflinks = links.size();
        for(int i = 0; i < numberOflinks; i++){
            links.get(i).click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            Set<String> windows = driver.getWindowHandles();
            windows.remove(existingWindow);
            String newWindow = windows.iterator().next();
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(existingWindow);
        }

    }
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
