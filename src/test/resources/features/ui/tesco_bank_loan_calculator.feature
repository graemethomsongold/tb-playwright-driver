@smoke @regression
Feature: Tesco Bank Loan Calculator
  As a prospective loan customer
  I want to use the loan calculator
  So that I can estimate my monthly repayments

  Background:
    Given I navigate to "https://www.tescobank.com/loans/"
    And I accept cookies if required
    And I navigate to "https://www.tescobank.com/loans/loan-calculator/"

  @smoke
  Scenario: Loan calculator page loads with the correct heading
    Then the page heading should be "Loan calculator"

  @regression
  Scenario: Default loan amount is pre-populated
    Then the loan amount input should show "7,500"

  @regression
  Scenario: Default loan duration is pre-populated
    Then the loan duration input should show "60" months

  @regression
  Scenario: Representative APR is displayed
    Then the representative APR should be shown

  @regression
  Scenario: Loan amount range boundaries are correct
    Then the minimum loan amount should be "3000"
    And the maximum loan amount should be "35000"

  @regression
  Scenario: Monthly repayments are calculated for the default loan
    Then the monthly repayments section should be visible
