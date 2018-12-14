import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;

public class CreateAccount_Test {
    private WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);



}
@Test
public void main() throws InterruptedException {

        driver.get("http://localhost/litecart/en/");

    String email = System.currentTimeMillis() / 1000L + "@testmail.com";
    String password="bobzen1823";

    CreateAccount(email,password);
    Thread.sleep(1000);

    logout();
    Thread.sleep(1000);

    login(email, password);
    Thread.sleep(1000);

    logout();
    Thread.sleep(1000);
}

private void CreateAccount(String email, String password){

        driver.findElement(By.xpath("//a[contains(text(),'New customers click here')]")).click();
    driver.findElement(By.name("firstname")).sendKeys("Shaul");
    driver.findElement(By.name("lastname")).sendKeys("Memshala");
    driver.findElement(By.name("address1")).sendKeys("Jabotinsky street");
    driver.findElement(By.name("postcode")).sendKeys("27001");
    driver.findElement(By.name("city")).sendKeys("Los Angeles");

    Select country = new Select(driver.findElement(By.name("country_code")));
    country.selectByVisibleText("United States");

    Select zone = new Select(driver.findElement(By.cssSelector("select[name='zone_code']")));
    zone.selectByVisibleText("California");

    driver.findElement(By.name("email")).sendKeys(email);
    driver.findElement(By.name("phone")).sendKeys("+1213589630120");
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.name("confirmed_password")).sendKeys(password);
    driver.findElement(By.name("create_account")).click();
}
    private void login(String email, String password) {
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
    }

    private void logout() {
        driver.findElement(By.cssSelector("div#box-account div.content li:last-child a")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

