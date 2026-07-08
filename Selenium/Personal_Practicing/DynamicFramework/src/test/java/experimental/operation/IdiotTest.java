package experimental.operation;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import doing.stuff.GettingLocators;

public class IdiotTest {
	private GettingLocators gl;
	private String[] locatortypes = {"name","tagnaMe","clAssname","By.something","linktExt","partialLinkText","CssSelector","xpatH","id"};
	
	@Test
	public void TestMethod() {
		gl = new GettingLocators();
		for(String type:locatortypes) {
			By by = gl.getBasicLocator(type, "test");
			System.out.println("Type = " + type);
			if(by==null) {
				System.out.println("Locator returned null.");
			} else {
				System.out.println("In the Else part for Type = " + type);
				System.out.println(by.toString());
			}
			System.out.println("");
		}
	}
	
}
