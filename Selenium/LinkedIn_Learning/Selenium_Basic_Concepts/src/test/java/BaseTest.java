import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setup() throws MalformedURLException {
        String browser = System.getProperty("browser", "firefox").toLowerCase();
        URL gridURL = new URL("http://localhost:4444");

        switch(browser){
            case "chrome":
                driver = new RemoteWebDriver(gridURL, new ChromeOptions().setPageLoadStrategy(PageLoadStrategy.NORMAL));
                break;
            case "firefox":
                driver = new RemoteWebDriver(gridURL, new FirefoxOptions().setPageLoadStrategy(PageLoadStrategy.NORMAL));
                break;
            default:
                driver = new RemoteWebDriver(gridURL, new ChromeOptions().setPageLoadStrategy(PageLoadStrategy.NORMAL));
                break;
        }
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
