package demo.project.pages;

import org.openqa.selenium.By;

import demo.project.actiondriver.ActionDriver;
import demo.project.base.BaseClass;

public class HomePage {
	
	//class variables
	private ActionDriver actionDr;
	//define locators by class
	private By adminTab = By.xpath("//span[text()='Admin']");
	private By userIDButton = By.className("oxd-userdropdown-name");
	//private By logoutButton = By.xpath("//a[text]()='Logout']");
	private By logoutButton = By.linkText("Logout");
	private By orangeHRMLogo = By.xpath("//div[@class='oxd-brand-banner']//img");
	private By pimTab = By.xpath("//span[text()='PIM']");
	private By employeeSearch = By.xpath("//label[text()='Employee Name']/following::input[1]");
	private By searchButton = By.xpath("//button[@type='submit'][text()=' Search ']");
	private By firstMiddleName = By.xpath("//div[@class='oxd-table-card']/div/div[3]");
	private By lastName = By.xpath("//div[@class='oxd-table-card']/div/div[4]");
	
	//Initialize the ActionDriver object by passing the WebDriver
	public HomePage() {//WebDriver wd) {
		//actionDr = new ActionDriver(wd);
		this.actionDr = BaseClass.getActionDriver();
	}
	
	//Method to verify the admin tab is visible
	public boolean isAdminTabVisible() {
		return actionDr.isDisplayed(adminTab);
	}
	
	//method to verify OrangeHRM logo is present
	public boolean verifyOrangeHRMLogo() {
		return actionDr.isDisplayed(orangeHRMLogo);
	}
	
	//method to get to PIM tab
	public void clickOnPIMTab() {
		actionDr.click(pimTab);
	}
	
	//method to search employee name
	public void searchEmployee(String name) {
		actionDr.enterText(employeeSearch, name);
		actionDr.click(searchButton);
		actionDr.scrollToElement(firstMiddleName);
	}
	
	//verify emoloyee's first and middle name
	public boolean verifyEmployeeFirstMiddleName(String fmName) {
		return actionDr.compareText(firstMiddleName, fmName);
	}

	//verify emoloyee's last name
	public boolean verifyEmployeeLastName(String lName) {
		return actionDr.compareText(lastName, lName);
	}

	//method to perform logout operation
	public void logout() {
		actionDr.click(userIDButton);
		actionDr.click(logoutButton);
	}
	
}
