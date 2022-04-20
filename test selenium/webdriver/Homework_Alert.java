package webdriver;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class Homework_Alert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Alert alert;

	@BeforeClass
	public void BeforeClass() {

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
	}

	//@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://demo.guru99.com/v4/");
		driver.findElement(By.name("btnLogin")).click();
		sleepInSecond(2);
		//Wait cho alert xuất hiện trong vòng 15 giây
		//Vừa wait vừa khai báo alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		
		//Switch to: Alert/Window/Tab/Frame/Iframe - có thể trực tiếp khai báo ở wait
		//alert = driver.switchTo().alert();
	
		//getText:
		alert.getText();
		Assert.assertEquals(alert.getText(), "User or Password is not valid");
		
		//Accept alert: Alert sẽ biến mất (Click OK)
		alert.accept();
		
		
	}

	//@Test
	public void TC_02_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[@onclick ='jsAlert()']")).click();
		sleepInSecond(2);
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id ='result']")).getText(), "You clicked an alert successfully" );
		
	}

	//@Test
	public void TC_03_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[@onclick = 'jsConfirm()']")).click();
		sleepInSecond(2);
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id ='result']")).getText(), "You clicked: Cancel" );
		
		driver.findElement(By.xpath("//button[@onclick = 'jsConfirm()']")).click();
		sleepInSecond(2);
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id ='result']")).getText(), "You clicked: Ok" );
		

	}

	//@Test
	public void TC_04_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[@onclick = 'jsPrompt()']")).click();
		sleepInSecond(2);
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		String alertMessage = "Alert message insert";
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys(alertMessage);
		sleepInSecond(5);
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id ='result']")).getText(), "You entered:" + alertMessage );
	
		
		driver.findElement(By.xpath("//button[@onclick = 'jsPrompt()']")).click();
		sleepInSecond(2);
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys(alertMessage);
		sleepInSecond(5);
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id ='result']")).getText(), "You entered: null" );
	

	}

	//@Test
	public void TC_05_Authentication_Alert() {
		//Thư viện alert không xử lý đc authentication alert
		
		String userName = "admin";
		String passWord = "admin";
		
		String url = "http://" + userName + ":" + passWord + "@" + "the-internet.herokuapp.com/basic_auth";
		
		driver.get(url);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		

	}

	@Test
	public void TC_06_Authentication_Alert() {
		//Thư viện alert không xử lý đc authentication alert
		
		String userName = "admin";
		String passWord = "admin";
		driver.get("http://the-internet.herokuapp.com/");
		
		String basicAuthenLink = driver.findElement(By.xpath("//a[text() = 'Basic Auth']")).getAttribute("href");
		driver.get(getlink(basicAuthenLink,userName,passWord));
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());

	}
	
	public String getlink(String link, String userName, String Password) {
		
		//http://the-internet.herokuapp.com/
		String[] links = link.split("//");
		return links[0] + "//" + userName + ":" + Password + "@" + links[1];
		
	}

	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
