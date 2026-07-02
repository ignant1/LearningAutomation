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
import demo.project.utilities.ExtentReportManager;

public class ActionDriver{
	
	private WebDriver webDr;
	private WebDriverWait wait;
	public static final Logger logger = BaseClass.logger;
	
	//Constructor for when the class gets created
	public ActionDriver(WebDriver wb) {
		logger.info("Action Driver constructor called.");
		webDr = wb;
		wait = new WebDriverWait(webDr, Duration.ofSeconds(Integer.parseInt(BaseClass.getProperties().getProperty("actiondriverwait"))));
		logger.info("Setup the WebDriver wait.");
	}
	
	//method to click an element
	public void click(By by) {
		String description = getElementDescription(by);
		logger.info("Running the Action Driver click method for " + by.toString());
		try {
			waitForElementToBeClickable(by, description);
			webDr.findElement(by).click();
			ExtentReportManager.logStep("Clicked Element: " + description);
			logger.info("Clicked Element: " + description);
		} catch (Exception e) {
			logger.error("Unable to click element: " + description);
			ExtentReportManager.logFailure(BaseClass.getWebDriver(), "Unable to click element: ", description + ": Unable to click.");
			logger.error("Error message: " + e.getMessage());
		}
	}
	
	//method to enter text into textbox
	public void enterText(By by, String text) {
		String description = getElementDescription(by);
		logger.info("Running the Action Driver enterText method for " + by.toString());
		logger.info("The text to enter is \'" + text + "\'");
		try {
			waitForElementToBeVisible(by, description);
			
			//Assigning element to variable to handle functionality
			//no longer going to do it directly in the driver
			WebElement element = webDr.findElement(by);
			//driver.findElement(by).clear();
			//driver.findElement(by).sendKeys(text);
			element.clear();
			element.sendKeys(text);
			ExtentReportManager.logStep("Entering text element: " + description);
			logger.info("Entering text element: " + description);
			logger.info("entered the text: \'" + text + "\'");
		} catch (Exception e) {
			ExtentReportManager.logFailure(BaseClass.getWebDriver(), "Unable to enter text into element: ", description + ": Unable to enter text.");
			logger.error("Unable to enter text into textbox: " + description);
			logger.error("Error Message: " + e.getMessage());
		}
	}
	
	//method to get text from textbox
	public String getText(By by) {
		String description = getElementDescription(by);
		logger.info("Running the Action Driver getText method for " + by.toString());
		try {
			waitForElementToBeVisible(by, description);
			logger.info("returning the text from the element: " + description);
			ExtentReportManager.logStep("Text was returned from element: " + description);
			return webDr.findElement(by).getText();
		} catch (Exception e) {
			ExtentReportManager.logFailure(BaseClass.getWebDriver(), "Unable to get text from element: ", description + ": Unable to get text.");
			logger.error("Unable to get text from element: " + description);
			logger.error("Error Message: " + e.getMessage());
			return "";
		}
	}
	
	//method to compare two texts
	public boolean compareText(By by, String expectedText) {
		String description = getElementDescription(by);
		logger.info("Running the Action Driver compareText method" + by.toString());
		logger.info("The text to compare is \'" + expectedText + "\'");
		try {
			waitForElementToBeVisible(by, description);
			String actualText = webDr.findElement(by).getText();
			if(expectedText.equals(actualText)) {
				ExtentReportManager.logStepWithScreenShot(BaseClass.getWebDriver(), "Compare Text", "Text Matches: " + actualText + " = " + expectedText);
				logger.info("Text Matches in the element: " + description);
				logger.info("Actual Text: \"" + actualText + "\" matches Expected Text : \""
						+ expectedText + "\" ");
				return true;
			} else {
				ExtentReportManager.logFailure(BaseClass.getWebDriver(), "Text Comparison Failed", "Text Does Not Match: " + actualText + " != " + expectedText);
				logger.info("Text Does Not Match in the element: " + description);
				logger.info("Actual Text: \"" + actualText + "\" does not match Expected Text : \""
						+ expectedText + "\" ");
				return false;
			}
		} catch (Exception e) {
			logger.error("Unable to get text from textbox: " + description);
			logger.error("Error Message: " + e.getMessage());
			return false;
		}
	}
	
	//method to check if element is displayed
	public boolean isDisplayed(By by){
		String description = getElementDescription(by);
		logger.info("Running the Action Driver isDisplayed method" + by.toString());
		try {
			waitForElementToBeVisible(by, description);
			
			//going to simplify the code to just return boolean isDisplayed, not going to include a printout
			ExtentReportManager.logStepWithScreenShot(BaseClass.getWebDriver(), "Element is displayed: ", description  + ": Element is visible.");
			logger.info("Returning boolean if the element, " + description + " is displayed or not.");
			return webDr.findElement(by).isDisplayed();
			/*
			 * boolean isDisplayed = driver.findElement(by).isDisplayed(); if(isDisplayed) {
			 * System.out.println("Element is visible"); return isDisplayed; } else {
			 * System.out.println("Element is not visible."); return isDisplayed; }
			 */
		} catch (Exception e) {
			ExtentReportManager.logFailure(BaseClass.getWebDriver(), "Element is not displayed: ", description + ": Element is invisible.");
			logger.error("Element is not visible: " + description);
			logger.error("Error message: " + e.getMessage());
			return false;
		}
	}
	
	//scroll to an element
	public void scrollToElement(By by) {
		String description = getElementDescription(by);
		logger.info("Running the Action Driver scrollToElement method" + by.toString());
		try {
			waitForElementToBeVisible(by, description);
			JavascriptExecutor jse = (JavascriptExecutor) webDr;
			jse.executeScript("arguments[0].scrollIntoView(true);", webDr.findElement(by));
			ExtentReportManager.logStepWithScreenShot(BaseClass.getWebDriver(), "Scrolled to Element: ", description + ": Scrolled");
			logger.info("Scrolled to the element: " + description);
		} catch (Exception e) {
			ExtentReportManager.logFailure(BaseClass.getWebDriver(), "Unable to scroll to the element: ", description + ": Did not scroll.");
			logger.error("Unable to scroll to the element: " + description);
			logger.error("Error Message: " + e.getMessage());
		}
	}
	
	//wait for the page to be loaded
	public void waitForPageToLoad(int timeOutInSeconds) {
		logger.info("Running the Action Driver waitForPageToLoad method");
		try {
			wait.withTimeout(Duration.ofSeconds(timeOutInSeconds)).until(WebDriver -> ((JavascriptExecutor) WebDriver)
					.executeScript("return document.readyState").equals("complete"));
			ExtentReportManager.logStepWithScreenShot(BaseClass.getWebDriver(), "Web Page Load: ", "Page loaded!");
			logger.info("Page loaded successfully.");
		} catch (Exception e) {
			ExtentReportManager.logFailure(BaseClass.getWebDriver(), "Web Page Load Failed: ","Page did not loaad within " + timeOutInSeconds + " seconds.");
			logger.info("Page did not load within " + timeOutInSeconds + " seconds. Exception: " + e.getMessage());
			logger.error("Page did not load within " + timeOutInSeconds + " seconds. Exception: " + e.getMessage());
		}
	}
	
	//wait for element to be clickable
	private void waitForElementToBeClickable(By by, String description) {
		//The get getElementDescription method was also being run by the method that called this one so will just pass the description through the call
		//String description = getElementDescription(by);
		logger.info("Running the Action Driver waitforElementToBeClickable method: " + by.toString());
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
			logger.info("Element is now clickable: " + description);
		} catch (Exception e) {
			ExtentReportManager.logFailure(BaseClass.getWebDriver(), "Element did not become clickable: ", description + ": Unable to click.");
			logger.error("Element is not clickable: " + description);
			logger.error("Error message: " + e.getMessage());
		}
	}
	
	//wait for element to be visible
	private void waitForElementToBeVisible(By by, String description) {
		//The get getElementDescription method was also being run by the method that called this one so will just pass the description through the call
		//String description = getElementDescription(by);
		logger.info("Running the Action Driver waitForElementToBeVisible method: " + by.toString());
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			logger.info("Element is now visible: " + description);
		} catch (Exception e) {
			ExtentReportManager.logFailure(BaseClass.getWebDriver(), "Element did not become visible: ", description + ": Invisible.");
			logger.error("Element is not visible: " + description);
			logger.error("Error Message: " + e.getMessage());
		}
	}
	
	//method to get the description of the element by locator
	public String getElementDescription(By locator) {
		logger.info("==========================================");
		logger.info("== in getElementDescription method");
		logger.info("== locator = " + locator.toString());
		logger.info("==========================================");
		//check for null driver or locator to avoid null pointer exception
		if(webDr==null) {
			return "Web Driver was not intialized.";
		}
		
		//find the element
		WebElement element = webDr.findElement(locator);
		
		//get the element attribute
		String name = element.getDomAttribute("name");
		String id = element.getDomAttribute("id");
		String text = element.getText();
		String className = element.getDomAttribute("class");
		String placeholder = element.getDomAttribute("placeholder");
		
		try {
			//Return the description based on available attribute
			if(isEmptyOrNull(name)){
				ExtentReportManager.logStep("Able to get Element name " + truncate(name,50));
				logger.info("== name = " + name);
				logger.info("==========================================");
				return ("Element name: " + truncate(name,50));
			} else if(isEmptyOrNull(id)){
				ExtentReportManager.logStep("Able to get Element id " + truncate(id,50));
				logger.info("== id = " + id);
				logger.info("==========================================");
				return ("Element id: " + truncate(id,50));
			} else if(isEmptyOrNull(text)){
				ExtentReportManager.logStep("Able to get Element text " + truncate(text,50));
				logger.info("== text = " + text);
				logger.info("==========================================");
				return ("Element text: " + truncate(text,50));
			} else if(isEmptyOrNull(className)){
				ExtentReportManager.logStep("Able to get Element className " + truncate(className,50));
				logger.info("== className = " + className);
				logger.info("==========================================");
				return ("Element className: " + truncate(className,50));
			} else if(isEmptyOrNull(placeholder)){
				ExtentReportManager.logStep("Able to get Element placeholder " + truncate(placeholder,50));
				logger.info("== placeholder = " + placeholder);
				logger.info("==========================================");
				return ("Element placeholder: " + truncate(placeholder,50));
			}
		} catch (Exception e) {
			ExtentReportManager.logFailure(BaseClass.getWebDriver(), "No attributes available: ", locator.toString() + ": No Attributes.");
			logger.error("Description not available for " + locator.toString());
			logger.error("Error message: " + e.getMessage());
		}
		
		return ("Description not available for " + locator.toString());
	}
	
	//utility method to check if string is empty or null
	private boolean isEmptyOrNull(String text) {
		return (text != null && !text.isEmpty());
	}
	
	//utility method to truncate long String
	private String truncate(String text, int maxLength) {
		if(text == null || text.length() <= maxLength) {
			return text;
		} else {
			return (text.substring(0, maxLength) + "...");
		}
	}
}
