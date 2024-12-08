package learning.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir")+"\\Reports\\Result.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Selenium Automation");
		
		ExtentReports extent =  new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Yashna");
		return extent;
	}

}
