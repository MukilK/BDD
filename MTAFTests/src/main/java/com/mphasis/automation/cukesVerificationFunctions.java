package com.mphasis.automation;


public class cukesVerificationFunctions {

	public static void verifyEquals(String expected,String actual){
		
		org.junit.Assert.assertEquals(expected, actual);
	}
	
	
}
