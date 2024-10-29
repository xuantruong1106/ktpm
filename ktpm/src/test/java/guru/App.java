package guru;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
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

    @BeforeClass
    public void setUp() {
        driver = new EdgeDriver();
        driver.get("http://www.demo.guru99.com/V4/");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
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
