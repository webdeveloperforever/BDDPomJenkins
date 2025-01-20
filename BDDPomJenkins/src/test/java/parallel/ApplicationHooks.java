package parallel;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;
import com.qa.util.ScreenshotUtil;
import com.qa.util.SendEmail;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {

	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	private ScreenshotUtil screenshotutil;
	private SendEmail sendemail;
	Properties prop;

	@Before(order = 0)
	public void getProperty() {
		configReader = new ConfigReader();
		prop = configReader.init_prop();
	}

	@Before(order = 1)
	public void launchBrowser() {
		String browserName = prop.getProperty("browser");
		driverFactory = new DriverFactory();
		driver = driverFactory.init_driver(browserName);
		
	}

	@After(order = 0)
	public void quitBrowser() {
		driver.quit();
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			configReader = new ConfigReader();
			prop = configReader.init_prop();
			String ServiceProvider=prop.getProperty("serviceprovider");
			String environment;
			if(prop.getProperty("excelpath").contains("QA")) {
				environment="QA";
			}else {
				environment="UAT";
			}
			if(prop.getProperty("sendscenarioerroremail").equals("true")) {
			screenshotutil=new ScreenshotUtil();
			String screenshotPath=screenshotutil.captureScreenshot(driver,scenario.getName());
			String toEmail="qatesthonda001@gmail.com";
			String subject="Test Failure:"+scenario.getName();
			String body="The Following Scenario has failed:"+scenario.getName()+"\n in "+ServiceProvider+" "+environment+" \n Please find the attached screenshot.";
			sendemail=new SendEmail();
			sendemail.sendEmail(toEmail, subject, body, screenshotPath);
			}
		}
	}

}
