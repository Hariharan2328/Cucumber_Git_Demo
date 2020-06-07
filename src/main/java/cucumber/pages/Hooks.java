package cucumber.pages;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.base.CucumberSeleniumBase;
import cucumber.utils.ReadSupportingFiles;

public class Hooks extends CucumberSeleniumBase{
	
	@Before
	public void preCondition(Scenario sc)
	{
		System.out.println(sc.getName());
		
		testCaseName = sc.getId().split(";")[0];
		System.out.println(sc.getId());
		createTestInExtentReport(testCaseName);
		createNodeUnderTestInExtentReport(testCaseName);
		startApp(ReadSupportingFiles.getPropertyValue("browser"),ReadSupportingFiles.getPropertyValue("url"));
		
	}
	
	
	@After
	public void tearDown()
	{
		closeBrowser();
	}

}
