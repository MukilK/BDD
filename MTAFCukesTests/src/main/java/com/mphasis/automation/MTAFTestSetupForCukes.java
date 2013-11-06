package com.mphasis.automation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mphasis.automation.ApplicationInterface.DriverFunctions;

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