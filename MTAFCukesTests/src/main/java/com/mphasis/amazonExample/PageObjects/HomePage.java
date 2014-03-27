package com.mphasis.amazonExample.PageObjects;

import com.mphasis.automation.GlobalInit;
import com.mphasis.automation.webdriver.override.MTAFBy;

public class HomePage extends GlobalInit {

	private MTAFBy lblSignInName = (MTAFBy) MTAFBy.id("nav-signin-text");
	private MTAFBy lnkLogoff = (MTAFBy) MTAFBy.id("nav-item-signout");
	private MTAFBy txtSearchBox = (MTAFBy) MTAFBy.id("twotabsearchtextbox");
	private MTAFBy btnStartSearch = (MTAFBy) MTAFBy.className("nav-submit-input");
	private MTAFBy menuSignInTitle = (MTAFBy) MTAFBy.id("nav-signin-title");

	public String isUserLoggedIn() {
		execEngine.waitforElement(lblSignInName, 5);
		return execEngine.getText(lblSignInName);
	}

	public void logOff() {
		execEngine.mouseHover(menuSignInTitle);
		execEngine.waitforElement(lnkLogoff, 2);
		execEngine.click(lnkLogoff);
	}

	public void searchForItem(String itemToSearchFor) {
		execEngine.sendText(txtSearchBox, itemToSearchFor);
		execEngine.click(btnStartSearch);
	}
}
