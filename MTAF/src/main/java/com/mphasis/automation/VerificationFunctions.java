package com.mphasis.automation;

import mx4j.log.Logger;

import org.junit.Assert;

public class VerificationFunctions {

	public static void verifyEquals(String expected, String actual) {

		Assert.assertEquals(expected, actual);
	}

}
