package nxt_newCustomer;

import nxt_newCustomer.LoginPage;
import nxt_newCustomer.NewCustomerPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class App {
    private WebDriver driver;
    private NewCustomerPage newCustomerPage;

    @BeforeClass
    public void setUp() {
        driver = new EdgeDriver();
        driver.get("http://www.demo.guru99.com/V4/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("mngr599504", "UbYgUmU");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement newCustomerLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("New Customer")));
        newCustomerLink.click();

        newCustomerPage = new NewCustomerPage(driver);
    }

    private void handleAlertIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            // No alert present
        }
    }

    @BeforeMethod
    public void resetBeforeEachTest() {
        driver.navigate().refresh();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement newCustomerLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("New Customer")));
        newCustomerLink.click();

        newCustomerPage = new NewCustomerPage(driver);
    }

    private void verifyErrorMessage(String expectedMessage, String testName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + expectedMessage + "')]")));
            Assert.assertTrue(errorElement.isDisplayed(), "Error message not displayed as expected for " + testName);

            Reporter.log("Test '" + testName + "' passed: Error message displayed as expected.");
            System.out.println("Test '" + testName + "' passed: Error message displayed as expected.");
        } catch (Exception e) {
            Reporter.log("Test '" + testName + "' failed: Error message not displayed as expected.");
            System.out.println("Test '" + testName + "' failed: Error message not displayed as expected.");
            captureScreenshot(testName); // Capture screenshot on failure
            Assert.fail("Error message not displayed as expected for " + testName);
        }
    }

    private void captureScreenshot(String testName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("./screenshots/" + testName + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Test cases for Customer Name
    @Test
    public void testCustomerNameWithNumbers() {
        newCustomerPage.enterCustomerName("1234");
//        newCustomerPage.clickSubmitButton();
//        handleAlertIfPresent();
        verifyErrorMessage("Numbers are not allowed", "testCustomerNameWithNumbers");
    }

    @Test
    public void testCustomerNameWithSpecialCharacters() {
        newCustomerPage.enterCustomerName("John@Doe");
//        newCustomerPage.clickSubmitButton();
//        handleAlertIfPresent();
        verifyErrorMessage("Special characters are not allowed", "testCustomerNameWithSpecialCharacters");
    }

    @Test
    public void testEmptyCustomerName() {
        newCustomerPage.enterCustomerName("");
        newCustomerPage.clickSubmitButton();
//        handleAlertIfPresent();
        verifyErrorMessage("Customer name must not be blank", "testEmptyCustomerName");
    }

    @Test
    public void testCustomerNameStartingWithSpace() {
        newCustomerPage.enterCustomerName(" John");
//        newCustomerPage.clickSubmitButton();
//        handleAlertIfPresent();
        verifyErrorMessage("First character can not have space", "testCustomerNameStartingWithSpace");
    }

    // Test cases for Address
    @Test
    public void testEmptyAddress() {
        newCustomerPage.enterAddress("");
        newCustomerPage.clickSubmitButton();
//        handleAlertIfPresent();
        verifyErrorMessage("Address Field must not be blank", "testEmptyAddress");
    }

    @Test
    public void testAddressStartingWithSpace() {
        newCustomerPage.enterAddress(" Ha Noi");
//        newCustomerPage.clickSubmitButton();
//        handleAlertIfPresent();
        verifyErrorMessage("First character can not have space", "testAddressStartingWithSpace");
    }

    @Test
    public void testAddressWithSpecialCharacters() {
        newCustomerPage.enterAddress("123 Main St@!");
//        newCustomerPage.clickSubmitButton();
//        handleAlertIfPresent();
        verifyErrorMessage("Special characters are not allowed", "testAddressWithSpecialCharacters");
    }

    // Test cases for City
    @Test
    public void testCityWithNumbers() {
        newCustomerPage.enterCity("City123");
//        newCustomerPage.clickSubmitButton();
//        handleAlertIfPresent();
        verifyErrorMessage("Numbers are not allowed", "testCityWithNumbers");
    }

    // Test cases for State
    @Test
    public void testStateWithSpecialCharacters() {
        newCustomerPage.enterState("State!@#");
//        newCustomerPage.clickSubmitButton();
//        handleAlertIfPresent();
        verifyErrorMessage("Special characters are not allowed", "testStateWithSpecialCharacters");
    }

    @Test
    public void testStateWithNumbers() {
        newCustomerPage.enterState("New York123");
//        newCustomerPage.clickSubmitButton();
        verifyErrorMessage("Numbers are not allowed", "testStateWithNumbers");
    }

    @Test
    public void testStateStartingWithSpace() {
        newCustomerPage.enterState(" NewYork");
//        newCustomerPage.clickSubmitButton();
        verifyErrorMessage("First character can not have space", "testStateStartingWithSpace");
    }

    @Test
    public void testEmptyState() {
        newCustomerPage.enterState("");
        newCustomerPage.clickSubmitButton();
        verifyErrorMessage("State must not be blank", "testEmptyState");
    }


    // Test cases for PIN
    @Test
    public void testPINWithLessThan6Digits() {
        newCustomerPage.enterPin("12345");
//        newCustomerPage.clickSubmitButton();
        verifyErrorMessage("PIN Code must have 6 Digits", "testPINWithLessThan6Digits");
    }

    @Test
    public void testPinWithAlphabeticCharacters() {
        newCustomerPage.enterPin("ABC123");
//        newCustomerPage.clickSubmitButton();
        verifyErrorMessage("Characters are not allowed", "testPinWithAlphabeticCharacters");
    }

    @Test
    public void testEmptyPin() {
        newCustomerPage.enterPin("");
        newCustomerPage.clickSubmitButton();
        verifyErrorMessage("PIN Code must not be blank", "testEmptyPin");
    }

    // Test cases for Mobile Number
    @Test
    public void testMobileNumberWithSpecialCharacters() {
        newCustomerPage.enterMobileNumber("091@#$%^");
//        newCustomerPage.clickSubmitButton();
        verifyErrorMessage("Special characters are not allowed", "testMobileNumberWithSpecialCharacters");
    }

    @Test
    public void testShortTelephoneNumber() {
        newCustomerPage.enterMobileNumber("123");
        newCustomerPage.clickSubmitButton();
        verifyErrorMessage("PIN Code must have 6 Digits", "testShortTelephoneNumber");
    }

    // Test cases for Email
    @Test
    public void testEmailWithoutAtSymbol() {
        newCustomerPage.enterEmail("invalidemail.com");
//        newCustomerPage.clickSubmitButton();
        verifyErrorMessage("Email-ID is not valid", "testEmailWithoutAtSymbol");
    }

    @Test
    public void testEmailWithoutDomainExtension() {
        newCustomerPage.enterEmail("test@gmail");
//        newCustomerPage.clickSubmitButton();
        verifyErrorMessage("Email-ID is not valid", "testEmailWithoutDomainExtension");
    }

    // Test cases for Telephone Number
    @Test
    public void testEmptyTelephoneNumber() {
        newCustomerPage.enterMobileNumber("");
        newCustomerPage.clickSubmitButton();
        verifyErrorMessage("Mobile no must not be blank", "testEmptyTelephoneNumber");
    }

    @Test
    public void testTelephoneNumberWithSpecialCharacters() {
        newCustomerPage.enterMobileNumber("123@456789");
        verifyErrorMessage("Special characters are not allowed", "testTelephoneNumberWithSpecialCharacters");
    }

    @Test
    public void testTelephoneNumberWithCharacters() {
        newCustomerPage.enterMobileNumber("123A56789");
        verifyErrorMessage("Characters are not allowed", "testTelephoneNumberWithCharacters");
    }

    @Test
    public void testTelephoneNumberWithLeadingSpace() {
        newCustomerPage.enterMobileNumber(" 1234567890");
        verifyErrorMessage("First character can not have space", "testTelephoneNumberWithLeadingSpace");
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
