import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChallengeOneTest extends BaseTest{
    @Test
    public void testContactFormSubmit(){
        driver.get("https://practicesoftwaretesting.com/contact");

        ContactPage contactPage = new ContactPage(driver);

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriverd-> driver.findElement(contactPage.firstNameLoc));

        //FillForm
        contactPage.fillForm();

        //Submit
        wait.until(webDriverd-> driver.findElement(contactPage.btnSubmitLoc));
        contactPage.submitForm();

        //Add explicit wait for the success alert to appear
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(contactPage.alertLoc));
        String alertText = alert.getText().trim();

        assertEquals("Thanks for your message! We will contact you shortly.", alertText);
    }
}
