package actiondriver;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;

public class ActionDriver{
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	//Constructor for when the class gets created
	public ActionDriver(WebDriver wb) {
		System.out.println("Action Driver constructor called.");
		driver = wb;
		wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(BaseClass.getProperties().getProperty("actiondriverwait"))));
		System.out.println("Setup the WebDriver wait.");
	}
	
	//method to click an element
	public void click(By by) {
		System.out.println("Running the Action Driver click method." + by.toString());
		try {
			waitForElementToBeClickable(by);
			driver.findElement(by).click();
			System.out.println("performed the click");
		} catch (Exception e) {
			System.out.println("Unable to click the element: " + e.getMessage());
		}
	}
	
	//method to enter text into textbox
	public void enterText(By by, String text) {
		System.out.println("Running the Action Driver enterText method" + by.toString());
		System.out.println("The text to enter is \'" + text + "\'");
		try {
			waitForElementToBeVisible(by);
			
			//Assigning element to variable to handle functionality
			//no longer going to do it directly in the driver
			WebElement element = driver.findElement(by);
			//driver.findElement(by).clear();
			//driver.findElement(by).sendKeys(text);
			element.clear();
			element.sendKeys(text);
			System.out.println("entered the text: \'" + text + "\'");
		} catch (Exception e) {
			System.out.println("Unable to enter text into textbox: " + e.getMessage());
		}
	}
	
	//method to get text from textbox
	public String getText(By by) {
		System.out.println("Running the Action Driver getText method");
		try {
			waitForElementToBeVisible(by);
			System.out.println("returning the text from the element.");
			return driver.findElement(by).getText();
		} catch (Exception e) {
			System.out.println("Unable to get text from textbox: " + e.getMessage());
			return "";
		}
	}
	
	//method to compare two texts
	public boolean compareText(By by, String expectedText) {
		System.out.println("Running the Action Driver compareText method" + by.toString());
		System.out.println("The text to compare is \'" + expectedText + "\'");
		try {
			waitForElementToBeVisible(by);
			String actualText = driver.findElement(by).getText();
			if(expectedText.equals(actualText)) {
				System.out.println("Text Matches:");
				System.out.println("Actual Text: \"" + actualText + "\" matches Expected Text : \""
						+ expectedText + "\" ");
				return true;
			} else {
				System.out.println("Text Does Not Match:");
				System.out.println("Actual Text: \"" + actualText + "\" does not match Expected Text : \""
						+ expectedText + "\" ");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Unable to get text from textbox: " + e.getMessage());
			return false;
		}
	}
	
	//method to check if element is displayed
	public boolean isDisplayed(By by){
		System.out.println("Running the Action Driver isDisplayed method" + by.toString());
		try {
			waitForElementToBeVisible(by);
			
			//going to simplify the code to just return boolean isDisplayed, not going to include a printout
			System.out.println("Returning boolean if the element is displayed or not.");
			return driver.findElement(by).isDisplayed();
			/*
			 * boolean isDisplayed = driver.findElement(by).isDisplayed(); if(isDisplayed) {
			 * System.out.println("Element is visible"); return isDisplayed; } else {
			 * System.out.println("Element is not visible."); return isDisplayed; }
			 */
		} catch (Exception e) {
			System.out.println("Element is not visible: " + e.getMessage());
			return false;
		}
	}
	
	//scroll to an element
	public void scrollToElement(By by) {
		System.out.println("Running the Action Driver scrollToElement method" + by.toString());
		try {
			waitForElementToBeVisible(by);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
			System.out.println("Scrolled to the element.");
		} catch (Exception e) {
			System.out.println("Unable to scroll to the element: " + e.getMessage());
		}
	}
	
	//wait for the page to be loaded
	public void waitForPageToLoad(int timeOutInSeconds) {
		System.out.println("Running the Action Driver waitForPageToLoad method");
		try {
			wait.withTimeout(Duration.ofSeconds(timeOutInSeconds)).until(WebDriver -> ((JavascriptExecutor) WebDriver)
					.executeScript("return document.readyState").equals("complete"));
			System.out.println("Page loaded successfully.");
		} catch (Exception e) {
			System.out.println("Page did not load within " + timeOutInSeconds + " seconds. Exception: " + e.getMessage());
		}
	}
	
	//wait for element to be clickable
	private void waitForElementToBeClickable(By by) {
		System.out.println("Running the Action Driver waitforElementToBeClickable method: " + by.toString());
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
			System.out.println("Element is now clickable.");
		} catch (Exception e) {
			System.out.println("Element is not clickable: " + e.getMessage());
		}
	}
	
	//wait for element to be visible
	private void waitForElementToBeVisible(By by) {
		System.out.println("Running the Action Driver waitForElementToBeVisible method" + by.toString());
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			System.out.println("Element is now visible.");
		} catch (Exception e) {
			System.out.println("Element is not visible: " + e.getMessage());
		}
	}
}
