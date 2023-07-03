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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Part3_User_Interaction2 {
	WebDriver driver;
	Actions action;
	//WebDriverWait explicitWait;
	
	
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
		//explicitWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	

	//@Test
	
	public void TC_01_Click_And_Hold() {
		
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		//Đang chứa 12 số/ item trong lits này
		
		//1. Click vào 1 số trong source -		// Vẫn giữ chuột/ chưa nhả ra
		action.clickAndHold(listNumber.get(0))
			// 3. Di chuyển tới điểm cuối
			.moveToElement(listNumber.get(7))
				//4. nhả chuột
				.release()
					//Execute
					.perform();
		
		sleepInSecond(5);
		List<WebElement> listSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(listSelected.size(), 8);
		
	}
	
	@Test
	
	public void TC_02_Click_And_Hold_Random() {
		
		driver.get("https://automationfc.github.io/jquery-selectable/");
		// Chạy được cho cả Mac và Windown
		Keys key = null;
		if (osName.contains("Windowns")) {
			key = Keys.CONTROL;
		} else {
			key = Keys.COMMAND;
		}
		
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		// Đang chứa 12 số/ item trong List này
		// Nhấn Ctrl xuống
		
		action.keyDown(key).perform();
		
		// click chọn các số trong random
		action.click(listNumber.get(0))
			.click(listNumber.get(4))
			.click(listNumber.get(6))
			.click(listNumber.get(8)).perform();
		
		// nhả phím ctrl ra
		action.keyUp(key).perform();
		
		sleepInSecond(3);
				
		
		
		
	}

		 
	public void sleepInSecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 
		
	}
	
	public static boolean isNullOrEmpty(String str) {
		if (str == null)
			return true;
		else if (str.trim().equals(""))
			return true;
		else
			return false;
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quite();
	}
	
	
}
	
