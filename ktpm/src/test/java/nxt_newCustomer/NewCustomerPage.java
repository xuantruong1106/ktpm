package nxt_newCustomer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewCustomerPage {
    private WebDriver driver;

    @FindBy(name = "name")
    private WebElement customerName;

    @FindBy(xpath = "//input[@value='m']")
    private WebElement genderMale;

    @FindBy(xpath = "//input[@value='f']")
    private WebElement genderFemale;

    @FindBy(name = "dob")
    private WebElement dateOfBirth;

    @FindBy(name = "addr")
    private WebElement address;

    @FindBy(name = "city")
    private WebElement city;

    @FindBy(name = "state")
    private WebElement state;

    @FindBy(name = "pinno")
    private WebElement pin;

    @FindBy(name = "telephoneno")
    private WebElement mobileNumber;

    @FindBy(name = "emailid")
    private WebElement email;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(name = "sub")
    private WebElement submitButton;

    @FindBy(id = "customerNameErrorId")
    private WebElement customerNameError;

    @FindBy(id = "addressErrorId")
    private WebElement addressError;

    @FindBy(id = "cityErrorId")
    private WebElement cityError;

    @FindBy(id = "stateErrorId")
    private WebElement stateError;

    @FindBy(id = "pinErrorId")
    private WebElement pinError;

    @FindBy(id = "mobileErrorId")
    private WebElement mobileError;

    @FindBy(id = "emailErrorId")
    private WebElement emailError;

    @FindBy(id = "passwordErrorId")
    private WebElement passwordError;

    public NewCustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterCustomerName(String name) {
        customerName.sendKeys(name);
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            genderMale.click();
        } else if (gender.equalsIgnoreCase("female")) {
            genderFemale.click();
        }
    }

    public void enterDateOfBirth(String dob) {
        dateOfBirth.sendKeys(dob);
    }

    public void enterAddress(String addr) {
        address.sendKeys(addr);
    }

    public void enterCity(String cityName) {
        city.sendKeys(cityName);
    }

    public void enterState(String stateName) {
        state.sendKeys(stateName);
    }

    public void enterPin(String pinCode) {
        pin.sendKeys(pinCode);
    }

    public void enterMobileNumber(String mobile) {
        mobileNumber.sendKeys(mobile);
    }

    public void enterEmail(String emailAddr) {
        email.sendKeys(emailAddr);
    }

    public void enterPassword(String pwd) {
        password.sendKeys(pwd);
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    // Các phương thức lấy thông báo lỗi
    public String getCustomerNameErrorMessage() {
        return customerNameError.getText();
    }

    public String getAddressErrorMessage() {
        return addressError.getText();
    }

    public String getCityErrorMessage() {
        return cityError.getText();
    }

    public String getStateErrorMessage() {
        return stateError.getText();
    }

    public String getPinErrorMessage() {
        return pinError.getText();
    }

    public String getMobileNumberErrorMessage() {
        return mobileError.getText();
    }

    public String getEmailErrorMessage() {
        return emailError.getText();
    }

    public String getPasswordErrorMessage() {
        return passwordError.getText();
    }
}
