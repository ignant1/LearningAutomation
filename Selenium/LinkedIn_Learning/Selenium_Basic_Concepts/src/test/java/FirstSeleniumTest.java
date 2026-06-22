import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstSeleniumTest extends BaseTest{
    @Test
    public void checkPageTitle(){
        driver.get("https://practicesoftwaretesting.com");

        assertEquals("Practice Software Testing - Toolshop - v5.0", driver.getTitle());
    }

    @Test
    public void checkContactHeading1(){
        driver.get("https://practicesoftwaretesting.com/contact");

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement heading = wait.until( webDriverd-> driver.findElement(By.tagName("h3")));
        //WebElement heading = driver.findElement(By.tagName("h3"));

        String headingText = heading.getText();

        assertEquals("Contact", headingText);
    }

    @Test
    public void checkContactHeading2(){
        driver.get("https://practicesoftwaretesting.com/contact");

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement heading = wait.until( webDriverd-> driver.findElement(By.tagName("h3")));
        //WebElement heading = driver.findElement(By.tagName("h3"));

        String headingText = heading.getText();

        assertEquals("Contact", headingText);
    }
}
