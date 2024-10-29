package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class basetests {
	private WebDriver driver;
	public void setUp() {
		System.setProperty("webdriver.edge.driver", "edge/msedgedriver.exe");
		driver = new EdgeDriver();
		driver.get("https://the-internet.herokuapp.com/");
		driver.manage().window().maximize();
		
		System.out.print(driver.getTitle());
	}
	
	public static void main(String[] args) {
		basetests test = new basetests();
		test.setUp();
	}
}
