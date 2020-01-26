package addToMemo.steps;

import addToMemo.pages.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseSteps {

    protected WebDriver driver;
    Logger logger = LoggerFactory.getLogger(BaseSteps.class);

    protected OneItemPage oneItemPage;
    protected ApplePhonesPage applePhonesPage;
    protected MemoPage memoPage;
    protected ShowSelectedPage showSelectedPage;
    protected MainSitePage mainSitePage;
    protected ItemPage itemPage;

    public BaseSteps(WebDriver driver) {
        this.driver = driver;
        this.oneItemPage = new OneItemPage(driver);
        this.applePhonesPage = new ApplePhonesPage(driver);
        this.memoPage = new MemoPage(driver);
        this.showSelectedPage = new ShowSelectedPage(driver);
        this.mainSitePage = new MainSitePage(driver);
        this.itemPage = new ItemPage(driver);
    }
}
