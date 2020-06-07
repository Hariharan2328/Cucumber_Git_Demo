package cucumber.pages;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import cucumber.base.CucumberSeleniumBase;


public class CreateLeadsPage extends CucumberSeleniumBase{
	
	/*CreateLeadsPage()
	{
		if(!getBrowserTitle().startsWith("Create Lead"))
		{
			System.out.println("Not Landed to Create Lead page");
			reportStep("This is not a Create Lead page","fail",true);
		}
	}*/
	
	@When("Enter company_name \"(.*)\" in CreateLeadPage")
	public CreateLeadsPage enterCompanyName(String cname)
	{
		
		clearAndenterText(locateElement("id","createLeadForm_companyName"), cname);
		return this;
	}

	@And("Enter first_name \"(.*)\" in CreateLeadPage")
	public CreateLeadsPage enterFirstName(String fname)
	{
		
		clearAndenterText(locateElement("id","createLeadForm_firstName"), fname);
		return this;
	}
	
	@And("Enter last_name \"(.*)\" in CreateLeadPage")
	public CreateLeadsPage enterLastName(String lname)
	{
		
		clearAndenterText(locateElement("id","createLeadForm_lastName"), lname);
		return this;
	}
	
	@And("click on Create_button")
	public ViewLeadsPage clickCreateLeadButton()
	{
		
		click(locateElement("xpath","//input[@name = 'submitButton']"),"Click button");
		
		
		return new ViewLeadsPage();
	}

}
