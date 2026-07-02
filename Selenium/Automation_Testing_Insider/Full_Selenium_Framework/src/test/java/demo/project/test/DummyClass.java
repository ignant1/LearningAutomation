package demo.project.test;

import org.testng.SkipException;
import org.testng.annotations.Test;

import demo.project.base.BaseClass;
import demo.project.utilities.ExtentReportManager;

//this class is used to validate the Framework is functioning properly
public class DummyClass extends BaseClass{
	
	@Test
	public void dummyTest() {
		////this has been moved to TestListener.onTestStart method
		//ExtentReportManager.startTest("Dummy Test 1");
		logger.info("==============================================================================");
		logger.info("Running dummyTest from " + this.getClass().getName() + " class.");
		logger.info("==============================================================================");
		String title = getWebDriver().getTitle();
		ExtentReportManager.logStep("Verifying page title is correct.");
		assert title.equals("OrangeHRM"):"Test Failed : Title is not matching.";
		ExtentReportManager.logStep("Validation successfully.");
		ExtentReportManager.logStep("No need to log out since login did not happen.");

		logger.info("Test Passed : Title matches!");
		ExtentReportManager.logSkip("This test case has been skipped.");
		throw new SkipException("Skipping the test as part of testing.");
	}
}
