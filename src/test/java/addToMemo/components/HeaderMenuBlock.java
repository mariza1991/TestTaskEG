package addToMemo.components;

import cucumber.api.java.it.Ma;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HeaderMenuBlock {

    protected WebDriver driver;

    public static final By MEMO_BTN_XPATH = By.xpath("//*[@id='main_table']//a[@title='Memo']");

    public static final By ADD_TO_MEMO_COUNTER = By.xpath("//*[@id='mnu_fav_id']");

    public HeaderMenuBlock(WebDriver driver) {
        this.driver = driver;
    }

    public Integer getCounterValue() {
        String currentValue = driver.findElement(ADD_TO_MEMO_COUNTER).getText();
        System.out.println("memo counter : " + currentValue);
        return Integer.parseInt(currentValue.substring(2, currentValue.length() - 1));
    }

    public Boolean isCounterChanged(Integer newCounterValue) {
        return (getCounterValue().equals(newCounterValue));
    }

    public void clickToMemoBtn() {
        driver.findElement(MEMO_BTN_XPATH).click();;
    }
}
