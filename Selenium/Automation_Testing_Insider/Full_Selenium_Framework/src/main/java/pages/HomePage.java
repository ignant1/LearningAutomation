package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import actiondriver.ActionDriver;

public class HomePage {
	
	//class variables
	private ActionDriver actionDr;
	//define locators by class
	private By adminTab = By.xpath("//span[text()='Admin']");
	private By userIDButton = By.className("oxd-userdropdown-name");
	//private By logoutButton = By.xpath("//a[text]()='Logout']");
	private By logoutButton = By.linkText("Logout");
	private By orangeHRMLogo = By.xpath("//div[@class='oxd-brand-banner']//img");
	
	//Initialize the ActionDriver object by passing the WebDriver
	public HomePage(WebDriver wd) {
		actionDr = new ActionDriver(wd);
	}
	
	//Method to verify the admin tab is visible
	public boolean isAdminTabVisible() {
		return actionDr.isDisplayed(adminTab);
	}
	
	//method to verify OrangeHRM logo is present
	public boolean verifyOrangeHRMLogo() {
		return actionDr.isDisplayed(orangeHRMLogo);
	}
	
	//method to perform logout operation
	public void logout() {
		actionDr.click(userIDButton);
		actionDr.click(logoutButton);
	}
	
}
