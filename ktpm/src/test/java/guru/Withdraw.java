package guru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Withdraw {
    private WebDriver driver;

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
        List<WebElement> inputElements = driver.findElements(By.tagName("input"));

        System.out.println("Found " + inputElements.size() + " input elements.");
        for (WebElement element : inputElements) {
            System.out.println("Input element with name: " + element.getAttribute("name"));
        }
    }

    public void enterValueAndCheckNotification() {
        WebElement accountNoField = driver.findElement(By.name("accountno"));
//        accountNoField.sendKeys("#%$^*");
        WebElement ammountField = driver.findElement(By.name("ammount"));
        WebElement descriptionField = driver.findElement(By.name("desc"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(driver -> !accountNoField.getAttribute("value").isEmpty());
        wait.until(driver -> !ammountField.getAttribute("value").isEmpty());
        wait.until(driver -> !descriptionField.getAttribute("value").isEmpty());

//        String accountNo = accountNoField.getAttribute("value");
//        System.out.println("Account No: " + accountNo);

        checkNotificationById("message2");
        checkNotificationById("message1");
    }

    public void checkNotificationById(String notificationId) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(notificationId)));

        String notificationText = notification.getText();
        System.out.println("Thông báo: " + notificationText);
    }

    public static void main(String[] args) {
        Withdraw withdraw = new Withdraw();
        withdraw.setUp();
        withdraw.enterValueAndCheckNotification();
    }
}
