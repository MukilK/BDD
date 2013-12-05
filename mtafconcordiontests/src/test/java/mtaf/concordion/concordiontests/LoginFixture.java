package mtaf.concordion.concordiontests;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import com.mphasis.amazonExample.PageObjects.HomePage;
import com.mphasis.amazonExample.PageObjects.LoginPage;

/* Run this class as a JUnit test. */
@RunWith(ConcordionRunner.class)
public class LoginFixture extends TestSetup {

	static LoginPage loginPage;
	static HomePage homePage;
	String User;
	String Pwd;

	public LoginFixture() {
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

	public void logintoAmazon(String UserName, String Password) {
	
		User = configuration.getString("valid.user");
		Pwd = configuration.getString("valid.userPassword");
		loginPage.Login(User, Pwd);
	}

	public String validateUserLoggedin() {
		if (configuration.getString("Hello.user").equals(
				homePage.isUserLoggedIn())) {
			homePage.logOff();
			return "Successful";

		} else {
			return "failed";

		}

		// Return Successful after validation
	}

}
