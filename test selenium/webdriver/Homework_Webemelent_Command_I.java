package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Homework_Webemelent_Command_I {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void BeforeClass() {

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_is_Display() {
		driver.get("https://www.facebook.com/");
		
		WebElement emailTextBox = driver.findElement(By.id("email"));
		
		if (emailTextBox.isDisplayed()) {
			emailTextBox.sendKeys("khanhlinh.93@gmail.com");
			System.out.println("Email text box is displayed");
			
		} else {
			System.out.println("Email text box is not displayed");

		}
		
	}
	
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
