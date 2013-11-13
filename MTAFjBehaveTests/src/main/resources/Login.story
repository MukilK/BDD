
Narrative:
In order to Ensure that a valid user alone can palce orders
As a valid user or an invalid user
I want to log into  Amazon

Scenario: A valid user logging into amazon should be granted access
Given a valid user
When user enters credentials and click submit
Then User is logged in

Scenario: An invalid user into amazon should not be granted access
Given an invalid user abcdefghi@abcdefghi.com
When user enters credentials and click submit
Then User is shown There was a problem with your request.There was an error with your E-Mail/Password combination. Please try again.