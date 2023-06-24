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
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Part3_Handle_Dropdown_Custom2 {
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
	
	//@Test
	
	public void TC_01_Button () {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		
		By loginButton = By.cssSelector("button.fhs-btn-login");
		
		//verify xem button có disabled
		
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		String loginButtonBackground = driver.findElement(loginButton).getCssValue("background-image");
		System.out.println(loginButtonBackground);
		
		Assert.assertTrue(loginButtonBackground.contains("rgb(224, 224, 224)"));
		
		
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("0988712344");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");
		
		//verify login button is enabled
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		loginButtonBackground = driver.findElement(loginButton).getCssValue("background-color");
		
		Color loginButtonBackgroundColor = Color.fromString(loginButtonBackground);
		Assert.assertEquals(loginButtonBackgroundColor.asHex().toUpperCase(), "#C92127");
		System.out.println(loginButtonBackground);
		
		
		}
	
	//@Test
	public void TC_02_Checkbox_Radio_Sign () {
		
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		//click chọn 1 checkbox
		
		driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).click();
		
		// click chọn 1 radio
		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).click();
		
		//verify các checkbox , radio dã được chon rồi
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).isSelected());
		
		// bỏ chọn checkbox
		driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).click();
		
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).isSelected());
		
		// radio không thể tự bỏ chọn => verify khi click lần 2 có bỏ chọn không?
		// click lần 2 vào radio
		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).isSelected());
	}
	
	@Test
	public void TC_03_Checkbox_Radio_Multill () {
		
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("input.form-checkbox"));
		// dùng vòng lặp để check all checkbox
		for(WebElement checkbox : allCheckboxes) {
			checkbox.click();
		}
		
		
		// Verify tất cả các checkbox đx được chọn thành công
		for(WebElement checkbox:allCheckboxes) {
			Assert.assertTrue(checkbox.isSelected());
		}
		
		//duyệt vồng lặp, gặp 1 giá trị  .. thì mới chọn
		
		for(WebElement checkbox : allCheckboxes) {
			if(checkbox.getAttribute("value").equals("Arthritis")) {
				checkbox.click();
			}
		}
	}
	
	@Test
	public void TC_04_Default_Checkbox () {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		//chọn nó
		// kiểm tra trạng thái checkbox trước khi chọn. nếu chưa chọn thì click để chọn. dấu ! để phủ định
		if (!driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected()) {
			driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
		}
		
		//checkToCheckbox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		
		// bo chon no
		if (driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected()) {
			driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
		}
		
		//uncheckToCheckbox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		
		}
	
	// có thể viết thành hàm như sau
	public void checkToCheckbox (By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public void uncheckToCheckbox (By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
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
	
