#Feature file example: To verify various functionalities OpenWeather Application provides
Feature: Verify various functionalities of the OpenWeather Application
 
 	#Tags can be mentioned which can then be used in Test Runner to filter out the scenarios 
	@Test1 
	Scenario Outline: Verifies the UI Elements of the application
		
		#Given is the precondition for the test case
		Given User is on the OpenWeather homepage 
		
		# Then refers to the Expected outcome
		Then User should be able to verify "<windowTitle>"
		
		# And refers to the additional expected outcome
		And city name text field is displayed and enabled
		And Search button is displayed and enabled
		
		# Examples passes the data values when used in combination with Scenario Outline
		Examples: 
		
			| windowTitle 										  | 
			| Сurrent weather and forecast - OpenWeatherMap		  |
			
	@Test2
	Scenario Outline: Verify that entering an invalid city gives "Not found" error
	
		Given User is on the OpenWeather homepage
		When User enters an invalid "<city>" name
		And clicks on the search button
		Then "<error>" is shown on the website
		
		Examples:
		
		| city   | error     |
		| xyz | Not found |
	
	@Test3	
	Scenario: Verify that entering a valid city gives the weather details for that city
	
		Given User is on the OpenWeather homepage
		When User enters an valid "Mumbai" name
		And clicks on the search button
		Then Website successfully returns the weather details for the entered city
		
	@Test4
	Scenario: Verify the response code for an API
		
		Given I have the url as "https://google.com"
		When I perform the GET operation on the URL "https://google.com"
		Then I should get the valid response code
		
	
		