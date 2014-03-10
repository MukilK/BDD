package com.mphasis.automation.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnsupportedBrowserDimension extends Throwable {

	public static final Logger logger = LoggerFactory
			.getLogger(UnsupportedBrowserDimension.class);

	public UnsupportedBrowserDimension(String windowSizePassed) {
		logger.error("Expected browser dimension format is 'width*height', Current Passed dimention format is "
				+ windowSizePassed);
		System.out
				.println("Expected browser dimension format is 'width*height', Current Passed dimention format is "
						+ windowSizePassed);

	}
}
