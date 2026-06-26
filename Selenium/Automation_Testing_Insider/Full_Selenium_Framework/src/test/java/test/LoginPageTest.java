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
		loginPage = new LoginPage(getDriver());
		homePage = new HomePage(getDriver());
	}
	
	@Test
	public void validLoginTest() {
		loginPage.performLogin("admin", "admin123");
		Assert.assertTrue(homePage.isAdminTabVisible(),"Admin tab should be visible after successfull login");
		homePage.logout();
		staticwait(2);
	}

	@Test
	public void invalidLoginTest() {
		loginPage.performLogin("admin", "admin12");
		String expectedErrorMessage = "Invalid credentials";
		Assert.assertTrue(loginPage.verifyErrorMessage(expectedErrorMessage),"Test Failed: invalid error message");
		staticwait(2);
	}
}
