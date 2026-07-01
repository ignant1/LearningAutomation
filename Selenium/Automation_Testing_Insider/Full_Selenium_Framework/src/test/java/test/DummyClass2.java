package test;

import org.testng.annotations.Test;

import demo.project.base.BaseClass;
import demo.project.utilities.ExtentReportManager;

//this class is used to validate the Framework is functioning properly
public class DummyClass2 extends BaseClass{
	
	@Test
	public void dummyTest2() {
		ExtentReportManager.startTest("Dummy Test 2");
		logger.info("==============================================================================");
		logger.info("Running dummyTest from " + this.getClass().getName() + " class.");
		logger.info("==============================================================================");
		String title = getWebDriver().getTitle();
		ExtentReportManager.logStep("Verifying page title is correct.");
		assert title.equals("OrangeHRM"):"Test Failed : Title is not matching.";
		ExtentReportManager.logStep("Validation successfully.");
		ExtentReportManager.logStep("No need to log out since login did not happen.");
		
		logger.info("Test Passed : Title matches!");
	}
}
