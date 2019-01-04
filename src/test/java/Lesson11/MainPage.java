package Lesson11;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends Page {
    @FindBy(css = "div.content div.name")
    public WebElement firstItem;

    public MainPage (WebDriver driver) {
        super(driver);
    }
    @Override
    public void open(){
        driver.get("http://localhost/litecart/en/");
    }

    public void chooseFirstItem() {
        firstItem.click();
    }


}
