package com.mphasis.automation;

import java.util.Iterator;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mphasis.automation.exceptions.UnSupportedBrowserException;

public class BrowserSettings {

	public enum supportedBrowsers {

		CHROME, FIREFOX, IEXPLORE, HTMLUNIT
	};

	private supportedBrowsers currentBrowser;
	public static final Logger logger = LoggerFactory
			.getLogger(BrowserSettings.class);
	private static String browerConfigPropertiesFile = "BrowserConfig.properties";
	Configuration configuration;

	public BrowserSettings() throws UnSupportedBrowserException,
			ConfigurationException {

		this((new PropertiesConfiguration(browerConfigPropertiesFile))
				.getString("current.browser"));
		//configuration is instantiated here since it throws an error & needs to be handled.
		configuration = new PropertiesConfiguration(browerConfigPropertiesFile);
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
		logger.debug("BrowserSettings Constructor.");

		for (supportedBrowsers sBrowser : supportedBrowsers.values()) {
			if (sBrowser.toString().toLowerCase().equals(browser.toLowerCase())) {
				setCurrentBrowser(sBrowser);
				return;
			}
		}
		throw new UnSupportedBrowserException();
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
				if (returnFirefoxProfile() != null) {
					driver = new FirefoxDriver(returnFirefoxProfile());
					logger.debug("PROFILE loaded Firefox driver started. ");
				} else {
					driver = new FirefoxDriver();
					logger.debug("Firefox driver started without profiles");
				}
				break;
			case IEXPLORE:
				driver = new InternetExplorerDriver();
				logger.debug("Internet Explorer driver started");
				break;
			case CHROME:
				driver = new ChromeDriver(returnChromeCapabilities());
				logger.debug("Chrome driver started.");
				break;
			default:
				driver = new HtmlUnitDriver();
				logger.debug("Defaulting to HTML Unit driver");
			}

		} catch (Exception x) {
			logger.error(
					"Exception caught in returnBrowserDriver function: {}",
					x.getMessage());

			return null;
		}
		return driver;
	}

	private DesiredCapabilities returnChromeCapabilities()
			throws ConfigurationException {
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		System.setProperty("webdriver.chrome.driver",
				configuration.getString("ChromeDriver.location"));
		capabilities.setCapability("chrome.binary",
				configuration.getString("ChromeBin.location"));
		return capabilities;
	}

	private FirefoxProfile returnFirefoxProfile() throws ConfigurationException {
		FirefoxProfile fp;
		Iterator<Configuration> keys = configuration.getKeys();
		if (!keys.hasNext()) {
			fp = null;
		} else {

			// TODO Add Logic for handling profile

			fp = new FirefoxProfile();
		}
		return fp;

	}
}