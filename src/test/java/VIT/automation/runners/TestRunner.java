package VIT.automation.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		features="classpath:features",   //to tell cucumber where is ur feature file(Path of Feature folder which hold feature file)
		glue="VIT.automation.stepdefs",   // to tell cucumber where is ur step def code(Path of StepDefinition file)
		tags="@Search",
		plugin = {"pretty",   // to generate reports
	            "html:target/html/htmlreport.html", //cucumber report
	                                                // in target html folder and in that filename.html
	            "json:target/json/file.json",
	            },
		     monochrome =true, //foreign(garbege)character remove from console
		     publish=true, //if we have to publishing a report thats why is true ...for online cucumber report
		    dryRun=false // to tell whether to test run(true) or actual run(false)
		    
//		    dryRun=true //to generate skeleton 
//		    dryRun=false // when we wish to run
		
		)

public class TestRunner {
	 //Class will be Emptity.
    //Nothing goes here
    //So do not get confused

}
//notes:
//1)┌──────────────────────────────────────────────────────────────────────────┐
//  │ View your Cucumber Report at:                                            │
//  │ https://reports.cucumber.io/reports/3f0f3bc1-4627-41cb-b068-d951d40b6e9a │
//  │                                                                          │
//  │ This report will self-destruct in 24h unless it is claimed or deleted.   │
//  └──────────────────────────────────────────────────────────────────────────┘

//this link get from console ..cucumber online report
//2)feature file is test case,apart from writing test case in Excel or in other 
// we use to write feature file as an BDD fasion
