@smoke @regression
Feature: Tesco Bank Savings Accounts
  As a prospective savings customer
  I want to browse savings account options
  So that I can find the right product for me

  Background:
    Given I navigate to "https://www.tescobank.com/savings/"
    And I accept cookies if required

  @smoke
  Scenario: Savings page loads with the correct heading
    Then the page heading should be "Savings Accounts"

  @regression
  Scenario: Fixed Rate Saver product is displayed
    Then I should see the "Fixed Rate Saver" savings product

  @regression
  Scenario: ISA products are displayed
    Then I should see the "Cash ISA" savings product

  @regression
  Scenario: Internet Saver product is displayed
    Then I should see the "Internet Saver" savings product
