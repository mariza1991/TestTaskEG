package addToMemo.pages;

import addToMemo.components.HeaderMenuBlock;
import addToMemo.steps.BaseSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static utils.WebdriverManager.getWebDriverWait;

public class Page<T extends Page> {

    protected HeaderMenuBlock headerMenuBlock;
    Logger logger = LoggerFactory.getLogger(Page.class);

    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = driver;
        this.wait = getWebDriverWait(driver);
        this.headerMenuBlock = new HeaderMenuBlock(driver, wait);
    }

    public T open(String url){
        driver.get(url);
        return (T)this;
    }
}
