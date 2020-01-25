package addToMemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MemoPage extends Page<MemoPage> {

    private static final String MEMO_PAGE_URL = "https://www.ss.com/ru/favorites/";
    private static final By ALL_ADDED_ITEMS = By.xpath("//*[contains(@id,'tr_')]//td[@class='msg2']//a");

    public MemoPage open(){
        return open(MEMO_PAGE_URL);
    }

    public MemoPage goToMemoFromMenu() {
        headerMenuBlock.clickToMemoBtn();
        return this;
    }

    public List<WebElement> getAllAddedItems(){
        return driver.findElements(ALL_ADDED_ITEMS);
    }
}
