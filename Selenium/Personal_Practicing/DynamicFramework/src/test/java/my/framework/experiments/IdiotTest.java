package my.framework.experiments;

import java.lang.reflect.Method;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import my.framework.activities.GettingLocators;
import my.framework.support.TraceManager;

public class IdiotTest{
	private GettingLocators gl;
	private String[] locatortypes = {"name","tagnaMe","clAssname","By.something","linktExt","partialLinkText","CssSelector","xpatH","id"};
	private static final Logger logger = TraceManager.getLogger(IdiotTest.class);
	
	@Test
	public void TestMethod1() {
		logger.info("Starting Test.");
		gl = new GettingLocators();
		logger.info("Created Getting Locator object");
		for(String type:locatortypes) {
			logger.info("Checking for locator type.");
			By by = gl.getBasicLocator(type, "test");
			logger.info("Type = " + type);
			if(by==null) {
				logger.info("Locator returned null.");
			} else {
				logger.info("In the Else part for Type = " + type);
				logger.info(by.toString());
			}
			logger.info("");
		}
	}
	
	@Test
	public void TestMethod2() {
		logger.info("Starting Test.");
		gl = new GettingLocators();
		logger.info("Created Getting Locator object");
		for(String type:locatortypes) {
			logger.info("Checking for locator type.");
			By by = gl.getBasicLocator(type, "test");
			logger.info("Type = " + type);
			if(by==null) {
				logger.info("Locator returned null.");
			} else {
				logger.info("In the Else part for Type = " + type);
				logger.info(by.toString());
			}
			logger.info("");
		}
	}
	
	@BeforeMethod
    public void setUp(Method method) {
        // 1. Assign the test name to the ThreadContext map
        // The RoutingAppender captures this key to name the log file
        ThreadContext.put("logfilepath", method.getName() + "_" + Thread.currentThread().threadId());
        
        logger.info("Starting setup for test: " + method.getName());
    }

	@AfterMethod
    public void tearDown() {
        logger.info("Flushing out ThreadContext and ending the test.");
        // 2. Always clear the context to avoid leaks across thread pools
        ThreadContext.clearAll();
    }
}
