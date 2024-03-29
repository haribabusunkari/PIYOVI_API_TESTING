package com.piyovi.common;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.piyovi.constants.HeadlessConstants;
import com.piyovi.util.EmailUtil;
import com.piyovi.util.FileHelper;
import com.piyovi.util.JSONHelper;
import com.piyovi.util.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class BasePage {
	public static ExtentReports extent;
	public static ExtentTest logger;
	public ExtentSparkReporter htmlReporter = null;
	public PropertyReader propertyReader = new PropertyReader();
	public static final String carriersPayLoadPath = "PayLoads/Carriers/";
	public static final String headlessPayLoadPath = "PayLoads/Headless/";
	public static String authToken = "";
	public static String env = "";
	EmailUtil emailUtil = new EmailUtil();
	
	@BeforeSuite
	public void initialization() throws IOException {
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/extentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		System.out.println("Test Environment : " +  System.getProperty("testEnv"));
		htmlReporter.config().setDocumentTitle(System.getProperty("testEnv") + "-PITOVI API Automation Report");
		htmlReporter.config().setReportName(System.getProperty("testEnv") +"-PITOVI API Automation Report");
		htmlReporter.config().setTheme(Theme.DARK);
		env = propertyReader.getApplicationProperty("testEnv");
	}
	
	@AfterMethod
	public void methodEnd(ITestResult testResult, ITestContext context) {
		logger.info("URL : " + RestAssured.baseURI);
		if(testResult.getStatus() == ITestResult.FAILURE)
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel(testResult.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
			logger.fail(testResult.getThrowable());
		}
		else if(testResult.getStatus() == ITestResult.SUCCESS)
		{
			logger.log(Status.PASS, MarkupHelper.createLabel(testResult.getName()+" Test Case PASSED", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.SKIP, MarkupHelper.createLabel(testResult.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
			logger.skip(testResult.getThrowable());
		}
	}

	public Response executePostRequest(String payloadFile) {
		var fileHelper = new FileHelper();
		var payload = fileHelper.getFile(payloadFile);
		var jsonHelper = new JSONHelper();

		var response = given()
				.contentType(ContentType.JSON)
				.body(payload)
				.when()
				.post();

		return response;
	}

	public void verifyTextAndLog(String testName, Object actualValue, Object expectedValue) {
		if(expectedValue.equals(actualValue)) {
			logger.pass(testName + " Pass");
		} else {
			logger.fail(expectedValue + " and " + actualValue + " are not same for Test " + testName);
		}
	}

	public void verifyBooleanStatus(String testName, boolean actualValue, boolean expectedValue) {
		if(expectedValue == actualValue) {
			logger.pass(testName + " Pass");
		} else {
			logger.fail(expectedValue + " and " + actualValue + " are not same for Test " + testName);
		}
	}

	public void generateAuthToken() throws IOException {
		RestAssured.baseURI = propertyReader.getApplicationProperty("baseURL_Headless") + HeadlessConstants.AUTH_URL;
		var fileHelper = new FileHelper();
		var payload = fileHelper.getFile(headlessPayLoadPath + HeadlessConstants.AUTH_PAYLOAD);
		var jsonHelper = new JSONHelper();

		this.authToken = given()
				.contentType(ContentType.JSON)
				.body(payload)
				.when()
				.post().then().extract().path("payload.token");
	}
			
	@AfterSuite
	public void closeAll() throws InterruptedException, IOException {
		extent.flush();
		Thread.sleep(5000);
		emailUtil.sendEmail();
	}
}
