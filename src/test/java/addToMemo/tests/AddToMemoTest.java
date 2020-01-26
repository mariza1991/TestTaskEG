package addToMemo.tests;

import addToMemo.steps.AddToMemoSteps;
import addToMemo.steps.ChooseItemSteps;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddToMemoTest extends BaseTest {

    @Test
    public void addToMemoOneRandomItemFromItemPageTest() {
        ChooseItemSteps chooseItemSteps = new ChooseItemSteps(driver);
        chooseItemSteps.goToCategoryPage();
        chooseItemSteps.goToRandomPageWithItems();
        chooseItemSteps.chooseRandomItemFromFirstPageWithItems();

        AddToMemoSteps addToMemoSteps = new AddToMemoSteps(driver);
        addToMemoSteps.addChosenItemToMemoFromItemPage();
        addToMemoSteps.goToMemoFromMenu();
        addToMemoSteps.counterInHeaderChanged();
        addToMemoSteps.theMemoPageContainsChosenItem();
    }

    @Test (dataProvider = "testData1")
    public void addFewItemsToMemoFromOneCategoryPage(String counter)  {
        ChooseItemSteps chooseItemSteps = new ChooseItemSteps(driver);
        chooseItemSteps.goToCategoryPage();
        chooseItemSteps.goToRandomPageWithItems();

        String addedItemsAmount = chooseItemSteps.chooseFewRandomItemsFromOneCategoryFromFirstItemPage(counter);

        logger.info("Was added " + addedItemsAmount + " items");
        AddToMemoSteps addToMemoSteps = new AddToMemoSteps(driver);
        addToMemoSteps.addSelectedItemsToMemoFromShowSelectedPage();
        addToMemoSteps.counterInHeaderIs(addedItemsAmount);
        addToMemoSteps.goToMemoFromMenu();
        addToMemoSteps.theMemoPageContainsChosenItems();
    }

    @Test (dataProvider = "testData2")
    public void addFewItemsToMemoFromDifferentCategoryPage(String firstCounter, String secondCounter) {
        ChooseItemSteps chooseItemSteps = new ChooseItemSteps(driver);
        chooseItemSteps.goToCategoryPage();
        chooseItemSteps.goToRandomPageWithItems();

        String addedItemsAmountFromFirstCategory = chooseItemSteps.chooseFewRandomItemsFromOneCategoryFromFirstItemPage(firstCounter);

        logger.info("Was added from first category " + addedItemsAmountFromFirstCategory);
        AddToMemoSteps addToMemoSteps = new AddToMemoSteps(driver);
        addToMemoSteps.addSelectedItemsToMemo();

        chooseItemSteps.goToAdditionalCategoryPage();
        String addedItemsAmountFromSecondCategory = chooseItemSteps.chooseFewRandomItemsFromOneCategoryFromFirstItemPage(secondCounter);

        logger.info("Was added from second category " + addedItemsAmountFromSecondCategory);
        addToMemoSteps.addSelectedItemsToMemo();
        String totalAmount = String.valueOf(Integer.parseInt(addedItemsAmountFromFirstCategory) + Integer.parseInt(addedItemsAmountFromSecondCategory));
        logger.info("Total amount of added items " + totalAmount);

        addToMemoSteps.counterInHeaderIs(totalAmount);
        addToMemoSteps.goToMemoFromMenu();
        addToMemoSteps.theMemoPageContainsChosenItems();
    }

    @DataProvider(name = "testData1")
    public Object[][] dataProvider1(){
        return new Object[][]{{"3"}};
    }

    @DataProvider(name = "testData2")
    public Object[][] dataProvider2(){
        return new Object[][]{{"3", "4"}};
    }
}
