@smoke @regression
Feature: Tesco Bank Loans
  As a prospective loan customer
  I want to explore personal loan options
  So that I can find a suitable loan

  Background:
    Given I navigate to "https://www.tescobank.com/loans/"
    And I accept cookies if required

  @smoke
  Scenario: Personal loans page loads with the correct heading
    Then the page heading should be "Personal loans"

  @regression
  Scenario: Loan calculator link is present on the loans page
    Then I should see the "Loan calculator" loan type

  @regression
  Scenario: Specific loan types are advertised
    Then I should see the "Car Loan" loan type
    And I should see the "Home Improvement Loan" loan type
