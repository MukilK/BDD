
Narrative:
In order to ensure that a user can search for Items
As an user
I want to search for a popular item

Scenario: Search for a popular item returns more than 1 results
Given a valid user
When user enters credentials and click submit
And user searches for gps
Then more than one result is shown
