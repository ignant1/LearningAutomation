package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BrowserSetup {

	public WebDriver driver;
	
	@BeforeMethod
	@Parameters("browser")
	public void OpenBrowser(String browser) {
		
		switch(browser) {
			case "chrome": {
				driver = new ChromeDriver();
				break;
			}
			case "firefox": {
				driver = new FirefoxDriver();
				break;
			}
			default : {
				driver = new EdgeDriver();
				break;
			}
		}		
		
		//open the web page and maximize
		driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
		driver.manage().window().maximize();
		
	}
	
	@AfterMethod
	public void CloseBrowser() throws Exception{
		
		//Thread.sleep(Duration.ofSeconds(10));
		//driver.close();
	}
}
