
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener
{
	
   	public ExtentSparkReporter sparkReporter;
   	public ExtentReports extent;
   	public ExtentTest test;
   	
   	public void onStart(ITestContext context) {
   		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/myReport.html");
   		
   		
   		sparkReporter.config().setDocumentTitle("Automation Report");
   		sparkReporter.config().setReportName("Functional Testing");
   		sparkReporter.config().setTheme(Theme.DARK);
   		
   		extent = new ExtentReports();
   		extent.attachReporter(sparkReporter);
   		
   		
   		extent.setSystemInfo("computer Name", "locahost");
   		extent.setSystemInfo("environment", "QA");
   		extent.setSystemInfo("Tester Name", "Aayush");
   		extent.setSystemInfo("OS","Window11");
   		extent.setSystemInfo("Browser name","Chrome");

   	}
   	
   	public void onTestSuccess(ITestResult result) {
   		test = extent.createTest(result.getName());// create a new entry in the report
   		test.log(Status.PASS, "Test case PASSED is:" + result.getName());// update status p/f/s
   	}
   	
   	public void onTestFailure(ITestResult result) {
   		test = extent.createTest(result.getName());// create a new entry in the report
   		test.log(Status.FAIL, "Test case FAILED is:" + result.getName());// update status p/f/s
   		test.log(Status.FAIL, "Test case fAILED cause is:" + result.getThrowable());// update status p/f/s

   	}
   	
 	public void onTestSkipped(ITestResult result) {
   		test = extent.createTest(result.getName());// create a new entry in the report
   		test.log(Status.SKIP, "Test case SKIPPED is:" + result.getName());// update status p/f/s
   	}
   	
public void onFinish(ITestContext context)   	{
	extent.flush();

}
   	
}