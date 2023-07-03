package webdriver;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Part3_Handle_Window_Tabs {
	WebDriver driver;
	Actions action;
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
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	//@Test
	
	public void TC_01_ID_Tab_Window () {
		// get parent page
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Windown / tab nó sẽ có 2 hàm để lấy ra ID của windown/tab đó
		// 1- lấy ra ID của tab/ windown đang đứng
		
		String basicFromID = driver.getWindowHandle();
		System.out.println("parent page = " + basicFromID);
		
		// click vào Google link để bật tab mới
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		
		sleepInSecond(3);
		
		switchToWindownByID(basicFromID);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		
		// Dùng hàm này sẽ lấy ra được ID của tab con
		String googleWindownID = driver.getWindowHandle();
		
		switchToWindownByID(googleWindownID);
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
				
	}
	
	@Test
		public void TC_02_Title_Tab_Window () {
		
		// get parent page
		driver.get("https://automationfc.github.io/basic-form/");
		
		// Click để bật tap mới
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);

		switchtoWindowByPageTitle("Google");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		
		// switch to web main
		switchtoWindowByPageTitle("Selenium WebDriver");
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/");
		
		//System.out.println("new tabs");
		
		// // switch to Facebook
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(2);		
		switchtoWindowByPageTitle("Facebook – log in or sign up");		
		driver.findElement(By.id("email")).sendKeys("098765423");
		driver.findElement(By.id("pass")).sendKeys("098765423");
		sleepInSecond(3);
		
		// switch to web main
		switchtoWindowByPageTitle("Selenium WebDriver");
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/");
		
		// switch to Tiki
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(2);
		switchtoWindowByPageTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		
	}

	// hàm so sánh khi có từ 2 tab/window
	public void switchtoWindowByPageTitle(String expectedPageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
			
		for(String id : allWindowIDs) {
				// switch từng ID trước
			driver.switchTo().window(id);
			sleepInSecond(2);
				
				//Lấy ra title của page này
			String actualTitlePage = driver.getTitle();
			if (actualTitlePage.equals(expectedPageTitle)) {
				break;
			}
		}
			
	}
		
	
	
		
	// hàm so sánh khi chỉ có 2 tabs/windown
	public void switchToWindownByID(String ortherID) {
		Set<String> allWindownIDs = driver.getWindowHandles();
		for(String id : allWindownIDs) {
			if (!id.equals(ortherID)) {
				driver.switchTo().window(id);
				sleepInSecond(2);
			}
		}
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
	
