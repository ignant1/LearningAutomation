package demo.project.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import demo.project.base.BaseClass;
import demo.project.utilities.ExtentReportManager;

public class TestListener implements ITestListener{

	//Triggers when a test case starts
	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		//start logging the extent report for the current test
		ExtentReportManager.startTest(testName);
		ExtentReportManager.logStep("Test, " + testName + ", started from the TestListener.");
	}

	//Triggers when a test successfully passes
	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		ExtentReportManager.logStepWithScreenShot(BaseClass.getWebDriver(), "Test Completed Successfully!", "Test End: " + testName + " - ✔ Test Passed");
	}

	//Triggers when a test fails
	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		String failureMessage = result.getThrowable().getMessage();
		ExtentReportManager.logStep(failureMessage);
		ExtentReportManager.logFailure(BaseClass.getWebDriver(), "Test Failed!", "Test End: " + testName + " - ❌ Test Failed");
	}

	//Triggers when test is skipped
	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		ExtentReportManager.logStep("Test Skipped: " + testName);
	}

	//This will trigger when test suite starts
	@Override
	public void onStart(ITestContext context) {
		//initialize the Extent Report
		ExtentReportManager.getExtentReport();
	}

	//This will trigger when the test suite ends
	@Override
	public void onFinish(ITestContext context) {
		//flush the extent report
		ExtentReportManager.endTest();
	}
	
}
