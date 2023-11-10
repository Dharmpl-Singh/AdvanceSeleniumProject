package genericUtilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersImplementation  implements ITestListener{

	ExtentReports report;
	ExtentTest test;
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"--Test Execution Started --");
		
		//@Test execution started
		test=report.createTest(methodName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"--Test PASS --");
		
		//test logs
		test.log(Status.PASS, methodName+"----TEST PASS ---");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"--Test FAILED --");
		System.out.println(result.getThrowable());
		
		//test logs
		test.log(Status.FAIL, methodName+"----- TEST FAIL -------");
		test.log(Status.INFO, result.getThrowable());
		
		
		WebDriverUtility wUtil=new WebDriverUtility();
		JavaUtility jUtil=new JavaUtility();
		String screenshotName = methodName+jUtil.getSystemDateInFormat();
		
		try {
			 String path=wUtil.captureScreenShot(BaseClass.sdriver, screenshotName);
			 test.addScreenCaptureFromPath(path);
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"--Test SKIPPED --");
		System.out.println(result.getThrowable());
		
		//test logs
		test.log(Status.SKIP, methodName+"----- TEST SKIP -------");
		test.log(Status.INFO, result.getThrowable());
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("--Suite Execution Started --");
		
		//Extent Report Configurations
		ExtentSparkReporter htmlReport=new ExtentSparkReporter(".\\ExtentReports\\Report-"+new JavaUtility().getSystemDateInFormat()+".html");
		htmlReport.config().setDocumentTitle("Execution Report");
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setReportName("QCO-SOEAJD-M5-Execution Report");
		
		report=new ExtentReports();
		report.attachReporter(htmlReport);
		report.setSystemInfo("Base Browser", "Edge");
		report.setSystemInfo("Base Platform", "Windows");
		report.setSystemInfo("Base Environment", "Testing");
		report.setSystemInfo("Base URL", "http://localhost:888");
		report.setSystemInfo("Reporter Name", "Dharmpal");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("--Suite Execution Finished --");
		
		//generate the report
		report.flush();
	}
	
	

}
