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

public class Topic_20_Part3_Javascript_Excecutor {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	
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
	
	//@Test
	
	public void TC_01_Javascript_Executor () {
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(5);
		Assert.assertEquals(executeForBrowser("return document.domain;"), "live.techpanda.org");
		Assert.assertEquals(executeForBrowser("return document.URL"), "http://live.techpanda.org/");
		
		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(3);
		
		hightlightElement("//a[text()='Samsung Galaxy']/parent::h2//following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2//following-sibling::div[@class='actions']/button");
		sleepInSecond(3);
		
		Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));
		
		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(3);
		
		hightlightElement("//input[@id='newsletter']");
		scrollToElementOnDown("//input[@id='newsletter']");
		sleepInSecond(3);
		
		sendkeyToElementByJS("//input[@id='newsletter']", "abc" + RandomNumber() + "@gmail.com");
		hightlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");
		sleepInSecond(3);
		
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription"));
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(5);
		
		Assert.assertEquals(executeForBrowser("return document.domain;"), "demo.guru99.com");
		
		
		
		
	}
	
	@Test
	public void TC_02_HTMLT5_Validation_Mess () {
		
		driver.get("https://warranty.rode.com/register");
		
		String registerButton = "//button[@type='submit']";
		String nameTextbox = "//input[@id='name']";
		String emailTextbox = "//input[@id='email']";
		String password = "//input[@id='password']";
		String confirmPassword = "//input[@id='password_confirmation']";
		
		clickToElementByJS(registerButton);
		sleepInSecond(2);
		Assert.assertEquals(getElementValidationMessage(nameTextbox), "Please fill out this field.");
		
		sendkeyToElementByJS(nameTextbox, "Automation");
		clickToElementByJS(registerButton);
		sleepInSecond(2);
		Assert.assertEquals(getElementValidationMessage(emailTextbox), "Please fill out this field.");
		
		sendkeyToElementByJS(emailTextbox, "auto@gmail.com");
		clickToElementByJS(registerButton);
		sleepInSecond(2);
		Assert.assertEquals(getElementValidationMessage(password), "Please fill out this field.");
		
		sendkeyToElementByJS(password, "123456789");
		clickToElementByJS(registerButton);
		sleepInSecond(2);
		Assert.assertEquals(getElementValidationMessage(confirmPassword), "Please fill out this field.");
		
	}

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}
	
	

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
		sleepInSecond(3);
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
		sleepInSecond(3);
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}
	
	public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
		jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}
	
	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}
	
	public String getAttributeInDOM(String locator, String attributeName) {
		return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

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
	
