package addToMemo.steps;

import addToMemo.pages.ItemPage;
import addToMemo.pages.MainSitePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ChooseItemSteps extends BaseSteps {

    public static MainSitePage mainSitePage = new MainSitePage();
    public static ItemPage itemPage = new ItemPage();

    @Given("^I am on the category page$")
    public void i_am_on_the_category_page(){
        mainSitePage
                .open()
                .open(chooseRandomCategory());
    }

    @When("^I choose random item page$")
    public void i_choose_random_item_page(){
        chooseRandomPageWithItem();
    }

    @When("^I choose random item from first item page$")
    public void i_choose_random_item_from_first_item_item_page(){
        openRandomItem();
    }

    //TODO divide into functions
    @When("^I choose \"(.*)\" random items from first item page$")
    public void i_choose_few_random_items_from_first_item_page(String query) {
        List<WebElement> allItems = itemPage.getAllItems();
        Integer count = Integer.parseInt(query);
        if (allItems.size() > count) {
            while(count > 0) {
                int randomInt = (int) ((allItems.size() - 1) * Math.random()) + 1;
                itemPage.setCheckboxToItem(String.valueOf(randomInt));
                count = count - 1;
            }
        } else {
            //TODO
            System.out.println("contains less items");
        //    chooseRandomPageWithItem();
        }
    }

    @When("^I choose random item from last item page$")
    public void i_choose_random_item_from_last_item_item_page(){
        //TODO use isItemPageContainsMoreThanOnePage()
    }

    public String chooseRandomPageWithItem() {
        while (!itemPage.isPageContainsItemList()) {
            String categoryPage = chooseRandomCategory();
            mainSitePage.open(categoryPage);
        }
        return "";
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
        //TODO info should be logged,
        System.out.println(choosenCategory);
        return choosenCategory;
    }

    public WebElement chooseRandomItemFromFirstItemPage() {
        List<WebElement> allItems = itemPage.getAllItems();
        WebElement choosenItem;
        if (allItems.size() > 0) {
            int randomInt = (int)(allItems.size() * Math.random());
            choosenItem = itemPage.getOneItems(String.valueOf(randomInt));
        } else {
            throw new RuntimeException("No items on page");
        }
        //TODO info should be logged,
        System.out.println(choosenItem);
        return choosenItem;
    }

    public void openRandomItem() {
        WebElement ItemPage = chooseRandomItemFromFirstItemPage();
        ItemPage.click();
        //TODO info should be logged,
        System.out.println(itemPage.getCurrentItemPageUrl());
    }
}
