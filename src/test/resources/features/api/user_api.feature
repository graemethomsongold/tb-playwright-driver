@api @regression
Feature: User API
  As an API consumer
  I want to manage users via the API
  So that I can perform CRUD operations

  @smoke
  Scenario: Get list of users
    When I request all users
    Then the response status code should be 200
    And the response should be successful

  Scenario: Get user by ID
    When I request user details for id "1"
    Then the response status code should be 200
    And the response should be successful
    And the response should contain a user id

  Scenario: Create a new user
    When I create a new user with name "John Doe" and email "john.doe@example.com"
    Then the response status code should be 201
    And the user name should be "John Doe"
    And the user email should be "john.doe@example.com"

  Scenario: Update an existing user
    Given a user exists with id "1"
    When I update user "1" with name "Updated Name"
    Then the response status code should be 200
    And the user name should be "Updated Name"

  Scenario: Delete a user
    When I delete user "1"
    Then the response status code should be 200

  Scenario: Get non-existent user returns 404
    When I request user details for id "99999"
    Then the response status code should be 404
