package com.mphasis.automation;

import com.mphasis.automation.ApplicationInterface.DriverFunctions;

public class GlobalInit {
	public static DriverFunctions execEngine = MTAFTestSetup
			.getInstance().execEngine;
	public static MTAFCore mtaf = MTAFTestSetup.getInstance().mtaf;
}