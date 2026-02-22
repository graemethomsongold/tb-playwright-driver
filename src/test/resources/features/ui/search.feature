@regression
Feature: Search functionality
  As a user
  I want to search for content
  So that I can find relevant information

  Background:
    Given I am on the "/search" page

  @smoke
  Scenario: Successful search returns results
    When I search for "playwright"
    Then I should see search results
    And the search results should contain "playwright"

  Scenario: Search with no results
    When I search for "zzzznoexist999"
    Then I should see no search results

  Scenario: Filter search results
    When I search for "testing"
    And I filter results by "recent"
    Then I should see search results

  Scenario: Click on search result navigates to detail page
    When I search for "automation"
    Then I should see search results
    When I click on search result 1
    Then the URL should contain "/detail"
