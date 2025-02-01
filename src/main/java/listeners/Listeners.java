package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import utilities.ExtentReport;

public class Listeners extends Base implements ITestListener {
	
	WebDriver driver = null;
	ExtentReports extentreport= ExtentReport.getExtentReport();
	ExtentTest extenttest;
	
	ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {

		String testname= result.getName();
		extenttest=extentreport.createTest(testname);
		
		//we have to convert this extenttest into thread safe mode so fot that to happen
		extentTestThread.set(extenttest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		String testname= result.getName();
//		extenttest.log(Status.PASS,testname+" got passed");

		extentTestThread.get().log(Status.PASS,testname+" got passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// code

		String testMethodName = result.getName();
//		extenttest.fail(result.getThrowable());
		
		extentTestThread.get().fail(result.getThrowable());

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try{
			String screenshotFilePath = takeScreenshots(testMethodName,(ITestResult) driver);
			extentTestThread.get().addScreenCaptureFromPath(screenshotFilePath, testMethodName);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

//	private void takeScreenshots(String testMethodName, WebDriver driver) {
//		// TODO Auto-generated method stub
//
//	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

		//with this flush method all the things we have provided the coding for now will be added to the report with this flush method that means it will flush all the things into the report
		extentreport.flush();
	}

}
