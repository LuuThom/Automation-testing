package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Part3_Handle_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, password, emailAdress, companyName, day, month, year;
	String countryName, provinceName, cityName, addressName, postalCode, phoneNumber;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		firstName = "First Name";
		lastName = "Last Name";
		emailAdress = "firstname123" + getRandomNumber() + "@gmail.com";
		companyName = " STC Company";
		password = "123456789";
		day ="10";
		month ="January";
		year = "1996";
		
		countryName = "United States";
		provinceName = "Florida";
		cityName = "Miama";
		addressName = "123 PO box";
		postalCode = "33111";
		phoneNumber = "+13055555584";
	}
	
	@Test
	
	public void TC_01_Register_New_Account () {
		driver.get("https://demo.nopcommerce.com/");
		
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText(day);
		
		// chọn giá trị trong select bằng 3 cách
		// chọn theo index
		//select.selectByIndex(7);
		
		// chọn theo value
		//select.selectByValue("14");
		
		// chọn theo text hiển thị UI
		//select.selectByVisibleText("18");
		//=> nên chọn theo UI vì index có thể thay đổi. value có thể khác với giá trị hiển thị  => nhầm lẫn, sai giá trị cần chọn
		
		// chọn theo text thì khi manual test lại sẽ dễ hơn. chọn theo vị trí là người dùng
		
		
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);
		
		driver.findElement(By.id("Email")).sendKeys(emailAdress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();
		
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		
		
		
		// verify
		driver.findElement(By.cssSelector("a.ico-login")).click();
		driver.findElement(By.id("Email")).sendKeys(emailAdress);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.cssSelector("button.login-button")).click();
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector("a.ico-account")).click();
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
		
		
	}
	
	@Test
	
	public void TC_02_Add_Address () {
		driver.findElement(By.cssSelector("li.customer-addresses>a")).click();
		driver.findElement(By.cssSelector("button.add-address-button")).click();
		
		driver.findElement(By.id("Address_FirstName")).sendKeys(firstName);
		driver.findElement(By.id("Address_LastName")).sendKeys(lastName);
		driver.findElement(By.id("Address_Email")).sendKeys(emailAdress);
		new Select(driver.findElement(By.id("Address_CountryId"))).selectByVisibleText(countryName);
		new Select(driver.findElement(By.id("Address_StateProvinceId"))).selectByVisibleText(provinceName);
		driver.findElement(By.id("Address_City")).sendKeys(cityName);
		driver.findElement(By.id("Address_Address1")).sendKeys(addressName);
		driver.findElement(By.id("Address_ZipPostalCode")).sendKeys(postalCode);
		driver.findElement(By.id("Address_PhoneNumber")).sendKeys(phoneNumber);
		
		driver.findElement(By.cssSelector("button.save-address-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("li.name")).getText(), firstName + " " + lastName);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.email")).getText().contains(emailAdress));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.phone")).getText().contains(phoneNumber));
		//Assert.assertEquals(driver.findElement(By.cssSelector("li.company")).getText(), companyName);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.address1")).getText(), addressName);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(cityName));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(provinceName));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(postalCode));

		Assert.assertEquals(driver.findElement(By.cssSelector("li.country")).getText(), countryName);

		
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
		 
	public void sleepInSecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 
		
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quite();
	}
	
	
}
	
