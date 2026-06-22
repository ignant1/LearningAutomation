package selenium;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MultiBrowser extends BrowserSetup{
	
	@Test
	@Parameters("browser")
	public void WebTesting(String browser) {
		driver.findElement(By.id("login1")).sendKeys(browser);;
	}

}
