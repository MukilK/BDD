package com.mphasis.automation;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mphasis.automation.ApplicationInterface.DriverFunctions;

public class MTAFTestSetup {

	public static final Logger LOGGER = LoggerFactory
			.getLogger(MTAFTestSetup.class);



	public DriverFunctions execEngine;
	public MTAFCore mtafCore;
	

	private static MTAFTestSetup msfc = new MTAFTestSetup();

	/**
	 * Gets the webdriver to be used
	 */

	private MTAFTestSetup() {
		mtafCore = new MTAFCore();
		mtafCore.initilize();
		execEngine = mtafCore.getExecutionEngine();
		

	}

	public static MTAFTestSetup getInstance() {
		return msfc;
	}

	

}