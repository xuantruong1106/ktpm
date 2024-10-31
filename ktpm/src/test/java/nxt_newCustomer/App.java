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
import org.testng.annotations.Test;
import java.time.Duration;
import org.openqa.selenium.Alert;

public class App {
    private WebDriver driver;
    private NewCustomerPage newCustomerPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "E:/Network Programming/Projects/ktpm/ktpm/edge/msedgedriver.exe");
        driver = new EdgeDriver();
        driver.get("http://www.demo.guru99.com/V4/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("mngr599504", "UbYgUmU");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement newCustomerLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("New Customer")));
        newCustomerLink.click();

        newCustomerPage = new NewCustomerPage(driver);
    }

    // Method to handle alerts if they appear
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

    private void verifyErrorMessage(String expectedMessage, String testName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + expectedMessage + "')]")));
            boolean isDisplayed = errorElement.isDisplayed();

            Assert.assertTrue(isDisplayed, "Error message not displayed as expected for " + testName);

            if (isDisplayed) {
                Reporter.log("Test '" + testName + "' passed: Error message displayed as expected.");
                System.out.println("Test '" + testName + "' passed: Error message displayed as expected.");
            }
        } catch (Exception e) {
            Reporter.log("Test '" + testName + "' failed: Error message not displayed as expected.");
            System.out.println("Test '" + testName + "' failed: Error message not displayed as expected.");
            Assert.fail("Error message not displayed as expected for " + testName);
        }
    }

    @Test
    public void testCustomerNameWithNumbers() {
        newCustomerPage.enterCustomerName("1234");
        newCustomerPage.clickSubmitButton();
        handleAlertIfPresent();
        verifyErrorMessage("Numbers are not allowed in Customer Name", "testCustomerNameWithNumbers");
    }

    @Test
    public void testCustomerNameWithSpecialCharacters() {
        newCustomerPage.enterCustomerName("John@Doe");
        newCustomerPage.clickSubmitButton();
        handleAlertIfPresent();
        verifyErrorMessage("Special characters are not allowed in Customer Name", "testCustomerNameWithSpecialCharacters");
    }

    @Test
    public void testEmptyCustomerName() {
        newCustomerPage.enterCustomerName("");
        newCustomerPage.clickSubmitButton();
        handleAlertIfPresent();
        verifyErrorMessage("Customer Name must not be blank", "testEmptyCustomerName");
    }

    @Test
    public void testCustomerNameStartingWithSpace() {
        newCustomerPage.enterCustomerName(" John");
        newCustomerPage.clickSubmitButton();
        handleAlertIfPresent();
        verifyErrorMessage("Leading spaces are not allowed in Customer Name", "testCustomerNameStartingWithSpace");
    }

    @Test
    public void testEmptyAddress() {
        newCustomerPage.enterAddress("");
        newCustomerPage.clickSubmitButton();
        handleAlertIfPresent();
        verifyErrorMessage("Address must not be blank", "testEmptyAddress");
    }

    @Test
    public void testAddressWithSpecialCharacters() {
        newCustomerPage.enterAddress("123 Main St@!");
        newCustomerPage.clickSubmitButton();
        handleAlertIfPresent();
        verifyErrorMessage("Special characters are not allowed", "testAddressWithSpecialCharacters");
    }

    @Test
    public void testCityWithNumbers() {
        newCustomerPage.enterCity("City123");
        newCustomerPage.clickSubmitButton();
        handleAlertIfPresent();
        verifyErrorMessage("Numbers are not allowed", "testCityWithNumbers");
    }

    @Test
    public void testStateWithSpecialCharacters() {
        newCustomerPage.enterState("State!@#");
        newCustomerPage.clickSubmitButton();
        handleAlertIfPresent();
        verifyErrorMessage("Special characters are not allowed", "testStateWithSpecialCharacters");
    }

    @Test
    public void testPINWithLessThan6Digits() {
        newCustomerPage.enterPin("12345");
        newCustomerPage.clickSubmitButton();
        verifyErrorMessage("PIN Code must have 6 Digits", "testPINWithLessThan6Digits");
    }

    @Test
    public void testMobileNumberWithSpecialCharacters() {
        newCustomerPage.enterMobileNumber("091@#$%^");
        newCustomerPage.clickSubmitButton();
        verifyErrorMessage("Characters are not allowed in Mobile Number", "testMobileNumberWithSpecialCharacters");
    }

    @Test
    public void testEmailWithoutAtSymbol() {
        newCustomerPage.enterEmail("invalidemail.com");
        newCustomerPage.clickSubmitButton();
        verifyErrorMessage("Email must be a valid email address", "testEmailWithoutAtSymbol");
    }

    @Test
    public void testShortPassword() {
        newCustomerPage.enterPassword("123");
        newCustomerPage.clickSubmitButton();
        verifyErrorMessage("Password must have at least 6 characters", "testShortPassword");
    }

    @Test
    public void testCustomerNameWithOnlySpaces() {
        newCustomerPage.enterCustomerName("   ");
        newCustomerPage.clickSubmitButton();
        verifyErrorMessage("Customer name must not be blank", "testCustomerNameWithOnlySpaces");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
