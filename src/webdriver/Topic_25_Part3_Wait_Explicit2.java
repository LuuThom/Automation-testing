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

public class Topic_25_Part3_Wait_Explicit2 {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");// lấy ra đường dẫn tương đối của thư mục
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	
	String canhdepFileName = "canhdep.jpg";
	String canhdep2FileName = "canhdep2.jpg";
	String canhdep3FileName = "canhdep3.jpg";
	
	String canhdepFilePath = projectPath + "\\uploadFiles\\" + canhdepFileName;
	String canhdep2FilePath = projectPath + "\\uploadFiles\\" + canhdep2FileName;
	String canhdep3FilePath = projectPath + "\\uploadFiles\\" + canhdep3FileName;
	
	
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
	
	//@Test
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
	
	//@Test
	public void TC_03_Ajax_Loading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicitWait = new WebDriverWait(driver, 15);
		
		// Wait cho Date Picker được hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadCalendar")));
		
		// Wait cho không có ngày được chọn
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
		
		//Wait cho ngay 19 duoc click
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='19']")));
		
		// CLick vao ngay 19
		driver.findElement(By.xpath("//a[text()='19']")).click();
		
		// Wait cho Ajax icon loading bieesn mat (invisible)
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));
		
		// Wait cho ngay vua được click là được phép click trở lại
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']/a[text()='19']")));
		
		// verify ngày được chọn hiển thị
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "Wednesday, July 19, 2023");
		
				
	}
	
	@Test
	public void TC_04_Upload_File() {
		driver.get("https://gofile.io/uploadFiles");
		explicitWait = new WebDriverWait(driver, 20);
		// Wait cho Add Files button được visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#filesUpload button.filesUploadButton")));
		
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(canhdepFilePath + "\n" + canhdep2FilePath + "\n" + canhdep3FilePath);
		
		//Wait cho các loading icon của từng file biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress div.progress-bar"))));
		
		// Wait cho Upload message thanfh coong duowjc visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row justify-content-center mainUploadSuccess']//div[contains(text(),'Your files have been successfully uploaded')]")));
		
		// Verify message nafy display
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='row justify-content-center mainUploadSuccess']//div[contains(text(),'Your files have been successfully uploaded')]")).isDisplayed());
		
		//Wait cho show file button dduwocj clickable
		//explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("//div[@class='row mb-2 mainUploadSuccessLink']//a[@class='ajaxLink']")));
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='row mb-2 mainUploadSuccessLink']//a[@class='ajaxLink']")));
		
		//CLick button show file
		driver.findElement(By.xpath("//div[@class='row mb-2 mainUploadSuccessLink']//a[@class='ajaxLink']")).click();
		
		// wait cho file name va button download/play hien thi
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'canhdep3.jpg')]")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button//span[contains(text(),'Download')]")));
		
		
		
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
	
