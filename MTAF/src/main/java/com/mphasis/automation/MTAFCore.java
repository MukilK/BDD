package com.mphasis.automation;

import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mphasis.automation.ApplicationInterface.DriverFunctions;
import com.mphasis.automation.exceptions.UnSupportedBrowserException;

//import com.kohls.poc.gravity.customhandlers.handler;

public class MTAFCore {

	public static final Logger logger = LoggerFactory.getLogger(MTAFCore.class);

	// Selenium Objects

	protected BrowserSettings browserSettings;
	public DriverFunctions execEngine;
	protected Actions builder;
	private static WebDriver driver;

	public MTAFCore() {

	}

	
	 private static final Thread CLOSE_THREAD = new Thread() {
	        @Override
	        public void run() {
	            driver.close();
	            driver.quit();
	        }
	    };

	    static {
	        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
	    }
	
	
	public void initilize() {
		try {
			browserSettings = new BrowserSettings();
			driver = browserSettings.returnBrowserDriver();
			execEngine = new DriverFunctions(driver);
		} catch (ConfigurationException ex) {
			logger.error("Configuration Exception : {} " + ex.getMessage());
		} catch (UnSupportedBrowserException e) {
			// TODO Auto-generated catch block
			logger.error("UnsupportedBrowserException : {} " + e.getMessage());
		}

	}

	public DriverFunctions getExecutionEngine() {
		return execEngine;
	}

	public boolean isdriverAlive() {

		if (driver == null) {
			return false;
		} else {
			return true;

		}
	}

	public WebDriver returnDriverObject() {
		return driver;
	}

	/**
	 * Shut down any browser instances still open now that tests have finished
	 * 
	 * @param driver
	 *            - driver object to stop
	 */
	public void stopDriver() {
		try {
			driver.quit();
			logger.info("Driver Closed Successfully");
		} catch (Exception x) {
			logger.error("Errrow hile trying to close driver : {}",
					x.getMessage());
		}
		driver = null;
	}

}