package selassignmentone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AssignmentOne {

	public static void Login() {
		//create Chrome driver
		WebDriver driver = new ChromeDriver();
		
		//Open Web page and maximize
		driver.get("https://saucedemo.com");
		driver.manage().window().maximize();
		
		//find username text field and enter information
		WebElement username = driver.findElement(By.id("user-name"));
		username.sendKeys("standard_user");
		
		//find password text field and enter data
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("secret_sauce");
		
		//find login button and click
		driver.findElement(By.id("login-button")).click();
		
	}
	
	public static void main(String[] args) {
		Login();
	}

}
