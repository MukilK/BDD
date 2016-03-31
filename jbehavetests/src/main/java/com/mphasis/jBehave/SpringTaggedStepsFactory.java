package com.mphasis.jBehave;


import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class SpringTaggedStepsFactory extends SpringStepsFactory {
    private String SCREENSHOT_BEAN = "screenshots";

    private ApplicationContext context;
    private String[] testingTags;

    public SpringTaggedStepsFactory(String testingTags, Configuration configuration,
                                    ApplicationContext context, StoryReporterBuilder reporter) {
        super(configuration, context);
        this.context = context;
        if(context.containsBean(SCREENSHOT_BEAN)) {
         //TODO Add support for screenshot
        }

        if (testingTags == null || testingTags.equals(""))
            return;
        this.testingTags = testingTags.split(",");
    }

    @Override
    protected List<Class<?>> stepsTypes() {
        List<Class<?>> types = new ArrayList<Class<?>>();
        for (String name : context.getBeanDefinitionNames()) {
            Class<?> type = context.getType(name);
            if (isAllowed(type) &&
                    (hasMatchingTagAnnotation(type) ||
                            isUntagged(type))) {
                types.add(type);
            }
        }
        return types;
    }

    private boolean hasTag(String expectedTag) {
        if (testingTags == null || testingTags.length == 0)
            return false;
        for (String tag : testingTags) {
            if (tag.equals(expectedTag)) {
                return true;
            }
        }
        return false;
    }

    private boolean isUntagged(Type type) {
        if (type instanceof Class<?>) {
            Annotation[] annotations = ((Class) type).getAnnotations();
            for (Annotation annotation : annotations) {
                if (Tag.class.isInstance(annotation)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean hasMatchingTagAnnotation(Type type) {
        if (type instanceof Class<?>) {
            Annotation[] annotations = ((Class) type).getAnnotations();
            for (Annotation annotation : annotations) {
                if (Tag.class.isInstance(annotation)) {
                    List<String> tags = Arrays.asList(((Tag) annotation).value());
                    if (tags.isEmpty()) {
                        return true;
                    }
                    if (testingTags != null && testingTags.length != 0)
                        for (String testingTag : testingTags) {
                            if (tags.contains(testingTag)) {
                                return true;
                            }
                        }
                }
            }
        }
        return false;
    }
}
