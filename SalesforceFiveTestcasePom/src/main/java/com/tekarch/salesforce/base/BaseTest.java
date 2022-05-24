package com.tekarch.salesforce.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.tekarch.salesforce.utility.CommonUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest {

	private WebDriver driver;

	@Parameters("browser")
	@BeforeMethod
	public void setUp(ITestContext context, @Optional("chrome") String browser) throws InterruptedException {
		getDriver(browser);
		context.setAttribute("driver", driver);
		context.setAttribute("browserType", browser);
		String url=CommonUtilities.getApplicationProperty("url");
		gotoUrl(url);
		Thread.sleep(1000);
	}


	@AfterMethod
	public void tearDown() throws InterruptedException {
		System.out.println("Base Class after method started");
		closeAllDriver();
		Thread.sleep(2000);
	}

	public void getDriver(String browser) {
		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if (browser.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		}
		else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();

	}
	public void gotoUrl(String url) {
		driver.get(url);
	}

	public void closeDriver() {
		driver.close();
	}

	public void closeAllDriver() {
		driver.quit();
	}

	public WebDriver getDriver() {
		return driver;
	}

}


