package cucumber.pages;

import cucumber.api.java.en.And;
import cucumber.base.CucumberSeleniumBase;


public class MyHomePage extends CucumberSeleniumBase{
	
	/*MyHomePage()
	{
		if(!getBrowserTitle().startsWith("My Home"))
		{
			System.out.println("Not Landed to My Home page");
			reportStep("This is not a My Home page","fail",true);
		}
	}*/
	
	
	@And("Click on Leads_link")
	public MyLeadsPage clickLeadsLink()
	{
		
		click(locateElement("linktext","Leads"),"Leads tab");
		
		return new MyLeadsPage();
		
		
	}

}
