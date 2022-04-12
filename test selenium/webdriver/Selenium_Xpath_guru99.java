package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class Selenium_Xpath_guru99 {
	WebDriver driver;
	String projectPath =  System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_Xpath_view_My_Account() {
		//Cho thằng có attribute giống nhau (not unique)
		driver.findElement(By.xpath("//div[@class='footer'] //a[@title = 'My Account']")).click();
		
	}

	@AfterTest
	public void afterTest() {
		//driver.quit();
	}

}
