package finding.elements;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

// this is to get an understanding of what element types have what basic data the can be pulled from them without getting it directly from an attribute.

public class FindingElements {
	//private String URL = "https://ultimateqa.com/complicated-page";
	private String URL = "https://www.amazon.com/";
	private WebDriver wd;
	private ArrayList<String> tags = new ArrayList<>(List.of("input","a","nav","ul","li","div","p","span","img","label","i","table","tr","td","iframe"));
	private String browser = "chrome";
	
	@Test
	public void ElementalData() {
		switch(browser) {
		case "chrome": {
			wd = new ChromeDriver();
			break;
		}
		case "firefox": {
			wd = new FirefoxDriver();
			break;
		}
		default : {
			wd = new EdgeDriver();
			break;
		}
	}		
		
		wd.get(URL);
		
		for(String tag : tags) {
			List<WebElement> elements = new ArrayList<>();
			int i = 0;
			try {
				elements = wd.findElements(By.tagName(tag));
				i = ThreadLocalRandom.current().nextInt(0,elements.size()-1);
			} catch (Exception e) {
				System.out.println("===============================================================");
				System.out.println("     No  "  + tag + " elements on this page");
				System.out.println("===============================================================");
				System.out.println("  ");
				continue;
			}

			System.out.println("===============================================================");
			System.out.println("     data found in class "  + tag);
			System.out.println("===============================================================");
			System.out.println("  ");
			System.out.println("data using getTagName(): " + elements.get(i).getTagName());
			System.out.println("data using getAccessibleName(): " + elements.get(i).getAccessibleName());
			System.out.println("data using getText(): " + elements.get(i).getText());
			System.out.println("data using getAriaRole(): " + elements.get(i).getAriaRole());
			System.out.println("data using toString(): " + elements.get(i).toString());
			System.out.println("  ");
		}
		
		wd.quit();
	}
}