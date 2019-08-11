/**
 * TEST RUNNER CLASS
 * 
 * @author Sanjeev
 * 
 * The Test Runner class helps you run specific Feature files
 * 
 * You can also map the feature files with the Step Definitions files where
 * the implementations have been made for the scenarios
 * 
 * Also you can declare different Cucumber options
 * 
 */
package Runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

// RunWith is a JUnit annotation to specify to run the test case as a Cucumber test
@RunWith(Cucumber.class)

//Cucumber Options user to map feature and step definitions and declare various execution options
@CucumberOptions(
		
		//Mentions the feature file location
		features = "src/test/java/Features/HomePage.feature",
		
		//maps the feature file to step definition folder location
		glue={"StepDefinitions"},
		
		//runs the test without executing to check if there is 1-1 mapping with features and step definitions
		dryRun=false,
		
		//monochrome gives a user friendly output on the console
		monochrome=true,
		
		//tags lets you select which scenarios to run, they are declared in feature file
		tags = {"@Test1,@Test2,@Test3"},
		
		//this will ensure that the execution will only happen if all the step definitions are there for all features
		strict = true
		)
 
public class TestRunner {
 
}