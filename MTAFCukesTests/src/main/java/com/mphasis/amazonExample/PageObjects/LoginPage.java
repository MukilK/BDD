package com.mphasis.amazonExample.PageObjects;

import com.mphasis.automation.GlobalInit;
import com.mphasis.automation.webdriver.override.MTAFBy;

public class LoginPage extends GlobalInit {

	private MTAFBy txtEmail = (MTAFBy) MTAFBy.id("ap_email");
	private MTAFBy txtPassword = (MTAFBy) MTAFBy.id("ap_password");
	private MTAFBy btnSignInUsingSecureServer = (MTAFBy) MTAFBy.id("signInSubmit-input");
	private MTAFBy lblinvalidLoginAttemptMessage = (MTAFBy) MTAFBy.id("message_error");

	public void Login(String UserName, String Pwd) {
		execEngine.sendText(txtEmail, UserName);
		execEngine.sendText(txtPassword, Pwd);
		execEngine.click(btnSignInUsingSecureServer);
	}

	public String returnInvalidLoginMessage() {
		execEngine.waitforElement(lblinvalidLoginAttemptMessage, 5);
		return execEngine.getText(lblinvalidLoginAttemptMessage);
	}

}
