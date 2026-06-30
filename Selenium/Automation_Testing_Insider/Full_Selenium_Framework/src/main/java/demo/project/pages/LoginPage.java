package demo.project.pages;

import org.openqa.selenium.By;

import demo.project.actiondriver.ActionDriver;
import demo.project.base.BaseClass;

public class LoginPage {

	//Define Class Objects
	private ActionDriver actionDr;
	
	//Initialize the ActionDriver object by passing the WebDriver
	public LoginPage() {//WebDriver wd) {
		//actionDr = new ActionDriver(wd);
		this.actionDr = BaseClass.getActionDriver();
	}
	
	//Define locators
	private By usernameField = By.name("username");
	private By passwordField = By.cssSelector("input[type='password']");
	private By loginButton = By.xpath("//button[text()=' Login ']");
	private By errorMessage = By.xpath("//p[text()='Invalid credentials']");
	
	//method to perform login
	public void performLogin(String userName, String passWord) {
		actionDr.enterText(usernameField, userName);
		actionDr.enterText(passwordField, passWord);
		actionDr.click(loginButton);
	}
	
	//method to check error message is displayed
	public boolean isErrorMessageDisplayed() {
		return actionDr.isDisplayed(errorMessage);
	}
	
	//method to get the text from error message
	public String getErrorMessageText() {
		return actionDr.getText(errorMessage);
	}
	
	//method to verify error message is correct
	public boolean  verifyErrorMessage(String expectedMessage) {
		return actionDr.compareText(errorMessage, expectedMessage);
	}
}
