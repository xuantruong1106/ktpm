package nxt_login_logout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage {
    WebDriver driver;

    @FindBy(linkText="Log out")
    WebElement logoutButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public void login() {
        logoutButton.click();
        driver.switchTo().alert().accept(); // Confirm logout alert
    }
    
    public void logout() {
        logoutButton.click();
        driver.switchTo().alert().accept(); // Confirm logout alert
    }
}
