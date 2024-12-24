package runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import utilities.CommonFunctions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "stepdefinitions",
    plugin = { "pretty", "html:target/cucumber-reports.html" },
    monochrome = true
)
public class TestRunner {
	
	
	
	@AfterClass
	public static void afterClass() throws InterruptedException{
		CommonFunctions.closeBrowser();
	}
	
}



