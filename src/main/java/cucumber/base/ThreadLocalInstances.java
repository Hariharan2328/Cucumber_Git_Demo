package cucumber.base;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class ThreadLocalInstances {
	
	private static final ThreadLocal<RemoteWebDriver> driver = new ThreadLocal();
	private static final ThreadLocal<WebDriverWait> wait  = new ThreadLocal();
	private static final ThreadLocal<ExtentTest> threadLocalTest = new ThreadLocal();
	private static final ThreadLocal<ExtentTest> threadLocalNode = new ThreadLocal();
	
	//Getter and setters of RemoteWebDriver
	//-----------------------
	public void setDriver(String browser)
	{
		if(browser.equalsIgnoreCase("chrome"))
		{
			driver.set(new ChromeDriver());
		}
		else if(browser.equalsIgnoreCase("ie"))
		{
			driver.set(new InternetExplorerDriver());
		}
		
		
	}
	
	public RemoteWebDriver getDriver()
	{
		return driver.get();
	}
	
	
	//Getter and setters of WebDriverWait
	//-----------------------------------
		
	public void setWait()
	{
		wait.set(new WebDriverWait(getDriver(),30));
	}
	
	public WebDriverWait getWait()
	{
		return wait.get();
	}
	
	
	//Getter and setters of test
	//-----------------------
	public void setTest(ExtentTest test)
	{
		threadLocalTest.set(test);
	}
	
	public ExtentTest getTest()
	{
		return threadLocalTest.get();
	}
	
	//Getter and setters of Node
	//-----------------------
	
	public void setNode(ExtentTest node)
	{
		threadLocalNode.set(node);
	}
	
	public ExtentTest getNode()
	{
		return threadLocalNode.get();
	}
}
