//package com.mphasis.jBehave;
//
//import static java.util.Arrays.asList;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import org.jbehave.core.configuration.Configuration;
//import org.jbehave.core.configuration.MostUsefulConfiguration;
//import org.jbehave.core.failures.FailingUponPendingStep;
//import org.jbehave.core.i18n.LocalizedKeywords;
//import org.jbehave.core.io.CodeLocations;
//import org.jbehave.core.io.LoadFromClasspath;
//import org.jbehave.core.io.StoryFinder;
//import org.jbehave.core.junit.JUnitStories;
//import org.jbehave.core.model.ExamplesTableFactory;
//import org.jbehave.core.parsers.RegexStoryParser;
//import org.jbehave.core.reporters.Format;
//import org.jbehave.core.reporters.StoryReporterBuilder;
//import org.jbehave.core.steps.ParameterControls;
//import org.jbehave.core.steps.ParameterConverters;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public abstract class StoryRunner_old extends JUnitStories {
//
//	protected String contextPath;
//	private String metaFilters;
//	protected String testingTags;
//	private static List<String> suiteList;
//	public static List<String> storiesList;
//	public static List<String> excludeStoriesList;
//	public static List<String> alreadyRunStoriesList = new ArrayList<String>();
//	public static List<String> alreadyExcludeStoriesList = new ArrayList<String>();
//	public static final Logger logger = LoggerFactory
//			.getLogger(StoryRunner_old.class);
//	public static final String STORY_POSTFIX = ".story";
//	
//	
//	StoryReporterBuilder storyReporterBuilder = new StoryReporterBuilder()
//			.withCodeLocation(
//					CodeLocations.codeLocationFromClass(this.getClass()))
//			.withDefaultFormats()
//			.withFailureTrace(true)
//			.withFailureTraceCompression(true)
//			.withFormats(Format.HTML, Format.CONSOLE, Format.IDE_CONSOLE,
//					Format.XML).withRelativeDirectory("jbehave");
//
//	public StoryRunner_old() {
//		setEmbedderControls();
//	}
//
//	public StoryRunner_old(String contextPath) {
//		this.contextPath = contextPath;
//		setEmbedderControls();
//	}
//
//	protected void setEmbedderControls() {
//		configuredEmbedder().embedderControls()
//				.doGenerateViewAfterStories(true)
//				.doIgnoreFailureInStories(true).doIgnoreFailureInView(true)
//				.useStoryTimeoutInSecs(300).doVerboseFailures(true);
//	}
//
//	protected void initProperties() {
//		testingTags = System.getProperty("testing.driver.tags", null);
//		metaFilters = System.getProperty("meta.filters", null);
//	}
//
//	private List<String> getMetaFilters() {
//		return asList(metaFilters.split(","));
//	}
//
//	private List<String> getTestingTags() {
//		return asList(testingTags.split(","));
//	}
//
//	public List<String> getSuiteList() {
//		if (suiteList == null) {
//			setSuiteList();
//		}
//		return suiteList;
//	}
//
//	private void setSuiteList() {
//		suiteList = new ArrayList<String>();
//		String suitesFromProperty = FrameworkProp.getSuiteList();
//		if ((suitesFromProperty != null) && !suitesFromProperty.equals("")) {
//			suiteList = asList(suitesFromProperty.split(","));
//		}
//	}
//
//	private List<String> metaFiltersFromTags() {
//		List<String> metaTags = new ArrayList<String>();
//
//		for (String tag : getTestingTags()) {
//			metaTags.add("+tag_" + tag);
//		}
//		return metaTags;
//	}
//
//	protected List<String> combinedMetaFilters() {
//		List<String> combined = new ArrayList<String>();
//		if (testingTags != null) {
//			combined.addAll(metaFiltersFromTags());
//		}
//
//		if (metaFilters != null) {
//			combined.addAll(getMetaFilters());
//		}
//
//		return combined;
//	}
//
//	protected List<String> storyPaths() {
//		return storiesForRunning(includeStories(), excludeStories());
//	}
//
//	protected abstract List<String> includeStories();
//
//	protected abstract List<String> excludeStories();
//
//	protected List<String> storiesForRunning(List<String> includeStories,
//			List<String> excludeStories) {
//		List<String> stories = new StoryFinder().findPaths(
//				org.jbehave.core.io.CodeLocations.codeLocationFromClass(
//						this.getClass()).getFile(), includeStories,
//				excludeStories);
//		List<String> runStoryList = getStoriesList();
//		List<String> excludeStoryList = getExcludeStoriesList();
//
//		List<String> storiesForDeletion = new ArrayList<String>();
//
//		if (runStoryList.size() == 0) {
//			if (excludeStoryList.size() != 0) {
//				for (String story : stories) {
//					for (String excStory : excludeStoryList) {
//						if (story.contains(excStory + STORY_POSTFIX)
//								&& !alreadyExcludeStoriesList
//										.contains(excStory)) {
//							storiesForDeletion.add(story);
//							alreadyExcludeStoriesList.add(excStory);
//						}
//					}
//				}
//			}
//			;
//			if (storiesForDeletion.size() > 0) {
//				stories.removeAll(storiesForDeletion);
//			}
//			return stories;
//		}
//
//		List<String> filteredStories = new ArrayList<String>();
//
//		for (String story : stories) {
//			for (String runStory : runStoryList) {
//				if (story.contains(runStory + STORY_POSTFIX)
//						&& !alreadyRunStoriesList.contains(runStory)) {
//					filteredStories.add(story);
//					alreadyRunStoriesList.add(runStory);
//				}
//			}
//			if (excludeStoryList.size() != 0) {
//				for (String excStory : excludeStoryList) {
//					if (story.contains(excStory + STORY_POSTFIX)) {
//						filteredStories.remove(story);
//					}
//				}
//			}
//		}
//
//		return filteredStories;
//	}
//
//	public List<String> getStoriesList() {
//		if (storiesList == null) {
//			setStoriesList();
//		}
//		return storiesList;
//	}
//
//	private void setStoriesList() {
//		String storyProperty = FrameworkProp.getStoryList();
//		if (storyProperty != null && storyProperty != "") {
//			List<String> storiesListFromProperty = asList(storyProperty
//					.split(","));
//			storiesList = storiesListFromProperty;
//		} else {
//			storiesList = Collections.emptyList();
//		}
//	}
//
//	public List<String> getExcludeStoriesList() {
//		if (excludeStoriesList == null) {
//			setExcludeStoriesList();
//		}
//		return excludeStoriesList;
//	}
//
//	private void setExcludeStoriesList() {
//		String storyProperty = FrameworkProp.getExcludeStoryList();
//		if (storyProperty != null && storyProperty != "") {
//			List<String> storiesListFromProperty = asList(storyProperty
//					.split(","));
//			excludeStoriesList = storiesListFromProperty;
//		} else {
//			excludeStoriesList = Collections.emptyList();
//		}
//	}
//
//	@Override
//	public Configuration configuration() {
//		ParameterConverters parameterConverters = new ParameterConverters();
//		ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(
//				new LocalizedKeywords(),
//				new LoadFromClasspath(this.getClass()), parameterConverters);
//
//		parameterConverters.addConverters(new NullAndEmptyStringConverter());
//		parameterConverters
//				.addConverters(new ParameterConverters.StringListConverter());
//		parameterConverters
//				.addConverters(new ParameterConverters.ExamplesTableConverter(
//						examplesTableFactory));
//
//		return new MostUsefulConfiguration()
//				.useStoryReporterBuilder(storyReporterBuilder)
//				.useParameterControls(
//						new ParameterControls()
//								.useDelimiterNamedParameters(true))
//				.usePendingStepStrategy(new FailingUponPendingStep())
//				.useParameterConverters(parameterConverters)
//				.doDryRun(Boolean.getBoolean(PROPERTY_DO_DRY_RUN))
//				.useStoryParser(new RegexStoryParser(examplesTableFactory));
//	}
//
//	protected ApplicationContext getApplicationContext() {
//		return applicationContext;
//	}
//
//	protected ApplicationContext createContext() {
//		if (applicationContext == null) {
//			applicationContext = new SpringApplicationContextFactory(
//					getContextPath()).createApplicationContext();
//		}
//
//		return applicationContext;
//	}
//
//	@Override
//	public SpringTaggedStepsFactory stepsFactory() {
//		return new SpringTaggedStepsFactory(testingTags, configuration(),
//				createContext(), storyReporterBuilder);
//	}
//
//	protected void beforeRun() {
//		logger.info("Suite: " + this.getClass().getSimpleName()
//				+ ". Before run.");
//	}
//
//	protected void afterRun() {
//		logger.info("Suite: " + this.getClass().getSimpleName()
//				+ ". After run.");
//	}
//
//	protected void beforeSuite() {
//	}
//
//	@Test
//	public void run() throws Throwable {
//		String suiteName = this.getClass().getSimpleName();
//
//		suiteList = getSuiteList();
//
//		/**
//		 * Two types of situation can happened: 1. suite list is empty or null,
//		 * that's why we should run all list from the template 2. suite list
//		 * contains the suite name, need to run stories from this suite
//		 */
//
//		if ((suiteList == null) || suiteList.isEmpty()
//				|| suiteList.contains(suiteName)) {
//			List<String> storyPath = storyPaths();
//			if (storyPath.size() == 0) {
//				logger.info(suiteName + ": empty list of stories for running.");
//				return;
//			}
//			beforeSuite();
//			configuredEmbedder().useSystemProperties(
//					FrameworkProp.getAllProperties());
//			initProperties();
//			configuredEmbedder().useEmbedderMonitor(
//					new CustomPrintStreamEmbedderMonitor(LoggerFactory
//							.getGlobalFileStream()));
//			configuredEmbedder().useMetaFilters(combinedMetaFilters());
//			try {
//				beforeRun();
//				configuredEmbedder().runStoriesAsPaths(storyPath);
//			} finally {
//				configuredEmbedder().generateCrossReference();
//				afterRun();
//			}
//		} else {
//			logger.info("Suite with name '" + suiteName
//					+ "' present in the suites run template,"
//					+ " but not in the list of running suites '" + suiteList
//					+ "'.");
//		}
//	}
//
//}
