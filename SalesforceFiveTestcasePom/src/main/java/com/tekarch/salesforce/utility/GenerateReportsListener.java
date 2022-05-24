package com.tekarch.salesforce.utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.tekarch.salesforce.base.BaseTest;


public class GenerateReportsListener extends BaseTest implements ITestListener {


	ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;


	public void onStart(ITestContext context) {
		htmlReporter = new ExtentHtmlReporter(Constants.GENERATE_REPORT_PATH);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Salesforce");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "Yamini");

		htmlReporter.config().setDocumentTitle("Test Execution Report");
		htmlReporter.config().setReportName("SalesForce tests");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
	}


	public void onTestStart(ITestResult result) {
		logger = extent.createTest(result.getMethod().getMethodName());
	}


	public void onTestSuccess(ITestResult result) {
		logger.log(Status.PASS, MarkupHelper.createLabel(result.getMethod().getMethodName() + " passed the Test", ExtentColor.GREEN));
	}


	public void onTestFailure(ITestResult result) {
		if (null != result.getThrowable()) {
			logger.log(Status.ERROR, result.getThrowable().getMessage());
		}

		logger.log(Status.FAIL, MarkupHelper.createLabel(result.getMethod().getMethodName() + " failed the Test", ExtentColor.RED));

		String methodName=result.getName().toString().trim();

		takeScreenShot(methodName);
	}


	public void onTestSkipped(ITestResult result) {
		logger.log(Status.SKIP, MarkupHelper.createLabel(result.getMethod().getMethodName() + " skipped the Test", ExtentColor.RED));
	}


	public void onFinish(ITestContext context) {
		extent.flush();
	}


	public void logTestInfo(String message) {

		logger.log(Status.INFO, message);
	}


	public void takeScreenShot(String MethodName) {
		System.out.println(getDriver().getTitle());
		TakesScreenshot ts = (TakesScreenshot) getDriver();


		File srcFile = ts.getScreenshotAs(OutputType.FILE);

		try {
			File destFile = new File(Constants.SCREENSHOT_PATH + Constants.CURRENT_EXECUTION_TIMESTAMP + "/" + MethodName + ".jpg");

			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
