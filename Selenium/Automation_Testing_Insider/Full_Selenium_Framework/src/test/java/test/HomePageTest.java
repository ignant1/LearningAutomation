package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import demo.project.base.BaseClass;
import demo.project.pages.HomePage;
import demo.project.pages.LoginPage;

public class HomePageTest extends BaseClass{

	//class variables
	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setupPages() {
		System.out.println("going to setup the login page and home page instances for the HomePageTest Class.");
		loginPage = new LoginPage();//getDriver());   - no longer need to pass the web driver
		homePage = new HomePage();//getDriver());   - no longer need to pass the web driver
	}
	
	@Test
	public void verifyOrnageHRMLogo() {
		System.out.println("==============================================================================");
		System.out.println("Running test case verifyOrnageHRMLogo.");
		System.out.println("==============================================================================");
		loginPage.performLogin("admin", "admin123");
		Assert.assertTrue(homePage.verifyOrangeHRMLogo(),"Logo is not visible");
		homePage.logout();
		staticwait(2);
	}

}
