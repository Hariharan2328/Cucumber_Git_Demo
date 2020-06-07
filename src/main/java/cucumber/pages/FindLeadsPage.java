package cucumber.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import cucumber.base.CucumberSeleniumBase;

public class FindLeadsPage extends CucumberSeleniumBase{
	
	/*public FindLeadsPage()
	{
		PageFactory.initElements(getDriver(), this);
	}*/
	
	public FindLeadsPage enterLeadID(int LeadID)
	{
		
		clearAndenterText(locateElement("xpath","//input[contains(@class,'x-form-text x-form-field') and @name='id']"), ""+LeadID);
		
		return this;
	}
	
	public FindLeadsPage clickFindLeadsButton()
	{
		click(locateElement("xpath","//button[text()='Find Leads']"), "Find Leads button");
		
		return this;
	}
	
	
	@FindBy(how = How.XPATH, using = "(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]") WebElement ele;
	public ViewLeadsPage clickLeadIDLink_In_SearchResult() throws InterruptedException
	{
		
		clickUsingActions(ele,"Lead id from Search result");
			
		
		return new ViewLeadsPage();
	}
	
	/*public FindLeadsPage verifyNoRecordsText()
	{
		if(driver.findElementByXPath("//div[text()='No records to display']").isDisplayed())
		{
			System.out.println("Record - "+intLeadID+"successfully deleted");
			
		}
		
		return this;
	}
	*/
	

}
