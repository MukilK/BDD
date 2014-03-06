package com.mphasis.automation;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mphasis.automation.exceptions.UnSupportedBrowserException;

public class BrowserSettings {

	public enum supportedBrowsers {

		CHROME, FIREFOX, IEXPLORE, HTMLUNIT,IPHONEIOS7
	};

	private supportedBrowsers currentBrowser;
	public static final Logger logger = LoggerFactory
			.getLogger(BrowserSettings.class);
	
	private static String globalConfigPropertiesFile = "GlobalConfig.properties";
	Configuration globalConfiguration;

	public BrowserSettings() throws UnSupportedBrowserException,
			ConfigurationException {

		this((new PropertiesConfiguration(globalConfigPropertiesFile))
				.getString("current.browser"));
		// configuration is instantiated here since it throws an error & needs
		// to be handled.
		
		globalConfiguration = new PropertiesConfiguration(
				globalConfigPropertiesFile);
	}

	/**
	 * Constructor of BrowserSettings returns the browser Object
	 * 
	 * @param browser
	 *            : The Browser name from the enumerator supportedBrowsers
	 * @return required driver type
	 * @throws ConfigurationException
	 * @throws UnSupportedBrowserException
	 * 
	 */
	private BrowserSettings(String browser) throws UnSupportedBrowserException {
		logger.debug("BrowserSettings Constructor. - Browser Selected " + browser);

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
	 * 
	 * @return String (Firefox, IE, HTMLUNIT, Chrome)
	 */
	public String getCurrentBrowser() {
		return currentBrowser.toString();
	}

	private supportedBrowsers getcurreBrowser() {
		System.out.println(currentBrowser.toString());
		return currentBrowser;
	}

	private void setCurrentBrowser(supportedBrowsers currentBrowser) {
		this.currentBrowser = currentBrowser;
	}

	/**
	 * Return the required Browser driver
	 * 
	 * @param supportedBrowsers
	 *            enumerator
	 * @return
	 */
	public WebDriver returnBrowserDriver() {
		WebDriver driver;
		try {
			switch (getcurreBrowser()) {
			case FIREFOX:
				if (returnFirefoxProfile(null) != null) {
					driver = new FirefoxDriver(returnFirefoxProfile(null));
					logger.debug("PROFILE loaded Firefox driver started. ");
				} else {
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
				if (returnFirefoxProfile("IPHONEIOS7") != null) {
					driver = new FirefoxDriver(returnFirefoxProfile("IPHONEIOS7"));
					logger.debug("PROFILE loaded Firefox driver started. ");
				} else {
					driver = new FirefoxDriver();
					logger.debug("Firefox driver started without profiles");
				}
				System.out.println("Firefox");
				break;
			default:
				driver = new FirefoxDriver();
				logger.debug("Defaulting to HTML Unit driver");
				System.out.println("HTML Unit");
				break;
			}

		} catch (Exception x) {
			logger.error(
					"Exception caught in returnBrowserDriver function: {}",
					x.getMessage());

			return null;
		}
		driver.manage()
				.timeouts()
				.implicitlyWait(globalConfiguration.getInt("global.timeout"),
						TimeUnit.SECONDS);
		if (globalConfiguration.getBoolean("window.maximized")){
			driver.manage().window().maximize();	
		}
		
		
		return driver;
	}

	private DesiredCapabilities returnChromeCapabilities()
			throws ConfigurationException {
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		System.setProperty("webdriver.chrome.driver",
				globalConfiguration.getString("ChromeDriver.location"));
		if (globalConfiguration.containsKey("ChromeBin.location")
				&& !globalConfiguration.getString("ChromeBin.location")
						.equals("")) {
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

	private FirefoxProfile returnFirefoxProfile(String deviceBrowser) throws ConfigurationException {
		FirefoxProfile fp=null;

		Iterator<Configuration> keys = globalConfiguration.getKeys();
		if (!keys.hasNext()) {
			fp = null;
		} else {
			
				if (!deviceBrowser.equals(null)){
					fp = new FirefoxProfile();
					fp.setPreference("general.useragent.override", returnUserAgentString(deviceBrowser));
				}
			

		}
		return fp;

	}

	private String returnUserAgentString(String deviceBrowser) throws ConfigurationException {
		
		Iterator<Configuration> keys = globalConfiguration.getKeys();
		if (!keys.hasNext()) {
			return(null);
		} else {
			return(globalConfiguration.getString(deviceBrowser));
		}
		

	}
}