package test;

import org.testng.annotations.Test;

import demo.project.base.BaseClass;

//this class is used to validate the Framework is functioning properly
public class DummyClass extends BaseClass{
	
	@Test
	public void dummyTest() {
		System.out.println("==============================================================================");
		System.out.println("Running dummyTest from " + this.getClass().getName() + " class.");
		System.out.println("==============================================================================");
		String title = getWebDriver().getTitle();
		assert title.equals("OrangeHRM"):"Test Failed : Title is not matching.";
		
		System.out.println("Test Passed : Title matches!");
	}
}
