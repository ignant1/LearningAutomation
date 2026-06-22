package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class FirstAutomation {

	@Test
	public static void FindingElements() throws InterruptedException {
		
		//Launch the browser
		WebDriver driver = new ChromeDriver();
		
		//Open web page
		driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
		
		//do things to the browser
		driver.manage().window().maximize();

		//WebElement usernametb = driver.findElement(By.id("login1"));
		//WebElement usernametb = driver.findElement(By.name("login"));
		//WebElement usernametb = driver.findElement(By.cssSelector("[id=\"login1\"]"));
		
		//Unsure about what the Xpath is you can inspect the element and then right-click the element attribute and then select copy -> Xpath
		// this is the full Xpath =  /html/body/div[2]/div[1]/div/div[2]/div[3]/form/div[1]/div/input
		WebElement usernametb = driver.findElement(By.xpath("//*[@id=\"login1\"]"));
		
		usernametb.sendKeys("Itsa_Me_Mario!");
		
		//Close the browser
		//driver.close();
	}

}
