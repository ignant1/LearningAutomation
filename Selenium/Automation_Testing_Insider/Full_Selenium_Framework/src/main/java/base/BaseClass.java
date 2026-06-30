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

import actiondriver.ActionDriver;

public class BaseClass {

	protected static Properties properties;
	protected static WebDriver webDr;
	protected static ActionDriver actionDr;
		
	////////////////////////////////////////////////////////////////
	/// 
	/// Browser setup methods
	/// 
	/////////////////////////////////////////////////////////////////
	//initialize webdriver based on the browser defined in the config file
	private void loadBrowser() {
		System.out.println("Initializing Browser.");
		String browser = properties.getProperty("browser");
		
		switch(browser) {
			case "chrome" : {
				System.out.println("Launching Chrom Browser.");
				webDr = new ChromeDriver();
				break;
			}
			case "firefox" : {
				System.out.println("Launching FireFox Browser.");
				webDr = new FirefoxDriver();
				break;
			}
			case "edge" : {
				System.out.println("Launching Edge Browser.");
				webDr = new EdgeDriver();
				break;
			}
			default : {
				System.out.println("Attempted to launch " + browser + " browser, which is not supported.");
				throw new IllegalArgumentException("Browser not supported: " + browser);
			}
		}
	}
	
	
	private void configureBrowser() {
		System.out.println("Configuring the browser.");
		
		//implicit wait
		webDr.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(properties.getProperty("implicitwait"))));
		System.out.println("Setup implicit wait for the browser");
		
		//maximize browser window
		webDr.manage().window().maximize();
		System.out.println("Maximized windows size for the browser.");
		
		//navigate to OrangeHRM website
		try {
			String url = properties.getProperty("url");
			System.out.println("Going to load the web page: " + url);
			webDr.get(url);
		} catch (Exception e) {
			System.out.println("Failed to navigate to the URL: " + e.getMessage());
		}
		
	}
	
	//access the properties config file
	public static Properties getProperties() {
		System.out.println("Getting the data from the congfig file.");
		return properties;
	}
	
	//short pause during testing to view the page
	public void staticwait(int seconds) {
		System.out.println("Pausing the test for " + seconds + " seconds.");
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}
	
	////////////////////////////////////////////////////////////////
	/// 
	/// Get and Set methods
	/// 
	/////////////////////////////////////////////////////////////////
	//access the Web Driver from outside the base class
	public static WebDriver getWebDriver() {
		System.out.println("Going to Return the Web Driver.");
		if(webDr == null) {
			System.out.println("Web Driver was not initialized.");
			throw new IllegalStateException ("Web Driver was not initialized.");
		}
		System.out.println("Returning the Web Driver.");
		return webDr;
	}
	
	//set the Wed Driver from outside the bass class
	public void setWebDriver(WebDriver wb) {
		System.out.println("Setting the Web Driver.");
		webDr = wb;
	}
	
	//access the Action Driver from outside the base class
	public static ActionDriver getActionDriver() {
		System.out.println("Going to Return the Action Driver.");
		if(actionDr == null) {
			System.out.println("Action Driver was not initialized.");
			throw new IllegalStateException ("Action Driver was not initialized.");
		}
		System.out.println("Returning the Action Driver.");
		return actionDr;
	}
	
	//set the Action Driver from outside the bass class
	public void setActionbDriver(ActionDriver ad) {
		System.out.println("Setting the Action Driver.");
		actionDr = ad;
	}
	
	////////////////////////////////////////////////////////////////
	/// 
	/// Annotated Testing methods
	/// 
	/////////////////////////////////////////////////////////////////
	@BeforeSuite
	public void loadConfig() throws IOException {
		System.out.println("Going to get the data from the config file.");
		//load the property config file
		properties = new Properties();
		System.out.println("Streaming the data from the file.");
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
		System.out.println("Loading the data into the Properties object.");
		properties.load(fis);
	}

	@BeforeMethod
	public void setup() throws IOException {
		System.out.println("==============================================================================");
		System.out.println("Setting up the WebDriver for: " + this.getClass().getSimpleName());
		System.out.println("==============================================================================");
		loadBrowser();
		System.out.println("Finished loading the browser.");
		configureBrowser();
		System.out.println("Finished configuring the browser.");
		staticwait(2);
		System.out.println("Waited for 2 seconds.");
		
		//initialize Action drive only once
		if(actionDr==null) {
			actionDr = new ActionDriver(webDr);
			System.out.println("Action Driver variable was null so created the instance.");
		}
	}
	
	@AfterMethod
	public void shutdown() {
		System.out.println("==============================================================================");
		System.out.println("going to shutdown the driver.");
		System.out.println("==============================================================================");
		if(webDr!=null) {
			try {
				System.out.println("Web Driver was not null and quiting the driver.");
				webDr.quit();
			} catch (Exception e) {
				System.out.println("unable to quit the driver: " + e.getMessage());
			}
		}
		
		System.out.println("Nullifying Web Driver and Action Driver instances.");
		webDr = null;
		actionDr = null;
	}
}
