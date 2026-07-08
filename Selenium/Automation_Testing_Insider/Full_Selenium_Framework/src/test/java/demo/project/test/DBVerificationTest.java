package demo.project.test;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import demo.project.base.BaseClass;
import demo.project.pages.HomePage;
import demo.project.pages.LoginPage;
import demo.project.utilities.DBConnectionManager;
import demo.project.utilities.DataProviderManager;
import demo.project.utilities.ExtentReportManager;

public class DBVerificationTest extends BaseClass{

	//class variables
	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setupPages() {
		logger.info("Going to setup the login page and home page instances for the DBVerificationTest Class.");
		loginPage = new LoginPage();//getDriver());   - no longer need to pass the web driver
		homePage = new HomePage();//getDriver());   - no longer need to pass the web driver
	}
	
	@Test(dataProvider="EmployeeVerification", dataProviderClass=DataProviderManager.class)
	public void verifyEmployeeNameFromDB(String emp_id, String emp_name) {
		
		//create SoftAssert object
		SoftAssert softAssert = getSoftAssert();
		//login to the site
		ExtentReportManager.logStep("Logging in with Admin Credentials.");
		loginPage.performLogin(properties.getProperty("username"), properties.getProperty("password"));
		
		//access the PIM page
		ExtentReportManager.logStep("Click the PIM Tab.");
		homePage.clickOnPIMTab();
		
		//Search for employee
		ExtentReportManager.logStep("Search for Employee.");
		homePage.searchEmployee(emp_name);
		staticwait(2);
		
		ExtentReportManager.logStep("Get the employee name from the database.");
		
		String employee_id = emp_id;
		
		//Fetch the employee data
		Map<String,String> employeeDetails = new HashMap<>();
		employeeDetails = DBConnectionManager.getEmployDetails(employee_id);
		String firstName = employeeDetails.get("emp_firstname");
		String middleName = employeeDetails.get("emp_middle_name");
		String lastName = employeeDetails.get("emp_lastname");
		
		//validate first and middle name
		String firstMiddleName = (firstName + " " + middleName).trim();
		ExtentReportManager.logStep("Verify employee's first (and middle) name: " + firstMiddleName);
		logger.info("Verify employee's first (and middle) name: " + firstMiddleName);
		softAssert.assertTrue(homePage.verifyEmployeeFirstMiddleName(firstMiddleName),"First (and middle) name is not matching!");

		//validate last name
		ExtentReportManager.logStep("Verify employee's last name.");
		softAssert.assertTrue(homePage.verifyEmployeeLastName(lastName),"Last name is not matching!");
		
		ExtentReportManager.logStep("Employee's name verified via database.");
		softAssert.assertAll();
	}
}
