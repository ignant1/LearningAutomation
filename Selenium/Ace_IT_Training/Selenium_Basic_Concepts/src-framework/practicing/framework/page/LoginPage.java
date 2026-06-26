package practicing.framework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import practicing.framework.utility.ReadPropFiles;

public class LoginPage {

	WebDriver WD;
	
	public LoginPage(WebDriver driver) {
		
		WD = driver;
	}
	
	public void EnterUsernameTextbox(String username) throws Exception{
		WD.findElement(By.id(ReadPropFiles.ReadElementData("login_username_id"))).sendKeys(username);
	}

	public void EnterPasswordTextbox(String password) throws Exception{
		WD.findElement(By.name(ReadPropFiles.ReadElementData("login_password_name"))).sendKeys(password);
	}

	public void ClickLoginButton() throws Exception{
		WD.findElement(By.className(ReadPropFiles.ReadElementData("login_button_class"))).click();
	}

	public void ClickCheckbox() throws Exception{
		WD.findElement(By.cssSelector(ReadPropFiles.ReadElementData("login_rememberme_css"))).click();
	}

}
