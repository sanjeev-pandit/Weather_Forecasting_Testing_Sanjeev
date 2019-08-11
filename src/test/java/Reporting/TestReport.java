/**
 * TEST REPORT CLASS
 * 
 * @author Sanjeev
 * 
 * This class is the implementation for Extent Reporting
 * 
 * This would create an "html" report post execution and save it under "Reports" folder as
 * 
 * "OpenWeatherTestReport.html"
 * 
 */

package Reporting;

import java.io.IOException;

//import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import TestBase.TestBase;
import Utilities.TestUtil;

public class TestReport extends TestBase {

	static ExtentReports extent = new ExtentReports();
	ExtentTest logger;

	//Set the Report properties
	static {
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "/Reports/OpenWeatherTestReport.html");
		extent.attachReporter(reporter);
		extent.setSystemInfo("Hostname", "OpenWeather");
		extent.setSystemInfo("Browser Name", prop.getProperty("browser"));
		extent.setSystemInfo("OS", "Mac");
	}

	//Create the test case entry in the report
	public void createTest(String testcase) {
		logger = extent.createTest(testcase);
	}
	
	//Make the testcase as passed
	public void scenarioPassed(String testcase) {
		logger.log(Status.PASS, MarkupHelper.createLabel(testcase + " is Passed", ExtentColor.GREEN));
	}

	public void tearDown() {
		extent.flush();
	}

	//Make the testcase failed
	public void scenarioFailed(String testcase) throws IOException {

		logger.log(Status.FAIL, MarkupHelper.createLabel(testcase + " Test Case Failed", ExtentColor.RED));

		String path = TestUtil.getScreenShot(driver);
		logger.log(Status.FAIL, "Screenshot below: ", MediaEntityBuilder.createScreenCaptureFromPath(path).build());

		driver.quit();
	}

}
