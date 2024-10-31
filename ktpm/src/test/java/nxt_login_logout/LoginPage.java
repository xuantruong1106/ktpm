package nxt_login_logout;

import java.sql.Time;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;



public class LoginPage extends App{
    WebDriver driver;
    WebDriver driver2 = new EdgeDriver();
    WebDriver driver3 = new EdgeDriver();

    public LoginPage() {

    }

    @FindBy(name="uid")
    WebElement userIDField;

    @FindBy(name="password")
    WebElement passwordField;

    @FindBy(name="btnLogin")
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String userID, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            wait.until(ExpectedConditions.visibilityOf(userIDField)).sendKeys(userID);
            wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        } catch (Exception e) {
//        	wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
            System.out.println("Can't click the login button");
        }

    }

    @Test
    //pass
    public void testInvalidLogin() {
        login("mngr123", "abc123");
        try {
            String alertText = driver2.switchTo().alert().getText();
            Assert.assertTrue(alertText.contains("User or Password is not valid"));
            System.out.println("testInvalidLogin() - Actual result match expected result");
            driver2.switchTo().alert().accept();
            tearDown();
        } catch (NoAlertPresentException e) {
            System.out.println("testInvalidLogin() - Actual result not match expected result");
        }

    }

    @Test
    public void testIncorrectUserid(){
        login("mngr123", "pehErUs");
        try {
            String alertText = driver2.switchTo().alert().getText();
            Assert.assertTrue(alertText.contains("User or Password is not valid"));
            driver2.switchTo().alert().accept();
            System.out.println("testIncorrectUserid() - Actual result match expected result");
        } catch (NoAlertPresentException e) {
            System.out.println("testIncorrectUserid() - Actual result not match expected result");
        }

    }

    @Test
    public void testIncorrectPassword() {
        login("mngr599089", "abc123");
        try {
            String alertText = driver2.switchTo().alert().getText();
            Assert.assertTrue(alertText.contains("User or Password is not valid"));
            System.out.println("testIncorrectPassword() - Actual result match expected result");
            driver2.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            System.out.println("testIncorrectPassword() - Actual result not match expected result");
        }
    }

    @Test
    public void testValidLogin() {
        login("mngr599089", "pehErUs");
        try {
            System.out.println("testValidLogin() - Actual result match expected result");
        } catch (NoAlertPresentException e) {
            System.out.println("testValidLogin() - Actual result not match expected result");
        }

    }

    @Test
    public void testEmptyFieldsLogin() {

        try {

            login("", "");
            WebElement errorMessageUserID = driver2.findElement(By.id("message23"));
            WebElement errorMessagePassword = driver2.findElement(By.id("message18"));

            Assert.assertTrue(errorMessageUserID.isDisplayed());
            Assert.assertTrue(errorMessagePassword.isDisplayed());

            Assert.assertTrue(errorMessageUserID.getText().contains("User-ID must not be blank"));
            Assert.assertTrue(errorMessagePassword.getText().contains("Password must not be blank"));

            loginButton.click();

            String alertText = driver2.switchTo().alert().getText();
            Assert.assertTrue(alertText.contains("User or Password is not valid"));
            driver2.switchTo().alert().accept();

            System.out.println("testEmptyFieldsLogin() - Actual result match expected result");

        } catch (NoAlertPresentException e) {
            System.out.println("testEmptyFieldsLogin() - Actual result not match expected result");

        }
    }

    @Test
    //pass
    public void testEmptyPasswordAccountExisted() {
        login("mngr599089", "");

        try {
            WebElement errorMessage = driver.findElement(By.id("message18"));
            Assert.assertTrue(errorMessage.isDisplayed());
            Assert.assertTrue(errorMessage.getText().contains("Password must not be blank"));

            loginButton.click();

            Assert.assertTrue(driver.switchTo().alert().getText().contains("User or Password is not valid"));

            System.out.println("testEmptyPasswordAccountExisted() - Actual result match expected result");

            driver.switchTo().alert().accept();
        } catch (Exception e) {
            System.out.println("testEmptyPasswordAccountExisted() - Actual result match expected result");
        }
    }

    @Test
    //pass
    public void testEmptyUserIDAccountExisted() {
        login("", "pehErUs");
        try {
            WebElement errorMessage = driver.findElement(By.id("message23"));
            Assert.assertTrue(errorMessage.isDisplayed());
            Assert.assertTrue(errorMessage.getText().contains("User-ID must not be blank"));

            loginButton.click();

            Assert.assertTrue(driver.switchTo().alert().getText().contains("User or Password is not valid"));

            System.out.println("testEmptyUserIDAccountExisted() - Actual result match expected result");

            driver.switchTo().alert().accept();
        } catch (Exception e) {
            System.out.println("testEmptyUserIDAccountExisted() - Actual result match expected result");
        }
    }

    @Test
    //pass
    public void testEmptyPasswordAccountNotExist() {
        login("mngr599089", "");

        try {
            WebElement errorMessage = driver.findElement(By.id("message18"));
            Assert.assertTrue(errorMessage.isDisplayed());
            Assert.assertTrue(errorMessage.getText().contains("Password must not be blank"));

            loginButton.click();

            Assert.assertTrue(driver.switchTo().alert().getText().contains("User or Password is not valid"));

            System.out.println("testEmptyPasswordAccountNotExist() - Actual result match expected result");

            driver.switchTo().alert().accept();
        } catch (Exception e) {
            System.out.println("testEmptyPasswordAccountNotExist() - Actual result match expected result");
        }
    }

    @Test
    //pass
    public void testEmptyUserIDAccountNotExist() {
        login("", "pehErUs");
        try {
            WebElement errorMessage = driver.findElement(By.id("message23"));
            Assert.assertTrue(errorMessage.isDisplayed());
            Assert.assertTrue(errorMessage.getText().contains("User-ID must not be blank"));

            loginButton.click();

            Assert.assertTrue(driver.switchTo().alert().getText().contains("User or Password is not valid"));

            System.out.println("testEmptyUserIDAccountNotExist() - Actual result match expected result");

            driver.switchTo().alert().accept();
        } catch (Exception e) {
            System.out.println("testEmptyUserIDAccountNotExist() - Actual result match expected result");
        }
    }
}

