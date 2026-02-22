@smoke @regression
Feature: Login functionality
  As a user
  I want to be able to login to the application
  So that I can access my account

  Background:
    Given I am on the login page

  @smoke
  Scenario: Successful login with valid credentials
    When I login with username "standard_user" and password "Password123!"
    Then I should be on the home page
    And I should see the welcome message

  Scenario: Failed login with invalid password
    When I login with username "standard_user" and password "wrong_password"
    Then I should see an error message "Invalid username or password"

  Scenario: Failed login with empty credentials
    When I click the login button
    Then I should see the login error message

  Scenario: Failed login with locked account
    When I login with username "locked_user" and password "LockedPass123!"
    Then I should see an error message "Your account has been locked"

  Scenario Outline: Login with various invalid credentials
    When I login with username "<username>" and password "<password>"
    Then I should see an error message "<error_message>"

    Examples:
      | username      | password     | error_message                  |
      | invalid_user  | Password123! | Invalid username or password   |
      | standard_user | short        | Invalid username or password   |
      |               | Password123! | Username is required           |
      | standard_user |              | Password is required           |
