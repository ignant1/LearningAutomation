import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChallangeTwoTest extends BaseTest{
    @ParameterizedTest
    @CsvFileSource(resources = "/contact-data.csv", numLinesToSkip = 0)
    public void testContactFormSubmitTwo(String firstName, String lastName, String email,String subject, String comment){
        driver.get("https://practicesoftwaretesting.com/contact");

        ContactPageTwo contactPage = new ContactPageTwo(driver);

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriverd-> driver.findElement(contactPage.firstNameLoc));

        //FillForm
        contactPage.fillForm(firstName, lastName, email, subject, comment);

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
