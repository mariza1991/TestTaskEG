package addToMemo.components;

import addToMemo.steps.BaseSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeaderMenuBlock {

    protected WebDriver driver;
    protected WebDriverWait wait;
    Logger logger = LoggerFactory.getLogger(HeaderMenuBlock.class);

    public static final By MEMO_BTN_XPATH = By.xpath("//*[@id='main_table']//a[@title='Memo']");

    public static final By ADD_TO_MEMO_COUNTER = By.xpath("//*[@id='mnu_fav_id']");

    public HeaderMenuBlock(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public Integer getCounterValue() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_TO_MEMO_COUNTER));
        String currentValue = driver.findElement(ADD_TO_MEMO_COUNTER).getText();
        logger.info("memo counter : " + currentValue);
        return Integer.parseInt(currentValue.substring(2, currentValue.length() - 1));
    }

    public Boolean isCounterChanged(Integer newCounterValue) {
        return (getCounterValue().equals(newCounterValue));
    }

    public void clickToMemoBtn() {
        driver.findElement(MEMO_BTN_XPATH).click();;
    }
}
