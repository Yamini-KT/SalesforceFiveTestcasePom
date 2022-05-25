package com.tekarch.salesforce.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	private static final int TIMEOUT_SECONDS=30;

	protected WebDriver driver;
	private static WebDriverWait wait;


	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait= new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
		PageFactory.initElements(driver, this);

	}

	public static void enterText(WebElement element,String text,String objName) {
		if(element.isDisplayed()) {
			clearElement(element,objName);
			element.sendKeys(text);
		}
		else {
			System.out.println("fail: "+objName+" element not displayed");
		}
	}

	public static String getText(WebElement element,String objName) {

		waitUntilVisible(element, objName);
		return element.getText();

	}

	public static void clickElement(WebElement element,String objName) {
		if(element.isDisplayed()) {
			element.click();
			System.out.println("pass:"+objName+" element clicked");
		}
		else {
			System.out.println("fail:"+objName+"  element not displayed");
		}
	}

	public static void clearElement(WebElement element,String objName) {
		if(element.isDisplayed()) {
			element.clear();
			System.out.println("pass:"+objName+"  element cleared");
		}
		else {
			System.out.println("fail:"+objName+" element not displayed");
		}
	}

	public static void waitUntilVisible(WebElement element,String objName) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}


	public static void waitUntilvisibilityOfElementLocated(By locator,String objName) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}


}
