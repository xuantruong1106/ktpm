package nxt_newCustomer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    @FindBy(name = "uid")
    private WebElement userId;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(name = "btnLogin")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUserId(String user) {
        userId.sendKeys(user);
    }

    public void enterPassword(String pwd) {
        password.sendKeys(pwd);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void login(String user, String pwd) {
        enterUserId(user);
        enterPassword(pwd);
        clickLoginButton();
    }
}


