package demo.project.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import demo.project.base.BaseClass;
import demo.project.pages.HomePage;
import demo.project.pages.LoginPage;
import demo.project.utilities.ExtentReportManager;

public class LoginPageTest extends BaseClass{

	//class variables
	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setupPages() {
		logger.info("Going to setup the login page and home page instances for the LoginPageTest Class.");
		loginPage = new LoginPage();//getDriver());   - no longer need to pass the web driver
		homePage = new HomePage();//getDriver());   - no longer need to pass the web driver
	}
	
	@Test
	public void TC01_validLoginTest() {
		////this has been moved to TestListener.onTestStart method
		//ExtentReportManager.startTest("Login Page-Text Case 01-Valid Login Test");
		logger.info("==============================================================================");
		logger.info("Running test case TC01_validLoginTest.");
		logger.info("==============================================================================");
		ExtentReportManager.logStep("Loading Login Page and entering username and password.");
		loginPage.performLogin("admin", "admin123");
		ExtentReportManager.logStep("Verifying Admin tab is visible or not.");
		Assert.assertTrue(homePage.isAdminTabVisible(),"Admin tab should be visible after successfull login");
		ExtentReportManager.logStep("Validation successfully.");
		homePage.logout();
		ExtentReportManager.logStep("Logged out successfully.");
		staticwait(2);

	}

	@Test
	public void TC02_invalidLoginTest() {
		////this has been moved to TestListener.onTestStart method
		//ExtentReportManager.startTest("Login Page-Text Case 02-Invalid Login Test");
		logger.info("==============================================================================");
		logger.info("Running test case TC02_invalidLoginTest.");
		logger.info("==============================================================================");
		ExtentReportManager.logStep("Loading Login Page and entering username and bad password.");
		loginPage.performLogin("admin", "admin12");
		ExtentReportManager.logStep("Verifying error message is correct.");
		String expectedErrorMessage = "Invalid credentials1";
		Assert.assertTrue(loginPage.verifyErrorMessage(expectedErrorMessage),"Test Failed: invalid error message");
		ExtentReportManager.logStep("Error message correct.");
		ExtentReportManager.logStep("No need to log out since login did not happen.");
		staticwait(2);
	}
}
