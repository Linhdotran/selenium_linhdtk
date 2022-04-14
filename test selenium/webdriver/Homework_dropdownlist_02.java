package webdriver;

import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class Homework_dropdownlist_02 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	

	@BeforeClass
	public void BeforeClass() {

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver ID
		
		//Wait apply cho các trag thái of element(Visible, invisible, precence, clickable
		explicitWait = new WebDriverWait(driver, 15);
		
		//Wait để tìm element (Dùng cho findElement or findElements)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
	}

	//@Test
	public void TC_01_Jquery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		By parent = By.id("number-button");
		By child = By.xpath("//ul[@id = 'number-menu']//div");
		
		selectItemInDropDown(parent, child, "2");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id = 'number-button']/span[@class = 'ui-selectmenu-text' and text() = '2']")));
		
		selectItemInDropDown(parent, child, "18");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id = 'number-button']/span[@class = 'ui-selectmenu-text' and text() = '18']")));
		
		selectItemInDropDown(parent, child, "7");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id = 'number-button']/span[@class = 'ui-selectmenu-text' and text() = '7']")));
		
		selectItemInDropDown(parent, child, "10");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id = 'number-button']/span[@class = 'ui-selectmenu-text' and text() = '10']")));
		
	}
	
	@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		By parent = By.xpath("//div[@role ='listbox']");
		By child = By.xpath("//div[@role ='listbox']//span");
		
		selectItemInDropDown(parent, child, "Elliot Fu");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role ='alert' and text() = 'Elliot Fu']")));
		
		selectItemInDropDown(parent, child, "Christian");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role ='alert' and text() = 'Christian']")));
		
		selectItemInDropDown(parent, child, "Justen Kitsune");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role ='alert' and text() = 'Justen Kitsune']")));
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
		}else {return false;
		
		}
	}
	
	public void selectItemInDropDown(By parentBy, By childBy, String expectedTextItem) {
		//1 - Click vao 1 element cho nó show hết ra
				driver.findElement(parentBy).click();
				
				//2 - Wait cho tat ca element dc load ra(co trong HTML/DOM)
				//precense
				explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
				
				//Store lại tất cả element (item of sropdown)
				List <WebElement> allitems = driver.findElements(childBy);
				
				for (WebElement item : allitems) {
					if (item.getText().trim().equals(expectedTextItem)) { //hàm .trim() để xóa khoảng trắng đầu vs cuồi of text
						if (item.isDisplayed() ) {	//3 - Nếu item mình cần chọn nó nằm trong view (nhìn thấy được) thì click vào
							item.click();
						} else { //4 - Nếu item mình cần chọn không nhìn thấy (che bên dưới) thì scroll xuống và click vào
							jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
							item.click();
						}
					}
				}
		
	}

}
