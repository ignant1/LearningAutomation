package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {

	protected static Properties properties;
	protected WebDriver driver;
		
	//initialize webdriver based on the browser defined in the config file
	private void loadBrowser() {
		
		String browser = properties.getProperty("browser");
		
		switch(browser) {
			case "chrome" : {
				driver = new ChromeDriver();
				break;
			}
			case "firefox" : {
				driver = new FirefoxDriver();
				break;
			}
			case "edge" : {
				driver = new EdgeDriver();
				break;
			}
			default : {
				throw new IllegalArgumentException("Browser not supported: " + browser);
			}
		}
	}
	
	
	private void configureBrowser() {
		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(properties.getProperty("implicitwait"))));
		
		//maximize browser window
		driver.manage().window().maximize();
		
		//navigate to OrangeHRM website
		try {
			driver.get(properties.getProperty("url"));
		} catch (Exception e) {
			System.out.println("Failed to navigate to the URL: " + e.getMessage());
		}
		
	}
	
	//short pause during testing to view the page
	public void staticwait(int seconds) {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}
	
	//access the driver from outside the base class
	public WebDriver getDriver() {
		return driver;
	}
	
	//access the properties config file
	public static Properties getProperties() {
		return properties;
	}
	
	//set the driver from outside the bass class
	public void setDriver(WebDriver wb) {
		driver = wb;
	}
	
	@BeforeSuite
	public void loadConfig() throws IOException {
		//load the property config file
		properties = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
		properties.load(fis);
	}

	@BeforeMethod
	public void setup() throws IOException {
		System.out.println("Setting up the WebDriver for: " + this.getClass().getSimpleName());
		loadBrowser();
		configureBrowser();
		staticwait(2);
	}
	
	@AfterMethod
	public void shutdown() {
		if(driver!=null) {
			try {
				driver.quit();
			} catch (Exception e) {
				System.out.println("unable to quit the driver: " + e.getMessage());
			}
		}
	}
}
