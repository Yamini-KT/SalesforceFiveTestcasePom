package com.tekarch.salesforce.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tekarch.salesforce.base.BasePage;

public class LoginPage extends BasePage{


	@FindBy(id="username") WebElement usernameElm;
	@FindBy(id="password") WebElement passwordElm;
	@FindBy(id="Login") WebElement loginButtonElm;
	@FindBy(id="error") WebElement loginErrorElm;
	@FindBy(id="rememberUn") WebElement rememberUnElm;
	@FindBy(id="idcard-identity") WebElement unameReloginElm;
	@FindBy(linkText="Forgot Your Password?") WebElement forgotPwdElm;


	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void enterUsername(String username) {
		waitUntilVisible(usernameElm, "Username field");
		clearElement(usernameElm, "Username field");
		enterText(usernameElm, username, "Username field");
	}

	public void enterPassword(String password) {
		clearElement(passwordElm, "Password field");
		enterText(passwordElm, password, "Password field");
	}

	public void clickLoginButton() {
		clickElement(loginButtonElm, "Login Button");
	}

	public String getLoginErrorMsg() {
		waitUntilVisible(loginErrorElm, "Login Error Message");
		return getText(loginErrorElm, "Login Error Message");
	}

	public void selectRememberMe() {
		clickElement(rememberUnElm, "Login Button");
	}

	public void clickForgotPassword() {
		clickElement(forgotPwdElm, "Login Button");
	}

	public String getReloginUsername() {
		waitUntilVisible(unameReloginElm, "Username field");
		return getText(unameReloginElm, "Username");

	}

	public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLoginButton();
	}


}
