package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import actiondriver.ActionDriver;

public class HomePage {
	
	//class variables
	private ActionDriver AD;
	//define locators by class
	private By adminTab = By.xpath("//span[text()='Admin']");
	private By userIDButton = By.className("oxd-userdropdown-name");
	//private By logoutButton = By.xpath("//a[text]()='Logout']");
	private By logoutButton = By.linkText("Logout");
	private By orangeHRMLogo = By.xpath("//div[@class='oxd-brand-banner']//img");
	
	//Initialize the ActionDriver object by passing the WebDriver
	public HomePage(WebDriver wd) {
		AD = new ActionDriver(wd);
	}
	
	//Method to verify the admin tab is visible
	public boolean isAdminTabVisible() {
		return AD.isDisplayed(adminTab);
	}
	
	//method to verify OrangeHRM logo is present
	public boolean verifyOrangeHRMLogo() {
		return AD.isDisplayed(orangeHRMLogo);
	}
	
	//method to perform logout operation
	public void logout() {
		AD.click(userIDButton);
		AD.click(logoutButton);
	}
	
}
