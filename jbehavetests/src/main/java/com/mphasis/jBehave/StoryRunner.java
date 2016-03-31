package com.mphasis.jBehave;

import java.util.Arrays;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.spring.SpringApplicationContextFactory;
import org.springframework.context.ApplicationContext;

public class StoryRunner extends JUnitStories {

	StoryReporterBuilder storyReporterBuilder = new StoryReporterBuilder()
			.withCodeLocation(
					CodeLocations.codeLocationFromClass(this.getClass()))
			.withDefaultFormats()
			.withFailureTrace(true)
			.withFailureTraceCompression(true)
			.withFormats(Format.HTML, Format.CONSOLE, Format.IDE_CONSOLE,
					Format.XML).withRelativeDirectory("jbehave");
	protected ApplicationContext applicationContext;
	protected String contextPath;
	protected String testingTags;
	protected static final String PROPERTY_DO_DRY_RUN = "doDryRun";

	protected String getContextPath() {
		return contextPath;
	}

	public StoryRunner() {
		this.contextPath = "/applicationContext.xml";
		setEmbedderControls();
	}

	protected void setEmbedderControls() {
		configuredEmbedder().embedderControls()
				.doGenerateViewAfterStories(true)
				.doIgnoreFailureInStories(true).doIgnoreFailureInView(true)
				.useStoryTimeoutInSecs(300).doVerboseFailures(true);
	}

	@Override
	public Configuration configuration() {
		ParameterConverters parameterConverters = new ParameterConverters();
		ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(
				new LocalizedKeywords(),
				new LoadFromClasspath(this.getClass()), parameterConverters);
		parameterConverters
				.addConverters(new ParameterConverters.StringListConverter());
		parameterConverters
				.addConverters(new ParameterConverters.ExamplesTableConverter(
						examplesTableFactory));

		return new MostUsefulConfiguration()
				.useStoryReporterBuilder(storyReporterBuilder)
				.useParameterControls(
						new ParameterControls()
								.useDelimiterNamedParameters(true))
				.usePendingStepStrategy(new FailingUponPendingStep())
				.useParameterConverters(parameterConverters)
				.doDryRun(Boolean.getBoolean(PROPERTY_DO_DRY_RUN))
				.useStoryParser(new RegexStoryParser(examplesTableFactory));
	}

	@Override
	public SpringTaggedStepsFactory stepsFactory() {
		return new SpringTaggedStepsFactory(testingTags, configuration(),
				createContext(), storyReporterBuilder);
	}

	protected void initProperties() {
		testingTags = System.getProperty("testing.driver.tags", null);

	}

	protected ApplicationContext createContext() {
		if (applicationContext == null) {
			applicationContext = new SpringApplicationContextFactory(
					getContextPath()).createApplicationContext();
		}

		return applicationContext;
	}

	@Override
	protected List<String> storyPaths() {
		StoryFinder finder = new StoryFinder();

		return finder.findPaths(
				CodeLocations.codeLocationFromClass(this.getClass()).getFile(),
				Arrays.asList("**/*.story"), Arrays.asList(""));
	}

}
