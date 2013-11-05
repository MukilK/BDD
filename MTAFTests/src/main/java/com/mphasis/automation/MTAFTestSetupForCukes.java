package com.mphasis.automation;

import com.mphasis.automation.BrowserSettings;
import com.mphasis.automation.MTAFCore;
import com.mphasis.automation.ApplicationInterface.DriverFunctions;

import cucumber.api.java.After;
import cucumber.api.java.Before;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MTAFTestSetupForCukes {

	public static final Logger LOGGER = LoggerFactory
			.getLogger(MTAFTestSetupForCukes.class);
	public  DriverFunctions execEngine;
	public MTAFCore mtaf;
	
	private static MTAFTestSetupForCukes msfc=new MTAFTestSetupForCukes();
	
	/**
	 * Gets the cuke enabled webdriver to be used
	 */

	private MTAFTestSetupForCukes(){
		mtaf = new MTAFCore();
		mtaf.initilize();
		execEngine = mtaf.getExecutionEngine();
		
	}
	
	public static MTAFTestSetupForCukes getInstance( ) {
	      return msfc;
	   }
	


}