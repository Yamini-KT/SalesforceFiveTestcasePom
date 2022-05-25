package com.tekarch.salesforce.pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tekarch.salesforce.base.BasePage;

public class HomePage extends BasePage {

	@FindBy(xpath="//*[@id=\"home_Tab\"]/a") WebElement homeTabElm;
	@FindBy(id="userNavLabel") WebElement userNavLblElm;
	@FindBy(id="userNav-menuItems") WebElement userNavMenuItemsElm;
	@FindBy(linkText="Logout") WebElement logoutButtonElm;

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public String getHomeTabText() {
		waitUntilVisible(homeTabElm, "Home Tab Element");
		return getText(homeTabElm, "Home Tab Text");
	}

	public String getUserNavMenuItems() {
		waitUntilVisible(userNavLblElm,"User Navigation Label");
		clickElement(userNavLblElm, "User Navigation Label");
		String menuItems = getText(userNavMenuItemsElm, "User Navigation Menu Items");
		clickElement(userNavLblElm, "User Navigation Label");
		return menuItems;
	}

	public void logout() {
		waitUntilVisible(userNavLblElm,"User Navigation Label");
		clickElement(userNavLblElm, "User Navigation Label");
		clickElement(logoutButtonElm, "Logout Button");
	}

}
