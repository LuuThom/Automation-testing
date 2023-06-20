package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
	}
	
	@Test
	public void TC_01_Web_Browser() {
		// >= 2: nó sẽ đóng tab / window mà nó đnag đứng
		// = 1: nó cũng đóng Browser
		driver.close();
		
		// không quan tâm bao nhiêu tab/ window => Browser
		driver.quit();
		
		// Tìm 1 element
		
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']"));
		
		// Tìm nhiều element
		List<WebElement> checkboxs = driver.findElements(By.xpath(""));
		
		// Mowr ta 1 url naof ddos
		driver.get("https://www.facebook.com/");
		
		// click vao link tieng viet
		
		// trar veef url cua web hien tai
		Assert.assertEquals(driver.getCurrentUrl(), "https://vi-en.facebook.com/");
		
		// Trả về source code HTML của page hiện tại
		// verify tương đối
		Assert.assertTrue(driver.getPageSource().contains("Facebook giúp bạn kết nối và chia sẻ với mọi người"));
		Assert.assertTrue(driver.getPageSource().contains("Facebook giúp bạn kết nối "));
		Assert.assertTrue(driver.getPageSource().contains("Facebook giúp bạn "));
		
		//Trả về title của page hiện tại
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		
		// WebDriver API - window/ tabs
		// Lấy ra đưuọc ID của window/Tab mà driver đang đứng (active)
		String loginWindowID = driver.getWindowHandle();//*
		
		//Laấy ra ID của tất cả các window/Tab
		Set<String> allIDs = driver.getWindowHandles();//*
		
		//Cookie / Cache
		Options opt = driver.manage();
		// Login thành công => lưu lại
		opt.getCookies(); //*
		// Testcase khác => set cookie vào lại => không cần phải login nữa
		opt.logs();
		
		Timeouts time = opt.timeouts();
		
		//Implicit wait and depend on: FindElement/ FindElements"
		// KHoảng thời gian chờ Element xuất hiện trong vòng x giây
		
		time.implicitlyWait(5, TimeUnit.SECONDS); // 5s = 5000ms = 5000000 
		time.implicitlyWait(5000, TimeUnit.MILLISECONDS);
		time.implicitlyWait(5000000, TimeUnit.MICROSECONDS);
		
		// Khoảng thời gian chờ page load xong trong x giây
		time.pageLoadTimeout(5, TimeUnit.SECONDS);
		
		//WebDriver API - Javascript Executor (JavascriptExecutor library)
		// Khoảng thời gian chờ script được thực thi trong vòng x giây
		time.setScriptTimeout(5, TimeUnit.SECONDS);
		
		Window win = opt.window();
		win.fullscreen();
		win.maximize(); //**
		
		// Test FUI : functional
		//Test GUI
		
		win.getPosition();
		win.getSize();
		
		Navigation nav = driver.navigate();
		nav.back();
		nav.refresh();
		nav.forward();
		nav.to("http://www.facebook.com");
		
		TargetLocator tar = driver.switchTo();
		//Webdriver API - Alert/ Authentication Alert (Alert Library)
		tar.alert();//*
		
		//Webdriver API - Frame/ Iframe (Frame libary)
		tar.frame("");//*
		
		//Webdriver API - Windows / Tabs
		tar.window("");//*
	
		
		
	
	}
	@Test
	public void TC_01_WebElement(){
		WebElement element = driver.findElement(By.className(""));
		// Dungf cho casc textbox/ textarea/ dropdown (Editable)
		// Xoa duw lieu di truoc khi nhap text
		element.clear();//*
		
		// Dungf cho casc textbox/ textarea/ dropdown (Editable)
		// nhap duw lieu
		element.sendKeys(""); //**
		
		// click vao element
		element.click();//**
		
		// verify attribute
		String searchAttribute = element.getAttribute("placeholder");//**
		
		// lấy ra thuộc tính css
		element.getCssValue("background-color");//*
		
		//Vị trí của element so với web bên ngoài
		element.getLocation();
		
		// lấy ra kích thước của element (bên trong)	
		element.getSize();
		
		// Location + Size
		element.getRect();
			
		//lấy ảnh chụp màn hình khi tc fail
		element.getScreenshotAs(OutputType.FILE);//*
		element.getScreenshotAs(OutputType.BYTES);
		element.getScreenshotAs(OutputType.BASE64);
		
		// lấy ra thẻ html cura element => truyền vào 1 locator khác
		driver.findElement(By.id("Email")).getTagName();
		driver.findElement(By.name("Email")).getTagName();
		String emailTextboxTagname = driver.findElement(By.cssSelector("#Email")).getTagName();
		driver.findElement(By.xpath("//" + emailTextboxTagname + "[@id='email']"));
		
		// get text
		element.getText();//**
		
		// ?? khi nào dùng get text - getAttribute
		// khi cái value mình cần lấy nó nằm bên ngoài -> getText
		// Khi value mình cần lấy nó nằm bên trong => getAttribute
		
		// verify xem 1 element có hiển thị hay không ( sử dụng với all element)
		// phạm vi : tất cả
		Assert.assertTrue(element.isDisplayed());//**
		Assert.assertFalse(element.isDisplayed());//**
		
		//Verify 1 elemetn có thao tác được hay không - dùng cho tất cả element
		Assert.assertTrue(element.isEnabled());
		Assert.assertFalse(element.isEnabled());	
		
		//Verify 1 elemetn có được chọn hay chưa - dùng cho tất cả element
		Assert.assertTrue(element.isSelected());//*
		Assert.assertFalse(element.isSelected());	//*
		
		// element thuộc thẻ form
		element.submit();
	}
	
	
}
	
