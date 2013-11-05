Feature: User should be able to search for Items

  Scenario: Search for a popular item returns more than 1 results
    Given a valid user
    And user enters credentials and click submit
    When user searches for "GPS"
    Then more than one result is shown
