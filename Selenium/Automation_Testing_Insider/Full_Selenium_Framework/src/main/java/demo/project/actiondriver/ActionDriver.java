package demo.project.actiondriver;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import demo.project.base.BaseClass;

public class ActionDriver{
	
	private WebDriver driver;
	private WebDriverWait wait;
	public static final Logger logger = BaseClass.logger;
	
	//Constructor for when the class gets created
	public ActionDriver(WebDriver wb) {
		logger.info("Action Driver constructor called.");
		driver = wb;
		wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(BaseClass.getProperties().getProperty("actiondriverwait"))));
		logger.info("Setup the WebDriver wait.");
	}
	
	//method to click an element
	public void click(By by) {
		logger.info("Running the Action Driver click method." + by.toString());
		try {
			waitForElementToBeClickable(by);
			driver.findElement(by).click();
			logger.info("performed the click");
		} catch (Exception e) {
			logger.info("Unable to click the element: " + e.getMessage());
			logger.error("Unable to click the element: " + e.getMessage());
		}
	}
	
	//method to enter text into textbox
	public void enterText(By by, String text) {
		logger.info("Running the Action Driver enterText method" + by.toString());
		logger.info("The text to enter is \'" + text + "\'");
		try {
			waitForElementToBeVisible(by);
			
			//Assigning element to variable to handle functionality
			//no longer going to do it directly in the driver
			WebElement element = driver.findElement(by);
			//driver.findElement(by).clear();
			//driver.findElement(by).sendKeys(text);
			element.clear();
			element.sendKeys(text);
			logger.info("entered the text: \'" + text + "\'");
		} catch (Exception e) {
			logger.info("Unable to enter text into textbox: " + e.getMessage());
			logger.error("Unable to enter text into textbox: " + e.getMessage());
		}
	}
	
	//method to get text from textbox
	public String getText(By by) {
		logger.info("Running the Action Driver getText method");
		try {
			waitForElementToBeVisible(by);
			logger.info("returning the text from the element.");
			return driver.findElement(by).getText();
		} catch (Exception e) {
			logger.info("Unable to get text from textbox: " + e.getMessage());
			logger.error("Unable to get text from textbox: " + e.getMessage());
			return "";
		}
	}
	
	//method to compare two texts
	public boolean compareText(By by, String expectedText) {
		logger.info("Running the Action Driver compareText method" + by.toString());
		logger.info("The text to compare is \'" + expectedText + "\'");
		try {
			waitForElementToBeVisible(by);
			String actualText = driver.findElement(by).getText();
			if(expectedText.equals(actualText)) {
				logger.info("Text Matches:");
				logger.info("Actual Text: \"" + actualText + "\" matches Expected Text : \""
						+ expectedText + "\" ");
				return true;
			} else {
				logger.info("Text Does Not Match:");
				logger.info("Actual Text: \"" + actualText + "\" does not match Expected Text : \""
						+ expectedText + "\" ");
				return false;
			}
		} catch (Exception e) {
			logger.info("Unable to get text from textbox: " + e.getMessage());
			logger.error("Unable to get text from textbox: " + e.getMessage());
			return false;
		}
	}
	
	//method to check if element is displayed
	public boolean isDisplayed(By by){
		logger.info("Running the Action Driver isDisplayed method" + by.toString());
		try {
			waitForElementToBeVisible(by);
			
			//going to simplify the code to just return boolean isDisplayed, not going to include a printout
			logger.info("Returning boolean if the element is displayed or not.");
			return driver.findElement(by).isDisplayed();
			/*
			 * boolean isDisplayed = driver.findElement(by).isDisplayed(); if(isDisplayed) {
			 * System.out.println("Element is visible"); return isDisplayed; } else {
			 * System.out.println("Element is not visible."); return isDisplayed; }
			 */
		} catch (Exception e) {
			logger.info("Element is not visible: " + e.getMessage());
			logger.error("Element is not visible: " + e.getMessage());
			return false;
		}
	}
	
	//scroll to an element
	public void scrollToElement(By by) {
		logger.info("Running the Action Driver scrollToElement method" + by.toString());
		try {
			waitForElementToBeVisible(by);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
			logger.info("Scrolled to the element.");
		} catch (Exception e) {
			logger.info("Unable to scroll to the element: " + e.getMessage());
			logger.error("Unable to scroll to the element: " + e.getMessage());
		}
	}
	
	//wait for the page to be loaded
	public void waitForPageToLoad(int timeOutInSeconds) {
		logger.info("Running the Action Driver waitForPageToLoad method");
		try {
			wait.withTimeout(Duration.ofSeconds(timeOutInSeconds)).until(WebDriver -> ((JavascriptExecutor) WebDriver)
					.executeScript("return document.readyState").equals("complete"));
			logger.info("Page loaded successfully.");
		} catch (Exception e) {
			logger.info("Page did not load within " + timeOutInSeconds + " seconds. Exception: " + e.getMessage());
			logger.error("Page did not load within " + timeOutInSeconds + " seconds. Exception: " + e.getMessage());
		}
	}
	
	//wait for element to be clickable
	private void waitForElementToBeClickable(By by) {
		logger.info("Running the Action Driver waitforElementToBeClickable method: " + by.toString());
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
			logger.info("Element is now clickable.");
		} catch (Exception e) {
			logger.info("Element is not clickable: " + e.getMessage());
			logger.error("Element is not clickable: " + e.getMessage());
		}
	}
	
	//wait for element to be visible
	private void waitForElementToBeVisible(By by) {
		logger.info("Running the Action Driver waitForElementToBeVisible method" + by.toString());
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			logger.info("Element is now visible.");
		} catch (Exception e) {
			logger.info("Element is not visible: " + e.getMessage());
			logger.error("Element is not visible: " + e.getMessage());
		}
	}
}
