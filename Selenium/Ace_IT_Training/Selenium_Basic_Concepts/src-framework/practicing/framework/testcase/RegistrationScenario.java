package practicing.framework.testcase;

import org.testng.annotations.Test;

import practicing.framework.base.InitiateBrowser;
import practicing.framework.page.RegistrationPage;

public class RegistrationScenario extends InitiateBrowser{
	
	@Test
	public void RS01_EmptyFullNameRegitrationErrorMessage() throws Exception{
		System.out.println("Test Case 1: Starting Test Case.");
		RegistrationPage RP = new RegistrationPage(driver);
		RP.ClearFullNameTextbox();
		RP.ClickCreateAccountButton();
		//System.out.println("Clicked the Create Account button.");
		if(RP.DisplayedErrorMessageEmptyFullName()) {
			System.out.println("Test Case 1: PASSED - Full name field is emtpy when attempting to Register an account.");
		} else {
			System.out.println("Test Case 1: FAILED - Correct error message did not appear for empty full name field");
		}
	}
	
	
	@Test
	public void RS02_EmptyNewEmailCheckAvailabilityErrorMessage() throws Exception {
		System.out.println("Test Case 2: Starting Test Case.");
		RegistrationPage RP = new RegistrationPage(driver);
		RP.ClearNewEmailTextbox();
		RP.ClickCheckAvailabilityButton();
		if (RP.DisplayedErrorMessageEmptyEmailAvailability()) {
			System.out.println("Test Case 2: PASSED - Rediff email field is emtpy when attempting to check availability.");
		} else {
			System.out.println("Test Case 2: FAILED - Correct error message did not appear for empty Rediff email field when attempting to check availability.");
		}
	}
	  
	@Test
	public void RS03_AlreadyTakenNewEmailCheckAvailabilityErrorMessage() throws Exception {
		System.out.println("Test Case 3: Starting Test Case.");
		RegistrationPage RP = new RegistrationPage(driver);
		RP.ClearNewEmailTextbox();
		RP.EnterNewEmailTextbox("g");
		RP.ClickCheckAvailabilityButton();
		if (RP.DisplayedErrorMessageEmailAlreadyTaken()) {
			System.out.println("Test Case 3: PASSED - Rediff email is already taken when attempting to check availability.");
		} else {
			System.out.println("Test Case 3: FAILED - Correct error message did not appear for already taken Rediff email.");
		}
	}
	  
	@Test
	public void RS04_EmptyNewEmailRegitrationErrorMessage() throws Exception {
		System.out.println("Test Case 4: Starting Test Case.");
		RegistrationPage RP = new RegistrationPage(driver);
		RP.ClearFullNameTextbox();
		RP.EnterFullNameTextbox("Big Bird");
		RP.ClearNewEmailTextbox();
		RP.ClickCreateAccountButton();
		if (RP.DisplayedErrorMessageEmptyEmailRegister()) {
			System.out.println("Test Case 4: PASSED - Rediff email field is emtpy when attempting to Register an account.");
		} else {
			System.out.println("Test Case 4: FAILED - Correct error message did not appear for empty email field.");
		}
	}

}
