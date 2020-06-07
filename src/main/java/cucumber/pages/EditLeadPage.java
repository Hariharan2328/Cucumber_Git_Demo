package cucumber.pages;

import cucumber.base.CucumberSeleniumBase;



public class EditLeadPage extends CucumberSeleniumBase{
	
	
	public EditLeadPage changeCompanyName(String CompanyName)
	{
		
		clearAndenterText(locateElement("xpath","//input[@id='updateLeadForm_companyName']"), CompanyName);
			
		return this;
	}
	
	public ViewLeadsPage clickUpdateButton()
	{
		click(locateElement("xpath","//input[@class='smallSubmit' and @value='Update']"),"Update button");
		
		//rgerger
		return new ViewLeadsPage();
	}
	
	

}
