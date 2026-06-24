package practicing.framework.utility;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenCapture {
	
	public static void TestResult(WebDriver driver, String name) throws Exception {
		
		TakesScreenshot tss = (TakesScreenshot)driver;
		
		File f = tss.getScreenshotAs(OutputType.FILE);
		
		File img = new File("./screenshot/" + name + ".png");
		
		FileUtils.copyFile(f, img);
	}
}
