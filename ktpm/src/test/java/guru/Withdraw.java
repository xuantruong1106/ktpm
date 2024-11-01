package guru;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Withdraw {
    private WebDriver driver;
    private int totalTests = 0;
    private int pass = 0;
    private int fail = 0;

    public void setUp() {
//        System.setProperty("webdriver.edge.driver", "edge/msedgedriver.exe");
//        WebElement userNameElement = driver.findElement(By.id("username"));
//        WebElement passwordElement = driver.findElement(By.id("password"));

        driver = new EdgeDriver();
        driver.get("http://www.demo.guru99.com/V4/manager/WithdrawalInput.php");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
//        System.out.println(driver.getTitle());

//        Tìm phần tử duy nhất
//        WebElement loginButton = driver.findElement(By.name("btnLogin"));

//        Tìm tất cả các phần tử <input>
//        List<WebElement> inputElements = driver.findElements(By.tagName("input"));
//
//        System.out.println("Found " + inputElements.size() + " input elements.");
//        for (WebElement element : inputElements) {
//            System.out.println("Input element with name: " + element.getAttribute("name"));
//        }

        enterSpecialValueAndCheckNotification();
        blankValueAndCheckNotification();
        enterCharacterValueAndCheckNotification();
        System.out.println("Total tests: " + totalTests);
        System.out.println("Pass: " + pass);
        driver.quit();
    }

//    public void enterSpecialValueAndCheckNotification() {
//        WebElement accountNoField = driver.findElement(By.name("accountno"));
//        accountNoField.sendKeys("#%$^*");
//        WebElement ammountField = driver.findElement(By.name("ammount"));
//        ammountField.sendKeys("#%$^*");
////        WebElement descriptionField = driver.findElement(By.name("desc"));
////        descriptionField.sendKeys("#%$^*");
//
////        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
////        wait.until(driver -> !accountNoField.getAttribute("value").isEmpty());
////        wait.until(driver -> !ammountField.getAttribute("value").isEmpty());
////        wait.until(driver -> !descriptionField.getAttribute("value").isEmpty());
//
////        String accountNo = accountNoField.getAttribute("value");
////        System.out.println("Account No: " + accountNo);
//
//        checkNotificationById("message2");
//        checkNotificationById("message1");
//    }

    public void enterSpecialValueAndCheckNotification() {
        checkFieldWithNotification("accountno", "#%$^*", "message2");
        checkFieldWithNotification("ammount", "#%$^*", "message1");
    }

    public void blankValueAndCheckNotification() {
        checkFieldWithNotification("accountno", "", "message2");
        checkFieldWithNotification("ammount", "", "message1");
        checkFieldWithNotification("desc", "", "message17");
    }

    public void enterCharacterValueAndCheckNotification() {
        checkFieldWithNotification("accountno", "abc", "message2");
        checkFieldWithNotification("ammount", "abc", "message1");
    }

//    public void checkNotificationById(String notificationId) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(notificationId)));
//
//        String notificationText = notification.getText();
//        System.out.println("Thông báo: " + notificationText);
//    }

    public void checkFieldWithNotification(String fieldName, String value, String notificationId) {
        totalTests++;

        WebElement field = driver.findElement(By.name(fieldName));
        field.clear();
        field.sendKeys(value);
        field.sendKeys(Keys.TAB);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(notificationId)));
//            WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(notificationId)));
//            String notificationText = notification.getText();
//            System.out.println("Thông báo: " + notificationText);

            pass++;
        } catch (TimeoutException e) {
            fail++;
            System.out.println("Fail: " + fail);
        }
    }

    public static void main(String[] args) {
        Withdraw withdraw = new Withdraw();
        withdraw.setUp();
    }
}
