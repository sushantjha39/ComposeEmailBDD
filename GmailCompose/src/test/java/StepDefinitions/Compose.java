package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;

public class Compose {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver, 10);
    }
    
    
    @Given("I am logged in to my Gmail account")
    public void i_am_logged_in_to_my_Gmail_account() throws InterruptedException {
       driver.get("https://accounts.google.com/v3/signin/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ifkv=ATuJsjyxR76VILlNt79GC6zm0--ZI_mQyrKuW6RbnEwy1SjjL6jhs2z-gof1ykEuIgNl66VobGmqVQ&rip=1&sacu=1&service=mail&flowName=GlifWebSignIn&flowEntry=ServiceLogin&dsh=S520876273%3A1709960504889883&theme=mn");
       
       WebElement enterEmail= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input [@type=\"email\"]")));
       enterEmail.sendKeys("seventycrores@gmail.com");
       WebElement nextButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Next']")));
       nextButton.click();
       WebElement enterPassword= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-label=\"Enter your password\"]")));
       enterPassword.sendKeys("seventycrores@123");
       Thread.sleep(3000);
       WebElement nextButton1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='VfPpkd-vQzf8d' and text()='Next']")));
       nextButton1.click();
       
    }

    @When("I click on the \"Compose\" button")
    public void i_click_the_Compose_button() {
        WebElement composeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"T-I T-I-KE L3\"]")));
        composeButton.click();
    }

    @And("And I enter the recipient's email address {string}")
    public void and_i_enter_the_recipient_s_email_address(String emailAddress)  throws InterruptedException {
    	 WebElement recipientField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-label=\"To recipients\"]")));
    	    
    	// WebElement recipientField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='To']")));
        
        recipientField.click();
        Thread.sleep(2000);
        recipientField.sendKeys("sushantjha39@gmail.com");
    }

    @And("I enter the subject and body of the email")
    public void i_enter_the_subject_and_body() {
        WebElement subjectField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\"subjectbox\"]")));
        subjectField.sendKeys("Test flow ");
        WebElement bodyField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Message Body']")));
        bodyField.sendKeys("Sample data");
    }


    @And("I click on the \"Send\" button")
    public void i_click_the_Send_button() {
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[aria-label='Send ‪(Ctrl-Enter)‬']")));
        sendButton.click();
    }

    @Then("I should see a confirmation message that the email was sent {string}")
    public void i_should_see_a_confirmation_message_that_the_email_was_sent(String expectedMessage) {
        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@class='bAq'][contains(text(), 'Message sent')]")));

        assertEquals(expectedMessage, confirmationMessage.getText());
        System.out.println(confirmationMessage.getText());
    }

}
