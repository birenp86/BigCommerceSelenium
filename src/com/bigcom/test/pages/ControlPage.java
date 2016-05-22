package com.bigcom.test.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import com.bigcom.test.util.BigComUtil;

public class ControlPage extends BigComUtil {

	WebDriver driver;

	private String checkEmailString = "Check Availability";

	@FindBy(css = "#nav-customers")
	@CacheLookup
	private WebElement customersTab;

	@FindBy(xpath = ".//*[@cp-nav-link='bc.customers.add']")
	private WebElement addCustomerButton;

	@FindBy(css = "#tab-div0")
	private WebElement newCustomerForm;

	@FindBy(id = "content-iframe")
	private WebElement custFrame;

	@FindBy(css = "#custFirstName")
	@CacheLookup
	private WebElement customerFirstName;

	@FindBy(css = "#custLastName")
	@CacheLookup
	private WebElement customerLastName;

	@FindBy(css = "#custEmail")
	@CacheLookup
	private WebElement customerEmail;

	@FindBy(css = ".btn.btn-secondary")
	private List<WebElement> buttonList;

	@FindBy(css = ".alert.alert-success")
	private WebElement emailCheckSuccess;

	@FindBy(css = "#custPassword")
	@CacheLookup
	private WebElement customerPassword;

	@FindBy(css = "#custPasswordConfirm")
	@CacheLookup
	private WebElement customerConfirmPassword;

	@FindBy(css = ".field-action li:nth-child(3)")
	private WebElement saveCustomer;

	@FindBy(css = ".is-alt .customer-email")
	private List<WebElement> customers;

	public ControlPage(WebDriver driver){
		this.driver = driver;
	}

	public void goToAddNewCustomer() {
		clickOn(driver, customersTab);
		clickOn(driver, addCustomerButton);
	}

	public Boolean verifyNewCustomerForm() {
		//verify login
		switchFrame(driver, custFrame);
		return checkVisibility(driver, newCustomerForm);
	}

	public void addCustomerInfo(String fName, String lName, String email, String password) {
		//switch frame
		switchFrame(driver, custFrame);
		//set first name
		clearAndInput(customerFirstName, fName);
		//set last name
		clearAndInput(customerLastName, lName);
		//set email
		clearAndInput(customerEmail, email);
		//check email
		boolean emailVerified = checkEmail();
		if(!emailVerified){
			log.error("Cannot Verify User Email!");
		}
		//set password
		clearAndInput(customerPassword, password);
		clearAndInput(customerConfirmPassword, password);
		clickOn(driver, saveCustomer);
	}	

	public boolean verifyCustomerAdded(String email) {
		for(WebElement wel : customers) {
			if(wel.getText().equals(email)){
				log.info("Found: {}", wel.getText());
				return true;
			}
		}
		return false;
	}

	private boolean checkEmail() {
		WebElement checkEmailAvailability = findButton(driver, buttonList, checkEmailString);
		if(checkEmailAvailability != null) {
			checkEmailAvailability.click();
			return checkVisibility(driver, emailCheckSuccess);
		}
		return false;
	}
}
