package runner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.base.CucumberSeleniumBase;

@CucumberOptions(features = {"src/test/java/features"},glue = {"cucumber.pages"},monochrome = true)
public class MyRunner extends CucumberSeleniumBase{
	
	 private TestNGCucumberRunner testNGCucumberRunner;

	 @BeforeClass(alwaysRun = true)
	    public void setUpClass() throws Exception {
	        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	       
	        System.out.println(this.getClass());
	    }

	    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
	    public void runScenario(PickleEventWrapper pickleWrapper, CucumberFeatureWrapper featureWrapper) throws Throwable {
	        // the 'featureWrapper' parameter solely exists to display the feature file in a test report
	    	
	        testNGCucumberRunner.runScenario(pickleWrapper.getPickleEvent());
	        
	    }

	    /**
	     * Returns two dimensional array of PickleEventWrapper scenarios with their associated CucumberFeatureWrapper feature.
	     *
	     * @return a two dimensional array of scenarios features.
	     */
	    @DataProvider
	    public Object[][] scenarios() {
	        if (testNGCucumberRunner == null) {
	            return new Object[0][0];
	        }
	        
	        return testNGCucumberRunner.provideScenarios();
	    }

	    @AfterClass(alwaysRun = true)
	    public void tearDownClass() throws Exception {
	        if (testNGCucumberRunner == null) {
	            return;
	        }
	        testNGCucumberRunner.finish();
	    }
	
	
}
