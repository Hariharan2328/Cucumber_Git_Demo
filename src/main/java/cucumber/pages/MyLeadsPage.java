package cucumber.pages;

import cucumber.api.java.en.And;
import cucumber.base.CucumberSeleniumBase;



public class MyLeadsPage extends CucumberSeleniumBase{
	
	/*MyLeadsPage()
	{
		if(!getBrowserTitle().startsWith("My Leads"))
		{
			System.out.println("Not Landed to My Leads page");
			reportStep("This is not a My Leads page","fail",true);
		}
	}*/
	
	@And("Click on Create_Leads link")
	public CreateLeadsPage clickCreateLead()
	{
		click(locateElement("linktext","Create Lead"),"Create Lead");
		
				
		return new CreateLeadsPage();
	}
	
	
	public FindLeadsPage clickFindLead()
	{
		
		click(locateElement("linktext","Find Leads"), "Find Leads");
		
		return new FindLeadsPage();
	}
	
	

}
