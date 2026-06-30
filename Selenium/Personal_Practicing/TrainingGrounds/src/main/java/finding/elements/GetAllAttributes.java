package finding.elements;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GetAllAttributes {
	public static void main(String[] args) {
		
        WebDriver driver = new ChromeDriver();
        System.out.println("started the driver");
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        System.out.println("Opened the webpage");
        // 1. Locate your target element
        WebElement element = driver.findElement(By.name("username"));
        System.out.println("got the web element");
        
        // 2. Cast driver to JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        System.out.println("setting upjava script");
        // 3. Execute JS script to collect all names and values into an object
        String jsScript = 
            "var items = {}; " +
            "for (var i = 0; i < arguments[0].attributes.length; i++) { " +
            "   items[arguments[0].attributes[i].name] = arguments[0].attributes[i].value; " +
            "} " +
            "return items;";
        System.out.println("created the javascript string");
        @SuppressWarnings("unchecked")
        Map<String, String> attributes = (Map<String, String>) js.executeScript(jsScript, element);
        System.out.println("creating the javascript paired values object");
        // 4. Print out your full list of attributes
        attributes.forEach((name, value) -> {
            System.out.println("Attribute Name: " + name + " | Value: " + value);
        });

        driver.quit();
	}

}
