package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import actiondriver.ActionDriver;

public class LoginPage {

	//Define Class Objects
	private ActionDriver AD;
	
	//Initialize the ActionDriver object by passing the WebDriver
	public LoginPage(WebDriver wd) {
		AD = new ActionDriver(wd);
	}
	
	//Define locators
	private By usernameField = By.name("username");
	private By passwordField = By.cssSelector("input[type='password']");
	private By loginButton = By.xpath("//button[text()=' Login ']");
	private By errorMessage = By.xpath("//p[text()='Invalid credentials']");
	
	//method to perform login
	public void performLogin(String userName, String passWord) {
		AD.enterText(usernameField, userName);
		AD.enterText(passwordField, passWord);
		AD.click(loginButton);
	}
	
	//method to check error message is displayed
	public boolean isErrorMessageDisplayed() {
		return AD.isDisplayed(errorMessage);
	}
	
	//method to get the text from error message
	public String getErrorMessageText() {
		return AD.getText(errorMessage);
	}
	
	//method to verify error message is correct
	public boolean  verifyErrorMessage(String expectedMessage) {
		return AD.compareText(errorMessage, getErrorMessageText());
	}
}
