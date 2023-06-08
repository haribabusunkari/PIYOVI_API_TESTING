package com.piyovi.common;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;



public class BasePage {
	public ExtentReports extent;
	public static ExtentTest logger;
	public ExtentSparkReporter htmlReporter = null;
	
	@BeforeSuite
	public void initialization() {
		System.out.println("Before Suite");
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\extentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}
	
	@AfterMethod
	public void methodEnd(ITestResult testResult, ITestContext context) {
		switch (testResult.getStatus()) {
			case ITestResult.SUCCESS:
				break;
	
			case ITestResult.FAILURE:
				break;
			}
	}
			
	@AfterSuite
	public void closeAll() {
		extent.flush();
	}
}
