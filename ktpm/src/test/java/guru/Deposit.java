package guru;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Deposit {
    private WebDriver driver;

    public void setUp() {
        driver = new EdgeDriver();
        driver.get("https://www.demo.guru99.com/V4/manager/DepositInput.php");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        /*List<WebElement> inputElements = driver.findElements(By.tagName("input"));

        System.out.println("Found " + inputElements.size() + " input elements.");
        for (WebElement element : inputElements) {
            System.out.println("Input element with name: " + element.getAttribute("name"));
        }*/
    }

    public void AccountNumberSpecialCharacterCheck() {
        WebElement AccountInput = driver.findElement(By.name("accountno"));
        AccountInput.sendKeys("#");
        WebElement AmmountInput = driver.findElement(By.name("ammount"));
        AmmountInput.sendKeys("1");
        WebElement DescriptionInput = driver.findElement(By.name("desc"));
        DescriptionInput.sendKeys("test1");

        checkNotificationById("message2");
    }

    public void AccountNumberCharacterCheck() {
        WebElement AccountInput = driver.findElement(By.name("accountno"));
        AccountInput.sendKeys("test");
        WebElement AmmountInput = driver.findElement(By.name("ammount"));
        AmmountInput.sendKeys("1");
        WebElement DescriptionInput = driver.findElement(By.name("desc"));
        DescriptionInput.sendKeys("test1");

        checkNotificationById("message2");
    }

    public void AccountNumberEmtpyCheck() {
        WebElement AccountInput = driver.findElement(By.name("accountno"));
        AccountInput.sendKeys("");
        AccountInput.sendKeys(Keys.TAB);

        WebElement AmmountInput = driver.findElement(By.name("ammount"));
        AmmountInput.sendKeys("1");

        WebElement DescriptionInput = driver.findElement(By.name("desc"));
        DescriptionInput.sendKeys("test1");

        checkNotificationById("message2");
    }

    public void AmmountSpecialCharacterCheck() {
        WebElement AccountInput = driver.findElement(By.name("accountno"));
        AccountInput.sendKeys("1");
        WebElement AmmountInput = driver.findElement(By.name("ammount"));
        AmmountInput.sendKeys("#");
        WebElement DescriptionInput = driver.findElement(By.name("desc"));
        DescriptionInput.sendKeys("test1");

        checkNotificationById("message1");
    }

    public void AmmountCharacterCheck() {
        WebElement AccountInput = driver.findElement(By.name("accountno"));
        AccountInput.sendKeys("1");
        WebElement AmmountInput = driver.findElement(By.name("ammount"));
        AmmountInput.sendKeys("#");
        WebElement DescriptionInput = driver.findElement(By.name("desc"));
        DescriptionInput.sendKeys("test1");

        checkNotificationById("message1");
    }

    public void AmmountEmptyCheck() {
        WebElement AccountInput = driver.findElement(By.name("accountno"));
        AccountInput.sendKeys("1");

        WebElement AmmountInput = driver.findElement(By.name("ammount"));
        AmmountInput.sendKeys("");
        //AmmountInput.sendKeys(Keys.TAB);

        WebElement DescriptionInput = driver.findElement(By.name("desc"));
        DescriptionInput.sendKeys("test1");

        checkNotificationById("message1");
    }

    public void DescriptionEmptyCheck() {
        WebElement AccountInput = driver.findElement(By.name("accountno"));
        AccountInput.sendKeys("1");

        WebElement AmmountInput = driver.findElement(By.name("ammount"));
        AmmountInput.sendKeys("1");

        WebElement DescriptionInput = driver.findElement(By.name("desc"));
        DescriptionInput.sendKeys("");
        DescriptionInput.sendKeys(Keys.TAB);

        checkNotificationById("message17");
    }

    public void ValidDepositCheck() {
        WebElement AccountInput = driver.findElement(By.name("accountno"));
        AccountInput.sendKeys("1");

        WebElement AmmountInput = driver.findElement(By.name("ammount"));
        AmmountInput.sendKeys("1");

        WebElement DescriptionInput = driver.findElement(By.name("desc"));
        DescriptionInput.sendKeys("test1");
        DescriptionInput.sendKeys(Keys.TAB);

        WebElement Submit = driver.findElement(By.name("AccSubmit"));
        Submit.click();

        if (driver.getPageSource().contains("Deposit succesfully")) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }
        
    }

    public void checkNotificationById(String notificationId) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(notificationId)));

        String notificationText = notification.getText();
        System.out.println("Result: " + notificationText);
        if ((notificationText.equals("Special characters are not allowed")) ||
                (notificationText.equals("Characters are not allowed")) ||
                (notificationText.equals("Account Number must not be blank")) ||
                (notificationText.equals("Amount must not be blank")) ||
                (notificationText.equals("Description must not be blank")))
        {
            System.out.println("PASS");
        }
        else {
            System.out.println("FAIL");
        }

    }

    public static void main(String[] args) {
        Deposit deposit = new Deposit();
        deposit.setUp();
        deposit.AccountNumberSpecialCharacterCheck();
        //deposit.AccountNumberCharacterCheck();
        //deposit.AccountNumberEmtpyCheck();
        //deposit.AmmountSpecialCharacterCheck();
        //deposit.AmmountCharacterCheck();
        //deposit.AmmountEmptyCheck();
        //deposit.DescriptionEmptyCheck();
        //deposit.ValidDepositCheck();
    }
}
