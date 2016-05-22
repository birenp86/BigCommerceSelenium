package com.bigcom.test.util;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BigComUtil {
	public static Logger log = (Logger) LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());
	public Env env = Env.instance();

	public void clearAndInput(WebElement wel, String inputString) {
		wel.clear();
		wel.sendKeys(inputString);
	}

	public boolean checkVisibility (WebDriver driver, WebElement wel) {
		return wel.isDisplayed();
	}

	public void clickOn (WebDriver driver, WebElement wel) {
		wel.click();
	}

	public void switchFrame (WebDriver driver, WebElement wel) {
		driver.switchTo().frame(wel);
	}

	public WebElement findButton(WebDriver driver, List<WebElement> buttonList, String checkString) {
		for(WebElement wel : buttonList){
			if(wel.getText().equals(checkString)){
				return wel;
			}
		}
		return null;
	}
}
