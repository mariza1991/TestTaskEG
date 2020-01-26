package addToMemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ApplePhonesPage extends Page<ApplePhonesPage> {

    private static final String APLE_PHONES_PAGE_URL = "https://www.ss.com/lv/electronics/phones/mobile-phones/apple/";

    public ApplePhonesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ApplePhonesPage open(){
        return open(APLE_PHONES_PAGE_URL);
    }
}
