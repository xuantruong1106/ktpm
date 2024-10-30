package nxt_login_logout;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.NoAlertPresentException;

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
    public void testInvalidLogin() {
        loginPage.login("mngr123", "abc123"); 
    	try {
            String alertText = driver.switchTo().alert().getText();
            Assert.assertTrue(alertText.contains("User or Password is not valid"));
            System.out.println("testInvalidLogin() - Actual result match expected result");
            driver.switchTo().alert().accept(); 
        } catch (NoAlertPresentException e) {
        	System.out.println("testInvalidLogin() - Actual result not match expected result");
        }

    }
    
    @Test
    public void testIncorrectUserid() {
    	loginPage.login("mngr123", "pehErUs"); 
    	try {
            String alertText = driver.switchTo().alert().getText();
            Assert.assertTrue(alertText.contains("User or Password is not valid"));
            driver.switchTo().alert().accept(); 
            System.out.println("testIncorrectUserid() - Actual result match expected result");
        } catch (NoAlertPresentException e) {
        	System.out.println("testIncorrectUserid() - Actual result not match expected result");
        }
    	
    }
    
    @Test
    public void testIncorrectPassword() {
    	loginPage.login("mngr599089", "abc123"); 
    	try {
            String alertText = driver.switchTo().alert().getText();
            Assert.assertTrue(alertText.contains("User or Password is not valid"));
            System.out.println("testIncorrectPassword() - Actual result match expected result");
            driver.switchTo().alert().accept(); 
        } catch (NoAlertPresentException e) {
        	System.out.println("testIncorrectPassword() - Actual result not match expected result");
        }
    }
    
    @Test
    public void testValidLogin() {
        loginPage.login("mngr599089", "pehErUs");        
        try {
            System.out.println("testValidLogin() - Actual result match expected result"); 
        } catch (NoAlertPresentException e) {
        	System.out.println("testValidLogin() - Actual result not match expected result");
        }
        
    }   

    @Test
    public void testEmptyFieldsLogin() {
        loginPage.login("", "");
        try {
        	
        	 WebElement errorMessageUserID = driver.findElement(By.id("message23"));
        	 WebElement errorMessagePassword = driver.findElement(By.id("message18"));
        	 
             Assert.assertTrue(errorMessageUserID.isDisplayed());
             Assert.assertTrue(errorMessagePassword.isDisplayed());
             
             Assert.assertTrue(errorMessageUserID.getText().contains("User-ID must not be blank"));
             Assert.assertTrue(errorMessagePassword.getText().contains("Password must not be blank"));
             
             loginPage.loginButton.click();
             
             String alertText = driver.switchTo().alert().getText();
             Assert.assertTrue(alertText.contains("User or Password is not valid"));
             driver.switchTo().alert().accept(); 
            
             System.out.println("testEmptyFieldsLogin() - Actual result match expected result");
            
        } catch (NoAlertPresentException e) {
        	System.out.println("testEmptyFieldsLogin() - Actual result not match expected result");
        }
    }

    @Test
    public void testEmptyPasswordAccountExisted() {
        loginPage.login("mngr599089", "");
        
        try {
        	WebElement errorMessage = driver.findElement(By.id("message18"));
            Assert.assertTrue(errorMessage.isDisplayed());
            Assert.assertTrue(errorMessage.getText().contains("Password must not be blank"));
            
            loginPage.loginButton.click();
            
            Assert.assertTrue(driver.switchTo().alert().getText().contains("User or Password is not valid"));
            
            System.out.println("testEmptyPasswordAccountExisted() - Actual result match expected result");
            
            driver.switchTo().alert().accept();
		} catch (Exception e) {
			System.out.println("testEmptyPasswordAccountExisted() - Actual result match expected result");
		}
    }

    @Test
    public void testEmptyUserIDAccountExisted() {
        loginPage.login("", "pehErUs");
        try {
        	WebElement errorMessage = driver.findElement(By.id("message23"));
            Assert.assertTrue(errorMessage.isDisplayed());
            Assert.assertTrue(errorMessage.getText().contains("User-ID must not be blank"));
            
            loginPage.loginButton.click();
            
            Assert.assertTrue(driver.switchTo().alert().getText().contains("User or Password is not valid"));
            
            System.out.println("testEmptyUserIDAccountExisted() - Actual result match expected result");
            
            driver.switchTo().alert().accept();
		} catch (Exception e) {
			System.out.println("testEmptyUserIDAccountExisted() - Actual result match expected result");
		}
    }
    
    @Test
    public void testEmptyPasswordAccountNotExist() {
        loginPage.login("mngr599089", "");
        
        try {
        	WebElement errorMessage = driver.findElement(By.id("message18"));
            Assert.assertTrue(errorMessage.isDisplayed());
            Assert.assertTrue(errorMessage.getText().contains("Password must not be blank"));
            
            loginPage.loginButton.click();
            
            Assert.assertTrue(driver.switchTo().alert().getText().contains("User or Password is not valid"));
            
            System.out.println("testEmptyPasswordAccountNotExist() - Actual result match expected result");
            
            driver.switchTo().alert().accept();
		} catch (Exception e) {
			System.out.println("testEmptyPasswordAccountNotExist() - Actual result match expected result");
		}
    }

    @Test
    public void testEmptyUserIDAccountNotExist() {
        loginPage.login("", "pehErUs");
        try {
        	WebElement errorMessage = driver.findElement(By.id("message23"));
            Assert.assertTrue(errorMessage.isDisplayed());
            Assert.assertTrue(errorMessage.getText().contains("User-ID must not be blank"));
            
            loginPage.loginButton.click();
            
            Assert.assertTrue(driver.switchTo().alert().getText().contains("User or Password is not valid"));
            
            System.out.println("testEmptyUserIDAccountNotExist() - Actual result match expected result");
            
            driver.switchTo().alert().accept();
		} catch (Exception e) {
			System.out.println("testEmptyUserIDAccountNotExist() - Actual result match expected result");
		}
    }

//    @Test
//    public void testSpecialCharacterLogin() {
//        loginPage.login("!@#$", "!@#$");
//        Assert.assertTrue(driver.switchTo().alert().getText().contains("User or Password is not valid"));
//        driver.switchTo().alert().accept();
//    }
    

    @Test
    public void testLogout() {
        loginPage.login("mngr599089", "pehErUs");
        homePage.logout();
        Assert.assertTrue(driver.getPageSource().contains("Guru99 Bank"));
    }

    @Test
    public void testBackAfterLogout() {
        loginPage.login("mngr599089", "pehErUs");
        try {
        	homePage.logout();
            driver.navigate().back();
            Assert.assertTrue(driver.getPageSource().contains("Guru99 Bank"));  // Expecting redirection to login
            System.out.println("testBackAfterLogout() - Fail - status: logined");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("testBackAfterLogout() - Pass - status: logouted");
		}
        
    }

    @Test
    public void testRefreshAfterLogout() {
        loginPage.login("mngr599089", "pehErUs");
        
        try {
        	homePage.logout();
            driver.navigate().refresh();
            Assert.assertTrue(driver.getPageSource().contains("Guru99 Bank"));
            System.err.println("testRefreshAfterLogout() - Fail - status: Logined");
		} catch (Exception e) {
			System.err.println("testRefreshAfterLogout() - Pass - status: Logouted");
		}  
        
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
    
   
    
    public static void main(String agrs[]) throws InterruptedException {
    
		App test = new App();
		test.setUp();
		test.testInvalidLogin();
		test.testIncorrectUserid();
		test.testIncorrectPassword();
		test.testEmptyFieldsLogin();
		test.testEmptyPasswordAccountExisted();
		test.testEmptyUserIDAccountExisted();
		test.testEmptyPasswordAccountNotExist();
		test.testEmptyUserIDAccountNotExist();
		test.testValidLogin();
		test.tearDown();
		test.testLogout();
		test.testBackAfterLogout();
		test.testRefreshAfterLogout();
		
	}
}
