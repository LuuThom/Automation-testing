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

public class Topic_23_Part3_WaitII {
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

		driver = new FirefoxDriver();
		
		// Ngoại lệ
		// Implicit Wait set ở đâu nó sẽ apply từ đó trở xuống
		// Nếu bị gán lại thì sẽ dùng cái giá trị mới / không dùng giá trị cũ
		// không set thì là 0s
		// => vì vậy chỉ cần set ở @BeforClass, bài này set ở từng TC để minh họa 
		
		
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//jsExecutor = (JavascriptExecutor) driver;
		
	}
	
	//@Test
	
	public void TC_01_FindElement () {
				
		// Tìm thấy duy nhất 1 node/element
		// Thao tác trực tiếp lên node đó
		// Vì nó tìm thấy nên k cần phải chờ hết timeout (10s)
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		driver.findElement(By.id("email")).click();
		// Tìm thấy nhiều hơn 1 element
		// nó sẽ thao tác với node đầu tiên
		// Không quan tâm tới các node còn lại
		// Trong trường hợp các bạn bắt locator sai thì nó tìm sai
		
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("luuthombnp95@gmail.com");
		
		// không tìm thấy element
		// có cơ chế tìm lại = 0.5s sẽ tìm lại 1 lần
		// nếu trong thời gian đang tìm lại mà thấy element thì thỏa mãn dk = pass
		// nếu hết timeout mà vẫn k tìm thấy element thì
		// + Đánh fail testcase tại step này
		// + Throw ra 1 ngoại lệ: NóuchElementException  
		driver.findElement(By.cssSelector("input[type='check']"));
	}
	
	//@Test
	public void TC_02_FindElements () {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		// Tìm thấy duy nhất 1 node/element
		// Thao tác trực tiếp lên node đó
		// Vì nó tìm thấy nên k cần phải chờ hết timeout (10s)
		List<WebElement> elements = driver.findElements(By.cssSelector("input#email"));
		System.out.println("List element number = "+elements.size());
		
		// Tìm thấy nhiều hơn 1 element
		// Tìm thấy và lưu nó vào list = element tương ứng
		
		elements = driver.findElements(By.cssSelector("input[type='email']"));
		System.out.println("List element number = " + elements.size());
		
		// không tìm thấy element
		// có cơ chế tìm lại = 0.5s sẽ tìm lại 1 lần
		// nếu trong thời gian đang tìm lại mà thấy element thì thỏa mãn dk = pass
		// nếu hết timeout mà vẫn k tìm thấy element thì
		// + không đánh fail Testcase
		// trả về 1 list rỗng
		// chạy tiếp action tiếp theo
		
		elements = driver.findElements(By.cssSelector("input[type='check']"));
		System.out.println("List element number = " + elements.size());
	}
	
	@Test
	public void TC_03_Not_Enough_Time() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		// Click vafo Start button
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		// get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
		
	}
	
	@Test
	public void TC_03_Enough_Time() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		// Click vafo Start button
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		// get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
		
	}
	
	@Test
	public void TC_03_More_Time() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		// Click vafo Start button
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		// get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
		
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
	
