package webdriver;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Part3_Handle_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;
	
	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	

	//@Test
	
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(3);
		
		//1. có thể switch qua và tương tác luôn
		//alert = driver.switchTo().alert();
		
		// 2. Cần wait trước rồi khi nào nó xuất hiện  mới switch qua và tương tác
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		//=> nên dùng cách 2 
		
		// verify alert title đúng như mong đợi
		
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
		
	}
	
	//@Test
	
	public void TC_02_Confirm_Alert() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(3);
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		//verify alert co title dung nhu mong doi
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
		
	}
		
	
	//@Test
	
	public void TC_03_Promt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(3);
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		//verify alert co title dung nhu mong doi
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		String courseName = " Fullstack Auto";
		alert.sendKeys(courseName);
		
		alert.accept();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: " + courseName);
	}
	//@Test
	
	public void TC_05_Authent_Alert() {
		
		//Truyền trực tiếp Username/ password vào trong chính Url này -> tự động signIn luôn
		// http:// + Username: Password @ the-internet.herokuapp.com/basic_auth
		driver.get(passUserAndPassToUrl("http://the-internet.herokuapp.com/basic_auth", "admin", "admin"));
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
		
	}
	
	//@Test
	
	public void TC_06_Authent_Alert_Url() {
		
		driver.get("http://the-internet.herokuapp.com");
		sleepInSecond(3);
		String authenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		driver.get(passUserAndPassToUrl(authenUrl, "admin", "admin"));
		
		//Truyền trực tiếp Username/ password vào trong chính Url này -> tự động signIn luôn
		// http:// + Username: Password @ the-internet.herokuapp.com/basic_auth
		driver.get(passUserAndPassToUrl("http://the-internet.herokuapp.com/basic_auth", "admin", "admin"));
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
		
	}
	
	//@Test
	
	public void TC_07_Authent_Alert_II() {
		// nhập trực tiếp user name và pass
		// nhưng hết cách mới dùng vì chỉ chạy AutoIT 
		// nhược điểm : AutoIT chỉ chạy được cho windows (không dùng cho Mac và linus). AutoIT không chạy ở trên các CI tool được (headless)

		
		//Truyền trực tiếp Username/ password vào trong chính Url này -> tự động signIn luôn
		
		
	}
	
	public String passUserAndPassToUrl(String url, String username, String password) {
		String[] arrayUrl = url.split("//");
		return arrayUrl[0] + "//" + username + ":" + password + "@" + arrayUrl[1];
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
	
