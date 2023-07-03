package webdriver;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Part3_Handle_PopupII {
	WebDriver driver;
	//WebDriverWait explicitWait;

	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chormedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
			System.setProperty("webdriver.chorme.driver", projectPath + "/browserDrivers/chormedriver");
		}
		
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", false);
		driver = new FirefoxDriver(options);
		//explicitWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	

	//@Test
	
	public void TC_01_Popup_Fixed_Not_In_Dom() {
		
		driver.get("https://tiki.vn/");
		sleepInSecond(3);
		// vì phần tử không có trong DOM thì nên sử dụng By
		// By: nó chưa có đi tìm element
		
		By loginPopup = By.cssSelector("div.ReactModal__Content");
				
		// nếu khai báo bằng webelement thì nó sẽ đi tìm element luôn=> mới mở trang thì k có element để tìm => lỗi
		//WebElement loginPopupElement = driver.findElement(By.cssSelector("div.ReactModal__Content"));
		
		// Verify nó chưa hiển thị khi chưa click vào Login Button
		// Trường hợp element không có trong DOM thì nên sử dụng findElements vì nó trả về null, có thể chạy tiếp Testcase. nếu dùng findElement thì nó sẽ đánh fail Testcase, không thể chạy tiếp.
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		
		// Click cho popup bật lên
		driver.findElement(By.cssSelector("div[data-view-id*='header_account']")).click();
		sleepInSecond(2);
		
		// Verify hien thi
		Assert.assertEquals(driver.findElements(loginPopup).size(), 1);
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("input[name='tel']")).sendKeys("0987678960");
		sleepInSecond(2);
		
		// close popup
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSecond(2);
		
		//Verify no khong con hien thi
		
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		
		
		
	}
	
	@Test
	
	public void TC_02_Popup_Fixed_Not_In_Dom_Tiki() {
		driver.get("https://tiki.vn/");
		sleepInSecond(3);
		// vì phần tử không có trong DOM thì nên sử dụng By
		// By: nó chưa có đi tìm element
		
		By loginPopup = By.cssSelector("div.ReactModal__Content");
				
		// nếu khai báo bằng webelement thì nó sẽ đi tìm element luôn=> mới mở trang thì k có element để tìm => lỗi
		//WebElement loginPopupElement = driver.findElement(By.cssSelector("div.ReactModal__Content"));
		
		// Verify nó chưa hiển thị khi chưa click vào Login Button
		// Trường hợp element không có trong DOM thì nên sử dụng findElements vì nó trả về null, có thể chạy tiếp Testcase. nếu dùng findElement thì nó sẽ đánh fail Testcase, không thể chạy tiếp.
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		
		// Click cho popup bật lên
		driver.findElement(By.cssSelector("div[data-view-id*='header_account']")).click();
		sleepInSecond(2);
		
		// Verify hien thi
		Assert.assertEquals(driver.findElements(loginPopup).size(), 1);
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		
		//verify hiển thị mess báo lỗi
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Email không được để trống']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Mật khẩu không được để trống']")).isDisplayed());
		
		
		// close popup
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSecond(2);
		
		//Verify no khong con hien thi
		
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
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
	
