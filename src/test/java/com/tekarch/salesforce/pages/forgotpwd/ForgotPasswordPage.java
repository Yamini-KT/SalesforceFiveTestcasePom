package com.tekarch.salesforce.pages.forgotpwd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tekarch.salesforce.base.BasePage;

public class ForgotPasswordPage extends BasePage {


	@FindBy(id="un") WebElement usernameElm;
	@FindBy(id="continue") WebElement continueBtnElm;
	@FindBy(xpath="//*[@id=\"header\"]") WebElement forgotPwdMsgElm;

	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
	}

	public void enterUsername(String username) {
		waitUntilVisible(usernameElm, "Username field");
		clearElement(usernameElm, "Username field");
		enterText(usernameElm, username, "Username field");
	}

	public void clickContinueButton() {
		clickElement(continueBtnElm, "Forgot Password Continue Button");
	}

	public String getForgotPwdFormMsg() {
		waitUntilVisible(forgotPwdMsgElm, "Forgot Message");
		return getText(forgotPwdMsgElm, "Forgot Message");
	}


}
