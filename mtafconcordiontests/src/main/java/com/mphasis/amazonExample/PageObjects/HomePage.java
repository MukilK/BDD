package com.mphasis.amazonExample.PageObjects;

import org.openqa.selenium.By;

import com.mphasis.automation.GlobalInit;

public class HomePage extends GlobalInit {

	private By lblSignInName = By.id("nav-signin-text");
	private By lnkLogoff = By.id("nav-item-signout");
	private By txtSearchBox = By.id("twotabsearchtextbox");
	private By btnStartSearch = By.className("nav-submit-input");
	private By menuSignInTitle = By.id("nav-signin-title");

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
