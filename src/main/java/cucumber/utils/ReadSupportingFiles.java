package cucumber.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.jayway.jsonpath.JsonPath;

public class ReadSupportingFiles{
	
	public static String getPropertyValue(String property)
	{
		
		return CucumberReporter.prop.getProperty(property);
	}
	
	public static String getLocator(String locatorName)
	{
		String returnLocatorValue  ="";
		try {
			returnLocatorValue= JsonPath.read(CucumberReporter.jsonFile, "$."+locatorName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnLocatorValue;
	}
	

}
