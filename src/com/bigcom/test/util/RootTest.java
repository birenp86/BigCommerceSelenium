package com.bigcom.test.util;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;

public class RootTest {

	public static Logger log = (Logger) LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());
	public static Env env = Env.instance();
	public static int ImplicWait = 25 * Env.waitMultiplier;
	public static int LoadWait = 25 * Env.waitMultiplier;
	public static final String HTTPS = "https://";
	public static final String F = "METHOD FAIL";
	public static final String E = "METHOD END";
	public static final String S = "METHOD START";
	public static final String SK = "METHOD SKIP";
	public RemoteWebDriver driver = null;

	public RemoteWebDriver setTestEnv() throws Exception {
		RemoteWebDriver driver = getWebDriver(DesiredCapabilities.firefox(), Env.selHost);
		return driver;
	}

	public RemoteWebDriver getWebDriver(DesiredCapabilities capability, String selHub) throws Exception {
		WebDriver driver = null;
		log.info("selHub = " + selHub);
		if (selHub.contains(Env.LOCAL)) {
			log.info("FIREFOX");
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(ImplicWait, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(LoadWait, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return (RemoteWebDriver) driver;
	}

	@AfterTest(alwaysRun = true)
	public void closeDriver() {
		if (null != driver) {
			try {
				driver.close();
			} catch (Exception e) {
				log.info(F);
				log.info(e.getLocalizedMessage());
				e.printStackTrace();
			}
		}
	}
}
