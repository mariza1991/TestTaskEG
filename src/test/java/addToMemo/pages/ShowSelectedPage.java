package addToMemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ShowSelectedPage extends Page<ShowSelectedPage>{

    private static final By SHOW_SELECTED_LINK = By.id("show_selected_a");
    private static final By ADD_SELECTED_TO_MEMO_LINK = By.xpath("//*[@id='a_fav_sel']");
    private static final By ALL_SELECTED_ITEMS = By.xpath("//*[contains(@id,'tr_')]//td[@class='msg2']//a");
    private static final String SELECTED_ITEM = "//*[contains(@id,'tr_')]";
    private static final String TABLE_WITH_ITEMS = "//*[@id='filter_frm']//table";
    public static List<String> selectedItemsUrls = new ArrayList<>();

    public ShowSelectedPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ShowSelectedPage goToShowSelectedPage() {
        driver.findElement(SHOW_SELECTED_LINK).click();
        return this;
    }

    public ShowSelectedPage addToMemoSelectedItems() {
        wait.until(ExpectedConditions.elementToBeClickable(ADD_SELECTED_TO_MEMO_LINK));
        driver.findElement(ADD_SELECTED_TO_MEMO_LINK).click();
        return this;
    }

    public void checkThatCounterIs(String counter) {
        headerMenuBlock.isCounterChanged(Integer.parseInt(counter));
    }

    public void setSelectedItems(){
        List<WebElement> selectedItems = driver.findElements(ALL_SELECTED_ITEMS);
        wait.until(ExpectedConditions.visibilityOfAllElements(selectedItems));

        for (int i = 1; i < selectedItems.size() + 1; i++) {
            selectedItemsUrls.add(driver.findElement(By.xpath(SELECTED_ITEM + "[" + i + "]//td[@class='msg2']//a")).getAttribute("href"));
        }

        logger.info("Number of selected items set to " + selectedItemsUrls.size());
        for (String list : selectedItemsUrls) {
            logger.info(list);
        }
    }

    public Boolean containsTable() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TABLE_WITH_ITEMS)));
        return (driver.findElements(By.xpath(TABLE_WITH_ITEMS)).size() > 1);
    }

    //TODO fix it
    public void setSelectedItemsForMoreThanOneCategory(){
        List<WebElement> selectedItems = driver.findElements(ALL_SELECTED_ITEMS);

        List<WebElement> tablesWithItems = driver.findElements(By.xpath(TABLE_WITH_ITEMS));
        wait.until(ExpectedConditions.visibilityOfAllElements(selectedItems));

        //*[@id='filter_frm']//table[1]//*[contains(@id,'tr_')]//a[@class='am']
        for (int j = 1; j <= tablesWithItems.size(); j++) {
            List<WebElement> itemsInTable = driver.findElements(By.xpath(TABLE_WITH_ITEMS + "[" + j + "]" +SELECTED_ITEM + "//a[@class='am']"));
            logger.info("Table on page , items in current table count " + itemsInTable.size());
            for (int i = 1; i <= itemsInTable.size(); i++) {
                selectedItemsUrls.add(driver.findElement(By.xpath(TABLE_WITH_ITEMS + "[" + j + "]" + SELECTED_ITEM + "[" + i + "]//td[@class='msg2']//a")).getAttribute("href"));
            }
        }
        selectedItemsUrls = getUniqueValues(selectedItemsUrls);
        logger.info("Number of selected items were set to " + selectedItemsUrls.size());
        for (String list : selectedItemsUrls) {
            logger.info(list);
        }
    }

    private static List getUniqueValues(List input) {
        return new ArrayList<>(new HashSet<>(input));
    }
}
