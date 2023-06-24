package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

public class Topic_08_Part3_Handle_Dropdown_Custom {
	WebDriver driver;
	
	WebDriverWait explicitWait;
	
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
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	@Test
	
	public void TC_01_Jquery () {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		//1. click vị trí bất kỳ để xổ ra data trong dropdown
		driver.findElement(By.id("speed-button")).click();
		
		//2. Chờ cho tất cả các item được load ra thành công
		// Locator phải lấy đại diện cho tất cả item
		// Lấy đến thẻ chứa text
		
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div[role='option']")));
		
		// Đưa hết item trong dropdown vào 1 list
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector("ul#speed-menu div[role='option']"));
		
		//3. Tìm item xem đúng cái đang cần hay không
		
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText();
			System.out.println(itemText);
			
			// 4. Kiểm tra cái text của item đúng với cái mình mong muốn
			if (itemText.equals("Fast")) {
				// 5. click vào item đó
				tempItem.click();
				
				// thoát ra khỏi vòng lặp để khi tìm được giá trị rồi thì out
				break;
			}
		}
		
		
	}
	
	//@Test
	
	public void TC_02_Add_Address () {
		
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
	
