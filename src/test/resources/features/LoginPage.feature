@login
Feature: Log in Clickcar
  As user I want to log in with my credential successfully

  @smoke
  Scenario: User login successfully with default credentials
    Given User is on Login page
    When User input default email and password
    And User click Login button
    Then User login successfully

  @data-driven
  Scenario Outline: User login with specific credentials
    Given User is on Login page
    When User input email "<email>" and password "<password>"
    And User click Login button
    Then Verify that log in status is "<status>"

    Examples: 
      | email                 | password  | status  |
      | hoangnd1@smartosc.com | Hoang123  | Success |
      | hoangnd1@smartosc.com | Hoang1234 | Failed  |

	# Refresh project and check test-output folder with file *_result to verify test results
  @data-file
  Scenario Outline: User login with data driven file
    When User login with data from excel with file name "<fileName>" and sheet name "<sheetName>"
    Examples: 
      | fileName       | sheetName |
      | loginData.xlsx | Sheet1    |
