package webdriver;



import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.FindActiveElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Homework_User_Interaction {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Alert alert;
	Actions action;

	@BeforeClass
	public void BeforeClass() {

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
	}

	//@Test
	public void TC_01_Hover_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class ='ui-tooltip-content']")).getText(),"We ask for your age only for statistical purposes.");
		
	}

	
	//@Test
	public void TC_02_Hover_Menu() {
		driver.get("http://www.myntra.com/");
		
		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main'][text()='Kids']"))).perform();
		sleepInSecond(5);
		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Kids Home Bath']")).isDisplayed());
		
	}
	
	
	//@Test
	public void TC_03_Hover_Element() {
		driver.get("https://www.fahasa.com/");
		
		action.moveToElement(driver.findElement(By.xpath("//span[@class='icon_menu']"))).perform();
		sleepInSecond(5);
		
		action.moveToElement(driver.findElement(By.xpath("//a[@title='Sách Trong Nước']//span[@class='menu-title'][contains(text(),'Sách Trong Nước')]"))).perform();
		
		driver.findElement(By.xpath("(//a[contains(text(),'Kỹ Năng Sống')])[3]")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//strong[contains(text(),'Kỹ năng sống')]")).isDisplayed());
		
		
	}
	
	//@Test
	public void TC_04_Click_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> allNumber = driver.findElements(By.xpath("//ol[@id = 'selectable']/li"));
		
		//click and hold tu 1 -4
		
		action.clickAndHold(allNumber.get(0)) //click chuột vào số 1
		.moveToElement(allNumber.get(3)) //giữ đên số 4
		.release() //thả chuột
		.perform(); // lệnh thực hiện thao tác
		sleepInSecond(3);
		
		
		
	}
	
	//@Test
	public void TC_05_Click_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> allNumber = driver.findElements(By.xpath("//ol[@id = 'selectable']/li"));
		
		//Nhấn phím ctrl
		action.keyDown(Keys.CONTROL).perform();
		
		//chọn randome 1 số
		action.click(allNumber.get(0)).click(allNumber.get(2)).click(allNumber.get(4));
		
		//nhả phím
		action.keyUp(Keys.CONTROL).perform();
		sleepInSecond(3);
		
		
		
	}
	
	@Test
	public void TC_06_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		action.doubleClick(driver.findElement(By.xpath("//button[text() = 'Double click me']"))).perform();
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
		
		
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
