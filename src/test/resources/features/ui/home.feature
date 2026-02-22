@regression
Feature: Home page functionality
  As a logged in user
  I want to interact with the home page
  So that I can navigate the application

  Background:
    Given I am on the login page
    When I login with username "standard_user" and password "Password123!"
    Then I should be on the home page

  @smoke
  Scenario: Home page displays welcome message
    Then I should see the welcome message

  Scenario: User can logout from home page
    When I logout
    Then the URL should contain "/login"

  Scenario: User can search from home page
    When I search for "playwright" from the home page
    Then the URL should contain "/search"
