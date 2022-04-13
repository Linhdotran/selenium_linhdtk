package webdriver;

import java.awt.Dimension;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import okio.Timeout;

public class Test_Selenium_WD_Cac_Ham {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeCLass() {

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}
	
	@Test
	public void Open_URL_byGet() {
		//Open URL
		driver.get("https://www.facebook.com/");
		
		//Đóng 1 tab đang active
		driver.close();
		
		//Đóng trình duyệt (k care có bn window/tab))
		driver.quit();
		
		//Lấy ra ID hiện tại of Window/tab đang active
		String messengerID = driver.getWindowHandle();
		
		//Lấy ra tất cả ID của tất cả Window/tab đang có
		driver.getWindowHandles();
		
		//Swtich nhảy đến 1 tab/window nào đó
		driver.switchTo().window(messengerID);
		
		//Tìm ra 1 cái element bằng 1 locator
		WebElement emailTextBox = driver.findElement(By.id(" "));
		emailTextBox.clear();
		emailTextBox.sendKeys("12345");
		
		//Tìm ra tất cả element bằng 1 locator
		List<WebElement> textBoxes = driver.findElements(By.id(" "));
		
		//Trả về URL of page hiện tại
		String homePageUrl = driver.getCurrentUrl();
		
		//Khai báo biến bằng đúng kiểu dữ liệu
		//Trả vể HTML source của page hiện tại
		String homePageSource = driver.getPageSource();
		
		//Trả về Page Title
		String homePageTitle = driver.getTitle();
		
		//Get/Xóa Cookie of 1 page
		//Build frameWork: Share state of class
	
		//Get cookie sau khi login xong > truyen vao cac class khac > reduce time login of từng class
		driver.manage().deleteAllCookies();
		
		//Build frameWork: Get ra log of 1 browser
		driver.manage().logs().getAvailableLogTypes();
		
		//Define time, Chờ cho vc tìm elemen(findElement/findElements)
		//1000ms = 1s
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
		
		//Chờ cho 1 page note thành công (Option) - vò get đã chờ rồi
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		
		//Java script executor (Option) K bắt buộc
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		//Mở browser full màn hình
		driver.manage().window().fullscreen();
		
		//maximize browser
		driver.manage().window().maximize();
		
		//Lấy ra vị trí hiện tại of browser
		driver.manage().window().setPosition(new Point(0,0));
		
		//Lấy ra kích thước
		driver.manage().window().getSize();
		
		driver.manage().window().setSize(new Dimension(1920, 1080));
		
		//Back to page
		driver.navigate().back();
		
		//Forward to page
		driver.navigate().forward();
		
		//Tải lại trang
		driver.navigate().refresh();
		
		//Đến trang by URL
		driver.navigate().to("https://www.facebook.com/")
		
		//Switch to Window/Tab/Alert/Frame/Iframe
		driver.switchTo().alert();
		driver.switchTo().window("");
		driver.switchTo().frame("");
		
			
	
		
	}

	
	
}
