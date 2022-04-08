package webdriver;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class Test_Alada_Homework {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String name, emailAddress, password, phone;

	// Action
	By nameTextboxBy = By.id("txtFirstname");
	By emailTextboxBy = By.id("txtEmail");
	By confirmEmailTextboxBy = By.id("txtCEmail");
	By passwordTextboxBy = By.id("txtPassword");
	By confirmPasswordTextboxBy = By.id("txtCPassword");
	By PhoneTextboxBy = By.id("txtPhone");
	By RegisterButtonBy = By.xpath("//form[@id ='frmLogin']//button");

	// Error
	By nameErrorMsgBy = By.id("txtFirstname-error");
	By emailErrorMsgBy = By.id("txtEmail-error");
	By confirmEmailErrorMsgBy = By.id("txtCEmail-error");
	By passwordErrorMsgBy = By.id("txtPassword-error");
	By confirmPasswordErrorMsgBy = By.id("txtCPassword-error");
	By PhoneErrorMsgBy = By.id("txtPhone-error");

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		name = "Khanh Linh";
		emailAddress = "khanhlinh01@gmail.com";
		password = "123456";
		phone = "0936554223";
	
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_Empty() {
		driver.findElement(RegisterButtonBy).click();
		Assert.assertEquals(driver.findElement(nameErrorMsgBy).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsgBy).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(passwordErrorMsgBy).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMsgBy).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(PhoneErrorMsgBy).getText(), "Vui lòng nhập số điện thoại.");

	}
	
	@Test
	public void TC_02_InvalidEmail() {
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys("12223@2323@");
		driver.findElement(confirmEmailTextboxBy).sendKeys("12223@2323@");
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmPasswordTextboxBy).sendKeys(password);
		driver.findElement(PhoneTextboxBy).sendKeys(phone);
		driver.findElement(RegisterButtonBy).click();
		
		Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsgBy).getText(), "Email nhập lại không đúng");
	

	}
	
	@Test
	public void TC_03_Incorect_Confirm_email() {
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys("kklimj@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmPasswordTextboxBy).sendKeys(password);
		driver.findElement(PhoneTextboxBy).sendKeys(phone);
		driver.findElement(RegisterButtonBy).click();
		
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsgBy).getText(), "Email nhập lại không đúng");
	
	}
	
	
	@Test
	public void TC_04_Password_Lessthan_6() {
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys("1234");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("1111");
		driver.findElement(PhoneTextboxBy).sendKeys(phone);
		driver.findElement(RegisterButtonBy).click();
		
		Assert.assertEquals(driver.findElement(passwordErrorMsgBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMsgBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	
	}
	
	@Test
	public void TC_05_Incorrect_Confirm_Password() {
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmPasswordTextboxBy).sendKeys("654321");
		driver.findElement(PhoneTextboxBy).sendKeys(phone);
		driver.findElement(RegisterButtonBy).click();
		
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMsgBy).getText(), "Mật khẩu bạn nhập không khớp");
	
	}
	
	
	@Test
	public void TC_06_Incorrect_PhoneNumber() {
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmPasswordTextboxBy).sendKeys(password);
		driver.findElement(PhoneTextboxBy).sendKeys("linh123");
		driver.findElement(RegisterButtonBy).click();
		
		Assert.assertEquals(driver.findElement(PhoneErrorMsgBy).getText(), "Vui lòng nhập con số");
		
		//Clear du lieu nhap lai
		driver.findElement(PhoneTextboxBy).clear();
		driver.findElement(PhoneTextboxBy).sendKeys("098876");
		Assert.assertEquals(driver.findElement(PhoneErrorMsgBy).getText(), "Số điện thoại phải từ 10-11 số.");
		
		//Clear du lieu nhap lai
		driver.findElement(PhoneTextboxBy).clear();
		driver.findElement(PhoneTextboxBy).sendKeys("098876");
		Assert.assertEquals(driver.findElement(PhoneErrorMsgBy).getText(), "Số điện thoại phải từ 10-11 số.");
		
		driver.findElement(PhoneTextboxBy).clear();
		driver.findElement(PhoneTextboxBy).sendKeys("123456");
		Assert.assertEquals(driver.findElement(PhoneErrorMsgBy).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
	
	}

	@AfterTest
	public void afterTest() {
		// driver.quit();
	}

}
