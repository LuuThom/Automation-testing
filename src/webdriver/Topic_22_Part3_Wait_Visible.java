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

public class Topic_22_Part3_Wait_Visible {
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

		driver = new ChromeDriver();
		action = new Actions(driver);
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//jsExecutor = (JavascriptExecutor) driver;
		
	}
	
	@Test
	
	public void TC_01_Visible_Display_Visibility () {
		
		driver.get("https://www.facebook.com/");
		
		// 1. Cos trên UI (bắt buộc)
		//1. Có trong HTml (bắt buộc)
		
		// chờ cho email address textbox hiển thị trong vòng 10s
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		
		driver.findElement(By.id("email")).sendKeys("abc@gmail.com");
				
	}
	
	//@Test
	public void TC_02_Invisible_undisplay () {
		//2. Khoong cos trên UI (bắt buộc)
		// Có trong HTML
		
		driver.get("https://www.facebook.com/");
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-from-button']")).click();
		
		// chờ cho re-enter email text box không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
		
	}
	
	//@Test
	public void TC_02_Invisible_Undisplayed_Invisibility_II () {
		//2. Khoong cos trên UI (bắt buộc)
		// Có trong HTML
			
		driver.get("https://www.facebook.com/");
			
		// chờ cho re-enter email text box không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
			
	}
	
	@Test
	public void TC_03_Present_I() {
		driver.get("https://www.facebook.com/");
		
		// 1. Có ở UI
		// Có trong HTMl
		
		// chờ cho email address textbox present trong HTMl trong vòng 10s
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
	}
	
	@Test
	public void TC_03_Present_II() {
		driver.get("https://www.facebook.com/");
		
		// 1. Khong Có ở UI
		// Có trong HTMl (bat buoc)
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-from-button']")).click();
		// chờ cho re-enter email text box không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
	}
	
	@Test
	public void TC_04_Staleness() {
		driver.get("https://www.facebook.com/");
		
		// 1. Khong Có ở UI (bat buoc)
		// khong co trong HTMl
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-from-button']")).click();
		
		// Phase 1: Element có trong case HTMl
		WebElement reEnterEmailAddressTextbox = driver.findElement(By.name("reg_email_confirmation__"));
		
		// thao tasc vs Element khác làm cho element không còn hiern thị trong DOM nữa
		
		// Close popup đi
		driver.findElement(By.cssSelector("img._8idr")).click();
		
		// chờ cho re-enter email text box không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.stalenessOf(reEnterEmailAddressTextbox));
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
	
