package com.chase.ncj.PageObjects;

import org.openqa.selenium.By;

import com.mphasis.automation.GlobalInit;

public class PersonalPage  extends GlobalInit{

	private By txtFirstName = By.id("sFirstName");
	private By txtMiddleInitial= By.id("sMiddleInitial");
	private By txtLastName = By.id("sLastName");
	private By lstSuffix = By.id("sSuffix");
	private By txtAddressOne= By.id("sStreetAddr1");
	private By txtAddressTwo= By.id("sStreetAddr2");
	private By txtApartmentUnitNumber = By.id("sApartment");
	private By txtCity = By.id("sCity");
	private By lstState= By.id("sState1");
	private By txtZip = By.id("sZip");
	private By btnNext=By.id("next1");
	private By bdyPersonalPage = By.tagName("body");

	
	public boolean isOnPersonalPage(String txtHeader) {
		

		if (execEngine.getText(bdyPersonalPage).contains(txtHeader)) {
			return true;
		}else{
			return false;
		}
		
	}
	
	
	public void fillNameDetails(String fName, String initial, String lName, String suffix ){
		execEngine.sendText(txtFirstName, fName);
		execEngine.sendText(txtMiddleInitial, initial);
		execEngine.sendText(txtLastName, lName);
		execEngine.selectFromList(lstSuffix, suffix);
		
	}
	
	public void fillAddress(String addressOne, String addressTwo, String Apt, String City, String State, String zipCode ){
		execEngine.sendText(txtAddressOne, addressOne);
		execEngine.sendText(txtAddressTwo, addressTwo);
		execEngine.sendText(txtApartmentUnitNumber, Apt);
		execEngine.sendText(txtCity, City);
		execEngine.selectFromList(lstState, State);
		execEngine.sendText(txtZip, zipCode);
		
	}
	
	public void submit(){
		execEngine.click(btnNext);
	}
	
	
	
}
