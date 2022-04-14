package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class Homework_textbox_Textarea_01 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String UserName = "Admin";
	String Password = "admin123";
	String firstName = "DTK", lastName = "Linh", employeeId = "06100";
	String editFirstName = "DO", editLastName = "TRAN";
	
	By txtEmpFirstName = By.id("personal_txtEmpFirstName");
	By txtEmpLastName = By.id("personal_txtEmpLastName");
	By txtEmployeeId = By.id("personal_txtEmployeeId");
	

	@BeforeClass
	public void BeforeClass() {

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
  
  @Test
  public void TC_01_Login() {
	 driver.get("https://opensource-demo.orangehrmlive.com/");
	 driver.findElement(By.xpath("//input[@id = 'txtUsername']")).sendKeys(UserName);
	 driver.findElement(By.xpath("//input[@id = 'txtPassword']")).sendKeys(Password);
	 driver.findElement(By.xpath("//input[@id = 'btnLogin']")).click();
	  
  }
  
  @Test
  public void TC_02_Add_Employee() {
	 
	 driver.findElement(By.xpath("//b[text()='PIM']")).click();
	 driver.findElement(By.xpath("//a[@id='menu_pim_addEmployee']")).click();
	 driver.findElement(By.id("firstName")).sendKeys(firstName);
	 driver.findElement(By.id("lastName")).sendKeys(lastName);
	 driver.findElement(By.id("employeeId")).clear();
	 driver.findElement(By.id("employeeId")).sendKeys(employeeId);
	 driver.findElement(By.id("btnSave")).click();
	 
	 Assert.assertEquals(driver.findElement(txtEmpFirstName), firstName);
	 Assert.assertEquals(driver.findElement(txtEmpLastName), lastName);
	 Assert.assertEquals(driver.findElement(txtEmployeeId), employeeId);
	 
	 Assert.assertTrue(driver.findElement(txtEmpFirstName).isDisplayed());
	 Assert.assertTrue(driver.findElement(txtEmpLastName).isDisplayed());
	 Assert.assertTrue(driver.findElement(txtEmployeeId).isDisplayed());
	 
  }
  
  @Test
  public void TC_03_Edit_Employee() {
	 
	 driver.findElement(By.id("btnSave")).click();
	 
	 driver.findElement(txtEmpFirstName).clear();
	 driver.findElement(txtEmpFirstName).sendKeys(editFirstName);
	 
	 driver.findElement(txtEmpLastName).clear();
	 driver.findElement(txtEmpLastName).sendKeys(editLastName);
	
	 
	 Assert.assertTrue(driver.findElement(txtEmpFirstName).isEnabled());
	 Assert.assertTrue(driver.findElement(txtEmpLastName).isEnabled());
	 
	 driver.findElement(By.id("btnSave")).click();
	 
	 Assert.assertEquals(driver.findElement(txtEmpFirstName), editFirstName);
	 Assert.assertEquals(driver.findElement(txtEmpLastName), editLastName);
	 
	 Assert.assertTrue(driver.findElement(txtEmpFirstName).isDisplayed());
	 Assert.assertTrue(driver.findElement(txtEmpLastName).isDisplayed());
	 Assert.assertTrue(driver.findElement(txtEmployeeId).isDisplayed());
	 
  }

	@AfterTest
	public void afterTest() {
	}

}
