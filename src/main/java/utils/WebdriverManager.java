package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class WebdriverManager {

    private static WebDriver driver;
    private static WebDriverWait wait;

    private static final String PATH_TO_PROPERTIES = "properties/settings.properties";

    public static WebDriver getDriver() {
        String browserName =
                PropertyReader.getPropertyFromFile(PATH_TO_PROPERTIES, "browser");
        if (driver == null) {
            switch (browserName) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "chrome-remote":
                    try {
                        driver = new RemoteWebDriver(new URL("http://192.168.0.2:4444/wd/hub"), //my hub address
                                DesiredCapabilities.chrome());
                        driver.manage().window().maximize();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    driver = new ChromeDriver();
                    break;
            }
        }
        return driver;
    }

    public static WebDriverWait getWebDriverWait() {
        String timeout =
                PropertyReader.getPropertyFromFile(PATH_TO_PROPERTIES, "timeout");
        wait = new WebDriverWait(driver, Long. parseLong(timeout));
        return wait;
    }
}
