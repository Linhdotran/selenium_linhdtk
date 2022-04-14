package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class HW_Guru99_Command_II {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String LoginURL = "https://demo.guru99.com/v4/";
	String CustomerID;
	String LoginPageURL, userID, Password, name, gender, dobip, dobop, addressip, addressop, citi, state, pin, phone,
			email;
	String editAddressip, editAddressop, editCiti, editState, editPin, editPhone, editEmail;
	JavascriptExecutor jsExecutor;

	By nameTextBox = By.name("name");
	By genderRadio = By.xpath("//input[@value='f']");
	By genderTextBox = By.name("gender");
	By DOBTextBox = By.name("dob");
	By addressTextArea = By.name("addr");
	By citiTextBox = By.name("city");
	By stateTextBox = By.name("state");
	By pinTextBox = By.name("pinno");
	By phoneTextBox = By.name("telephoneno");
	By emailTextBox = By.name("emailid");
	By passwordTextBox = By.name("password");

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	@BeforeClass
	public void BeforeClass() {

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		jsExecutor = (JavascriptExecutor) driver;

		name = "Joilie Anna";
		gender = "female";
		dobip = "01/01/1990";
		dobop = "1990-01-01";
		addressip = "230 Bridge NewYork";
		addressop = "230 Bridge NewYork";
		citi = "Los Angeles";
		state = "California";
		pin = "225588";
		phone = "0987654332";
		email = "angela" + getRandomNumber() + "@mail.net";
		Password = "123456";

		editAddressip = "240 Bolling TimeCity";
		editAddressop = "240 Bolling TimeCity";
		editCiti = "Amsta";
		editState = "MU";
		editPin = "786509";
		editPhone = "0987654888";
		editEmail = "Jolie" + getRandomNumber() + "@mail.net";

		driver.get(LoginURL);
		LoginPageURL = driver.getCurrentUrl();
	}


	@Test
	public void TC_01_Register() {
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys("khanhlinh05@gmail.com");
		driver.findElement(By.name("btnLogin")).click();
		userID = driver.findElement(By.xpath("//td[text() = 'User ID :']/following-sibling::td")).getText();
		Password = driver.findElement(By.xpath("//td[text() = 'Password :']/following-sibling::td")).getText();
	}

	@Test
	public void TC_02_Login() {
		driver.get(LoginPageURL);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(Password);
		driver.findElement(By.name("btnLogin")).click();

		Assert.assertTrue(driver
				.findElement(By.xpath(
						"//marquee[@class='heading3' and text() = \"Welcome To Manager's Page of Guru99 Bank\"]"))
				.isDisplayed());

	}

	@Test
	public void TC_03_New_Customer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(nameTextBox).sendKeys(name);
		driver.findElement(genderRadio).click();
		
		jsExecutor.executeScript("arguments[0].removeAttribute('type')",driver.findElement(DOBTextBox));
		driver.findElement(DOBTextBox).sendKeys(dobip);
		driver.findElement(addressTextArea).sendKeys(addressip);
		driver.findElement(citiTextBox).sendKeys(citi);
		driver.findElement(stateTextBox).sendKeys(state);
		driver.findElement(pinTextBox).sendKeys(pin);
		driver.findElement(phoneTextBox).sendKeys(phone);
		driver.findElement(emailTextBox).sendKeys(email);
		driver.findElement(passwordTextBox).sendKeys(Password);
		driver.findElement(By.name("sub")).click();
		Assert.assertTrue(driver .findElement(By.xpath("//p[@class = 'heading3' and text() = 'Customer Registered Successfully!!!']")).isDisplayed());

		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),
				gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dobop);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				addressop);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), citi);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				email);

		CustomerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();

	}

	@Test
	public void TC_04_Edit() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(CustomerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		Assert.assertEquals(driver.findElement(nameTextBox).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(genderTextBox).getAttribute("value"), gender);
		Assert.assertEquals(driver.findElement(DOBTextBox).getAttribute("value"), dobop);
		Assert.assertEquals(driver.findElement(addressTextArea).getAttribute("value"), addressip);
		Assert.assertEquals(driver.findElement(citiTextBox).getAttribute("value"), citi);
		Assert.assertEquals(driver.findElement(stateTextBox).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(pinTextBox).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(phoneTextBox).getAttribute("value"), phone);
		Assert.assertEquals(driver.findElement(emailTextBox).getAttribute("value"), email);

		driver.findElement(addressTextArea).clear();
		driver.findElement(addressTextArea).sendKeys(editAddressip);
		
		driver.findElement(citiTextBox).clear();
		driver.findElement(citiTextBox).sendKeys(editCiti);
		
		driver.findElement(stateTextBox).clear();
		driver.findElement(stateTextBox).sendKeys(editState);
		
		driver.findElement(pinTextBox).clear();
		driver.findElement(pinTextBox).sendKeys(editPin);
		
		driver.findElement(phoneTextBox).clear();
		driver.findElement(phoneTextBox).sendKeys(editPhone);
		
		driver.findElement(emailTextBox).clear();
		driver.findElement(emailTextBox).sendKeys(editEmail);
		
		driver.findElement(By.name("sub")).click();
		
		Assert.assertTrue(driver
				.findElement(By.xpath("//p[@class = 'heading3' and text() = 'Customer details updated Successfully!!!']"))
				.isDisplayed());
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(), CustomerID);
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),
				gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dobop);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				editAddressop);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCiti);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				editEmail);
		
	}

	@AfterTest
	public void afterTest() {
	}

}
