//package com.mphasis.jBehave;
//
//import java.io.IOException;
//import java.util.Properties;
//import java.util.concurrent.atomic.AtomicBoolean;
//
//import org.apache.commons.configuration.Configuration;
//import org.apache.commons.configuration.PropertiesConfiguration;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class FrameworkProp_old {
//
//	public static final Logger logger = LoggerFactory
//			.getLogger(FrameworkProp_old.class);
//	private static final String SUITE_LIST_PROPERTY_NAME = "suite.list";
//	private static final AtomicBoolean isPropertiesLoaded = new AtomicBoolean(
//			false);
//	private static final String JRUNNER_CONFIG_FILENAME = "runner.config.file.location";
//	public static final String STORY_LIST_PROPERTY_NAME = "story.list";
//	Configuration configuration;
//
//	public FrameworkProp_old() {
//		configuration = new PropertiesConfiguration(browerConfigPropertiesFile);
//	}
//
//	public static String getSuiteList() {
//		return getProperty(SUITE_LIST_PROPERTY_NAME, true);
//	}
//
//	public static String getProperty(String propertyName, boolean allowEmpty) {
//		return getProperty(propertyName, "", allowEmpty);
//	}
//
//	public static String getProperty(String propertyName, String defaultValue,
//			boolean allowEmpty) {
//
//		if (!isPropertiesLoaded.get())
//			try {
//				isPropertiesLoaded.getAndSet(initAllProperties());
//			} catch (Exception e) {
//				logger.error(
//						"Could not import properties from execution/runtime properties file: "
//								+ e.getMessage(), false);
//			}
//
//		String property = "";
//		try {
//			if ((defaultValue == null) || defaultValue.equals("")) {
//				property = System.getProperty(propertyName);
//				property = (property == null) ? allProperties
//						.getProperty(propertyName) : property;
//			} else {
//				property = System.getProperty(propertyName, defaultValue);
//				property = (property == null) ? allProperties.getProperty(
//						propertyName, defaultValue) : property;
//			}
//		} catch (Exception e) {
//			logger.error("Could not get system property " + propertyName + ": "
//					+ e.getMessage(), false);
//		}
//
//		return property;
//	}
//
//	public static String getStoryList() {
//		return getProperty(STORY_LIST_PROPERTY_NAME, true);
//	}
//
//	synchronized static boolean initAllProperties() throws IOException {
//		if (System.getProperty(JRUNNER_CONFIG_FILENAME) == null) {
//			throw new IllegalArgumentException("System property '"
//					+ JRUNNER_CONFIG_FILENAME + "' was not set");
//		}
//		if (System.getProperty(ATG_CONFIG_FILENAME) == null) {
//			throw new IllegalArgumentException("System property '"
//					+ ATG_CONFIG_FILENAME + "' was not set");
//		}
//
//		Properties runnerProperties = new Properties();
//		runnerProperties.load(new BufferedReader(new FileReader(System
//				.getProperty(JRUNNER_CONFIG_FILENAME))));
//		Properties execProperties = new Properties();
//		execProperties.load(new BufferedReader(new FileReader(System
//				.getProperty(ATG_CONFIG_FILENAME))));
//		runnerProperties.putAll(execProperties);
//		AtgProperties.setAllProperties(runnerProperties);
//		return true;
//	}
//
//	public static String getExcludeStoryList() {
//		return getProperty(EXCLUSE_STORY_LIST_PROPERTY_NAME, true);
//	}
//}
