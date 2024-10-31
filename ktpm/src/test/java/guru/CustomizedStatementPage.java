package guru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CustomizedStatementPage {
    private final WebDriver driver;
    private final By accountNoField = By.xpath("//tbody/tr[6]/td[2]/input[1]");
    private final By fromDateField = By.xpath("//tbody/tr[7]/td[2]/input[1]");
    private final By toDateField = By.xpath("//tbody/tr[8]/td[2]/input[1]");
    private final By minimumTransactionValueField = By.xpath("//tbody/tr[9]/td[2]/input[1]");
    private final By numberOfTransactionField = By.xpath("//tbody/tr[10]/td[2]/input[1]");
    private final By submitButton = By.xpath("//tbody/tr[13]/td[2]/input[1]");
    private final By accountNoAlert = By.xpath("//label[@id='message2']");
    private final By fromDateAlert = By.xpath("//label[@id='message26']");
    private final By toDateAlert = By.xpath("//label[@id='message27']");
    private final By minimumTransactionValueAlert = By.xpath("//label[@id='message12']");
    private final By numberOfTransactionAlert = By.xpath("//label[@id='message13']");

    public CustomizedStatementPage(WebDriver driver) {this.driver = driver;}
    public void setAccountNo(String accountNo) {
        driver.findElement(accountNoField).sendKeys(accountNo);
    }
    public void setFromDate(String fromDate) {
        driver.findElement(fromDateField).sendKeys(fromDate);
    }
    public void setToDate(String toDate) {
        driver.findElement(toDateField).sendKeys(toDate);
    }
    public void setMinimumTransactionValue(String minimumTransactionValue) {
        driver.findElement(minimumTransactionValueField).sendKeys(minimumTransactionValue);
    }
    public void setNumberOfTransaction(String numberOfTransaction) {
        driver.findElement(numberOfTransactionField).sendKeys(numberOfTransaction);
    }
    public void submit() {
        driver.findElement(submitButton).click();
    }
    public String getAccountNoAlert() {
        return driver.findElement(accountNoAlert).getText();
    }
    public String getFromDateAlert() {
        return driver.findElement(fromDateAlert).getText();
    }
    public String getToDateAlert() {
        return driver.findElement(toDateAlert).getText();
    }
    public String getMinimumTransactionValueAlert() {
        return driver.findElement(minimumTransactionValueAlert).getText();
    }
    public String getNumberOfTransactionAlert() {
        return driver.findElement(numberOfTransactionAlert).getText();
    }
}
