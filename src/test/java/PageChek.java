import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PageChek {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void Start()
    {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void myTest() throws Exception
    {
        // open mainpage of shop
        driver.get("http://localhost/litecart/");
        WebElement mainpage = driver.findElement(By.cssSelector("#box-campaigns .price-wrapper"));
        // creating a list of products attributes on mainpage
        String nameonmainpage = driver.findElement(By.cssSelector("#box-campaigns .product .name")).getAttribute("textContent");
        String regularprice = mainpage.findElement(By.cssSelector(".regular-price")).getAttribute("textContent");
        String campaignprice = mainpage.findElement(By.cssSelector(".campaign-price")).getAttribute("textContent");
        String regularpricecolor = mainpage.findElement(By.cssSelector(".regular-price")).getCssValue("color");
        String regularpricedec = mainpage.findElement(By.cssSelector(".regular-price")).getTagName();
        String regulapricefontsize = mainpage.findElement(By.cssSelector(".regular-price")).getCssValue("font-size");
        String campaignpricecolor = mainpage.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
        String campaignpricedec = mainpage.findElement(By.cssSelector(".campaign-price")).getTagName();
        String campaignpricefontsize = mainpage.findElement(By.cssSelector(".campaign-price")).getCssValue("font-size");

        // checking of each elemnt have only one sticker
        //System.out.println("Click on childpage");
        driver.findElement(By.cssSelector("#box-campaigns .link")).click();
        //wait.until(titleIs("Yellow Duck | Subcategory | Rubber Ducks | My Store"));

        // creating a list of products attributes on mainpage
        String nameonproductpage = driver.findElement(By.cssSelector("#box-product .title")).getAttribute("textContent");
        WebElement productpage = driver.findElement(By.cssSelector("#box-product .price-wrapper"));
        String regularpriceP = productpage.findElement(By.cssSelector(".regular-price")).getAttribute("textContent");
        String campaignpriceP = productpage.findElement(By.cssSelector(".campaign-price")).getAttribute("textContent");
        String regularpricecolorP = productpage.findElement(By.cssSelector(".regular-price")).getCssValue("color");
        String regularpricedecP = productpage.findElement(By.cssSelector(".regular-price")).getTagName();
        String regulapricefontsizeP = productpage.findElement(By.cssSelector(".regular-price")).getCssValue("font-size");
        String campaignpricecolorP = productpage.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
        String campaignpricedecP = productpage.findElement(By.cssSelector(".campaign-price")).getTagName();
        String campaignpricefontsizeP = productpage.findElement(By.cssSelector(".campaign-price")).getCssValue("font-size");


        // assert on а) на главной странице и на странице товара совпадает текст названия товара
        Assert.assertTrue(nameonmainpage.equals(nameonproductpage));
        // assert on б) на главной странице и на странице товара совпадают цены (обычная и акционная)
        Assert.assertTrue(regularprice.equals(regularpriceP));
        Assert.assertTrue(campaignprice.equals(campaignpriceP));
        // assert on в) обычная цена серая и зачёркнутая, а акционная цена красная и жирная
        // mainpage
        Assert.assertTrue(regularpricecolor.equals("rgba(119, 119, 119, 1)"));
        Assert.assertTrue(regularpricedec.equals("s"));
        Assert.assertTrue(campaignpricecolor.equals("rgba(204, 0, 0, 1)"));
        Assert.assertTrue(campaignpricedec.equals("strong"));
        // productpage
        Assert.assertTrue(regularpricecolorP.equals("rgba(102, 102, 102, 1)"));
        Assert.assertTrue(regularpricedecP.equals("s"));
        Assert.assertTrue(campaignpricecolorP.equals("rgba(204, 0, 0, 1)"));
        Assert.assertTrue(campaignpricedecP.equals("strong"));

        //assert on г) акционная цена крупнее, чем обычная
        Assert.assertTrue(Double.parseDouble(regulapricefontsize.replace("px","")) < Double.parseDouble(campaignpricefontsize.replace("px","")));
        Assert.assertTrue(Double.parseDouble(regulapricefontsizeP.replace("px","")) < Double.parseDouble(campaignpricefontsizeP.replace("px","")));

    }

    @After
    public void Stop()
    {
        driver.quit();
        driver = null;
    }

}