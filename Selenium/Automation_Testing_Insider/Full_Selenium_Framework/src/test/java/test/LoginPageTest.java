package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.HomePage;
import pages.LoginPage;

public class LoginPageTest extends BaseClass{

	//class variables
	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setupPages() {
		System.out.println("going to setup the login page and home page instances for the LoginPageTest Class.");
		loginPage = new LoginPage();//getDriver());   - no longer need to pass the web driver
		homePage = new HomePage();//getDriver());   - no longer need to pass the web driver
	}
	
	@Test
	public void TC01_validLoginTest() {
		System.out.println("==============================================================================");
		System.out.println("Running test case TC01_validLoginTest.");
		System.out.println("==============================================================================");
		loginPage.performLogin("admin", "admin123");
		Assert.assertTrue(homePage.isAdminTabVisible(),"Admin tab should be visible after successfull login");
		homePage.logout();
		staticwait(2);
	}

	@Test
	public void TC02_invalidLoginTest() {
		System.out.println("==============================================================================");
		System.out.println("Running test case TC02_validLoginTest.");
		System.out.println("==============================================================================");
		loginPage.performLogin("admin", "admin12");
		String expectedErrorMessage = "Invalid credentials";
		Assert.assertTrue(loginPage.verifyErrorMessage(expectedErrorMessage),"Test Failed: invalid error message");
		staticwait(2);
	}
}
