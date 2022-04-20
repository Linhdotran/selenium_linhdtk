package webdriver;

import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class Homework_Chbk_radio_button {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	String UserName = "Khanhlinh@gmail.com";
	String UserPassword = "123456";

	By loginButton = By.cssSelector(("button.fhs-btn-login"));
	By loginUserName = By.cssSelector("input#login_username");
	By loginPassword = By.cssSelector("input#login_password");

	@BeforeClass
	public void BeforeClass() {

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		// driver ID

		// Wait apply cho các trag thái of element(Visible, invisible, precence,
		// clickable
		explicitWait = new WebDriverWait(driver, 15);

		// Wait để tìm element (Dùng cho findElement or findElements)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
	}

	@Test
	public void TC_01_JavascriptExecutor() {

		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

		//Verify login button isDisable
		Assert.assertTrue(driver.findElement(loginButton).isDisplayed());

		//Nhap du lieu

		driver.findElement(loginUserName).sendKeys(UserName);
		driver.findElement(loginPassword).sendKeys(UserPassword);
		sleepInSecond(1);

		//Verify login button isEnable
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		
		//Verify login button with background color
		String redcolor = driver.findElement(loginButton).getCssValue("background-color");
		System.out.println("RGBA = " + redcolor);
		String hexColor = Color.fromString(redcolor).asHex();
		Assert.assertEquals(hexColor, "#c92127");

		//Refresh lai trang
		driver.navigate().refresh();

		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		
		//Remove disabled attribute of login button
		//document.querySelector("button.fhs-btn-login").removeAttribute('disabled'); > query check console at Web
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(loginButton));

		sleepInSecond(2);
		
		driver.findElement(loginButton).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div[class='fhs-input-box checked-error'] div[class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div[class='fhs-input-box fhs-input-display checked-error'] div[class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		
	}
	
	@Test
	public void TC_02_Kendo_Radio() {
		
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		
		By Petrol20 = By.xpath("//label[text() = '2.0 Petrol, 147kW']/preceding-sibling::input");
		By Diesel16 = By.xpath("//label[text() = '1.6 Diesel, 77kW']/preceding-sibling::input");
		By Petrol36 = By.xpath("//label[text() = '3.6 Petrol, 191kW']/preceding-sibling::input");
		
		Assert.assertFalse(driver.findElement(Petrol20).isSelected());
		driver.findElement(Petrol20).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(Petrol20).isSelected());
		driver.findElement(Diesel16).click();
		sleepInSecond(2);
		
		Assert.assertFalse(driver.findElement(Petrol20).isSelected());
		Assert.assertTrue(driver.findElement(Diesel16).isSelected());
		Assert.assertFalse(driver.findElement(Petrol36).isEnabled());
		
	}
	
	@Test
	public void TC_03_Kendo_Checkbox() {
		
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		By luggage = By.xpath("//label[text() = 'Luggage compartment cover']/preceding-sibling::input");
		By rear = By.xpath("//label[text() = 'Rear side airbags']/preceding-sibling::input");
		By Towbar = By.xpath("//label[text() = 'Towbar preparation']/preceding-sibling::input");
		
		Assert.assertFalse(driver.findElement(luggage).isSelected());
		//driver.findElement(luggage).click();
		checkToCheckBox(luggage);
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(luggage).isSelected());
		
		uncheckToCheckBox(luggage);
		sleepInSecond(2);
		Assert.assertFalse(driver.findElement(luggage).isSelected());
		
		//driver.findElement(rear).click();
		uncheckToCheckBox(rear);
		sleepInSecond(2);
		Assert.assertFalse(driver.findElement(rear).isSelected());
		Assert.assertFalse(driver.findElement(Towbar).isEnabled());
		
		
	}

	//tạo hàm để check các checkbox xem đã selected chưa, nếu chưa mới click
	public void checkToCheckBox (By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public void uncheckToCheckBox (By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
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

	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			return true;
		} else {
			return false;

		}
	}

	public void selectItemInDropDown(By parentBy, By childBy, String expectedTextItem) {

		// Cho cho element dc phep click
		explicitWait.until(ExpectedConditions.elementToBeClickable(parentBy));

		// 1 - Click vao 1 element cho nó show hết ra
		driver.findElement(parentBy).click();

		// 2 - Wait cho tat ca element dc load ra(co trong HTML/DOM)
		// precense
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));

		// Store lại tất cả element (item of sropdown)
		List<WebElement> allitems = driver.findElements(childBy);

		for (WebElement item : allitems) {
			if (item.getText().trim().equals(expectedTextItem)) { // hàm .trim() để xóa khoảng trắng đầu vs cuồi of text
				if (item.isDisplayed()) { // 3 - Nếu item mình cần chọn nó nằm trong view (nhìn thấy được) thì click vào
					item.click();
				} else { // 4 - Nếu item mình cần chọn không nhìn thấy (che bên dưới) thì scroll xuống và
							// click vào
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					item.click();
				}
			}
		}

	}

}
