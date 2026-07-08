package doing.stuff;

import org.openqa.selenium.By;

public class GettingLocators {
	
	public By getBasicLocator(String type, String name) {
		if(type.toLowerCase().contains("xpath")){
			return getByXPath(name);
		} else if(type.toLowerCase().contains("cssselector")){
			return getByCSSSelector(name);
		} else if(type.toLowerCase().contains("linktext") && !type.contains("partiallinktext")){
			return getByLinkText(name);
		} else if(type.toLowerCase().contains("classname")){
			return getByClassName(name);
		} else if(type.toLowerCase().contains("id")){
			return getByID(name);
		} else if(type.toLowerCase().contains("name") && !type.contains("classname") && !type.contains("tagname")){
			return getByName(name);
		} else if(type.toLowerCase().contains("partiallinktext")){
			return getByPartialLinkText(name);
		} else if(type.toLowerCase().contains("tagname")){
			return getByTagName(name);
		} else {
			System.out.println("Invaid locator type provided: " + type.toLowerCase());
			return null;
		}
	}
	
	////////////////////////////////////////////////////////////////
	/// 
	/// methods that return By locators
	/// 
	/////////////////////////////////////////////////////////////////

	private By getByXPath(String text) {
		return By.xpath(text);
	}
	
	private By getByCSSSelector(String text) {
		return By.cssSelector(text);
	}

	private By getByLinkText(String text) {
		return By.linkText(text);
	}

	private By getByClassName(String text) {
		return By.className(text);
	}

	private By getByID(String id) {
		return By.id(id);
	}

	private By getByName(String name) {
		return By.name(name);
	}

	private By getByTagName(String name) {
		return By.tagName(name);
	}
	
	private By getByPartialLinkText(String name) {
		return By.partialLinkText(name);
	}

}
