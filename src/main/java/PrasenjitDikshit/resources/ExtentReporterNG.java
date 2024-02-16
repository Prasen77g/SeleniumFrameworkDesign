package PrasenjitDikshit.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	
	public static ExtentReports getReportObject()
	{
		//Set path for Storing the report:-
				String path = System.getProperty("user.dir")+"\\reports\\index.html";
				ExtentSparkReporter reporter = new ExtentSparkReporter(path);
				//Set Report Name:-
				reporter.config().setReportName("Web Automation Result");
				//Set Document Name:-
				reporter.config().setDocumentTitle("Test Results");
				
				ExtentReports extent = new ExtentReports();
				extent.attachReporter(reporter);
				extent.setSystemInfo("Tester", "Prasenjit Dikshit");
				return extent;
				
				
	}

}
