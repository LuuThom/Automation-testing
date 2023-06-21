package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Practice {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	By emailTextbox = By.id("mail");
	By ageUnder18Radio = By.cssSelector("#under_18");
	By educationTextArea = By.cssSelector("#edu");
	By nameUser5Text = By.xpath("//h5[text()='Name: User5']");
	By passwordTextbox= By.cssSelector("#disable_password");
	By biographyTextArea = By.cssSelector("#bio");
	By developmentCheckbox = By.cssSelector("#development");
	
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
	
	//@Test
	
	public void TC_01_Display () {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Textbox
		if(driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("Selenium web element");
			System.out.println("Email textbox is displayed");
		} else {
			System.out.println("Email textbox is not display");
		}
		
		// text area
		if(driver.findElement(educationTextArea).isDisplayed()) {
			driver.findElement(educationTextArea).sendKeys("Selenium web element 2");
			System.out.println("Education textarea is displayed");
		} else {
			System.out.println("Education textarea is not displayed");
		}
		
		// radio
		if(driver.findElement(ageUnder18Radio).isDisplayed()) {
			driver.findElement(ageUnder18Radio).click();
			System.out.println("Age Under is displayed");
		} else {
			System.out.println("Age Under is not displayed");
		}
		
		// name
		if(driver.findElement(nameUser5Text).isDisplayed()) {
			System.out.println("nameUser5Text is displayed");
		} else {
			System.out.println("nameUser5Text is not displayed");
		}		
		
	}
	//@Test
	
	public void TC_02_Enabled () {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if(driver.findElement(passwordTextbox).isEnabled()) {
			System.out.println("Password Textbox is enabled");
		} else {
			System.out.println("Password Textbox is display");
		}
		
		if(driver.findElement(biographyTextArea).isEnabled()) {
			System.out.println("Bio Textarea is enabled");
		} else {
			System.out.println("Bio Textarea  is display");
		}
		
	}
	@Test
	
	public void TC_03_Selected () {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// verify checkbox chưa được selected
		Assert.assertFalse(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertFalse(driver.findElement(developmentCheckbox).isSelected());
		
		// click checkbox/ radio
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(developmentCheckbox).click();
		
		sleepInSecond(3);
		
		// verify checkbox/radio đã được selected
		Assert.assertTrue(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertTrue(driver.findElement(developmentCheckbox).isSelected());
	
	}
	//@Test
	public void TC_04_Register () {
		
		
	}
		 
	public void sleepInSecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 
		
	}
	
	
}
	
