package selassignmenttwo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Assignment2 {

	WebDriver driver;
	Select dropdown;
	
	@Test
	public void DoStuff() {
		driver.findElement(By.xpath("//html/body/div[2]/div[1]/div/div[2]/div[4]/p/a")).click();
		
		driver.findElement(By.cssSelector("[placeholder='Enter your full name']")).sendKeys("Big Bird");
		
		//using multiple partial info to find element
		driver.findElement(By.cssSelector("input[name^='login'][id^='login']")).sendKeys("Ace_IT");
		//driver.findElement(By.xpath("//input[contains(@name,'login')][contains(@id,'login')]")).sendKeys("Ace_IT");
		
		driver.findElement(By.cssSelector("[placeholder='Enter password']")).sendKeys("Password1!");
		
		driver.findElement(By.cssSelector("[placeholder='Retype password']")).sendKeys("Password1!");
	
		
		dropdown = new Select(driver.findElement(By.cssSelector("select[name^='DOB_Day']")));
		dropdown.selectByValue("07");
		
		dropdown = new Select(driver.findElement(By.cssSelector("select[name^='DOB_Month']")));
		dropdown.selectByValue("07");
		
		dropdown = new Select(driver.findElement(By.cssSelector("select[name^='DOB_Year']")));
		dropdown.selectByValue("2013");
		
		driver.findElement(By.cssSelector("input[type='radio'][value='f']")).click();
		
		dropdown = new Select(driver.findElement(By.id("country")));
		dropdown.selectByValue("222");
		
		driver.findElement(By.cssSelector("input[name^='chk_altemail']")).click();
		
		dropdown = new Select(driver.findElement(By.cssSelector("select[name^='hint']")));
		dropdown.selectByIndex(2);
		
		driver.findElement(By.cssSelector("input[type='password'][name^='hint']")).sendKeys("Ace_IT");
		
		driver.findElement(By.cssSelector("input[name^='mothername']")).sendKeys("Ace_IT");
		
	}
	
	@BeforeMethod
	public void OpenBrowser() {
				
		driver = new ChromeDriver();
		
		
		//open the web page and maximize
		driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
		driver.manage().window().maximize();
		
	}
	
	@AfterMethod
	public void CloseBrowser() {
		//driver.close();
	}
	
}
