package demo.project.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import demo.project.actiondriver.ActionDriver;
import demo.project.utilities.ExtentReportManager;
import demo.project.utilities.LoggerManager;

public class BaseClass {

	protected static Properties properties;
	
	//Replacing this driver variables with a thread local driver variable to be able to run the test cases in parallel
	//protected static WebDriver webDr;
	//protected static ActionDriver actionDr;
	
	//thread local driver variables
	private static ThreadLocal<WebDriver> webDr = new ThreadLocal<>();
	private static ThreadLocal<ActionDriver> actionDr = new ThreadLocal<>();
	
	public static final Logger logger = LoggerManager.getLogger(BaseClass.class);
		
	////////////////////////////////////////////////////////////////
	/// 
	/// Browser setup methods
	/// 
	/////////////////////////////////////////////////////////////////
	
	//updating to include thread safety Web driver creation
	//initialize webdriver based on the browser defined in the config file
	private void loadBrowser() throws IOException{
		logger.info("Initializing Browser.");
		String browser = properties.getProperty("browser");
		
		switch(browser) {
			case "chrome" : {
				logger.info("Launching Chrom Browser.");
				//webDr = new ChromeDriver();
				webDr.set(new ChromeDriver());
				ExtentReportManager.registerWebDriver(getWebDriver());
				break;
			}
			case "firefox" : {
				logger.info("Launching FireFox Browser.");
				//webDr = new FirefoxDriver();
				webDr.set(new FirefoxDriver());
				ExtentReportManager.registerWebDriver(getWebDriver());
				break;
			}
			case "edge" : {
				logger.info("Launching Edge Browser.");
				//webDr = new EdgeDriver();
				webDr.set(new EdgeDriver());
				ExtentReportManager.registerWebDriver(getWebDriver());
				break;
			}
			default : {
				logger.error("Attempted to launch " + browser + " browser, which is not supported.");
				logger.info("Attempted to launch " + browser + " browser, which is not supported.");
				throw new IllegalArgumentException("Browser not supported: " + browser);
			}
		}
	}
	
	
	private void configureBrowser() {
		logger.info("Configuring the browser.");
		
		//implicit wait
		getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(properties.getProperty("implicitwait"))));
		logger.info("Setup implicit wait for the browser");
		
		//maximize browser window
		getWebDriver().manage().window().maximize();
		logger.info("Maximized windows size for the browser.");
		
		//navigate to OrangeHRM website
		try {
			String url = properties.getProperty("url");
			logger.info("Going to load the web page: " + url);
			getWebDriver().get(url);
		} catch (Exception e) {
			logger.info("Failed to navigate to the URL: " + e.getMessage());
			logger.error("Failed to navigate to the URL: " + e.getMessage());
		}
		
	}
	
	//access the properties config file
	public static Properties getProperties() {
		logger.info("Getting the data from the congfig file.");
		return properties;
	}
	
	//short pause during testing to view the page
	public void staticwait(int seconds) {
		logger.info("Pausing the test for " + seconds + " seconds.");
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}
	
	////////////////////////////////////////////////////////////////
	/// 
	/// Get and Set methods
	/// 
	/////////////////////////////////////////////////////////////////
	//access the Web Driver from outside the base class
	public static WebDriver getWebDriver() {
		logger.info("Going to Return the Web Driver.");
		if(webDr.get() == null) {
			logger.info("Web Driver was not initialized.");
			logger.error("Web Driver was not initialized.");
			throw new IllegalStateException ("Web Driver was not initialized.");
		}
		logger.info("Returning the Web Driver.");
		return webDr.get();
	}
	
	//set the Wed Driver from outside the bass class
	public void setWebDriver(WebDriver wb) {
		logger.info("Setting the Web Driver.");
		webDr.set(wb);
	}
	
	//access the Action Driver from outside the base class
	public static ActionDriver getActionDriver() {
		logger.info("Going to Return the Action Driver.");
		if(actionDr.get() == null) {
			logger.info("Action Driver was not initialized.");
			logger.error("Action Driver was not initialized.");
			throw new IllegalStateException ("Action Driver was not initialized.");
		}
		logger.info("Returning the Action Driver.");
		return actionDr.get();
	}
	
	//set the Action Driver from outside the bass class
	public void setActionbDriver(ActionDriver ad) {
		logger.info("Setting the Action Driver.");
		actionDr.set(ad);
	}
	
	////////////////////////////////////////////////////////////////
	/// 
	/// Annotated Testing methods
	/// 
	/////////////////////////////////////////////////////////////////
	@BeforeSuite
	public void loadConfig() throws IOException {
		logger.info("Going to get the data from the config file.");
		//load the property config file
		properties = new Properties();
		logger.info("Streaming the data from the file.");
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
		logger.info("Loading the data into the Properties object.");
		properties.load(fis);
		logger.info("config.properties file loaded.");
		
		//start the Extent Report
		ExtentReportManager.getExtentReport();
	}

	//making method synchronized so that each thread can use it one at a time.
	@BeforeMethod
	public synchronized void setup() throws IOException {
		logger.info("==============================================================================");
		logger.info("Setting up the WebDriver for: " + this.getClass().getSimpleName());
		logger.info("==============================================================================");
		loadBrowser();
		logger.info("Finished loading the browser.");
		configureBrowser();
		logger.info("Finished configuring the browser.");
		
		logger.info("Web Driver initialized and browser maximized.");
		//logger.trace("This is a Trace message for class: " + this.getClass().getSimpleName());
		//logger.error("This is a Error message for class: " + this.getClass().getSimpleName());
		//logger.debug("This is a debug message for class: " + this.getClass().getSimpleName());
		//logger.fatal("This is a fatal message for class: " + this.getClass().getSimpleName());
		//logger.warn("This is a warn message for class: " + this.getClass().getSimpleName());
		
		staticwait(2);
		logger.info("Waited for 2 seconds.");
		
		///////////////////////////////////////////////////////////////////////
		//changing to make it thread safe
		////initialize Action drive only once
		//if(actionDr==null) {
		//	actionDr = new ActionDriver(webDr);
		//	logger.info("Action Driver variable was null so created the instance. Thread ID: " + Thread.currentThread().threadId());
		//}
		///////////////////////////////////////////////////////////////////////
		
		//new action driver initialization for thread safety
		actionDr.set(new ActionDriver(getWebDriver()));
		logger.info("Action Driver intiialized with thread safey in mind: " + Thread.currentThread().threadId());
		
	}
	
	//making method synchronized so that each thread can use it one at a time.
	@AfterMethod
	public synchronized void shutdown() {
		logger.info("==============================================================================");
		logger.info("going to shutdown the driver.");
		logger.info("==============================================================================");
		if(getWebDriver()!=null) {
			try {
				logger.info("Web Driver was not null and quiting the driver.");
				getWebDriver().quit();
			} catch (Exception e) {
				logger.info("unable to quit the driver: " + e.getMessage());
				logger.error("unable to quit the driver: " + e.getMessage());
			}
		}
		
		//updating to Thread Local process
		logger.info("Nullifying Web Driver and Action Driver instances.");
		//webDr = null;
		//actionDr = null;
		webDr.remove();
		actionDr.remove();
		ExtentReportManager.endTest();
	}
}
