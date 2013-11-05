package com.mphasis.automation.cukesDriver;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.mphasis.automation.MTAFCore;
import com.mphasis.automation.MTAFTestSetupForCukes;
import com.mphasis.automation.ApplicationInterface.DriverFunctions;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class setUpAndTearDown {

	private DriverFunctions execEngine = MTAFTestSetupForCukes.getInstance().execEngine;
	public MTAFCore mtafSingleton = MTAFTestSetupForCukes.getInstance().mtaf;
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
	public void tearClassDown() {
		
	}
}
