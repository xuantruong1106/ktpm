package guru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage {
    WebDriver driver;

    @FindBy(linkText="Log out")
    WebElement logoutButton;

    @FindBy(linkText="New Account")
    WebElement newAccountLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        logoutButton.click();
        driver.switchTo().alert().accept(); // Confirm logout alert
    }

    public void clickNewAccount() {
        newAccountLink.click();
    }
}
