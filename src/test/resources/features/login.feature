Feature: Login

  @ui @smoke @sample
  Scenario: Successful login
    Given user opens the login page
    When user enters valid credentials
    And user clicks login
    Then home page should be displayed
