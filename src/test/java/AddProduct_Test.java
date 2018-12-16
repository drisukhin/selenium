import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;


public class AddProduct_Test {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void before() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void addProduct(){
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.findElement(By.linkText("Catalog")).click();
        driver.findElement(By.linkText("Add New Product")).click();
        if ( !driver.findElement(By.cssSelector("input[type=radio]")).isSelected() )
        {
            driver.findElement(By.cssSelector("input[type=radio]")).click();
        }
        driver.findElement(By.name("name[en]")).sendKeys("ProductName");
        driver.findElement(By.name("code")).sendKeys("ProductCode");

        List<WebElement> categories = driver.findElements(By.name("categories[]"));
        for (WebElement checkbox : categories) {
            if ( !checkbox.isSelected() )
            {
                checkbox.click();
            }
        }

        Select defaultCategory = new Select(driver.findElement(By.name("default_category_id")));
        defaultCategory.selectByVisibleText("Subcategory");

        List<WebElement> productGroups = driver.findElements(By.name("product_groups[]"));
        for (WebElement checkbox : productGroups) {
            if ( !checkbox.isSelected() )
            {
                checkbox.click();
            }
        }

        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("100");

        File f = new File("src/test/java/duck.png");
        driver.findElement(By.name("new_images[]")).sendKeys(f.getAbsolutePath());

        driver.findElement(By.name("date_valid_from")).sendKeys("27/02/2017");

        driver.findElement(By.name("date_valid_to")).sendKeys("27/02/2018");

        driver.findElement(By.linkText("Information")).click();

        Select manufacturerId = new Select(driver.findElement(By.name("manufacturer_id")));
        manufacturerId.selectByVisibleText("ACME Corp.");

//        Select supplierId = new Select(driver.findElement(By.name("supplier_id")));
//        manufacturerId.selectByVisibleText("");

        driver.findElement(By.name("keywords")).sendKeys("keyword1, keyword2");

        driver.findElement(By.name("short_description[en]")).sendKeys("Short description");

        driver.findElement(By.className("trumbowyg-editor")).sendKeys("Description");

        driver.findElement(By.name("head_title[en]")).sendKeys("Head Title");

        driver.findElement(By.name("meta_description[en]")).sendKeys("Meta Description");

        driver.findElement(By.linkText("Prices")).click();

        driver.findElement(By.name("purchase_price")).clear();
        driver.findElement(By.name("purchase_price")).sendKeys("10");

        Select purchasePriceCurrencyCode = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        purchasePriceCurrencyCode.selectByVisibleText("US Dollars");

//        Select taxClassId = new Select(driver.findElement(By.name("tax_class_id")));
//        taxClassId.selectByVisibleText("");

        driver.findElement(By.name("save")).click();

        assertTrue(driver.findElement(By.linkText("ProductName")).isDisplayed());
    }

    @After
    public void after() {
        driver.quit();
        driver = null;
    }
}