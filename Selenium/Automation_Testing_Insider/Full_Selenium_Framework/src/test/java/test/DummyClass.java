package test;

import org.testng.annotations.Test;

import base.BaseClass;

//this class is used to validate the Framework is functioning properly
public class DummyClass extends BaseClass{
	
	@Test
	public void dummyTest() {
		String title = webDr.getTitle();
		assert title.equals("OrangeHRM"):"Test Failed : Title is not matching.";
		
		System.out.println("Test Passed : Title matches!");
	}
}
