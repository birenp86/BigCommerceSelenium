package com.bigcom.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import com.bigcom.test.util.BigComUtil;

public class LoginPage extends BigComUtil {

	WebDriver driver;

	@FindBy(css = "#user_email")
	@CacheLookup
	private WebElement userName;

	@FindBy(css = "#user_password")
	@CacheLookup
	private WebElement password;

	@FindBy(css = ".login-form-button")
	private WebElement loginButton;
	
	@FindBy(css = "#nav-dashboard")
	private WebElement dashBoard;

	public LoginPage(WebDriver driver){
		this.driver = driver;
	}

	public void userLogin(String user, String userKey) throws InterruptedException {
		//login
		setUsername(user);
		setPassword(userKey);
		clickLogin();
	}

	public Boolean verifyLogin() {
		//verify login
		return checkVisibility(driver, loginButton);
	}
	
	public Boolean verifyInDashboard() {
		//verify login
		return checkVisibility(driver, dashBoard);
	}

	private void setUsername(String user) {
		//set user name
		clearAndInput(userName, user);
	}

	private void setPassword(String userKey) {
		//set password
		clearAndInput(password, userKey);
	}

	private void clickLogin() throws InterruptedException {
		//click login button
		loginButton.click();
		//wait for successful login
		Thread.sleep(3000);
	}
}
