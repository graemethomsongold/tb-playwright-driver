@smoke @regression
Feature: Tesco Bank Homepage
  As a prospective customer
  I want to visit the Tesco Bank homepage
  So that I can learn about their financial products

  Background:
    Given I navigate to "https://www.tescobank.com"
    And I accept cookies if required

  @smoke
  Scenario: Homepage loads with the correct title
    Then the page title should contain "Tesco Bank"

  @smoke
  Scenario: Key product sections are visible on the homepage
    Then I should see the "Credit Cards" product section
    And I should see the "Personal Loans" product section
    And I should see the "Savings Accounts" product section

  @regression
  Scenario: Login link is present and points to the secure login area
    Then the login link should be visible
    And the login link should point to the secure area
