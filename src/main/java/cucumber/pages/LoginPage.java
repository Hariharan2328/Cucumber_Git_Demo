package cucumber.pages;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import cucumber.base.CucumberSeleniumBase;



public class LoginPage extends CucumberSeleniumBase{
	
	@When("Enter user_name \"(.*)\" in Login page")
	public LoginPage enterUsername(String uname) {
		
		clearAndenterText(locateElement("id", "username"),uname);
		
		return this;
	}

	@And("Enter pass_word \"(.*)\" in Login page")
	public LoginPage enterPassword(String pwd) {
		
		clearAndenterText(locateElement("id", "password"),pwd);
		return this;
	}
	
	@When("Click on Login_button")
	public HomePage clickLogin() {
		
		click(locateElement("classname", "decorativeSubmit"),"Login button");
		
		return new HomePage();
	}
	
}
