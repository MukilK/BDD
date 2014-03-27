package com.chase.ncj.PageObjects;

import com.mphasis.automation.GlobalInit;
import com.mphasis.automation.webdriver.override.MTAFBy;

public class PersonalPage extends GlobalInit {

	private MTAFBy txtFirstName =(MTAFBy) MTAFBy.id("sFirstName");
	private MTAFBy txtMiddleInitial =(MTAFBy) MTAFBy.id("sMiddleInitial");
	private MTAFBy txtLastName =(MTAFBy) MTAFBy.id("sLastName");
	private MTAFBy lstSuffix =(MTAFBy) MTAFBy.id("sSuffix");
	private MTAFBy txtAddressOne =(MTAFBy) MTAFBy.id("sStreetAddr1");
	private MTAFBy txtAddressTwo =(MTAFBy) MTAFBy.id("sStreetAddr2");
	private MTAFBy txtApartmentUnitNumber =(MTAFBy) MTAFBy.id("sApartment");
	private MTAFBy txtCity =(MTAFBy) MTAFBy.id("sCity");
	private MTAFBy lstState =(MTAFBy) MTAFBy.id("sState1");
	private MTAFBy txtZip =(MTAFBy) MTAFBy.id("sZip");
	private MTAFBy btnNext =(MTAFBy) MTAFBy.id("next1");
	private MTAFBy bdyPersonalPage =(MTAFBy) MTAFBy.tagName("body");
	private MTAFBy nodeAddAddressLine2 =(MTAFBy) MTAFBy.id("add-address-line-2-trigger");

	public boolean isOnPersonalPage(String txtHeader) {

		if (execEngine.getText(bdyPersonalPage).contains(txtHeader)) {
			return true;
		} else {
			return false;
		}

	}

	public void fillNameDetails(String fName, String initial, String lName,
			String suffix) {
		execEngine.sendText(txtFirstName, fName);
		execEngine.sendText(txtMiddleInitial, initial);
		execEngine.sendText(txtLastName, lName);
		execEngine.selectFromList(lstSuffix, suffix);

	}

	public void fillAddress(String addressOne, String addressTwo, String Apt,
			String City, String State, String zipCode) {
		execEngine.sendText(txtAddressOne, addressOne);
		if (mtafCore.returnDeviceType().equals("iphone5")) {
			execEngine.click(nodeAddAddressLine2);
		}
		execEngine.sendText(txtAddressTwo, addressTwo);
		execEngine.sendText(txtApartmentUnitNumber, Apt);
		execEngine.sendText(txtCity, City);
		execEngine.selectFromList(lstState, State);
		execEngine.sendText(txtZip, zipCode);

	}

	public void submit() {
		execEngine.click(btnNext);
	}

}
