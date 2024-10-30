package guru;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class App {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    NewAccountPage newAccountPage;

    @BeforeClass
    public void setUp() {
        //driver = new EdgeDriver();
        driver = new ChromeDriver();
        driver.get("http://www.demo.guru99.com/V4/");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        newAccountPage = new NewAccountPage(driver);
    }

    @Test
    public void testValidLogin() {
        loginPage.login("mngr599089", "pehErUs");
        Assert.assertTrue(driver.getPageSource().contains("Welcome To Manager's Page of Guru99 Bank"));
    }

    @Test
    public void testInvalidLogin() {
        loginPage.login("invalid", "invalid");
        Assert.assertTrue(driver.switchTo().alert().getText().contains("User or Password is not valid"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    @Test
    public void testEmptyFieldsLogin() {
        loginPage.login("", "");
        Assert.assertTrue(driver.switchTo().alert().getText().contains("User or Password is not valid"));
        driver.switchTo().alert().accept();
    }

    @Test
    public void testEmptyPassword() {
        loginPage.login("mngr123", "");
        Assert.assertTrue(driver.switchTo().alert().getText().contains("User or Password is not valid"));
        driver.switchTo().alert().accept();
    }

    @Test
    public void testEmptyUserID() {
        loginPage.login("", "abc123");
        Assert.assertTrue(driver.switchTo().alert().getText().contains("User or Password is not valid"));
        driver.switchTo().alert().accept();
    }

    @Test
    public void testSpecialCharacterLogin() {
        loginPage.login("!@#$", "!@#$");
        Assert.assertTrue(driver.switchTo().alert().getText().contains("User or Password is not valid"));
        driver.switchTo().alert().accept();
    }

    @Test
    public void testLogout() {
        loginPage.login("mngr123", "abc123");
        homePage.logout();
        Assert.assertTrue(driver.getPageSource().contains("Guru99 Bank"));
    }

    @Test
    public void testBackAfterLogout() {
        loginPage.login("mngr123", "abc123");
        homePage.logout();
        driver.navigate().back();
        Assert.assertTrue(driver.getPageSource().contains("Guru99 Bank"));  // Expecting redirection to login
    }

    @Test
    public void testRefreshAfterLogout() {
        loginPage.login("mngr123", "abc123");
        homePage.logout();
        driver.navigate().refresh();
        Assert.assertTrue(driver.getPageSource().contains("Guru99 Bank"));  // Expecting redirection to login
    }

    // Test case for New Account
    @Test
    @DisplayName("TC1 - New Account")
    public void testCase1() {
        loginPage.login("mngr599089", "pehErUs");
        homePage.clickNewAccount();
        newAccountPage.setCustomerID("");
        newAccountPage.setAccountType("Savings");
        newAccountPage.setInitialDeposit("1000");
        Assert.assertEquals(newAccountPage.getCustomerIDAlert(), "Customer ID is required");
    }

    @Test
    @DisplayName("TC2 - New Account")
    public void testCase2() {
        loginPage.login("mngr599089", "pehErUs");
        homePage.clickNewAccount();
        newAccountPage.setCustomerID("@#$%");
        newAccountPage.setAccountType("Current");
        newAccountPage.setInitialDeposit("1000");
        Assert.assertEquals(newAccountPage.getCustomerIDAlert(), "Special characters are not allowed");
    }

    @Test
    @DisplayName("TC3 - New Account")
    public void testCase3() {
        loginPage.login("mngr599089", "pehErUs");
        homePage.clickNewAccount();
        newAccountPage.setCustomerID("abc");
        newAccountPage.setAccountType("Savings");
        newAccountPage.setInitialDeposit("1000");
        Assert.assertEquals(newAccountPage.getCustomerIDAlert(), "Characters are not allowed");
    }

    @Test
    @DisplayName("TC4 - New Account")
    public void testCase4() {
        loginPage.login("mngr599089", "pehErUs");
        homePage.clickNewAccount();
        newAccountPage.setCustomerID(" 123");
        newAccountPage.setAccountType("Current");
        newAccountPage.setInitialDeposit("1000");
        Assert.assertEquals(newAccountPage.getCustomerIDAlert(), "First character can not have space");
    }

    @Test
    @DisplayName("TC5 - New Account")
    public void testCase5() {
        loginPage.login("mngr599089", "pehErUs");
        homePage.clickNewAccount();
        newAccountPage.setCustomerID("123");
        newAccountPage.setAccountType("Savings");
        newAccountPage.setInitialDeposit("");
        //wait.until(ExpectedConditions.alertIsPresent());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        Assert.assertEquals(newAccountPage.getInitialDepositAlert(), "Initial deposit must not be blank");
    }

    @Test
    @DisplayName("TC6 - New Account")
    public void testCase6() {
        loginPage.login("mngr599089", "pehErUs");
        homePage.clickNewAccount();
        newAccountPage.setCustomerID("123");
        newAccountPage.setAccountType("Current");
        newAccountPage.setInitialDeposit("@#$%");
        Assert.assertEquals(newAccountPage.getInitialDepositAlert(), "Special characters are not allowed");
    }

    @Test
    @DisplayName("TC7 - New Account")
    public void testCase7() {
        loginPage.login("mngr599089", "pehErUs");
        homePage.clickNewAccount();
        newAccountPage.setCustomerID("123");
        newAccountPage.setAccountType("Savings");
        newAccountPage.setInitialDeposit("abc");
        Assert.assertEquals(newAccountPage.getInitialDepositAlert(), "Characters are not allowed");
    }

    @Test
    @DisplayName("TC8 - New Account")
    public void testCase8() {
        loginPage.login("mngr599089", "pehErUs");
        homePage.clickNewAccount();
        newAccountPage.setCustomerID("123");
        newAccountPage.setAccountType("Current");
        newAccountPage.setInitialDeposit(" 123");
        Assert.assertEquals(newAccountPage.getInitialDepositAlert(), "First character can not have space");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
    
    public static void main(String agrs[]) {
		App test = new App();
		test.setUp();
		test.testEmptyPassword();
	}
}
