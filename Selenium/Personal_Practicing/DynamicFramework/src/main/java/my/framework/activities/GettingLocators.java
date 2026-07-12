package my.framework.activities;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import my.framework.support.TraceManager;

public class GettingLocators {
	
	public static final Logger logger = TraceManager.getLogger(GettingLocators.class);
	
	public By getBasicLocator(String type, String name) {
		if(type.toLowerCase().contains("xpath")){
			logger.info("Confirmed locator type is xpath.");
			return getByXPath(name);
		} else if(type.toLowerCase().contains("cssselector")){
			logger.info("Confirmed locator type is cssselector.");
			return getByCSSSelector(name);
		} else if(type.toLowerCase().contains("linktext") && !type.contains("partiallinktext")){
			logger.info("Confirmed locator type is linktext.");
			return getByLinkText(name);
		} else if(type.toLowerCase().contains("classname")){
			logger.info("Confirmed locator type is classname.");
			return getByClassName(name);
		} else if(type.toLowerCase().contains("id")){
			logger.info("Confirmed locator type is id.");
			return getByID(name);
		} else if(type.toLowerCase().contains("name") && !type.contains("classname") && !type.contains("tagname")){
			logger.info("Confirmed locator type is name.");
			return getByName(name);
		} else if(type.toLowerCase().contains("partiallinktext")){
			logger.info("Confirmed locator type is partiallinktext.");
			return getByPartialLinkText(name);
		} else if(type.toLowerCase().contains("tagname")){
			logger.info("Confirmed locator type is tagname.");
			return getByTagName(name);
		} else {
			logger.info("Invaid locator type provided: " + type.toLowerCase());
			return null;
		}
	}
	
	////////////////////////////////////////////////////////////////
	/// 
	/// methods that return By locators
	/// 
	/////////////////////////////////////////////////////////////////

	private By getByXPath(String text) {
		logger.info("Returning By xpath");
		return By.xpath(text);
	}
	
	private By getByCSSSelector(String text) {
		logger.info("Returning By cssselector");
		return By.cssSelector(text);
	}

	private By getByLinkText(String text) {
		logger.info("Returning By linktext");
		return By.linkText(text);
	}

	private By getByClassName(String text) {
		logger.info("Returning By classname");
		return By.className(text);
	}

	private By getByID(String id) {
		logger.info("Returning By id");
		return By.id(id);
	}

	private By getByName(String name) {
		logger.info("Returning By name");
		return By.name(name);
	}

	private By getByTagName(String name) {
		logger.info("Returning By tagname");
		return By.tagName(name);
	}
	
	private By getByPartialLinkText(String name) {
		logger.info("Returning By partiallinktext");
		return By.partialLinkText(name);
	}

}
