package com.bigcom.test.ng;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bigcom.test.pages.ControlPage;
import com.bigcom.test.pages.LoginPage;
import com.bigcom.test.util.Env;
import com.bigcom.test.util.RootTest;

public class NewCustomerSuite extends RootTest {

	private LoginPage loginPage;
	private ControlPage controlPage;
	String user = Env.user;
	String userKey = Env.userkey;

	@BeforeClass
	public void setEnv() {
		try {
			//set Environment and login based on properties file
			log.info(S);
			driver = super.setTestEnv();
			driver.get(HTTPS + Env.bouncerFrontEnd);
			driver.manage().window().maximize();
			loginPage = PageFactory.initElements(driver, LoginPage.class);
			controlPage = PageFactory.initElements(driver, ControlPage.class);
			loginPage.userLogin(user, userKey);
			Assert.assertTrue(loginPage.verifyInDashboard(), "Login Failed");
			log.info(E);
		} catch (Exception e) {
			log.info(e.getLocalizedMessage());
			e.printStackTrace();
			log.info(F);
			Assert.fail(e.getLocalizedMessage() + "\n" + e.getStackTrace());
		}
	}

	@Parameters({"firstName", "lastName", "emailPreFix", "emailPostFix", "password"})
	@Test(enabled = true)
	public void testAddCustomer(@Optional("firstName") String firstName, @Optional("lastName") String lastName, 
			@Optional("bigcommerce") String emailPreFix, @Optional("@bigcom.com") String emailPostFix,
			@Optional("bigcommerce123") String password) {
		try {
			log.info(S);
			controlPage = PageFactory.initElements(driver, ControlPage.class);
			controlPage.goToAddNewCustomer();
			//Assert.assertTrue(controlPage.verifyNewCustomerForm(), "Not in New Customer Page");
			String email = generateUniqueEmail(emailPreFix, emailPostFix);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			controlPage.addCustomerInfo(firstName, lastName, email, password);
			Assert.assertTrue(controlPage.verifyCustomerAdded(email), "Customer not Added");
			log.info(E);
		} catch (Exception e) {
			log.info(e.getLocalizedMessage());
			e.printStackTrace();
			log.info(F);
			Assert.fail(e.getLocalizedMessage() + "\n" + e.getStackTrace());
		}
	}

	private String generateUniqueEmail(String emailPreFix, String emailPostFix) {
		return (emailPreFix + "+" + System.currentTimeMillis() + emailPostFix);
	}
}
