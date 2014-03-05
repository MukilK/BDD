package com.mphasis.automation.exceptions;

public class UnSupportedBrowserException extends Throwable{

	public UnSupportedBrowserException(String browser){
		System.out.println("Unsupported Browser "+ browser);
	}
}
