package com.mphasis.amazonExample.PageObjects;

import org.openqa.selenium.By;

import com.mphasis.automation.GlobalInit;

public class HomePage extends GlobalInit {

	private By lblUserID = By.id("nav-your-amazon");
	private By lnkLogoff=By.id("nav-item-signout");
	private By txtSearchBox=By.id("twotabsearchtextbox");
	private By btnStartSearch=By.className("nav-submit-input");
	
	public String isUserLoggedIn(){
		execEngine.waitforElement(lblUserID, 5);
		return execEngine.getText(lblUserID);
	}
	
	public void logOff(){
		execEngine.click(lnkLogoff);
	}
	
	public void searchForItem(String itemToSearchFor){
		execEngine.sendText(txtSearchBox, itemToSearchFor);
		execEngine.click(btnStartSearch);
	}

}
