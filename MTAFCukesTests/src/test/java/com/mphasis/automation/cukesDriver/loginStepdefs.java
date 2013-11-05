package com.mphasis.automation.cukesDriver;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.support.PageFactory;

import com.mphasis.amazonExample.PageObjects.HomePage;
import com.mphasis.amazonExample.PageObjects.LoginPage;
import com.mphasis.automation.GlobalInit;
import com.mphasis.automation.cukesVerificationFunctions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class loginStepdefs extends GlobalInit {

	static LoginPage loginPage;
	static HomePage homePage;
	private static String commonDataProperties = "CommonData.properties";

	Configuration configuration;
	String User;
	String Pwd;

	public loginStepdefs() {

		try {
			configuration = new PropertiesConfiguration(commonDataProperties);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loginPage = PageFactory.initElements(mtaf.returnDriverObject(),
				LoginPage.class);
		homePage = PageFactory.initElements(mtaf.returnDriverObject(),
				HomePage.class);
	}

	@Given("^a valid user$")
	public void a_valid_user() throws Throwable {
		User = configuration.getString("valid.user");
		Pwd = configuration.getString("valid.userPassword");
	}

	@When("^user enters credentials and click submit$")
	public void user_enters_credentials_and_click_submit() throws Throwable {
		loginPage.Login(User, Pwd);
	}

	@Then("^User is logged in$")
	public void User_is_logged_in() throws Throwable {
		cukesVerificationFunctions.verifyEquals(
				configuration.getString("welcome.user"),
				homePage.isUserLoggedIn());
		homePage.logOff();
	}

	@Given("^an invalid user abcdefghi@abcdefghi.com$")
	public void an_invalid_user_abcdefghi_abcdefghi_com() throws Throwable {
		User = configuration.getString("invalid.user");
		Pwd = configuration.getString("invalid.userPassword");
	}

	@Then("^User is shown There was a problem with your request.There was an error with your E-Mail/Password combination. Please try again.$")
	public void User_is_shown_There_was_a_problem_with_your_request_There_was_an_error_with_your_E_Mail_Password_combination_Please_try_again()
			throws Throwable {

		cukesVerificationFunctions.verifyEquals(
				configuration.getString("invalidloginattempt.message"),
				loginPage.returnInvalidLoginMessage());

	}

	@When("^user searches for \"([^\"]*)\"$")
	public void user_searches_for_something(String itemToSearchFor)
			throws Throwable {
		homePage.searchForItem(itemToSearchFor);
	}

	@Then("^more than one result is shown$")
	public void more_than_one_result_is_shown() throws Throwable {
		homePage.logOff();
	}

}
