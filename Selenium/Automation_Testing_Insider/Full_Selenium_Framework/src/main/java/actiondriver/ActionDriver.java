package actiondriver;

import java.time.Duration;
import java.util.Properties;

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
		driver = wb;
		wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(BaseClass.getProperties().getProperty("actiondriverwait"))));
	}
	
	//method to click an element
	public void click(By by) {
		try {
			waitForElementToBeClickable(by);
			driver.findElement(by).click();
		} catch (Exception e) {
			System.out.println("Unable to click the element: " + e.getMessage());
		}
	}
	
	//method to enter text into textbox
	public void enterText(By by, String text) {
		try {
			waitForElementToBeVisible(by);
			
			//Assigning element to variable to handle functionality
			//no longer going to do it directly in the driver
			WebElement element = driver.findElement(by);
			//driver.findElement(by).clear();
			//driver.findElement(by).sendKeys(text);
			element.clear();
			element.sendKeys(text);
			
		} catch (Exception e) {
			System.out.println("Unable to enter text into textbox: " + e.getMessage());
		}
	}
	
	//method to get text from textbox
	public String getText(By by) {
		try {
			waitForElementToBeVisible(by);
			return driver.findElement(by).getText();
		} catch (Exception e) {
			System.out.println("Unable to get text from textbox: " + e.getMessage());
			return "";
		}
	}
	
	//method to compare two texts
	public boolean compareText(By by, String expectedText) {
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
		try {
			waitForElementToBeVisible(by);
			
			//going to simplify the code to just return boolean isDisplayed, not going to include a printout
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
		try {
			waitForElementToBeVisible(by);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
		} catch (Exception e) {
			System.out.println("Unable to scroll to the element: " + e.getMessage());
		}
	}
	
	//wait for the page to be loaded
	public void waitForPageToLoad(int timeOutInSeconds) {
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
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			System.out.println("Element is not clickable: " + e.getMessage());
		}
	}
	
	//wait for element to be visible
	private void waitForElementToBeVisible(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			System.out.println("Element is not visible: " + e.getMessage());
		}
	}
}
