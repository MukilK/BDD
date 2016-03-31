package com.mphasis.automation;

import com.mphasis.automation.ApplicationInterface.DriverFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MTAFTestSetup {

    public static final Logger LOGGER = LoggerFactory
            .getLogger(MTAFTestSetup.class);
    private static MTAFTestSetup msfc = new MTAFTestSetup();
    public DriverFunctions execEngine;
    public MTAFCore mtafCore;

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