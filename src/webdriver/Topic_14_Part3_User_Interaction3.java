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
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Part3_User_Interaction3 {
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
	
	public void TC_01_Double_Click() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "Hello Automation Guys!");
		
		
	}
	
	//@Test
	
	public void TC_02_Right_Click() {
		
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");				
		
		action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
		
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
		
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		sleepInSecond(2);
		
		driver.switchTo().alert().accept();
		sleepInSecond(2);
		
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
		
		
		
	}
	
	@Test
	
		public void TC_03_Drag_And_Drop() {
			
			driver.get("https://automationfc.github.io/kendo-drag-drop/");
			
			WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
			WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));
			
			action.dragAndDrop(smallCircle, bigCircle).perform();
			
			sleepInSecond(3);
			
			Assert.assertEquals(bigCircle.getText(), "You did great!");
			
			//Verify background color
			
			String bigCircleBackgroundColor = bigCircle.getCssValue("background-color");
			System.out.println(bigCircleBackgroundColor);
			
			String bigCircleHexa = Color.fromString(bigCircleBackgroundColor).asHex();
			
			bigCircleHexa = bigCircleHexa.toUpperCase();
			Assert.assertEquals(bigCircleHexa, "#03A9F4");
			
		}
	
	@Test
	public void TC_03_Drag_And_Drop_HTML5() {
		
		
	}
	
//	public String getContentFile(String filePath) throws IOException {
//		Charset cs = Charset.forName("UTF-8");
//		FileInputStream stream = new FileInputStream(filePath);
//		try {
//			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
//			StringBuilder builder = new StringBuilder();
//			char[] buffer = new char[8192];
//			int read;
//			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
//				builder.append(buffer, 0, read);
//			}
//			return builder.toString();
//		} finally {
//			stream.close();
//		}
//	}

		 
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
	
