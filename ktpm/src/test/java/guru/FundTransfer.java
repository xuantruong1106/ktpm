package guru;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FundTransfer {
    private WebDriver driver;
    private int totalTests = 0;
    private int pass = 0;
    private int fail = 0;

    public void setUp() {
//        System.setProperty("webdriver.edge.driver", "edge/msedgedriver.exe");

        driver = new EdgeDriver();
        driver.get("https://www.demo.guru99.com/V4/manager/FundTransInput.php");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        specialValueAndCheckNotification();
        blankValueAndCheckNotification();
        characterValueAndCheckNotification();
        sameValueAndCheckNotification();
        checkSubmitButton();
        checkResetButton();

        System.out.println("Total tests: " + totalTests);
        System.out.println("Pass: " + pass);
        System.out.println("Fail: " + fail);

        driver.quit();
    }

    public void specialValueAndCheckNotification() {
        checkFieldWithNotification("payersaccount", "#%$^*", "message10");
        checkFieldWithNotification("payeeaccount", "#%$^*", "message11");
        checkFieldWithNotification("ammount", "#%$^*", "message1");
    }

    public void blankValueAndCheckNotification() {
        checkFieldWithNotification("payersaccount", "", "message10");
        checkFieldWithNotification("payeeaccount", "", "message11");
        checkFieldWithNotification("ammount", "", "message1");
        checkFieldWithNotification("desc", "", "message17");
    }

    public void characterValueAndCheckNotification() {
        checkFieldWithNotification("payersaccount", "abc", "message10");
        checkFieldWithNotification("payeeaccount", "abc", "message11");
        checkFieldWithNotification("ammount", "abc", "message1");
    }

    public void sameValueAndCheckNotification() {
        checkFieldWithNotification("payersaccount", "09999", "message10");
        checkFieldWithNotification("payeeaccount", "09999", "message11");
    }

    public void checkFieldWithNotification(String fieldName, String value, String notificationId) {
        totalTests++;

        WebElement field = driver.findElement(By.name(fieldName));
        field.clear();
        field.sendKeys(value);
        field.sendKeys(Keys.TAB);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(notificationId)));

            pass++;
        } catch (TimeoutException e) {
            fail++;
        }
    }

    public void checkResetButton() {
        totalTests++;

        WebElement resetButton = driver.findElement(By.name("res"));
        resetButton.click();

        boolean isPayersAccountNoEmpty = driver.findElement(By.name("payersaccount")).getAttribute("value").isEmpty();
        boolean isPayeeAccountEmpty = driver.findElement(By.name("payeeaccount")).getAttribute("value").isEmpty();
        boolean isAmountEmpty = driver.findElement(By.name("ammount")).getAttribute("value").isEmpty();
        boolean isDescEmpty = driver.findElement(By.name("desc")).getAttribute("value").isEmpty();

        if (isPayersAccountNoEmpty && isPayeeAccountEmpty && isAmountEmpty && isDescEmpty) {
            pass++;
        } else {
            fail++;
        }
    }

    public void checkSubmitButton() {
        totalTests++;

        WebElement submitButton = driver.findElement(By.name("AccSubmit"));
        submitButton.click();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());

            driver.switchTo().alert().accept();
            pass++;
        } catch (Exception e) {
            fail++;
        }
    }

    public static void main(String[] args) {
        FundTransfer fundTransfer = new FundTransfer();
        fundTransfer.setUp();
    }
}
