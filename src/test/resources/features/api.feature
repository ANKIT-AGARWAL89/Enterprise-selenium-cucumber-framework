Feature: User API

  @api @smoke
  Scenario: Get users
    When client gets the users
    Then API response status should be 200
