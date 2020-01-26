package addToMemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MemoPage extends Page<MemoPage> {

    private static final String MEMO_PAGE_URL = "https://www.ss.com/lv/favorites/";
    private static final By ALL_ADDED_ITEMS = By.xpath("//*[contains(@id,'tr_')]//td[@class='msg2']//a");

    public MemoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public MemoPage open(){
        return open(MEMO_PAGE_URL);
    }

    public MemoPage goToMemoFromMenu() {
        headerMenuBlock.clickToMemoBtn();
        wait.until(ExpectedConditions.urlMatches(MEMO_PAGE_URL));
        return this;
    }

    public List<WebElement> getAllAddedItems(){
        return driver.findElements(ALL_ADDED_ITEMS);
    }
}
