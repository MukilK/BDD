package com.mphasis.automation;

import com.mphasis.automation.ApplicationInterface.DriverFunctions;
import com.mphasis.automation.exceptions.UnSupportedBrowserException;
import com.mphasis.automation.exceptions.UnsupportedBrowserDimension;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.kohls.poc.gravity.customhandlers.handler;

public class MTAFCore {

    public static final Logger logger = LoggerFactory.getLogger(MTAFCore.class);

    // Selenium Objects
    private final static String globalPropertyFileName = "GlobalConfig.properties";
    private static WebDriver driver;
    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            if (driver != null) {
                driver.close();
                driver.quit();
            }

        }
    };
    private static String globalConfigPropertiesFile = globalPropertyFileName;
    private static String deviceType = "device.type";

    static {
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public DriverFunctions execEngine;
    protected BrowserSettings browserSettings;
    protected Actions builder;
    Configuration globalConfiguration;

    public MTAFCore() {
        try {
            globalConfiguration = new PropertiesConfiguration(
                    globalConfigPropertiesFile);
        } catch (ConfigurationException e) {

            System.out.println("Error while initialising Config file "
                    + globalConfigPropertiesFile + " in MTAFTestSetup.");
        }
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
        } catch (UnsupportedBrowserDimension e) {
            logger.error("UnsupportedBrowserDimensionException : {} " + e.getMessage());
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
     * @param driver - driver object to stop
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

    public String returnDeviceType() {
        return globalConfiguration.getString(deviceType);
    }
}