package practicing.framework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import practicing.framework.utility.ReadPropFiles;

public class RegistrationPage {

	WebDriver WD;
	
	public RegistrationPage(WebDriver driver) throws Exception{
		
		WD = driver;
		
		//navigate to the Registration Page
		WD.findElement(By.cssSelector(ReadPropFiles.ReadElementData("login_newaccount_link_css"))).click();
	}
	
	/*==================================================================================================*/
	//methods to click an element
	/*==================================================================================================*/
	public void ClickCreateAccountButton() throws Exception{
		WD.findElement(By.id(ReadPropFiles.ReadElementData("registration_createaccount_button_id"))).click();
	}

	public void ClickCheckAvailabilityButton() throws Exception{
		WD.findElement(By.cssSelector(ReadPropFiles.ReadElementData("registration_checkavailablity_button_css"))).click();
	}
	
	/*==================================================================================================*/
	//methods to enter text into element
	/*==================================================================================================*/
	public void EnterFullNameTextbox(String username) throws Exception{
		WD.findElement(By.cssSelector(ReadPropFiles.ReadElementData("registration_fullname_textbox_css"))).sendKeys(username);
	}

	public void EnterNewEmailTextbox(String username) throws Exception{
		WD.findElement(By.xpath(ReadPropFiles.ReadElementData("registration_newemail_textbox_xpath"))).sendKeys(username);
	}

	public void EnterPasswordTextbox(String username) throws Exception{
		WD.findElement(By.id(ReadPropFiles.ReadElementData("registration_password_textbox_id"))).sendKeys(username);
	}

	public void EnterRetypePasswordTextbox(String username) throws Exception{
		WD.findElement(By.id(ReadPropFiles.ReadElementData("registration_retypepw_textbox_id"))).sendKeys(username);
	}

	/*==================================================================================================*/
	//methods to clear text from elements
	/*==================================================================================================*/
	public void ClearFullNameTextbox() throws Exception{
		WD.findElement(By.cssSelector(ReadPropFiles.ReadElementData("registration_fullname_textbox_css"))).clear();
	}
	
	public void ClearNewEmailTextbox() throws Exception{
		WD.findElement(By.xpath(ReadPropFiles.ReadElementData("registration_newemail_textbox_xpath"))).clear();
	}
	
	public void ClearPasswordTextbox() throws Exception{
		WD.findElement(By.id(ReadPropFiles.ReadElementData("registration_password_textbox_id"))).clear();
	}
	
	public void ClearRetypePasswordTextbox() throws Exception{
		WD.findElement(By.id(ReadPropFiles.ReadElementData("registration_retypepw_textbox_id"))).clear();
	}
	
	/*==================================================================================================*/
	//methods to check visibility of error message elements
	/*==================================================================================================*/
	public boolean DisplayedErrorMessageEmptyFullName() throws Exception {
		return WD.findElement(By.xpath(ReadPropFiles.ReadElementData("registration_emptyname_errormessage_xpath"))).isDisplayed();
	}

	public boolean DisplayedErrorMessageEmailAlreadyTaken() throws Exception {
		return WD.findElement(By.xpath(ReadPropFiles.ReadElementData("registration_alreadytaken_errormessage_xpath"))).isDisplayed();
	}

	public boolean DisplayedErrorMessageEmptyEmailAvailability() throws Exception {
		return WD.findElement(By.xpath(ReadPropFiles.ReadElementData("registration_emptyemailavailability_errormessage_xpath"))).isDisplayed();
	}

	public boolean DisplayedErrorMessageEmptyEmailRegister() throws Exception {
		return WD.findElement(By.xpath(ReadPropFiles.ReadElementData("registration_emptyemailregister_errormessage_xpath"))).isDisplayed();
	}

	public boolean DisplayedErrorMessageEmptyPassword() throws Exception {
		return WD.findElement(By.xpath(ReadPropFiles.ReadElementData("registration_emptypassword_errormessage_xpath"))).isDisplayed();
	}

	public boolean DisplayedErrorMessageEmptyRetryPassword() throws Exception {
		return WD.findElement(By.xpath(ReadPropFiles.ReadElementData("registration_emptyretypepw_errormessage_xpath"))).isDisplayed();
	}

}
