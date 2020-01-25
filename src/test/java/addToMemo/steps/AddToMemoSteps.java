package addToMemo.steps;

import addToMemo.pages.MemoPage;
import addToMemo.pages.OneItemPage;
import addToMemo.pages.ShowSelectedPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.List;

import static addToMemo.steps.ChooseItemSteps.itemPage;
import static addToMemo.steps.ChooseItemSteps.mainSitePage;

public class AddToMemoSteps extends BaseSteps {

    public static OneItemPage oneItemPage = new OneItemPage();
    public static MemoPage memoPage = new MemoPage();
    public static ShowSelectedPage showSelectedPage = new ShowSelectedPage();

    @When("^I add an item to memo$")
    public void i_add_an_item_to_memo(){
        oneItemPage.addToMemo();
        oneItemPage.setItem();
        if(oneItemPage.isAddAlertAppeared()) {
            //TODO add logging for event
            System.out.println("Alert appeared");
            oneItemPage.acceptAlert();
        }
    }

    @When("^I add selected items to memo$")
    public void i_add_selected_items_to_memo() {
        showSelectedPage.goToShowSelectedPage()
                .addToMemoSelectedItems();
        if(oneItemPage.isAddAlertAppeared()) {
            //TODO add logging for event
            System.out.println("Alert appeared");
            oneItemPage.acceptAlert();
        }
        showSelectedPage.setSelectedItems();
    }

    @When("^I go to memo$")
    public void i_go_to_memo(){
        memoPage.goToMemoFromMenu();
    }

    @Then("^Counter in header changed$")
    public void counter_in_header_changed() {
        oneItemPage.checkThatCounterChanged();
    }

    @Then("^Counter in header is \"(.*)\"")
    public void counter_in_header_is(String query) {
        showSelectedPage.checkThatCounterIs(query);
    }

    @Then("^The memo page contains chosen item$")
    public void the_memo_page_contains_chosen_item() {
        checkMemoPageContainsAddedItem(oneItemPage.chosenItemPageUrl);
    }

    @Then("^The memo page contains chosen items$")
    public void the_memo_page_contains_chosen_items() {
        checkMemoPageContainsAddedItems();
    }

    private Boolean checkMemoPageContainsAddedItem(String itemPageUrl){
        List<WebElement> allAddedItems = memoPage.getAllAddedItems();
        System.out.println("Chosen item was : " + itemPageUrl);
        for (WebElement item: allAddedItems){
            if (item.getAttribute("href").equals(itemPageUrl)){
                return true;
            } else {
                throw new RuntimeException("No categories on page");
            }
        }
        return false;
    }

    private void checkMemoPageContainsAddedItems(){
        for (String item : showSelectedPage.selectedItemsUrls) {
            checkMemoPageContainsAddedItem(item);
        }
    }
}
