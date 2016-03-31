package com.mphasis.automation;

import com.mphasis.automation.exceptions.UnSupportedBrowserException;
import com.mphasis.automation.exceptions.UnsupportedBrowserDimension;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class BrowserSettings {

    public static final Logger logger = LoggerFactory
            .getLogger(BrowserSettings.class);
    private final static String globalPropertyFileName = "GlobalConfig.properties";
    private final static String propCurrentBrowser = "current.browser";
    private final static String propWindowSizeForDevice = "device.type";
    private final static String propvalueMaximized = "maximized";
    private final static String propGlobalTimeOut = "global.timeout";
    private final static String propSuffixScreen = "screen.";

    ;
    private static String globalConfigPropertiesFile = globalPropertyFileName;
    Configuration globalConfiguration;
    private supportedBrowsers currentBrowser;

    public BrowserSettings() throws UnSupportedBrowserException,
            ConfigurationException {

        this((new PropertiesConfiguration(globalConfigPropertiesFile))
                .getString(propCurrentBrowser));
        // configuration is instantiated here since it throws an error & needs
        // to be handled.

        globalConfiguration = new PropertiesConfiguration(
                globalConfigPropertiesFile);
    }

    /**
     * Constructor of BrowserSettings returns the browser Object
     *
     * @param browser : The Browser name from the enumerator supportedBrowsers
     * @return required driver type
     * @throws ConfigurationException
     * @throws UnSupportedBrowserException
     */
    private BrowserSettings(String browser) throws UnSupportedBrowserException {
        logger.debug("BrowserSettings Constructor. - Browser Selected "
                + browser);

        for (supportedBrowsers sBrowser : supportedBrowsers.values()) {
            if (sBrowser.toString().toLowerCase().equals(browser.toLowerCase())) {
                setCurrentBrowser(sBrowser);
                return;
            }
        }
        throw new UnSupportedBrowserException(browser);
    }

    /**
     * Returns the current browser being used
     *
     * @return String (Firefox, IE, HTMLUNIT, Chrome)
     */
    public String getCurrentBrowser() {
        return currentBrowser.toString();
    }

    private void setCurrentBrowser(supportedBrowsers currentBrowser) {
        this.currentBrowser = currentBrowser;
    }

    private supportedBrowsers getcurrentBrowser() {
        System.out.println(currentBrowser.toString());
        return currentBrowser;
    }

    /**
     * Return the required Browser driver
     *
     * @param supportedBrowsers enumerator
     * @return
     * @throws UnSupportedBrowserException
     * @throws UnsupportedBrowserDimension
     */
    public WebDriver returnBrowserDriver() throws UnSupportedBrowserException,
            UnsupportedBrowserDimension {
        WebDriver driver;
        try {
            switch (getcurrentBrowser()) {
                case FIREFOX:
                    if (returnFirefoxProfile(null) != null) {
                        logger.debug("Loading Firefox driver with profiles.");
                        driver = new FirefoxDriver(returnFirefoxProfile(null));
                        logger.debug("PROFILE loaded Firefox driver started. ");
                    } else {
                        logger.debug("Loading Firefox driver without profiles.");
                        driver = new FirefoxDriver();
                        logger.debug("Firefox driver started without profiles");
                    }
                    System.out.println("Firefox");
                    break;
                case IEXPLORE:
                    driver = new InternetExplorerDriver(returnIECapabilities());
                    logger.debug("Internet Explorer driver started");
                    System.out.println("IE");
                    break;
                case CHROME:
                    driver = new ChromeDriver(returnChromeCapabilities());
                    logger.debug("Chrome driver started.");
                    System.out.println("Chrome");
                    break;
                case IPHONEIOS7:
                    // This option is with User Agent.
                    if (returnFirefoxProfile("IPHONEIOS7") != null) {
                        driver = new FirefoxDriver(
                                returnFirefoxProfile("IPHONEIOS7"));
                        logger.debug("PROFILE loaded Firefox driver started. ");
                    } else {
                        driver = new FirefoxDriver();
                        logger.debug("Firefox driver started without profiles");
                    }
                    System.out.println("Firefox");
                    break;

                case REMOTEFF:
                    driver = new RemoteWebDriver(new URL(
                            globalConfiguration.getString("grid.url")),
                            returnBrowserCapabilitiesForRemoteWebDriver("FF"));
                    break;
                case REMOTECHROME:
                    driver = new RemoteWebDriver(new URL(
                            globalConfiguration.getString("grid.url")),
                            returnBrowserCapabilitiesForRemoteWebDriver("Chrome"));
                    break;
                default:
                /*
                 * driver = new FirefoxDriver();
				 * logger.debug("Defaulting to HTML Unit driver");
				 * System.out.println("HTML Unit"); break;
				 */
                    throw new UnSupportedBrowserException(getCurrentBrowser());
            }

        } catch (Exception x) {
            logger.error(
                    "Exception caught in returnBrowserDriver function: {}",
                    x.getMessage());

            return null;
        }
        driver.manage()
                .timeouts()
                .implicitlyWait(globalConfiguration.getInt(propGlobalTimeOut),
                        TimeUnit.SECONDS);

        String windowSizeForDevices = getPropWindowSize();

        if (windowSizeForDevices.equals(propvalueMaximized)
                || windowSizeForDevices.equals("")) {
            driver.manage().window().maximize();
        } else {
            String[] windowSize = windowSizeForDevices.split("x");
            try {
                Dimension sd = new Dimension(Integer.parseInt(windowSize[0]),
                        Integer.parseInt(windowSize[1]));
                driver.manage().window().setSize(sd);
            } catch (NumberFormatException ex) {
                throw new UnsupportedBrowserDimension(windowSizeForDevices);
            }
        }

        return driver;
    }

    private String getPropWindowSize() {

        String deviceType = globalConfiguration
                .getString(propWindowSizeForDevice);
        return globalConfiguration.getString(propSuffixScreen + deviceType);

    }

    private DesiredCapabilities returnChromeCapabilities()
            throws ConfigurationException {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        System.setProperty("webdriver.chrome.driver",
                globalConfiguration.getString("ChromeDriver.location"));
        if (globalConfiguration.containsKey("ChromeBin.location")
                && !globalConfiguration.getString("ChromeBin.location").equals(
                "")) {
            capabilities.setCapability("chrome.binary",
                    globalConfiguration.getString("ChromeBin.location"));
        }

        return capabilities;
    }

    private DesiredCapabilities returnIECapabilities()
            throws ConfigurationException {
        DesiredCapabilities capabilities = DesiredCapabilities
                .internetExplorer();
        System.setProperty("webdriver.ie.driver",
                globalConfiguration.getString("ieDriver.location"));
        return capabilities;
    }

    private FirefoxProfile returnFirefoxProfile(String deviceBrowser)
            throws ConfigurationException {
        FirefoxProfile fp = null;

        Iterator<Configuration> keys = globalConfiguration.getKeys();
        if (!keys.hasNext()) {

        } else {

            if (deviceBrowser != null) {
                fp = new FirefoxProfile();
                fp.setPreference("general.useragent.override",
                        returnUserAgentString(deviceBrowser));
            }

        }
        return fp;
    }

    private String returnUserAgentString(String deviceBrowser)
            throws ConfigurationException {

        Iterator<Configuration> keys = globalConfiguration.getKeys();
        if (!keys.hasNext()) {
            return (null);
        } else {
            return (globalConfiguration.getString(deviceBrowser));
        }

    }

    private DesiredCapabilities returnBrowserCapabilitiesForRemoteWebDriver(
            String Browser) {
        DesiredCapabilities dc = null;
        if (Browser.equals("FF")) {
            dc = DesiredCapabilities.firefox();

        } else if (Browser.equals("Chrome")) {
            dc = DesiredCapabilities.chrome();

        }
        return dc;
    }

    public enum supportedBrowsers {

        CHROME, FIREFOX, IEXPLORE, HTMLUNIT, IPHONEIOS7, REMOTEFF, REMOTECHROME
    }

}