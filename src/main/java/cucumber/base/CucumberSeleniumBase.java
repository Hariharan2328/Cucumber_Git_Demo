package cucumber.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import cucumber.utils.CucumberReporter;

public class CucumberSeleniumBase extends CucumberReporter{
	
	public void startApp(String browser,String url)
	{
		//comment adding
		try {
			
			
			if(browser.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				
				setDriver(browser);
			}
			else if(browser.equalsIgnoreCase("ie"))
			{
				
			}
			else if(browser.equalsIgnoreCase("firefox"))
			{
				
			}
			
			getDriver().get(url);
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			
			reportStep(browser +" Browser is opened successfully","pass",true);
		} catch (WebDriverException e) {
			reportStep("Browser is not opened","fail",true);
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	public WebElement locateElement(String locator,String locatorValue)
	{
		try {
			switch(locator)
			{
				case "id": return getDriver().findElementById(locatorValue);
				case "name": return getDriver().findElementByName(locatorValue);
				case "classname": return getDriver().findElementByClassName(locatorValue);
				case "linktext": return getDriver().findElementByLinkText(locatorValue);
				case "partiallinktext": return getDriver().findElementByPartialLinkText(locatorValue);
				case "tagname": return getDriver().findElementByTagName(locatorValue);
				case "xpath": return getDriver().findElementByXPath(locatorValue);
				case "css": return getDriver().findElementByCssSelector(locatorValue);
				
			}
		} catch (NoSuchElementException e) {
			reportStep("Element is not found","fail",true);
			e.printStackTrace();
		}
		catch (WebDriverException e) {
			reportStep("Unknown Exception when trying to locate element","fail",true);
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	public List<WebElement> locateElements(String locator,String locatorValue)
	{
		try {
			switch(locator)
			{
				case "id": return getDriver().findElementsById(locatorValue);
				case "name": return getDriver().findElementsByName(locatorValue);
				case "classname": return getDriver().findElementsByClassName(locatorValue);
				case "linktext": return getDriver().findElementsByLinkText(locatorValue);
				case "partiallinktext": return getDriver().findElementsByPartialLinkText(locatorValue);
				case "tagname": return getDriver().findElementsByTagName(locatorValue);
				case "xpath": return getDriver().findElementsByXPath(locatorValue);
				case "css": return getDriver().findElementsByCssSelector(locatorValue);
				
			}
		} catch (NoSuchElementException e) {
			reportStep("Element is not found","fail",true);
			e.printStackTrace();
		}
		catch (WebDriverException e) {
			reportStep("Unknown Exception when trying to locate element","fail",true);
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	//----------------------------------------------------------------Alert specific methods------------------------------
	//-------------------------------------------------------------------------------------------------------------------
	
	public void switchToAlert()
	{
		try {
			getDriver().switchTo().alert();
		} catch (NoAlertPresentException e) {
			reportStep("No alert is present","fail",true);
			e.printStackTrace();
		}
	}
	
	public String getAlertText()
	{
		String text ="";
		try {
			Alert alert = getDriver().switchTo().alert();
			text = alert.getText();
			
		} catch (NoAlertPresentException e) {
			reportStep("No alert is present","fail",true);
			e.printStackTrace();
		}
		
		return text;
	}
	
	public void enterTextInAlert(String text)
	{
		
		try {
			Alert alert = getDriver().switchTo().alert();
			alert.sendKeys(text);
			
		} catch (NoAlertPresentException e) {
			reportStep("No alert is present","fail",true);
			e.printStackTrace();
		}
		
		
	}
	
	public void acceptAlert()
	{
		
		try {
			Alert alert = getDriver().switchTo().alert();
			alert.accept();
			
		} catch (NoAlertPresentException e) {
			reportStep("No alert is present","fail",true);
			e.printStackTrace();
		}
		
		
	}
	
	public void dismissAlert()
	{
		
		try {
			Alert alert = getDriver().switchTo().alert();
			alert.dismiss();
			
		} catch (NoAlertPresentException e) {
			reportStep("No alert is present","fail",true);
			e.printStackTrace();
		}
		
		
	}
	
	//----------------------------------------------------------------Frames specific methods------------------------------
	//-------------------------------------------------------------------------------------------------------------------
		
	
	public void switchToFrameByNameID(String nameID)
	{
		try {
			getDriver().switchTo().frame(nameID);
		} catch (NoSuchFrameException e) {
			reportStep("No frame is present with name or ID - "+nameID,"fail",true);
			e.printStackTrace();
		}
	}
	
	public void switchToFrameByIndex(int index)
	{
		try {
			getDriver().switchTo().frame(index);
		} catch (NoSuchFrameException e) {
			reportStep("No frame is present with index - "+index,"fail",true);
			e.printStackTrace();
		}
	}
	
	public void switchToFrameByIndex(WebElement element)
	{
		try {
			getDriver().switchTo().frame(element);
		} catch (NoSuchFrameException e) {
			reportStep("No frame is present with element - "+element,"fail",true);
			e.printStackTrace();
		}
	}
	
	
	public void switchToDefaultContent()
	{
		try {
			getDriver().switchTo().defaultContent();
		} catch (WebDriverException e) {
			
			e.printStackTrace();
		}
	}
	
	
	//----------------------------------------------------------------Window specific methods------------------------------
	//-------------------------------------------------------------------------------------------------------------------
	
	
	
	public void switchToWindowByTitle(String title)
	{
		
		try {
			Set<String> windowHandles = getDriver().getWindowHandles();
			List<String> lst_Windows = new ArrayList(windowHandles);
			
			for(String each:lst_Windows)
			{
			
				getDriver().switchTo().window(each);
				
				if(getDriver().getTitle().equalsIgnoreCase(title))
				{
					reportStep("Switched to window with title - "+title,"pass",true);
					break;
				}
			}
		} catch (NoSuchWindowException e) {
			reportStep("No such window with title - "+title,"fail",true);
			e.printStackTrace();
		}
		catch(WebDriverException e)
		{
			reportStep("Unknown exception when switching window with title - "+title,"fail",true);
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	public void switchToWindowByIndex(int index)
	{
		
		try {
			Set<String> windowHandles = getDriver().getWindowHandles();
			List<String> lst_Windows = new ArrayList(windowHandles);
			
			getDriver().switchTo().window(lst_Windows.get(index));
			
			
		} catch (NoSuchWindowException e) {
			reportStep("No such window with index - "+index,"fail",true);
			e.printStackTrace();
		}
		catch(WebDriverException e)
		{
			reportStep("Unknown exception when switching window with index - "+index,"fail",true);
			e.printStackTrace();
		}
		
		
	}
	
	
	
	//WebElement specific methods
	//----------------------------
	
	public void clearAndenterText(WebElement ele,String text)
	{
		try {
			Thread.sleep(500);
			setWait();
			getWait().until(ExpectedConditions.visibilityOf(ele));
			ele.clear();
			ele.sendKeys(text);
			reportStep("Entered text - "+text,"pass",true);
		} catch (StaleElementReferenceException e) {
			reportStep("Element is stale now when entering text -"+text,"fail",true);
			e.printStackTrace();
		}
		catch (ElementNotInteractableException e) {
			reportStep("Element is not interactable now when entering text -"+text,"fail",true);
			e.printStackTrace();
		}
		catch(InterruptedException e)
		{
			
		}
		catch (WebDriverException e) {
			reportStep("Unknown exception when entering text -"+text,"fail",true);
			e.printStackTrace();
		}
		
		
	}
	
	
	public void clearText(WebElement ele)
	{
		try {
			Thread.sleep(500);
			setWait();
			getWait().until(ExpectedConditions.visibilityOf(ele));
			ele.clear();
		
			reportStep("Cleared text ","pass",true);
		} catch (StaleElementReferenceException e) {
			reportStep("Element is stale now when clearing text","fail",true);
			e.printStackTrace();
		}
		catch (ElementNotInteractableException e) {
			reportStep("Element is not interactable now when clearing text","fail",true);
			e.printStackTrace();
		}
		catch(InterruptedException e)
		{
			
		}
		catch (WebDriverException e) {
			reportStep("Unknown exception when clearing text ","fail",true);
			e.printStackTrace();
		}
	}
	
	public void click(WebElement ele,String elementContent)
	{
		try {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setWait();
			getWait().until(ExpectedConditions.elementToBeClickable(ele));
			reportStep("Clicking on Element "+elementContent,"pass",true);
			
			ele.click();
		
		} catch (StaleElementReferenceException e) {
			reportStep("Element is stale now when clicking element "+elementContent,"fail",true);
			e.printStackTrace();
		}
		catch (ElementClickInterceptedException e) {
			reportStep("Element is not clickable due to click intercepted when trying to click element "+elementContent,"fail",true);
			e.printStackTrace();
		}
		catch (ElementNotInteractableException e) {
			reportStep("Element is not interactable now when clicking element "+elementContent,"fail",true);
			e.printStackTrace();
		}
		
		catch (WebDriverException e) {
			reportStep("Unknown exception when when clicking element "+elementContent,"fail",true);
			e.printStackTrace();
		}
	}
	
	
	public void clickUsingActions(WebElement ele,String elementContent)
	{
		try {
			Thread.sleep(2000);
			setWait();
			getWait().until(ExpectedConditions.elementToBeClickable(ele));
			
			reportStep("Clicking on Element "+elementContent,"pass",true);
			Actions action = new Actions(getDriver());
			action.moveToElement(ele).click().build().perform();
			
		
		} catch (StaleElementReferenceException e) {
			reportStep("Element is stale now when clicking element "+elementContent,"fail",true);
			e.printStackTrace();
		}
		catch (ElementNotInteractableException e) {
			reportStep("Element is not interactable now when clicking element "+elementContent,"fail",true);
			e.printStackTrace();
		}
		catch(InterruptedException e)
		{
			
		}
		catch (WebDriverException e) {
			reportStep("Unknown exception when when clicking element "+elementContent,"fail",true);
			e.printStackTrace();
		}
	}
	
	
	public void selectDropDownByVisibleText(WebElement ele,String text,String elementContent)
	{
		try {
			Thread.sleep(500);
			setWait();
			getWait().until(ExpectedConditions.visibilityOf(ele));
			Select slt = new Select(ele);
			slt.selectByVisibleText(text);
			reportStep("Drop down value - "+text+"is selected from "+elementContent,"pass",true);
		} catch (StaleElementReferenceException e) {
			reportStep("Element is stale now when selecting element "+elementContent,"fail",true);
			e.printStackTrace();
		}
		catch (ElementNotInteractableException e) {
			reportStep("Element is not interactable now when selecting element "+elementContent,"fail",true);
			e.printStackTrace();
		}
		catch(InterruptedException e)
		{
			
		}
		catch (WebDriverException e) {
			reportStep("Unknown exception when when selecting element "+elementContent,"fail",true);
			e.printStackTrace();
		}
	}
	
	public void selectDropDownByValue(WebElement ele,String value,String elementContent)
	{
		try {
			Thread.sleep(500);
			setWait();
			getWait().until(ExpectedConditions.visibilityOf(ele));
			Select slt = new Select(ele);
			slt.selectByValue(value);
			reportStep("Drop down value - "+value+"is selected from "+elementContent,"pass",true);
		} catch (StaleElementReferenceException e) {
			reportStep("Element is stale now when selecting element "+elementContent,"fail",true);
			e.printStackTrace();
		}
		catch (ElementNotInteractableException e) {
			reportStep("Element is not interactable now when selecting element "+elementContent,"fail",true);
			e.printStackTrace();
		}
		catch(InterruptedException e)
		{
			
		}
		catch (WebDriverException e) {
			reportStep("Unknown exception when when selecting element "+elementContent,"fail",true);
			e.printStackTrace();
		}
	}
	
	public void selectDropDownByIndex(WebElement ele,int index,String elementContent)
	{
		try {
			Thread.sleep(500);
			setWait();
			getWait().until(ExpectedConditions.visibilityOf(ele));
			Select slt = new Select(ele);
			slt.selectByIndex(index);
			reportStep("Drop down value with index - "+index+"is selected from "+elementContent,"pass",true);
		} catch (StaleElementReferenceException e) {
			reportStep("Element is stale now when selecting element "+elementContent,"fail",true);
			e.printStackTrace();
		}
		catch (ElementNotInteractableException e) {
			reportStep("Element is not interactable now when selecting element "+elementContent,"fail",true);
			e.printStackTrace();
		}
		catch(InterruptedException e)
		{
			
		}
		catch (WebDriverException e) {
			reportStep("Unknown exception when when selecting element "+elementContent,"fail",true);
			e.printStackTrace();
		}
	}
	
	public String getElementText(WebElement ele,String elementContent)
	{
		String text = "";
		try {
			Thread.sleep(500);
			setWait();
			getWait().until(ExpectedConditions.visibilityOf(ele));
			text = ele.getText();
			reportStep("Fetched text from Element "+elementContent,"pass",true);
		} catch (StaleElementReferenceException e) {
			reportStep("Element is stale now when getting text from "+elementContent,"fail",true);
			e.printStackTrace();
		}
		catch (ElementNotInteractableException e) {
			reportStep("Element is not interactable now getting text from  "+elementContent,"fail",true);
			e.printStackTrace();
		}
		catch(InterruptedException e)
		{
			
		}
		catch (WebDriverException e) {
			reportStep("Unknown exception getting text from  "+elementContent,"fail",true);
			e.printStackTrace();
		}
		
		return text;
	}
	
	
	public boolean isElementDisplayed(WebElement ele,String elementContent)
	{
		boolean bool = false;
		try {
			Thread.sleep(500);
			setWait();
			getWait().until(ExpectedConditions.visibilityOf(ele));
			bool = ele.isDisplayed();
			reportStep("Element "+elementContent+" is displayed","pass",true);
		} catch (StaleElementReferenceException e) {
			reportStep("Element is stale now when getting text from "+elementContent,"fail",true);
			e.printStackTrace();
		}
		catch (ElementNotInteractableException e) {
			reportStep("Element is not interactable now getting text from  "+elementContent,"fail",true);
			e.printStackTrace();
		}
		catch(InterruptedException e)
		{
			
		}
		catch (WebDriverException e) {
			reportStep("Unknown exception getting text from  "+elementContent,"fail",true);
			e.printStackTrace();
		}
		
		return bool;
	}
	
	
	public String getBrowserTitle()
	{
		String title="";
		try {
			title = getDriver().getTitle();
		} catch (WebDriverException e) {
			reportStep("Unknown exception getting title","fail",true);
			e.printStackTrace();
		}
		
		return title;
	}
	
	
	
	
	public void closeBrowser()
	{
		getDriver().close();
	}
	
	
	
	
	
	
	
}
