package addToMemo.tests;


import addToMemo.steps.BaseSteps;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import static utils.WebdriverManager.getDriver;
import static utils.WebdriverManager.quitAll;

@Listeners(TestListener.class)
public class BaseTest {

    protected WebDriver driver;
    Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeClass(alwaysRun = true)
    public void before() {
        driver = getDriver();
    }

    @AfterMethod
    public void afterTest() {
        getDriver().manage().deleteAllCookies();
    }

    @AfterClass
    public static void afterAll() {
        getDriver().quit();
        quitAll();
    }
}