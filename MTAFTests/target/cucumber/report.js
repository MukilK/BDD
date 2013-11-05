$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("com/mphasis/automation/cukesDriver/Login.feature");
formatter.feature({
  "id": "a-valid-user-should-have-access-to-amazon",
  "description": "",
  "name": "A valid user should have access to Amazon",
  "keyword": "Feature",
  "line": 1
});
formatter.before({
  "duration": 12449300416,
  "status": "passed"
});
formatter.scenario({
  "id": "a-valid-user-should-have-access-to-amazon;a-valid-user-logging-into-amazon-should-be-granted-access",
  "description": "",
  "name": "A valid user logging into amazon should be granted access",
  "keyword": "Scenario",
  "line": 3,
  "type": "scenario"
});
formatter.step({
  "name": "a valid user",
  "keyword": "Given ",
  "line": 4
});
formatter.step({
  "name": "user enters credentials and click submit",
  "keyword": "When ",
  "line": 5
});
formatter.step({
  "name": "User is logged in",
  "keyword": "Then ",
  "line": 6
});
formatter.match({
  "location": "loginStepdefs.a_valid_user()"
});
formatter.result({
  "duration": 124029382,
  "status": "passed"
});
formatter.match({
  "location": "loginStepdefs.user_enters_credentials_and_click_submit()"
});
formatter.result({
  "duration": 3170597308,
  "status": "passed"
});
formatter.match({
  "location": "loginStepdefs.User_is_logged_in()"
});
formatter.result({
  "duration": 192561250,
  "status": "passed"
});
formatter.after({
  "duration": 15189,
  "status": "passed"
});
formatter.before({
  "duration": 433486032,
  "status": "passed"
});
formatter.scenario({
  "id": "a-valid-user-should-have-access-to-amazon;an-invalid-user-into-amazon-should-not-be-granted-access",
  "description": "",
  "name": "An invalid user into amazon should not be granted access",
  "keyword": "Scenario",
  "line": 8,
  "type": "scenario"
});
formatter.step({
  "name": "an invalid user abcdefghi@abcdefghi.com",
  "keyword": "Given ",
  "line": 9
});
formatter.step({
  "name": "user enters credentials and click submit",
  "keyword": "When ",
  "line": 10
});
formatter.step({
  "name": "User is shown There was a problem with your request.There was an error with your E-Mail/Password combination. Please try again.",
  "keyword": "Then ",
  "line": 11
});
formatter.match({
  "location": "loginStepdefs.an_invalid_user_abcdefghi_abcdefghi_com()"
});
formatter.result({
  "duration": 2269754,
  "status": "passed"
});
formatter.match({
  "location": "loginStepdefs.user_enters_credentials_and_click_submit()"
});
formatter.result({
  "duration": 844536026,
  "status": "passed"
});
formatter.match({
  "location": "loginStepdefs.User_is_shown_There_was_a_problem_with_your_request_There_was_an_error_with_your_E_Mail_Password_combination_Please_try_again()"
});
formatter.result({
  "duration": 173794011,
  "status": "passed"
});
formatter.after({
  "duration": 13137,
  "status": "passed"
});
formatter.uri("com/mphasis/automation/cukesDriver/Search.feature");
formatter.feature({
  "id": "user-should-be-able-to-search-for-items",
  "description": "",
  "name": "User should be able to search for Items",
  "keyword": "Feature",
  "line": 1
});
formatter.before({
  "duration": 488265139,
  "status": "passed"
});
formatter.scenario({
  "id": "user-should-be-able-to-search-for-items;search-for-a-popular-item-returns-more-than-1-results",
  "description": "",
  "name": "Search for a popular item returns more than 1 results",
  "keyword": "Scenario",
  "line": 3,
  "type": "scenario"
});
formatter.step({
  "name": "a valid user",
  "keyword": "Given ",
  "line": 4
});
formatter.step({
  "name": "user enters credentials and click submit",
  "keyword": "And ",
  "line": 5
});
formatter.step({
  "name": "user searches for \"GPS\"",
  "keyword": "When ",
  "line": 6
});
formatter.step({
  "name": "more than one result is shown",
  "keyword": "Then ",
  "line": 7
});
formatter.match({
  "location": "loginStepdefs.a_valid_user()"
});
formatter.result({
  "duration": 1784933,
  "status": "passed"
});
formatter.match({
  "location": "loginStepdefs.user_enters_credentials_and_click_submit()"
});
formatter.result({
  "duration": 1544234337,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "GPS",
      "offset": 19
    }
  ],
  "location": "loginStepdefs.user_searches_for_something(String)"
});
formatter.result({
  "duration": 3924567735,
  "status": "passed"
});
formatter.match({
  "location": "loginStepdefs.more_than_one_result_is_shown()"
});
formatter.result({
  "duration": 146109501,
  "status": "passed"
});
formatter.after({
  "duration": 22578,
  "status": "passed"
});
});