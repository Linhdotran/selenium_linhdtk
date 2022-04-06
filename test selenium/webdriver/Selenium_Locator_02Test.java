package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Selenium_Locator_02Test {
	WebDriver driver;
	String projectPath =  System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC01_Login_by_Name() {
		driver.findElement(By.name("login")).click();
		driver.navigate().refresh();
	}
	
	@Test
	public void TC02_Login_by_id() {
		driver.findElement(By.id("loginbutton")).click();
		driver.navigate().refresh();
	}
	
	@Test
	public void TC03_email_CSS() {
		driver.findElement(By.cssSelector("#email")).sendKeys("linh@gmail.com");
		driver.findElement(By.cssSelector("input[name = 'pass']")).sendKeys("123456");
		driver.navigate().refresh();
	}
	
	@Test
	public void TC04_email_Xpath() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("linhkhanh@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("123456");
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
