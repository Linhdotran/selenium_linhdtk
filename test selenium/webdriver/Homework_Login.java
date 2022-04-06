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

	@Test
	public void Step_01_Click_My_Account() {
		
		driver.findElement(By.xpath("//a[@class ='skip-link skip-account']//span[@class='label']")).click();
		
	}

	@Test
	public void Step_02_Click_Log_In() {
		
		driver.findElement(By.xpath("//a[@title='Log In']")).click();
		
	}
	
	@Test
	public void Step_03_Click_Send2() {
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).isDisplayed());
		
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
