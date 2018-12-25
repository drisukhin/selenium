import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;


public class CheckPageTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);


    }

    @Test
    public void productTest() throws InterruptedException {
            driver.get("http://localhost/litecart/admin/login.php");
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("admin");
            driver.findElement(By.name("login")).click();

      driver.get ("http://localhost/litecart/en/");

        String mainName = driver.findElement(
                By.cssSelector("#box-campaigns .product:first-child .name")).getText();
        String mainRegPrice = driver.findElement(
                By.cssSelector("#box-campaigns .product:first-child .regular-price")).getText();
        String mainCampPrice = driver.findElement(
                By.cssSelector("#box-campaigns .product:first-child .campaign-price")).getText();
        getStyleAndColorProduct("#box-campaigns");



        driver.findElement(By.cssSelector("#box-campaigns > div > ul > li > a.link")).click();
        String duckName = driver.findElement(
                By.cssSelector("#box-product h1")).getText();
        String duckRegPrice = driver.findElement(
                By.cssSelector("#box-product .regular-price")).getText();
        String duckCampPrice = driver.findElement(
                By.cssSelector("#box-product .campaign-price")).getText();
        getStyleAndColorProduct("#box-product");



        Assert.assertEquals(mainName, duckName);
        Assert.assertEquals(mainRegPrice, duckRegPrice);
        Assert.assertEquals(mainCampPrice, duckCampPrice);

    }


    public void getStyleAndColorProduct(String selector ){


        WebElement oldPrice = driver.findElement(By.cssSelector(selector+" s"));
        String colorOldPrice = oldPrice.getCssValue("color");
        float sizeOldPrice = Float.parseFloat(oldPrice.getCssValue(
                "font-size").replaceAll("[a-zA-Z]", ""));

        WebElement  newPrice = driver.findElement(By.cssSelector(selector+" strong"));
        String colorNewPrice = newPrice.getCssValue("color");
        float sizeNewPrice = Float.parseFloat(newPrice.getCssValue(
                "font-size").replaceAll("[a-zA-Z]", ""));

        Assert.assertTrue("New price more old!",
                sizeNewPrice > sizeOldPrice);
        Assert.assertTrue("New price style is not fat",
                newPrice.getTagName().equals("strong"));

        Assert.assertTrue(oldPrice.getTagName().equals("s"));

        isGreyColor(colorOldPrice);
        isRedColor(colorNewPrice);

    }


    public void isGreyColor(String color){
        String[] isGray = color.replaceAll("[a-zA-Z()]","").split(", ");
        Assert.assertEquals(isGray[0], isGray[1]);
        Assert.assertEquals(isGray[0], isGray[2]);
        Assert.assertEquals(isGray[1], isGray[2]);
    }

    public void  isRedColor(String color){

        String[] isRed = color.replaceAll("[a-zA-Z()]","").split(", ");
        Assert.assertTrue("Color does not match", Integer.parseInt(isRed[0]) != 0);
        Assert.assertTrue("Color does not match",Integer.parseInt(isRed[1]) == 0);
        Assert.assertTrue("Color does not match",Integer.parseInt(isRed[2]) == 0);
    }
    @After
    public void afterTest() {
        driver.quit();
        driver = null;

    }
} 