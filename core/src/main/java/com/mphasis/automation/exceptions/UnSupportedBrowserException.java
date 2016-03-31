package com.mphasis.automation.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnSupportedBrowserException extends Throwable {
    public static final Logger logger = LoggerFactory
            .getLogger(UnSupportedBrowserException.class);

    public UnSupportedBrowserException(String browser) {
        logger.error("Unsupported Browser " + browser);
        System.out.println("Unsupported Browser " + browser);
    }
}
