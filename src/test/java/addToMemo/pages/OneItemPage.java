package addToMemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OneItemPage extends Page<OneItemPage> {

    private static final By ADD_TO_MEMO_BTN = By.xpath("//*[@id='a_fav']");

    private static final By ADD_TO_MEMO_ALERT = By.xpath("//*[@id='alert_dv']");
    private static final By ACCEPT_ALERT_BTN = By.xpath("//*[@id='alert_ok']");
    private static final By ITEM_TITLE = By.id("msg_div_msg");
    public static String chosenItemPageUrl = "";

    public OneItemPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public OneItemPage addToMemo() {
        driver.findElement(ADD_TO_MEMO_BTN).click();
        return this;
    }

    public Boolean isAddAlertAppeared(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_TO_MEMO_ALERT));
        return (driver.findElement(ADD_TO_MEMO_ALERT).isDisplayed());
    }

    public void acceptAlert() {
        driver.findElement(ACCEPT_ALERT_BTN).click();
    }

    public void checkThatCounterChanged() {
        Integer counter = headerMenuBlock.getCounterValue();
        headerMenuBlock.isCounterChanged(counter);
    }

    public void setItem() {
        chosenItemPageUrl = driver.getCurrentUrl();
    }

    public String getItemTitle() {
        return driver.findElement(ITEM_TITLE).getText();
    }
}
