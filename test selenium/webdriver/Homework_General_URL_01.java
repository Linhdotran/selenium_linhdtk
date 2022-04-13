package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Homework_General_URL_01 {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void BeforeClass() {

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_URL() {
		driver.get("http://live.techpanda.org/");
		
		String registerPageURL = driver.getCurrentUrl();
		Assert.assertEquals(registerPageURL, "http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		String loginPageURL = driver.getCurrentUrl();
		Assert.assertEquals(loginPageURL, "http://live.techpanda.org/index.php/customer/account/login/");
		
	}
	
	
	//@Test
	public void TC_02_Title() {
		
		driver.get("http://live.techpanda.org/");
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Home page");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "Customer Login");
		
	}
	
	
	//@Test
	public void TC_03_Navigator() {
		
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.navigate().back();
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Home page");
		
		driver.navigate().forward();
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "Customer Login");
		
	}
	
	
	@Test
	public void TC_04_Page_Source() {
		
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String loginPageSource = driver.getPageSource();
		Assert.assertTrue(loginPageSource.contains("Already registered?"));
		driver.navigate().back();
		String homePageSource = driver.getPageSource();
		Assert.assertTrue(homePageSource.contains("This is demo site for"));
		
	}

	@AfterTest
	public void afterTest() {
	driver.quit();
	}

}
