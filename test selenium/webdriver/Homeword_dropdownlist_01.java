package webdriver;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class Homeword_dropdownlist_01 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;
	
	By Country = By.name("country");

	@BeforeClass
	public void BeforeClass() {

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.rode.com/wheretobuy");
	}

	@Test
	public void TC_01_Dropdown() {
	
		select = new Select(driver.findElement(Country));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText("Vietnam");
		Assert.assertEquals(select.getOptions().size(), 233);
		
		
	}

	@AfterTest
	public void afterTest() {
	}

}
