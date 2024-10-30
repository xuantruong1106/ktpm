package guru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewAccountPage {
    private WebDriver driver;
    private By customerIDField = By.xpath("//tbody/tr[2]/td[2]/input[1]");
    private By accountTypeField = By.xpath("//tbody/tr[3]/td[2]/select[1]");
    private By initialDepositField = By.xpath("//tbody/tr[4]/td[2]/input[1]");
    private By getCustomerIDAlert = By.xpath("//label[@id='message14']");
    private By initialDepositAlert = By.xpath("//label[@id='message19']");

    public NewAccountPage(WebDriver driver) {this.driver = driver;}
    public void setCustomerID(String customerID) {
        driver.findElement(customerIDField).sendKeys(customerID);
    }
    public void setAccountType(String accountType) {
        driver.findElement(accountTypeField).sendKeys(accountType);
    }
    public void setInitialDeposit(String initialDeposit) {
        driver.findElement(initialDepositField).sendKeys(initialDeposit);
    }
    public String getCustomerIDAlert() {
        return driver.findElement(getCustomerIDAlert).getText();
    }
    public String getInitialDepositAlert() {
        return driver.findElement(initialDepositAlert).getText();
    }
}
