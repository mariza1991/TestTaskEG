package addToMemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class ShowSelectedPage extends Page<ShowSelectedPage>{

    private static final By SHOW_SELECTED_LINK = By.id("show_selected_a");
    private static final By ADD_SELECTED_TO_MEMO_LINK = By.id("a_fav_sel");
    private static final By ALL_SELECTED_ITEMS = By.xpath("//*[contains(@id,'tr_')]//td[@class='msg2']//a");
    private static final String SELECTED_ITEM = "//*[contains(@id,'tr_')]";
    public static List<String> selectedItemsUrls = new ArrayList<>();

    public ShowSelectedPage goToShowSelectedPage() {
        driver.findElement(SHOW_SELECTED_LINK).click();
        return this;
    }

    public ShowSelectedPage addToMemoSelectedItems() {
        driver.findElement(ADD_SELECTED_TO_MEMO_LINK).click();
        return this;
    }

    public void checkThatCounterIs(String counter) {
        headerMenuBlock.isCounterChanged(Integer.parseInt(counter));
    }

    public void setSelectedItems(){
        List<WebElement> selectedItems = driver.findElements(ALL_SELECTED_ITEMS);
        wait.until(ExpectedConditions.visibilityOfAllElements(selectedItems));

        for (int i = 1; i < selectedItems.size(); i++) {
            System.out.println(SELECTED_ITEM + "[" + i + "]//td[@class='msg2']//a");
            selectedItemsUrls.add(driver.findElement(By.xpath(SELECTED_ITEM + "[" + i + "]//td[@class='msg2']//a")).getAttribute("href"));
        }
    }
}
