package com.tekarch.salesforce.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.tekarch.salesforce.base.BaseTest;
import com.tekarch.salesforce.pages.forgotpwd.ForgotPasswordPage;
import com.tekarch.salesforce.pages.home.HomePage;
import com.tekarch.salesforce.pages.login.LoginPage;
import com.tekarch.salesforce.utility.CommonUtilities;

public class AutomationScripts extends BaseTest {

	private static final String username=CommonUtilities.getApplicationProperty("username");
	private static final String password=CommonUtilities.getApplicationProperty("password");

	@Test (priority=0)
	public void logintest_tc1() {
		try {

			String expected = "Please enter your password.";
			LoginPage loginpage = new LoginPage(getDriver());
			loginpage.login(username, "");
			String actual = loginpage.getLoginErrorMsg();
			Assert.assertEquals(actual, expected);
		}
		catch(Exception e) {
			Assert.assertFalse(true, e.getMessage());
		}
	}

	@Test(priority=1)
	public void logintest_tc2(){
		try {
			String expected = "Home";
			LoginPage loginpage = new LoginPage(getDriver());
			loginpage.login(username, password);			
			HomePage homepage = new HomePage(getDriver());
			String actual = homepage.getHomeTabText();
			homepage.logout();
			Assert.assertEquals(actual, expected);

		}
		catch(Exception e) {
			Assert.assertFalse(true, e.getMessage());
		}
	}

	@Test(priority=2)
	public void logintest_tc3(){
		try {

			String expected = username;
			LoginPage loginpage = new LoginPage(getDriver());
			loginpage.enterUsername(username);
			loginpage.enterPassword(password);
			loginpage.selectRememberMe();
			loginpage.clickLoginButton();

			HomePage homepage = new HomePage(getDriver());
			homepage.logout();

			String actual=loginpage.getReloginUsername();
			Assert.assertEquals(actual, expected);

		}
		catch(Exception e) {
			Assert.assertFalse(true, e.getMessage());
		}
	}

	@Test(priority=3)
	public void logintest_tc4a(){
		try {

			String expected = "Check Your Email";
			LoginPage loginpage = new LoginPage(getDriver());
			loginpage.clickForgotPassword();
			ForgotPasswordPage forgotpage = new ForgotPasswordPage(getDriver());
			forgotpage.enterUsername(username);
			forgotpage.clickContinueButton();
			String actual = forgotpage.getForgotPwdFormMsg();
			Assert.assertEquals(actual, expected);

		}
		catch(Exception e) {
			Assert.assertFalse(true, e.getMessage());
		}
	}


	@Test(priority=4)
	public void logintest_tc4b(){
		try {

			String expected = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
			LoginPage loginpage = new LoginPage(getDriver());	
			loginpage.login("12345", "12345");
			String actual = loginpage.getLoginErrorMsg();
			Assert.assertEquals(actual, expected);

		}
		catch(Exception e) {
			Assert.assertFalse(true, e.getMessage());
		}
	}

	@Test(priority=5)
	public void logintest_tc5(){
		try {
			String expected = "My Profile";
			LoginPage loginpage = new LoginPage(getDriver());
			loginpage.login(username, password);			
			HomePage homepage = new HomePage(getDriver());


			String actual = homepage.getUserNavMenuItems();

			homepage.logout();
			Assert.assertTrue(actual.contains(expected));

		}
		catch(Exception e) {
			Assert.assertFalse(true, e.getMessage());
		}
	}

}
