package guru;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

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
//        userIDField.clear();
//        passwordField.clear();
        
        
        try {
        	userIDField.sendKeys(userID);
            passwordField.sendKeys(password);
        	loginButton.click();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("can't click");
		}
        
    }
}

