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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Part3_Handle_Upload_File {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");// lấy ra đường dẫn tương đối của thư mục
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	
	String canhdepFileName = "canhdep.jpg";
	String canhdep2FileName = "canhdep2.jpg";
	String canhdep3FileName = "canhdep3.jpg";
	
	String canhdepFilePath = projectPath + "\\uploadFiles\\" + canhdepFileName;
	String canhdep2FilePath = projectPath + "\\uploadFiles\\" + canhdep2FileName;
	String canhdep3FilePath = projectPath + "\\uploadFiles\\" + canhdep3FileName;
	
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
		jsExecutor = (JavascriptExecutor) driver;
		
	}
	
	@Test
	
	public void TC_01_Sign_File () {
		
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(canhdepFilePath);
		sleepInSecond(1);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(canhdep2FilePath);
		sleepInSecond(1);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(canhdep3FilePath);
		sleepInSecond(1);
		
		// verify ảnh được load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + canhdepFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + canhdep2FileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + canhdep3FileName + "']")).isDisplayed());
		
		// Click upload file
//		List<WebElement> buttonUpload = driver.findElements(By.cssSelector("table button.start"));
//		for (WebElement button : buttonUpload) {
//			button.click();
//			sleepInSecond(3);
//		}
//		
//		// Verify upload success (link)
//		
//		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + canhdepFileName + "']")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + canhdep2FileName + "']")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + canhdep3FileName + "']")).isDisplayed());
//		
//		// Verify upload success (anh)
//		
//		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + canhdepFileName + "')]"));
//		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + canhdep2FileName + "')]"));
//		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + canhdep3FileName + "')]"));
	}
	
	//@Test
	public void TC_01_Multiple_File () {
		
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(canhdepFilePath + "\n" + canhdep2FilePath + "\n" + canhdep3FilePath);
		sleepInSecond(2);
		
		
		// verify anh được load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + canhdepFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + canhdep2FileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + canhdep3FileName + "']")).isDisplayed());
		
		// Click upload file
		List<WebElement> buttonUpload = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement button : buttonUpload) {
			button.click();
			sleepInSecond(3);
		}
		
		// Verify upload success (link)
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + canhdepFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + canhdep2FileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + canhdep3FileName + "']")).isDisplayed());
		
		// Verify upload success (anh)
		
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + canhdepFileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + canhdep2FileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + canhdep3FileName + "')]"));
	}
	
	// check anh is displayed
	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}
	
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
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
	
