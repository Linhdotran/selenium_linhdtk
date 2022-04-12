package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Selenium_Locator_01 {
	// khai báo 1 biến đại diện cho Selenium Webdriver
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeTest
	public void beforeTest() {

		// Mở trình duyệt firefox
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// Set timeout để tìm element
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// Mở application lên (AUT/SUT)
		driver.get("https://www.facebook.com/");
	}


	public void TC01_FindElement() {
	//Single element: WebElement
		
		WebElement loginButton = driver.findElement(By.className(" "));
		loginButton.click();
		
		// driver.findElement(By.className(" ")).click(); //Action
		// driver.findElement(By.className(" ")).getText(); //Action
		
		//findelement
		//By.xxx: vs locator nao
		//Action gì: click/sendkey/getText...
		
		
	//Multiple element: List<WebElement>
		
	List<WebElement> buttons = driver.findElements(By.className(""));
	buttons.get(0).click();
	
	
	}
	
	//<button value="1" class="_42ft _4jy0 _52e0 _4jy6 _4jy1 selected _51sy" id="loginbutton" name="login" tabindex="0" type="submit">Log In</button>
	@Test
	public void TC02_ID() {
	//Selenium locator
		driver.findElement(By.id("loginbutton")).click();
		
	//verify email error message xuat hien
		Assert.assertTrue(driver.findElement(By.className("_9ay7")).isDisplayed());
	}

	
	@Test
	public void TC03_Class() {
		
	//refresh
		driver.navigate().refresh();
	//Click by className
		driver.findElement(By.className("_42ft _4jy0 _52e0 _4jy6 _4jy1 selected _51sy")).click();
		
	//verify email error message xuat hien
		Assert.assertTrue(driver.findElement(By.className("_9ay7")).isDisplayed());
		
		
	}
	
	@Test
	public void TC04_Name() {
		
	//refresh
		driver.navigate().refresh();
				
	//Click by Name
		driver.findElement(By.name("login")).click();
		
	//verify email error message xuat hien
		Assert.assertTrue(driver.findElement(By.className("_9ay7")).isDisplayed());
		
	}
	
	@Test
	public void TC05_Tagname() {
	// Hien thi het tat ca duong link tai man hinh nay sau do getText ra
		driver.navigate().refresh();
		List<WebElement> LoginPageLinks = driver.findElements(By.tagName("a"));
		for (WebElement webElement : LoginPageLinks) {
		System.out.println(webElement.getText());
		}
	}
	
	
	@Test
	public void TC05_LinkText() {
		driver.navigate().refresh();
		
	//lay nguyen chuoi link text
		driver.findElement(By.linkText("Forgotten password?")).click();
		
	//verify indentify email xuat hien
				Assert.assertTrue(driver.findElement(By.id("identify_email")).isDisplayed());
	}
	
	
	
	@Test
	public void TC06_PartialLinkText() {
		
		driver.findElement(By.className("_42ft _4jy0 _9nq1 textPadding20px _4jy3 _517h _51sy")).click();
		//lay 1 phan link text
		
		driver.findElement(By.partialLinkText("Sign up")).click();
		Assert.assertTrue(driver.findElement(By.id("u_0_10_mL")).isDisplayed());
		
	}
	
	
	
	public void TC07_CSS() {
	//Selenium locator
		driver.findElement(By.)
		
	}
	
	
	public void TC08_Xpath() {
	//Selenium locator
		driver.findElement(By.)
		
	}


	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
