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

public class Topic_15_Part3_Handle_Popup {
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
	
	public void TC_01_Popup() {
		
		driver.get("https://ngoaingu24h.vn/");
		sleepInSecond(3);
		By LoginPopup = By.cssSelector("div#modal-login-v1 div.modal-content");
		
		//verify popup is undisplayed
		Assert.assertFalse(driver.findElement(LoginPopup).isDisplayed());
		
		// Click vafo button đăng nhập
		
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(2);
		// verify popup is display
		
		Assert.assertTrue(driver.findElement(LoginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		
		
	}
	
	@Test
	
	public void TC_02_Fixed_In_Dom_KyNa() {
		driver.get("https://skills.kynaenglish.vn/");
		sleepInSecond(5);
		By loginPopup = By.cssSelector("div#k-popup-account-login");
		
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		System.out.println("display");
		
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456789");
		
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(1);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		sleepInSecond(2);
		System.out.println("click");
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		//Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		System.out.println("un-display");
		
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
	
