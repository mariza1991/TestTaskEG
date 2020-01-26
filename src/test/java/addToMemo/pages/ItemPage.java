package addToMemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ItemPage extends Page<ItemPage>{

    private static final By FIRST_ITEM = By.xpath("//*[contains(@id,'tr_')][1]");

    private static final String ALL_ITEMS = "//*[contains(@id,'tr_') and not(contains(@style,'display'))]";

    private static final By PREVIOUS_PAGE = By.xpath("//*[@name='nav_id' and @rel='prev']");

    public ItemPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Boolean isPageContainsItemList(){
        return (driver.findElements(FIRST_ITEM).size() > 0);
    }

    public List<WebElement> getAllItems(){
        return driver.findElements(By.xpath(ALL_ITEMS));
    }

    public WebElement getOneItems(String number){
        return driver.findElement(By.xpath(ALL_ITEMS + "[" + number + "]"));
    }

    public void setCheckboxToItem(String number){
        driver.findElement(By.xpath(ALL_ITEMS + "[" + number + "]//input")).click();
    }

    public String getCurrentItemPageUrl() {
        return driver.getCurrentUrl();
    }

    public Boolean isItemPageContainsMoreThanOnePage(){
        return (driver.findElements(PREVIOUS_PAGE).size() > 0);
    }

    public String getLastPageUrl() {
        return driver.findElement(PREVIOUS_PAGE).getAttribute("href");
    }

}
