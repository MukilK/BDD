@ff
Feature: A user tries to complete flexapp without filling all mandatory fields

  Scenario: User Fills in all fields except Type of residence field
    Given that user is on the FlexApp Credit card application page "Tell us about yourself."
    And user enters valid data into all the mandatory fields in the Personal section
      | First Name | John           |
      | Initial    | Doe            |
      | Last Name  | David          |
      | Suffix     | Jr             |
      | Address 1  | 140th johns St |
      | Address 2  | Spencers Lane  |
      | Unit       | 3103           |
      | City       | Overland Park  |
      | State      | KS             |
      | Zip Code   | 66223          |
    When he clicks on Next button
    Then he must land on the Financial section page "We need some financial information."
    And he sees Type of residence field with default label for the drop down field as "Select One"
    When user expands the Type of residence list on Financial section page
    Then he see options
      | Rent  |
      | Own   |
      | Other |
    When completes all other fields except Type of residence on this page and taps on Next
      | Income       | 100000   |
      | IncomeSource | Employed |
      | Employer     | Mphasis  |
    Then he sees an error overview on the screen with the content "Please complete these fields to continue to next step:"
    And he verifies that all other fields remain unchanged.
      | Income       | 100000   |
      | IncomeSource | Employed |
      | Employer     | Mphasis  |
