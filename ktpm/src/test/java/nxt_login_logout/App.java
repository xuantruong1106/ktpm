package nxt_login_logout;

import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.NoAlertPresentException;

public class App {
    WebDriver driver;
    LoginPage loginPage;
    LogoutPage logoutPage;
    
    @BeforeClass
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://www.demo.guru99.com/V4/");
        
        loginPage = new LoginPage(driver);
        logoutPage = new LogoutPage(driver);
        PageFactory.initElements(driver, this);
        
        driver.quit();
    }
    
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
    
    public static void main(String args[]) {
		App test = new App();
		test.setUp();
	}
    
}
