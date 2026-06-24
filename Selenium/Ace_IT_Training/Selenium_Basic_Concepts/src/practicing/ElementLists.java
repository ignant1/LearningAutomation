package practicing;

import java.io.FileInputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ElementLists{
	
	WebDriver driver;
	XSSFWorkbook excel;
	SoftAssert soft;
	
	@Test
	public void TestCaseOne() {
		
		Assert.assertEquals(driver.getTitle(), "Learn and Practice Automation | automateNow");
		soft.assertEquals(driver.findElement(By.cssSelector("span[class*='copyright']")).getText(), " - automateNow, LLC. All rights reserved.		");
		//Add output to doc to report the test
		
		//get all the images on the page
		List<WebElement> imageList = driver.findElements(By.cssSelector("img[class*='wp-image']"));
		
		//validate the number of images
		soft.assertFalse(imageList.size()<19);
		soft.assertFalse(imageList.size()>19);
		//Add output to doc to report the test
		
		//get the image data from the Excel doc
		XSSFRow data = ((XSSFSheet)excel.getSheet("ImageList")).getRow(0);
						
		for (WebElement image : imageList) {

			boolean found = false;

			for (Cell item : data) {
				
				if(item.getStringCellValue().equals(image.getAttribute("data-image-title"))) {
					
					soft.assertEquals(item.getStringCellValue(), image.getAttribute("data-image-title"));
					//Add output to doc to report the test
					found = true;
					break;
					
				}
			}
			
			if(!found) {
				
				soft.assertEquals("", image.getAttribute("data-image-title"));
				//Add output to doc to report the test
				
			}
			
		}

		//List<WebElement> buttonList = driver.findElements(By.cssSelector("a[class*='element-button']"));
		//soft.assertFalse(buttonList.size()<19);
		//soft.assertFalse(buttonList.size()>19);
		//for (WebElement button : buttonList) {
		//	
		//	System.out.println(button.getText());
		//	
		//}
		
		soft.assertAll();
		
	}
	
	@BeforeMethod
	public void OpenBrowser() throws Exception{
		
		//Setup the Browser
		driver = new ChromeDriver();
		driver.get("https://practice-automation.com/");
		driver.manage().window().maximize();
		
		//Get data from the Excel file
		FileInputStream fls = new FileInputStream("./TestData/ElementList.xlsx");
		excel = new XSSFWorkbook(fls);
	
		//Create soft assert
		soft = new SoftAssert();
	}

}
