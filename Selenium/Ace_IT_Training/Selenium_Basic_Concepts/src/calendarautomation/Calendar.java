package calendarautomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Calendar {
	WebDriver driver;
	Select dropdown;
	
	@Test
	public void EditCalendar() throws Exception{
		
		//element is wrapped in a div.span which prevents the click functionality from working
		driver.findElement(By.cssSelector("a[href$='/calendars/']")).sendKeys(Keys.RETURN);
		
		//Clicking the textbox will open the date picker
		driver.findElement(By.cssSelector("input[name*='selectorenteradate'][id*='selectorenteradate']")).click();
		
		driver.findElement(By.cssSelector("button[aria-label*='month picker'][class*='month']")).click();
		
		driver.findElement(By.cssSelector("button[data-month*='6'][class*='month']")).click();
		
		Thread.sleep(Duration.ofSeconds(6));
		
		driver.findElement(By.cssSelector("button[aria-label*='year picker'][class*='year']")).click();
		
		driver.findElement(By.cssSelector("button[data-year*='2011'][class*='year']")).click();
		
		Thread.sleep(Duration.ofSeconds(6));
		
		driver.findElement(By.cssSelector("[data-date='1310367600000']")).click();
		
		driver.findElement(By.cssSelector("button[type*='submit'][class*='pushbutton']")).click();
		
	}
	
	@BeforeMethod
	public void OpenBrowser()  throws Exception{
		String url = "https://practice-automation.com/";
		
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
	
	}
	
	@AfterMethod
	public void CloseBrowser() {
		//driver.close();
	}
}
