package com.mphasis.jBehave;

import com.mphasis.automation.ApplicationInterface.DriverFunctions;
import com.mphasis.automation.MTAFCore;
import com.mphasis.automation.MTAFTestSetup;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("thread")
public class SetUpAndTearDown {

    static Configuration configuration;
    private static String commonDataProperties = "CommonData.properties";
    public MTAFCore mtafSingleton = MTAFTestSetup.getInstance().mtaf;
    public MTAFCore mtaf = new MTAFCore();
    private DriverFunctions execEngine = MTAFTestSetup.getInstance().execEngine;

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
