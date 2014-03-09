package com.mphasis.automation.cukesDriver;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.chase.ncj.PageObjects.FinancialPage;
import com.chase.ncj.PageObjects.PersonalPage;
import com.mphasis.amazonExample.PageObjects.HomePage;
import com.mphasis.amazonExample.PageObjects.LoginPage;
import com.mphasis.automation.GlobalInit;
import com.mphasis.automation.VerificationFunctions;
import com.mphasis.automation.ApplicationInterface.DriverFunctions;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.table.TableDiffException;

public class ApplyOnlineStepdefs extends GlobalInit {

	public static final Logger logger = LoggerFactory
			.getLogger(DriverFunctions.class);

	static FinancialPage financialPage;
	static PersonalPage personalPage;
	private static String commonDataProperties = "CommonData.properties";
	Configuration configuration;

	public ApplyOnlineStepdefs() {

		try {
			configuration = new PropertiesConfiguration(commonDataProperties);
		} catch (ConfigurationException e) {

			logger.error("Error reading configuration " + e.getMessage());
		}
		personalPage = PageFactory.initElements(mtaf.returnDriverObject(),
				PersonalPage.class);
		financialPage = PageFactory.initElements(mtaf.returnDriverObject(),
				FinancialPage.class);

	}

	@Given("that user is on the FlexApp Credit card application page \"(.+)\"")
	public void that_user_is_on_the_FlexApp_Credit_card_application_page(
			String txtHeader) throws Throwable {
		VerificationFunctions.verifyTrue(personalPage
				.isOnPersonalPage(txtHeader));

	}

	@Given("^user enters valid data into all the mandatory fields in the Personal section$")
	public void user_enters_valid_data_into_all_the_mandatory_fields_in_the_Personal_section(
			DataTable details) throws Throwable {
		List<List<String>> detailArray = details.raw();
		personalPage.fillNameDetails(detailArray.get(0).get(1).trim(),
				detailArray.get(1).get(1).trim(), detailArray.get(2).get(1)
						.trim(), detailArray.get(3).get(1).trim());
		personalPage.fillAddress(detailArray.get(4).get(1).trim(), detailArray
				.get(5).get(1).trim(), detailArray.get(6).get(1).trim(),
				detailArray.get(7).get(1).trim(), detailArray.get(8).get(1)
						.trim(), detailArray.get(9).get(1).trim());

	}

	@When("^he clicks on Next button$")
	public void he_clicks_on_Next_button() throws Throwable {
		personalPage.submit();
	}

	@Then("he must land on the Financial section page \"(.+)\"")
	public void he_must_land_on_the_Financial_section_page(String txtHeader)
			throws Throwable {
		financialPage.isOnFinancePage(txtHeader);
	}

	@Then("^he sees Type of residence field with default label for the drop down field as \"(.+)\"")
	public void he_sees_Type_of_residence_field_with_default_label_for_the_drop_down_field_as_Select_one(
			String typeOfResidence) throws Throwable {
		VerificationFunctions.verifyEquals(typeOfResidence,
				financialPage.getValueInTypeOfResidence());
	}

	@When("^user expands the Type of residence list on Financial section page$")
	public void user_expands_the_Type_of_residence_list_on_Financial_section_page()
			throws Throwable {
		financialPage.clickHousingType();
	}

	@Then("^he see options$")
	public void he_see_options(DataTable housingOptions) throws Throwable {
		boolean allOptionsAvailable = true;
		try {

			List<String> lstHS = financialPage.getAllHousingTypeOptions();
			List<List<String>> lstTemp = housingOptions.raw();
			for (Iterator iterator = lstTemp.iterator(); iterator.hasNext();) {
				List<String> list = (List<String>) iterator.next();
				VerificationFunctions.verifyTrue(lstTemp.contains(list));
			}

		} catch (TableDiffException ex) {
			System.out.println(ex.getMessage());
			allOptionsAvailable = false;
		}

	}

	@When("^completes all other fields except Type of residence on this page and taps on Next$")
	public void completes_all_other_fields_except_Type_of_residence_on_this_page_and_taps_on_Next(
			DataTable financialPageFields) throws Throwable {
		List<List<String>> financialPageFieldsList = financialPageFields.raw();
		financialPage.fillAnnualIncome(financialPageFieldsList.get(0).get(1));
		financialPage.selectSourceOfIncome(financialPageFieldsList.get(1)
				.get(1));
		financialPage.fillEmployerInfo(financialPageFieldsList.get(2).get(1));
		financialPage.submitFinanceDetails();
	}

	@Then("^he sees an error overview on the screen with the content \"([^\"]*)\"$")
	public void he_sees_an_error_overview_on_the_screen_with_the_content(
			String msg) throws Throwable {
		VerificationFunctions.verifyEquals(msg,
				financialPage.getErrorMsgIncompleteFields());
	}

	@Then("he verifies that all other fields remain unchanged.")
	public void he_verifies_that_all_other_fields_remain_unchanged(
			DataTable financialInfoFields) throws Throwable {
		List<List<String>> financialPageFieldsList = financialInfoFields.raw();
		VerificationFunctions.verifyEquals(financialPageFieldsList.get(0)
				.get(1), financialPage.getAnnualIncome().replace(",", ""));
		VerificationFunctions.verifyEquals(financialPageFieldsList.get(1)
				.get(1), financialPage.getSourceOfIncome());
		VerificationFunctions.verifyEquals(financialPageFieldsList.get(2)
				.get(1), financialPage.getEmployerInfo());

	}

}
