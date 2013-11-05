package com.mphasis.automation;

import com.mphasis.automation.ApplicationInterface.DriverFunctions;

public class GlobalInit {
	public static DriverFunctions execEngine = MTAFTestSetupForCukes
			.getInstance().execEngine;
	public static MTAFCore mtaf = MTAFTestSetupForCukes.getInstance().mtaf;
}
