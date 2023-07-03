package webdriver;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Part3_Wait_Explicit {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");// lấy ra đường dẫn tương đối của thư mục
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	
	
	@BeforeClass

	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		
		// apply 15s cho cac trang thai
		//explicitWait = new WebDriverWait(driver, 15);
			
	}
	
	// dùng cách chờ cho element hiện thì bắt đầu thao tác
	//@Test
	public void TC_01_Not_Enough_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 3);
		// Click vafo Start button
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//Thiếu thời gian để cho 1 element tiếp theo hoạt động được
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		// get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
		
	}
	
	//@Test
	public void TC_02_Enough_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 5);
		// Click vafo Start button
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		//Thiếu thời gian để cho 1 element tiếp theo hoạt động được
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		// get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
		
	}
	
	//@Test
	public void TC_03_More_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait = new WebDriverWait(driver, 25);
		// Click vafo Start button
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		//Thiếu thời gian để cho 1 element tiếp theo hoạt động được
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		// get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
		
	}
	
	
	@Test
	public void TC_01_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 3);
		// Click vafo Start button
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//Thiếu thời gian để cho 1 element tiếp theo hoạt động được
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		// get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
		
	}
	
	//ngoài cách trên thì sử dụng cách chờ 1 element biến mất thì thực hiện bước tiếp
	
	@Test
	public void TC_02_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 5);
		// Click vafo Start button
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		//Thiếu thời gian để cho 1 element tiếp theo hoạt động được
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		// get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
		
	}
	
	
 
	public void sleepInSecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 
		
	}
	
	public int RandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
	@AfterClass
	public void afterClass() {
		//driver.quite();
	}
	
	
}
	
