package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Homework_Login {
	WebDriver driver;
	String projectPath =  System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/");
	}


	public void TC_01_Login_empty_account() {
		
		driver.findElement(By.xpath("//a[@class ='skip-link skip-account']//span[@class='label']")).click();
		driver.findElement(By.xpath("//a[@title='Log In']")).click();
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).isDisplayed());
	}

	
	
	
	public void TC_02_Login_Invalid_Email() {
		driver.findElement(By.xpath("//a[@class ='skip-link skip-account']//span[@class='label']")).click();
		driver.findElement(By.xpath("//a[@title='Log In']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123456@123456.123455");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).isDisplayed());
	}
	

	public void TC_03_Login_Invalid_PW() {
		driver.findElement(By.xpath("//a[@class ='skip-link skip-account']//span[@class='label']")).click();
		driver.findElement(By.xpath("//a[@title='Log In']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("khanhlinh@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).isDisplayed());
	}
	
	
	public void TC_04_Login_Incorrect_emailpw() {
		driver.findElement(By.xpath("//a[@class ='skip-link skip-account']//span[@class='label']")).click();
		driver.findElement(By.xpath("//a[@title='Log In']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("khanhlinh@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='error-msg']//span")).isDisplayed());
	}
	
	@Test
	public void TC_05_Create_Account() {
		driver.findElement(By.xpath("//a[@class ='skip-link skip-account']//span[@class='label']")).click();
		driver.findElement(By.xpath("//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Linh");
		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys("Tran");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Do");
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys("khanhlinh@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span")).isDisplayed());
	}
	
	@AfterTest
	public void afterTest() {
		//driver.quit();
	}

}
