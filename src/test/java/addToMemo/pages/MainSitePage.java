package addToMemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class MainSitePage extends Page<MainSitePage> {

    private static final String MAIN_PAGE_URL = "https://www.ss.com/";

    private static final By ALL_CATEGORIES_LINK = By.xpath("//*[contains(@id,'page_main')]//a[@id and @title]");

    public MainSitePage() {
        PageFactory.initElements(driver, this);
    }

    public MainSitePage open(){
        return open(MAIN_PAGE_URL);
    }

    public List<WebElement> getAllCategories(){
        return driver.findElements(ALL_CATEGORIES_LINK);
    }

    //TODO - find category by sth
    public String findCategoryByTitle(String title){
        for(WebElement link : getAllCategories()){
            if (link.getText().equals(title)){
                return link.getAttribute("href");
            }
        }
        throw new RuntimeException("");
    }
}
