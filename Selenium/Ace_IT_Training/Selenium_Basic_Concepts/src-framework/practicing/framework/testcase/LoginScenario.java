package practicing.framework.testcase;

import org.testng.annotations.Test;

import practicing.framework.base.InitiateBrowser;
import practicing.framework.page.LoginPage;

public class LoginScenario extends InitiateBrowser{

	@Test
	public void ValidLoginCredentials() throws Exception{
		LoginPage login = new LoginPage(driver);
		login.EnterUsernameTextbox("John1");
		login.EnterPasswordTextbox("pass1234");
		login.ClickCheckbox();
		login.ClickLoginButton();
	}
}
