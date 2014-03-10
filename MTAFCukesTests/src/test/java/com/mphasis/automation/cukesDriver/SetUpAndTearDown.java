package com.mphasis.automation.cukesDriver;

import java.io.IOException;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.WebDriverException;

import com.mphasis.automation.MTAFCore;
import com.mphasis.automation.MTAFTestSetup;
import com.mphasis.automation.ApplicationInterface.DriverFunctions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class SetUpAndTearDown {

	private DriverFunctions execEngine = MTAFTestSetup.getInstance().execEngine;
	public MTAFCore mtafSingleton = MTAFTestSetup.getInstance().mtafCore;
	public MTAFCore mtaf = new MTAFCore();
	private static String commonDataProperties = "CommonData.properties";
	static Configuration configuration;

	@Before
	public void StartBrowser() {

		try {

			configuration = new PropertiesConfiguration(commonDataProperties);
			execEngine.navigateToUrl(
					configuration.getString("application.url"), null, 0);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 */

	@After
	public void embedScreenshot(Scenario scenario) {
		try {
			byte[] screenshot;

			try {
				screenshot = execEngine.getscreenShot();
				scenario.embed(screenshot, "image/png");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (WebDriverException somePlatformsDontSupportScreenshots) {
			System.err
					.println(somePlatformsDontSupportScreenshots.getMessage());
		}
	}
}
