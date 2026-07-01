package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import demo.project.base.BaseClass;
import demo.project.pages.HomePage;
import demo.project.pages.LoginPage;
import demo.project.utilities.ExtentReportManager;

public class HomePageTest extends BaseClass{

	//class variables
	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setupPages() {
		logger.info("going to setup the login page and home page instances for the HomePageTest Class.");
		loginPage = new LoginPage();//getDriver());   - no longer need to pass the web driver
		homePage = new HomePage();//getDriver());   - no longer need to pass the web driver
	}
	
	@Test
	public void verifyOrnageHRMLogo() {
		ExtentReportManager.startTest("Home Page-Logo Test");
		logger.info("==============================================================================");
		logger.info("Running test case verifyOrnageHRMLogo.");
		logger.info("==============================================================================");
		ExtentReportManager.logStep("Loading Login Page and entering username and password.");
		loginPage.performLogin("admin", "admin123");
		ExtentReportManager.logStep("Verifying Logo is visible or not.");
		Assert.assertTrue(homePage.verifyOrangeHRMLogo(),"Logo is not visible");
		ExtentReportManager.logStep("Validation successfully.");
		homePage.logout();
		ExtentReportManager.logStep("Logged out successfully.");
		staticwait(2);
	}

}
