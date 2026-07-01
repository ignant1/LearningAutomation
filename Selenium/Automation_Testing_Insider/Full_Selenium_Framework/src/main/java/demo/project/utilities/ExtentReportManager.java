package demo.project.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

	private static ExtentReports extentRpt;
	private static ThreadLocal<ExtentTest> testReport = new ThreadLocal<>();
	private static Map<Long,WebDriver> mapDr = new HashMap<>();
	
	//initialize the extent report
	public synchronized static ExtentReports getExtentReport() {
		if(extentRpt == null) {
			
			//configure the report
			String reportPath = System.getProperty("user.dir") + "/src/test/resources/ExtentReport/ExtentReport.html";
			ExtentSparkReporter extentSpark = new ExtentSparkReporter(reportPath);
			extentSpark.config().setReportName("Automation Test Report");
			extentSpark.config().setDocumentTitle("Orange HRM");
			extentSpark.config().setTheme(Theme.DARK);
			
			//setup the report
			extentRpt = new ExtentReports();
			extentRpt.attachReporter(extentSpark);
			extentRpt.setSystemInfo("Operating System", System.getProperty("os.name"));
			extentRpt.setSystemInfo("Java Version", System.getProperty("java.version"));
			extentRpt.setSystemInfo("User Name", System.getProperty("user.name"));
		}
		
		return extentRpt;
	}
	
	//start the test
	public static synchronized ExtentTest startTest(String testName) {
		ExtentTest extentTest = getExtentReport().createTest(testName);
		testReport.set(extentTest);
		return extentTest;
	}
	
	//End the test
	public static synchronized void endTest() {
		getExtentReport().flush();
	}
	
	//Get current threads
	public static synchronized ExtentTest getTest() {
		return testReport.get();
	}
	
	public static String getTestName() {
		ExtentTest currentTest = getTest();
		if(currentTest != null) {
			return currentTest.getModel().getName();
		} else {
			return "Not test is currently active for this thread.";
		}
	}
	
	//Log a step
	public static void logStep(String logMessage) {
		getTest().info(logMessage);
	}
	
	//Log a step validation with screenshot
	public static void logStepWithScreenShot(WebDriver WD, String logMessage, String screenShotMessage) {
		getTest().pass(logMessage);
		// call the screenshot method
		attachScreenShot(WD, screenShotMessage);
	}
	
	//Log a failure
	public static void logFailure(WebDriver WD, String logFailure, String screenShotMessage) {
		String colorMessage = String.format("<span style='color:red;'>%s</span>", logFailure);
		getTest().fail(colorMessage);
		// call the screenshot method
		attachScreenShot(WD, screenShotMessage);
	}
	
	public static void logSkip(String logMessage) {
		String colorMessage = String.format("<span style='color:orange;'>%s</span>", logMessage);
		getTest().skip(colorMessage);
	}
	
	//Take screenshot with date/time stamp in the file
	public static synchronized String takeScreenShot(WebDriver WD, String screenShotName) {
		TakesScreenshot ts = (TakesScreenshot)WD;
		File src = ts.getScreenshotAs(OutputType.FILE);
		
		//format data and time for file name
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		String destinationPath = System.getProperty("user.dir") + "/src/test/resources/ScreenShots/" + screenShotName + "_" + timeStamp + ".png";
		
		File image = new File(destinationPath);
		try {
			FileUtils.copyFile(src, image);
		} catch (IOException e) {
			getTest().fail("Failed to move screen shot to foler. " + screenShotName);
			getTest().fail(e.getMessage());	
		}
		
		//convert screenshot to Base64 to embed the image
		String base64Format = convertToBase64(src);
		return base64Format;
	}
	
	//Convert screenshot to Base64 format
	public static String convertToBase64(File screenShotFile) {
		String base64Format = "";
		
		//read the file content into a byte array and convert to Base64
		try {
			base64Format = Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(screenShotFile));
		} catch (IOException e) {
			getTest().fail("Failed to convert screen shot to Base64.");
			getTest().fail(e.getMessage());	
		}
				
		return base64Format;
	}
	
	//attach screen shot to report using Base64
	public static synchronized void attachScreenShot(WebDriver WD, String message) {
		try {
			String screenShotBase64 = takeScreenShot(WD, getTestName());
			getTest().info(message,com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromBase64String(screenShotBase64).build());
		} catch (Exception e) {
			getTest().fail("Failed to attach Screen Shot. " + message);
			getTest().fail(e.getMessage());	
		}
	}
	
	//Register the Web Driver for the current thread
	public static void registerWebDriver(WebDriver WD) {
		mapDr.put(Thread.currentThread().threadId(), WD);
	}
}
