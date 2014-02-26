package com.mphasis.automation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mphasis.automation.ApplicationInterface.DriverFunctions;

public class MTAFTestSetup {

	public static final Logger LOGGER = LoggerFactory
			.getLogger(MTAFTestSetup.class);
	public  DriverFunctions execEngine;
	public MTAFCore mtaf;
	
	private static MTAFTestSetup msfc=new MTAFTestSetup();
	
	/**
	 * Gets the webdriver to be used
	 */

	private MTAFTestSetup(){
		mtaf = new MTAFCore();
		mtaf.initilize();
		execEngine = mtaf.getExecutionEngine();
		
	}
	
	public static MTAFTestSetup getInstance( ) {
	      return msfc;
	   }
	


}