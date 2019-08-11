/**
 * STEP DEFINITION CLASS
 * 
 * This class includes the implementation for all the scenarios mentioned
 * in the feature file
 * 
 * @author Sanjeev
 * 
 */

package StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Pages.HomePage;
import Reporting.TestReport;
import TestBase.TestBase;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;
import static io.restassured.RestAssured.*;

public class HomePageStepDefinitions extends TestBase {

	//Declare all the variables
	WebDriver driver;
	WebElement searchCityText;
	WebElement btnSearch;
	Response res;
	
	//Create an instance of HomePage class to call the HomePage actions
	HomePage homePage = new HomePage();
	
	//Create an instance of TestReport class to create an Extent Report
	TestReport testReport = new TestReport();

	//Below are the Given When Then implementations
	@Given("^User is on the OpenWeather homepage$")
	public void user_is_on_the_OpenWeather_homepage() throws Throwable {
		TestBase.initialization();
	}

	@Then("^User should be able to verify \"([^\"]*)\"$")
	public void user_should_be_able_to_verify(String arg1) throws Throwable {
		testReport.createTest("Verify Title");
		String actualTitle = homePage.fetchWindowTitle();
		Assert.assertEquals(actualTitle, arg1);
		testReport.scenarioPassed("Verify Title");
	}

	@Then("^city name text field is displayed and enabled$")
	public void city_name_text_field_is_displayed_and_enabled() throws Throwable {
		testReport.createTest("Verify Search Text Box");
		Assert.assertTrue(homePage.verifySearchBox());
		testReport.scenarioPassed("Verify Search Text Box");
	}

	@Then("^Search button is displayed and enabled$")
	public void search_button_is_displayed_and_enabled() throws Throwable {
		testReport.createTest("Verify Search Button");
		Assert.assertTrue(homePage.verifySearchButton());
		testReport.scenarioPassed("Verify Search Button");
	}

	@When("^User enters an invalid \"([^\"]*)\" name$")
	public void user_enters_an_invalid_name(String arg1) throws Throwable {
		homePage.searchCity(arg1);
	}

	@When("^clicks on the search button$")
	public void clicks_on_the_search_button() throws Throwable {
		homePage.clickSearch();
	}

	@Then("^\"([^\"]*)\" is shown on the website$")
	public void is_shown_on_the_website(String arg1) throws Throwable {
		testReport.createTest("Verify 'Not Found' Error");
		
		try {
			//Pass some random string like "abc" instead of "arg1" to see how the screenshot functionality works
			Assert.assertTrue(homePage.errorResult(arg1));
			testReport.scenarioPassed("Verify 'Not Found' Error");
		}
		catch (AssertionError e) {
			testReport.scenarioFailed("Verify 'Not Found' Error");
		}		
	}

	@When("^User enters an valid \"([^\"]*)\" name$")
	public void user_enters_an_valid_name(String arg1) throws Throwable {
		homePage.searchCity(arg1);
	}

	@Then("^Website successfully returns the weather details for the entered city$")
	public void website_successfully_returns_the_weather_details_for_the_entered_city() throws Throwable {
		testReport.createTest("Verify Search Functionality");
		Assert.assertTrue(homePage.verifyResult("Mumbai"));
		testReport.scenarioPassed("Verify Search Functionality");
		testReport.tearDown();
	}
	
	//This After Hook gets executed after every scenario, 
	//so this can be used to closed the session post every scenario
	@After
	public void closeBrowser() {
		homePage.closeApplication();
	}
	
	@Given("^I have the url as \"([^\"]*)\"$")
	public void i_have_the_url_as(String arg1) throws Throwable {
		given().contentType(ContentType.JSON);
	}

	@When("^I perform the GET operation on the URL \"([^\"]*)\"$")
	public void i_perform_the_GET_operation_on_the_URL(String arg1) throws Throwable {
		res = when().get(arg1);
	}

	@Then("^I should get the valid response code$")
	public void i_should_get_the_valid_response_code() throws Throwable {
		res.then().statusCode(200);
	}
}
