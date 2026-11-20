Feature: Database validation

  @database
  Scenario: Validate user in database
    Then user "testuser" should exist in database
