package nxt_login_logout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutPage extends App{
	
	public LogoutPage() {
	    // Constructor mặc định
	}
	
    WebDriver driver;

    @FindBy(linkText = "Log out")
    WebElement logoutButton;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        try {
            logoutButton.click();
        } catch (Exception e) {
            System.out.println("Unable to logout: " + e.getMessage());
        }
    }
    
    @Test
    public void testLogout() {
        loginPage.login("mngr599089", "pehErUs");
        try {
        	logout();
            Assert.assertTrue(driver.getPageSource().contains("You Have Succesfully Logged Out!!"));
            driver.switchTo().alert().accept();
            System.out.println("testLogout() - Pass - status: logouted");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("testLogout() - Fail - status: logined");
		}
    }

    @Test
    public void testBackAfterLogout() {
        loginPage.login("mngr599089", "pehErUs");
        try {
        	logout();
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
        	logout();
            driver.navigate().refresh();
            Assert.assertTrue(driver.getPageSource().contains("Guru99 Bank"));
            System.err.println("testRefreshAfterLogout() - Fail - status: Logined");
		} catch (Exception e) {
			System.err.println("testRefreshAfterLogout() - Pass - status: Logouted");
		}  
        
    }

}
