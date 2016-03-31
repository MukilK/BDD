package com.mphasis.automation;

import org.junit.Assert;

public class VerificationFunctions {

    public static void verifyEquals(String expected, String actual) {

        Assert.assertEquals(expected, actual);
    }

    public static void verifyTrue(boolean actual) {

        Assert.assertTrue(actual);
    }

    public static void verifyFalse(boolean actual) {

        Assert.assertTrue(actual);
    }


}
