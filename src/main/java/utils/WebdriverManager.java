package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebdriverManager {

    private static WebDriver driver;

    private static final String PATH_TO_PROPERTIES = "properties/settings.properties";

    public static WebDriver getDriver() {
        String browserName =
                PropertyReader.getPropertyFromFile(PATH_TO_PROPERTIES, "browser");
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
        return driver;
    }
}
