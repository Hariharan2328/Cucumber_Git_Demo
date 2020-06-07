package cucumber.pages;

import cucumber.api.java.en.When;
import cucumber.base.CucumberSeleniumBase;



public class HomePage extends CucumberSeleniumBase{
	
	/*HomePage()
	{
		
		if(!getBrowserTitle().startsWith("Leaftaps - TestLeaf Automation Platform"))
		{
			System.out.println("Not Landed to Home page");
			reportStep("This is not a Home page","fail",true);
		}
	}*/
	
	@When("Click on CRM-SFA_link")
	public MyHomePage clickCRMSFA()
	{
		
		
		click(locateElement("linktext","CRM/SFA"),"CRM SFA link");
			
		return new MyHomePage();
	}

}
