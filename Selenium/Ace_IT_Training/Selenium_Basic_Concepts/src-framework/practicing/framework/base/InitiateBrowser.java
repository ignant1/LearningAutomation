package practicing.framework.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import practicing.framework.utility.ReadPropFiles;

public class InitiateBrowser {
	
	//class variables
	protected WebDriver driver;
	
	@BeforeMethod
	public void LaunchBrowser() throws Exception{
		
		//Launch specified browser
		switch(ReadPropFiles.ReadConfigFile("Browser")) {
		
			case "chrome": {
				driver = new ChromeDriver();
				break;
			}
			case "firefox": {
				driver = new FirefoxDriver();
				break;
			}
			case "safari": {
				driver = new SafariDriver();
				break;
			}
			default : {
				driver = new EdgeDriver();
				break;
			}
		}
		
		//load URL web page
		driver.get(ReadPropFiles.ReadConfigFile("URL"));
		
		//manage browser settings
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	
	@AfterMethod
	public void CloseBrowser() throws Exception{
		
		//shutdown the driver
		Thread.sleep(Duration.ofSeconds(3));
		driver.quit();
	}
}
