package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="src/test/java/cucumber",
		glue="PrasenjitDikshit.stepDefination",
		monochrome=true, 
		//tags = "@Regression", 
		plugin= {"pretty","html:target/htmlReports/cucumber.html"}
)
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
