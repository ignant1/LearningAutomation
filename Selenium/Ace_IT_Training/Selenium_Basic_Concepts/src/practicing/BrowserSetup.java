package practicing;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BrowserSetup {

	public WebDriver driver;
	public XSSFWorkbook excel;
	
	@BeforeMethod
	@Parameters("browser")
	public void OpenBrowser(String browser) throws Exception{
		
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
		
		//Get data from the Excel file
		FileInputStream fls = new FileInputStream("./TestData/ElementList.xlsx");
		excel = new XSSFWorkbook(fls);
		
	}
	
	@AfterMethod
	public void CloseBrowser() throws Exception{
		
		driver.close();
		
	}
}
