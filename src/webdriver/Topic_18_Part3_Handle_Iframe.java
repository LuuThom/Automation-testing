package webdriver;

import java.util.List;
import java.util.Random;
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

public class Topic_18_Part3_Handle_Iframe {
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
	
	@Test
	
	public void TC_01_Iframe () {
		driver.get("https://skills.kynaenglish.vn/");
		
		//verify hiển thị facebook iframe
		// thẻ iframe vẫn thuộc web hiện tại là kyna
		Assert.assertTrue(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")).isDisplayed());
		
		
		// trong thẻ iframe chứa DOM của web khác nên cần switch vào đúng cái iframe
		//có thể dùng 3 cách
		//1. dùng index
		//driver.switchTo().frame(0);
		//2. dùng name / id. nhưng chỉ sử dụng cho trường hợp có name/id
		
		//driver.switchTo().frame("");
		
		//3. dùng element
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")));
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(), "165K followers");
		
		// switchto webmain rồi từ webmain switchto iframe mong muốn thì mới thao tác được
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("cs_chat_iframe");// vì thẻ có id nên dùng frame(name);
		
		// click vafo chat để show lên Chat support
		
		driver.findElement(By.cssSelector("div.button_bar")).click();
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Emma");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0987654567");
		new Select(driver.findElement(By.id("serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.name("message")).sendKeys("Tư vấn");
		sleepInSecond(3);
		
		System.out.println("backmain");
		// quay về main
		
		driver.switchTo().defaultContent();
		
		System.out.println("backmain 23");
		
		// Search voi key Excel
		//driver.findElement(By.cssSelector("i.fa-search")).click();
		driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
		
//		Keys key = null;
//		if (osName.contains("Windowns")) {
//			key = Keys.ENTER;
//		} else {
//			key = Keys.COMMAND;
//		}
//		
//		action.keyDown(key).perform();
//		System.out.println("backmain 56");
//		
//		sleepInSecond(5);
		//Keys key = Keys.ENTER;
		//action.keyDown(key).perform();
		driver.findElement(By.cssSelector("button.search-button")).click();
		System.out.println("backmain 66");
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		
		for(WebElement course : courseName) {
			Assert.assertTrue(course.getText().contains("Excel"));
			System.out.println(course.getText());
		}
		
	}
	
	//@Test
	
	public void TC_02_Frame_HDFC () {
		
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		//Switch qua frame chứa Login textbox
		driver.switchTo().frame("login_page");
		
		driver.findElement(By.name("fldLoginUserId")).sendKeys("john2022");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("input#keyboard")).isDisplayed());
		driver.findElement(By.cssSelector("input#keyboard")).sendKeys("john20222023");
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
	
