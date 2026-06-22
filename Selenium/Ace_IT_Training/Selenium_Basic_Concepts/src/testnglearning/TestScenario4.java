package testnglearning;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestScenario4 {

	@Test(priority=1)
	public void TestCase01() {
		Assert.assertEquals("Hello", "Hello TestNG");
	}

	@Test(groups="smoke")
	public void TestCase02() {
		Assert.assertEquals("Hello TestNG", "Hello TestNG");
	}

	@Test
	public void TestCase03() {
		Assert.assertEquals("Hello TestNG", "Hello");
	}
	
	@Test(priority=2,groups="smoke")
	public void TestCase04() {
		Assert.assertEquals(5, 1);
	}
	
	@Test(dependsOnMethods="TestCase04")
	public void TestCase05() {
		Assert.assertEquals(5, 5);
	}

	@Test(priority=1,groups="smoke")
	public void TestCase06() {
		Assert.assertEquals(5, "5");
	}
}
