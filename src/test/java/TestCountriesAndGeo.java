import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
public class TestCountriesAndGeo{
    private WebDriver driver;
    private WebDriverWait wait;
    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }
    @Test
    public void signIn() throws InterruptedException {

        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        String searchPage = "Countries";
        searchAndClick(searchPage);
        Countries();

        searchPage = "Geo Zones";
        searchAndClick(searchPage);
        Geo_Zones();
    }
    private void Geo_Zones() {
        String name, zone;
        String root = "td#content table.dataTable tbody";
        String table = ".//tr/td[3]//option[@selected='selected']";
        int count = driver.findElements(By.cssSelector("td#content table.dataTable tr.row")).size();
        for (int i=1; i<=count; i++) {
            zone = ".//tr[@class='row'][" + i + "]/td[3]/a";
            name = currentWE(root, zone).getAttribute("textContent");
            currentWE(root, zone).click();
            checkSortInList(root, table, name, true, true);
            buttonCancelClick();
            driver.findElement(By.cssSelector(root));
        }
    }
    private void Countries() {

        String name = "Countries";
        String root = "td#content table.dataTable tbody";
        String table = "./tr[@class='row']/td[5]/a";
        checkSortInList(root, table, name, true, true);

        table = "./tr[@class='row']/td[6]";
        List<WebElement> listWE= fillListWE(root, table);
        List<String> listS = fillList(listWE, true, true);
        int i = 1;
        for (String zone:listS){
            if (!zone.equals("0")) {
                table = "./tr[@class='row'][" + i +"]/td[5]/a";
                name = currentWE(root, table).getAttribute("textContent");
                currentWE(root, table).click();
                table = "./tr/td[3]";
                checkSortInList(root, table, name, false, false);
                buttonCancelClick();
                driver.findElement(By.cssSelector(root));
            }
            i++;
        }
    }
    private void buttonCancelClick () {
        driver.findElement(By.cssSelector("td#content p button[name=cancel]")).click();
    }
    private WebElement currentWE(String rootLoc, String element) {
        WebElement root = driver.findElement(By.cssSelector(rootLoc));
        return root.findElement(By.xpath(element));
    }
    private void checkSortInList(String rootLoc, String tableLoc, String name, boolean first, boolean last){
        List<WebElement> listWE = fillListWE(rootLoc, tableLoc);
        List<String> list = fillList(listWE, first, last);
        printResult(isListSorted(list), name);
    }
    private List<WebElement> fillListWE(String rootLoc, String tableLoc){
        WebElement root = driver.findElement(By.cssSelector(rootLoc));
        List<WebElement> listWE = root.findElements(By.xpath(tableLoc));
        return listWE;
    }
    private List<String> fillList(List<WebElement> listWE, boolean first, boolean last) {
        List<String> resultList = new ArrayList<>();
        for (WebElement we:listWE)
            resultList.add(we.getAttribute("textContent"));
        if (!first) resultList.remove(0);
        if (!last) resultList.remove(resultList.size()-1);
        return resultList;
    }
    private boolean isListSorted(List<String> list) {
        //list.set(5,"Mordor");
        String previous = list.get(0);
        String current;
        boolean result = true;
        for (int i=1; i<list.size(); i++) {
            current = list.get(i);
            if (current.compareTo(previous) < 0) {
                result = false;
                System.out.println(previous + " " + current);
                break;
            }
            previous = current;
        }
        return result;
    }
    private void searchAndClick(String item) {
        System.out.println();
        System.out.println("*** This is check sorting in " + item + " page.");
        String locator="", currentPage = "";
        int i = 0;
        while( !currentPage.equals(item) ) {
            i++;
            locator = "ul#box-apps-menu li#app-:nth-child(" + i + ")";
            if (isElementPresent(driver, By.cssSelector(locator)) )
                currentPage = driver.findElement(By.cssSelector(locator)).getText();
        }
        driver.findElement(By.cssSelector(locator)).click();
    }
    private void printResult(boolean result, String page) {
        String s = "WRONG";
        if (result)
            s = "CORRECT";
        System.out.println("*** The list on page for " + page + " has the " + s + " sorting");
    }
    boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}