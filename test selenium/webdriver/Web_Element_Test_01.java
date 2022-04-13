package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class Web_Element_Test_01 {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void BeforeClass() {

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}


	@Test
	public void f() {
		
		//Web browser command/method/API
		// Driver instance/variable
		driver.get("https://www.facebook.com/");
		
		//Web Element command/method/API
		
		WebElement loginButton = driver.findElement(By.name("login"));
		loginButton.click();
		
		//Khai bao biến
		WebElement emailTextBox = driver.findElement(By.id("email"));
		
		emailTextBox.clear();   //xóa dữ liệu
		emailTextBox.sendKeys("klinh@gmail.com");   //nhập dữ liệu 
		emailTextBox.isDisplayed();
		
		//Xóa dữ liệu trong editable tab/validate
		emailTextBox.click(); //action click
		
		emailTextBox.getAttribute("placeholder");  // trả về dữ liệu trong attribute of element.
		
		loginButton.getCssValue("background-color");
		//#166fe5
		//hexa
		
		loginButton.getLocation();
		loginButton.getSize();
		loginButton.getRect();
		
		//Take screenshot > Attach to HTML report
		loginButton.getScreenshotAs(OutputType.FILE);
		loginButton.getScreenshotAs(OutputType.BASE64);
		loginButton.getScreenshotAs(OutputType.BYTES);
		
		//Tên thẻ HTML
		//đầu ra of step này là đâu vào of step kia
		loginButton.getTagName();
		
		loginButton.getText();
		
		//Kiểm tra 1 element có hiển thị hay không(ng dùng nhìn thấy đc và thao tác đc)
		loginButton.isDisplayed();
		Assert.assertTrue(loginButton.isDisplayed());
		
		//Kiểm tra 1 element có thao tác đc hay không
		loginButton.isEnabled();
		
		//Kiểm tra 1 element đã được chọn hay chưa (radio/checkbox/dropdown)
		loginButton.isSelected();
		
		//Submit vào 1 form
		loginButton.submit();
			
	}

	@AfterTest
	public void afterTest() {
	}

}
