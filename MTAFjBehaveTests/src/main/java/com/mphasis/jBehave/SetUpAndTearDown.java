package com.mphasis.jBehave;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.BeforeStory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mphasis.automation.MTAFCore;
import com.mphasis.automation.MTAFTestSetup;
import com.mphasis.automation.ApplicationInterface.DriverFunctions;

@Component
@Scope("thread")
public class SetUpAndTearDown {

	private DriverFunctions execEngine = MTAFTestSetup.getInstance().execEngine;
	public MTAFCore mtafSingleton = MTAFTestSetup.getInstance().mtaf;
	public MTAFCore mtaf = new MTAFCore();
	private static String commonDataProperties = "CommonData.properties";
	static Configuration configuration;

	@BeforeStory
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

	@AfterStory
	public void tearClassDown() {

	}
}
