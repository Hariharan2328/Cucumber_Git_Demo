package cucumber.pages;

import cucumber.api.java.en.Then;
import cucumber.base.CucumberSeleniumBase;

public class ViewLeadsPage extends CucumberSeleniumBase{
	
	/*public ViewLeadsPage()
	{
		if(!getBrowserTitle().startsWith("View Lead"))
		{
			System.out.println("Not Landed to View Lead page");
			reportStep("This is not a View Lead page","fail",true);
		}
	}*/
	
	@Then("Verify View_Leads page")
	public void fetchLeadId()
	{
		String temp = getElementText(locateElement("id","viewLead_companyName_sp"), "Company name");
		
		temp = temp.replaceAll("[\\D]", "");
		int intLeadID = Integer.parseInt(temp);
		System.out.println("Successfully created Lead - "+intLeadID);

	}
	
	
	public EditLeadPage clickEditButton()
	{
		
		click(locateElement("linktext","Edit"),"Edit link");
		
		return new EditLeadPage();
	}
	
	public MyLeadsPage clickDeleteButton()
	{
		//driver.findElementByLinkText("Delete").click();
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		return new MyLeadsPage();
	}
	
	
	public ViewLeadsPage verifyEditedCompanyName(String companyName)
	{
		String strCompanyname = getElementText(locateElement("xpath","//span[@id='viewLead_companyName_sp']"), "View Lead");
		
		if(strCompanyname.contains(companyName)) 
		
		{
			System.out.println("Edit done successfully");
		}
		
		
		return this;
	
	}

}
