package cucumber.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import cucumber.base.ThreadLocalInstances;

public class CucumberReporter extends ThreadLocalInstances{
	
	public static ExtentReports extent;
	public String testCaseName;
	
	public static Properties prop;
	public static File jsonFile;
	
	File f;
	@BeforeSuite
	public void setUpExtent()
	{
		f=new File("./reports"); 
		if(!f.exists())
		{
			f.mkdir();
		}
		ExtentHtmlReporter reporter = new ExtentHtmlReporter("./reports/extent.html");
		reporter.setAppendExisting(false);
		
		extent= new ExtentReports();
		extent.attachReporter(reporter);
		
		prop = new Properties();
		FileInputStream fip;
		try {
			fip = new FileInputStream("./supportingfiles/config.properties");
			prop.load(fip);
			jsonFile  = new File("./supportingfiles/OR.json");
			
		} catch (IOException e) {
	
			e.printStackTrace();
		}
	
	}
	
	
	public void createTestInExtentReport(String tcName)
	{
		ExtentTest test = extent.createTest(tcName);
		setTest(test);
	}
	
	
	public void createNodeUnderTestInExtentReport(String tcName)
	{
		ExtentTest node = getTest().createNode(tcName);
		setNode(node);
	}

	
	public void reportStep(String desc,String status,boolean screenshot)
	{
		String screenshotFileName;
		MediaEntityModelProvider img = null;
		if(screenshot & !status.equalsIgnoreCase("INFO"))
		{
			Date currDate = new Date();
			
			SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
			
			screenshotFileName = formatter.format(currDate);
			
			try {
				FileUtils.copyFile(getDriver().getScreenshotAs(OutputType.FILE),new File("./reports/screenshots/"+screenshotFileName+".jpg"));
				img = MediaEntityBuilder.createScreenCaptureFromPath("./../reports/screenshots/"+screenshotFileName+".jpg").build();
			}  catch (IOException e) {
				
				e.printStackTrace();
			}
			
			
		}
		
		if(status.equalsIgnoreCase("PASS"))
		{
			getNode().pass(desc, img);
		}
		else if(status.equalsIgnoreCase("FAIL"))
		{
			getNode().fail(desc, img);
		}
		
	}
	
	@AfterSuite
	public void tearDownExtent()
	{
		extent.flush();
	}
	
	
}
