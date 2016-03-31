package com.mphasis.jBehave;

import com.mphasis.amazonExample.PageObjects.HomePage;
import com.mphasis.amazonExample.PageObjects.LoginPage;
import com.mphasis.automation.GlobalInit;
import com.mphasis.automation.VerificationFunctions;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("thread")
public class Stepdefs extends GlobalInit {

    static LoginPage loginPage;
    static HomePage homePage;
    private static String commonDataProperties = "CommonData.properties";

    Configuration configuration;
    String User;
    String Pwd;

    public Stepdefs() {

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

    @Given("a valid user")
    public void givenAValidUser() {
        User = configuration.getString("valid.user");
        Pwd = configuration.getString("valid.userPassword");
    }

    @When("user enters credentials and click submit")
    public void whenUserEntersCredentialsAndClickSubmit() {
        loginPage.Login(User, Pwd);
    }

    @Then("User is logged in")
    public void thenUserIsLoggedIn() {
        VerificationFunctions.verifyEquals(
                configuration.getString("Hello.user"),
                homePage.isUserLoggedIn());
        homePage.logOff();
    }

    @Given("an invalid user abcdefghi@abcdefghi.com")
    public void givenAnInvalidUserAbcdefghiabcdefghicom() {
        User = configuration.getString("invalid.user");
        Pwd = configuration.getString("invalid.userPassword");
    }

    @Then("User is shown There was a problem with your request.There was an error with your E-Mail/Password combination. Please try again.")
    public void thenUserIsShownThereWasAProblemWithYourRequestThereWasAnErrorWithYourEMailPasswordCombinationPleaseTryAgain() {

        VerificationFunctions.verifyEquals(
                configuration.getString("invalidloginattempt.message"),
                loginPage.returnInvalidLoginMessage());

    }

    @When("user searches for $anItemToSearchFor")
    public void user_searches_for_something(String anItemToSearchFor)
            throws Throwable {
        homePage.searchForItem(anItemToSearchFor);
    }

    @Then("more than one result is shown")
    public void thenMoreThanOneResultIsShown() throws Throwable {
        homePage.logOff();
    }

}
