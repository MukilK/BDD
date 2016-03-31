package com.mphasis.automation.cukesDriver;

import com.mphasis.automation.ApplicationInterface.DriverFunctions;
import com.mphasis.automation.MTAFCore;
import com.mphasis.automation.MTAFTestSetup;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.WebDriverException;

import java.io.IOException;

public class SetUpAndTearDown {

    static Configuration configuration;
    private static String commonDataProperties = "CommonData.properties";
    public MTAFCore mtafSingleton = MTAFTestSetup.getInstance().mtafCore;
    public MTAFCore mtaf = new MTAFCore();
    private DriverFunctions execEngine = MTAFTestSetup.getInstance().execEngine;

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
