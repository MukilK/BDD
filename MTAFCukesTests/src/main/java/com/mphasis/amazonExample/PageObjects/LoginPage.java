package com.mphasis.amazonExample.PageObjects;

import org.openqa.selenium.By;

import com.mphasis.automation.GlobalInit;

public class LoginPage extends GlobalInit {

	private By txtEmail = By.id("ap_email");
	private By txtPassword = By.id("ap_password");
	private By btnSignInUsingSecureServer = By.id("signInSubmit-input");
	private By lblinvalidLoginAttemptMessage = By.id("message_error");

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
