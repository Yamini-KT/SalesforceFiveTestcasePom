package com.tekarch.salesforce.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.tekarch.salesforce.base.BaseTest;
import com.tekarch.salesforce.pages.login.LoginPage;
import com.tekarch.salesforce.utility.CommonUtilities;

public class SFLoginTest extends BaseTest{
	
	
	private static final String userName=CommonUtilities.getApplicationProperty("username");
	private static final String password=CommonUtilities.getApplicationProperty("password");

	@Test
	public void logintest_tc1() {
		try {

			String expected = "Please enter your password.";
			LoginPage loginpage = new LoginPage(getDriver());
			loginpage.login(userName, password);

			String actual = loginpage.getLoginErrorMsg();

			Assert.assertEquals(actual, expected);
		}
		catch(Exception e) {
			Assert.assertFalse(true, e.getMessage());
		}
	}

}
