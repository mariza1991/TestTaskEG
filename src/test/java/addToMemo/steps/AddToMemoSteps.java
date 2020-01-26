package addToMemo.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class AddToMemoSteps extends BaseSteps {

    public AddToMemoSteps(WebDriver driver) {
        super(driver);
    }

    public void addChosenItemToMemoFromItemPage(){
        oneItemPage.addToMemo();
        oneItemPage.setItem();
        if(oneItemPage.isAddAlertAppeared()) {
            logger.info("Alert appeared");
            oneItemPage.acceptAlert();
        }
    }

    public void addSelectedItemsToMemo() {
        showSelectedPage.goToShowSelectedPage()
                .addToMemoSelectedItems();
        if(oneItemPage.isAddAlertAppeared()) {
            logger.info("Alert appeared");
            oneItemPage.acceptAlert();
        }
        if (showSelectedPage.containsTable()) {
            logger.info("I see table");
            showSelectedPage.setSelectedItemsForMoreThanOneCategory();
        } else {
            showSelectedPage.setSelectedItems();
        }
    }

    public void goToMemoFromMenu(){
        memoPage.goToMemoFromMenu();
    }

    public void counterInHeaderChanged() {
        oneItemPage.checkThatCounterChanged();
    }

    public void counterInHeaderIs(String query) {
        showSelectedPage.checkThatCounterIs(query);
    }

    public void theMemoPageContainsChosenItem() {
        assertTrue(checkMemoPageContainsAddedItem(oneItemPage.chosenItemPageUrl));
    }

    public void theMemoPageContainsChosenItems() {
        assertTrue(checkMemoPageContainsAddedItems());
    }

    private Boolean checkMemoPageContainsAddedItem(String itemPageUrl){
        List<WebElement> allAddedItems = memoPage.getAllAddedItems();
        logger.info("Chosen item was : " + itemPageUrl);
        for (WebElement item: allAddedItems){
            if (item.getAttribute("href").equals(itemPageUrl)){
                return true;
            } else {
                throw new RuntimeException("No added item in memo");
            }
        }
        return true;
    }

    private Boolean checkMemoPageContainsAddedItems(){
        List<WebElement> allAddedItems = memoPage.getAllAddedItems();

        for (WebElement item: allAddedItems){
            logger.info("xpath " + item + " ,link " + item.getAttribute("href"));
            if (!showSelectedPage.selectedItemsUrls.contains(item.getAttribute("href"))){
                logger.error("Memo Page doesn't contains added item, see logs in /target/output/");
                return false;
            }
        }
        return true;
    }
}
