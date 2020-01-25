package addToMemo.pages;

import addToMemo.components.HeaderMenuBlock;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.WebdriverManager.getDriver;
import static utils.WebdriverManager.getWebDriverWait;

public class Page<T extends Page> {

    protected HeaderMenuBlock headerMenuBlock;

    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page() {
        this.driver = getDriver();
        this.wait = getWebDriverWait();
        this.headerMenuBlock = new HeaderMenuBlock(driver);
    }

    public T open(String url){
        driver.get(url);
        return (T)this;
    }
}
