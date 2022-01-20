@e2eflow
Feature: Checkout complete
  As user I want to search and buy a car

Background:
Given User login successfully with default credentials
	
	@checkout
  Scenario: User search car and checkout successfully   
    When User go to Catalogue page
    And Search and select car by keyword "Toyota rav4"
    And User buy current car
    Then Verify check out page is displayed
