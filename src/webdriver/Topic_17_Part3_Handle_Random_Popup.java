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

public class Topic_17_Part3_Handle_Random_Popup {
	WebDriver driver;
	//WebDriverWait explicitWait;

	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAdress = "auto2" + getRandomNumber() + "@gmail.com";
	
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
	
	// popup random là popup chỉ xuất hiện trong 1 khoảng thời gian nhất định: ví dụ popup quảng cáo cho giáng sinh
	// Yêu cầu: Nếu popup hiển thị thì thực hiện thao tác
	
	//@Test
	
	public void TC_01_Popup_Random_In_Dom() {
		
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(3);
		
		By lePopup = By.cssSelector("div.lepopup-poup-container>div:not([style^='display:none'])");
		
		// vì nó luôn có trong dom nên có thể dùng hàm isdisplay kiểm tra được
		if(driver.findElement(lePopup).isDisplayed()) {
			// Nhập email vào
			driver.findElement(By.cssSelector("div.lepoup-input>input")).sendKeys(emailAdress);
			sleepInSecond(2);
			driver.findElement(By.cssSelector("a[data-label='Get the Books']>span")).click();
			sleepInSecond(5);
			// Verrify
			Assert.assertEquals(driver.findElement(By.cssSelector("div.lepopup-element-html-content>h4")).getText(), "Thank you!");
			Assert.assertTrue(driver.findElement(By.cssSelector("div.lepopup-element-html-content>p")).getText().contains("Your sign-up request was successful."));
			
			// Đóng popup, qua step tiếp theo
			// sau ~5s nó sẽ tự đóng popup	
			sleepInSecond(15);
		}
		
		String articlename = "Agile Testing Explained";
		
		// Qua step nay
		
		driver.findElement(By.cssSelector("input#s")).sendKeys(articlename);
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.post-listing>article:first-child>h2>a")).getText(), articlename);
		
	}
	
	@Test
	
	public void TC_02_Popup_Random_In_Dom() {
		
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(25);
		
		By popup = By.cssSelector("div#tve-p-scroller");
		
		if(driver.findElement(popup).isDisplayed()) {
			//close popup hoawjc click vafo button
			driver.findElement(By.cssSelector("div#tve-p-scroller div.thrv_icon")).click();
			sleepInSecond(2);
			
		}
		
		driver.findElement(By.xpath("//button[text()='Danh sách khóa học']")).click();
		
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getTitle(), "Lịch khai giảng các khóa học tại VNK EDU | VNK EDU");
		
	}
	
	
	//@Test
	
	public void TC_03_Popup_Random_Not_In_Dom_Tiki() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(3);
		
		By popup = By.cssSelector("div.popup-content");
		
		// find element => sẽ bị fail khi click vào element
		// findelements => k bị fail kjhi k thấy element => tả về list rỗng
		
		// isDisplayed()
		if(driver.findElements(popup).size() > 0 && driver.findElements(popup).get(0).isDisplayed()) {
			driver.findElement(By.id("popup-name")).sendKeys("ABC");
			driver.findElement(By.id("popup-email")).sendKeys(emailAdress);
			driver.findElement(By.id("popup-phone")).sendKeys("0123456789");
			driver.findElement(By.cssSelector("button#close-popup")).click();
			sleepInSecond(2);			
		}
		
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		sleepInSecond(3);
		
		String courseName = "Khóa học Thiết kế và Thi công Hệ thống BMS";
		
		driver.findElement(By.id("search-course")).sendKeys(courseName);
		driver.findElement(By.cssSelector("button#search-course-button")).click();
		sleepInSecond(3);
		
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
	
