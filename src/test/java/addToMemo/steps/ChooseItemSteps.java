package addToMemo.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ChooseItemSteps extends BaseSteps {

    public ChooseItemSteps(WebDriver driver) {
        super(driver);
    }

    public void goToCategoryPage(){
        mainSitePage
                .open()
                .open(chooseRandomCategory());
    }

    public void goToAdditionalCategoryPage(){
        applePhonesPage.open();
    }

    public void goToRandomPageWithItems(){
        chooseRandomPageWithItem();
    }

    public void chooseRandomItemFromFirstPageWithItems(){
        openRandomItem();
    }

    public String chooseFewRandomItemsFromOneCategoryFromFirstItemPage(String counter) {
        List<WebElement> allItems = itemPage.getAllItems();
        Integer count = Integer.parseInt(counter);
        if (allItems.size() > count) {
            while (count > 0) {
                itemPage.setCheckboxToItem(String.valueOf(count));
                count = count - 1;
            }
            return counter;
        } else {
            logger.info("Contains less items than needed. Needed " + counter);
            count = allItems.size();
            while (count > 0) {
                itemPage.setCheckboxToItem(String.valueOf(count));
                count = count - 1;
            }
            return String.valueOf(allItems.size());
        }
    }

    private void chooseRandomPageWithItem() {
        while (!itemPage.isPageContainsItemList()) {
            String categoryPage = chooseRandomCategory();
            mainSitePage.open(categoryPage);
        }
    }

    private String chooseRandomCategory(){
        List<WebElement>  allCategories = mainSitePage.getAllCategories();
        String choosenCategory;
        if (allCategories.size() > 0) {
            int randomInt = (int)(allCategories.size() * Math.random());
            choosenCategory = allCategories.get(randomInt).getAttribute("href");
        } else {
            throw new RuntimeException("No categories on page");
        }
        logger.info(choosenCategory);
        return choosenCategory;
    }

    private WebElement chooseRandomItemFromFirstItemPage() {
        List<WebElement> allItems = itemPage.getAllItems();
        WebElement choosenItem;
        if (allItems.size() > 0) {
            int randomInt = (int)(allItems.size() * Math.random());
            choosenItem = itemPage.getOneItems(String.valueOf(randomInt));
        } else {
            throw new RuntimeException("No items on page");
        }
        logger.info(choosenItem.toString());
        return choosenItem;
    }

    private void openRandomItem() {
        WebElement ItemPage = chooseRandomItemFromFirstItemPage();
        ItemPage.click();
        logger.info("I opened random item. My item is " + itemPage.getCurrentItemPageUrl());
    }
}
