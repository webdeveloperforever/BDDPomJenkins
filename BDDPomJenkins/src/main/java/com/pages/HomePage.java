package com.pages;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.json.JsonException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.util.ConfigReader;
import com.qa.util.ExcelUtils;

public class HomePage {
	private WebDriver driver;
	private ConfigReader configReader;
	private Properties prop;
	private ExcelUtils excelUtils;
	List<String> serviceProviderurls;
	String serviceProviderUrl;
	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/c-own-global-header[1]/div[2]/div[1]/header[1]/div[1]/div[3]/lightning-layout[1]/slot[1]/c-own-profile[1]/ul[1]/li[2]/div[1]")
	WebElement MygarageuserNameField;
	@FindBy(xpath = "/html[1]/body[1]/div[3]/header[1]/div[1]/div[1]/c-header-component[1]/header[1]/div[1]/div[2]/div[1]/div[2]/label[1]")
	WebElement DreamshopuserNameField;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/c-own-global-header[1]/div[2]/div[1]/header[1]/div[1]/div[3]/lightning-layout[1]/slot[1]/c-own-profile[1]/ul[1]/li[1]/div[1]/button[1]/span[1]/img[1]")
	WebElement userIcon;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/c-own-global-header[1]/div[2]/div[1]/header[1]/div[1]/div[3]/lightning-layout[1]/slot[1]/c-own-profile[1]/ul[1]/li[1]/div[1]/div[1]/ul[1]/li[2]/a[1]/span[1]")
	WebElement logout;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/header[1]/div[1]/div[1]/c-header-component[1]/section[1]/div[1]/div[4]/lightning-button[1]/button[1]")
	WebElement dreamshoplogout;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/nav[1]/div[1]/div[2]/p[1]")
	WebElement ahe_hhe_logout;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/nav[1]/div[1]/div[2]/div[1]/div[2]/span[2]")
	WebElement AcuraCertifieduserNameField;

	@FindBy(xpath = "(//*[name()='path'][@stroke='black'])[1]")
	WebElement acuraCertifieduserIcon;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/nav[1]/div[1]/div[2]/div[1]/div[2]/ul[1]/li[5]/a[1]")
	WebElement acuraCertifiedLogout;

	@FindBy(xpath = "//a[normalize-space()='Edit Profile']")
	WebElement acuraCertifiedProfile;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/nav[1]/div[1]/div[2]/div[1]/div[1]/button[1]/span[1]/*[name()='svg'][1]")
	WebElement userIconAcuraCertified;

	@FindBy(xpath = "//a[normalize-space()='Sign In']")
	WebElement loginButtonAcuraCertified;

	@FindBy(xpath = "//body/div/nav[@aria-label='Main Menu']/div/div/div/div[2]/div[1]/div[1]/span[1]")
	WebElement AcurauserNameField;

	@FindBy(xpath = "//button[@data-expanded-class='acr-nav-account--expanded']")
	WebElement acurauserIcon;

	@FindBy(xpath = "(//span[contains(text(),'Sign Out')])[2]")
	WebElement acuraLogout;

	@FindBy(xpath = "(//span[contains(text(),'Account Information')])[2]")
	WebElement acuraProfile;

	@FindBy(xpath = "//body/div/nav[@aria-label='Main Menu']/div/div/div/div/div/div/ul/li[1]/a[1]")
	WebElement loginButtonAcura;

	@FindBy(xpath = "(//*[name()='svg'][@aria-label='profile'])[2]")
	WebElement userIconAcura;

	@FindBy(xpath = "//button[@class='slds-button slds-button_outline-brand slds-m-left_x-small login-button']")
	WebElement login;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/c-own-global-header[1]/div[2]/div[1]/header[1]/div[1]/div[3]/lightning-layout[1]/slot[1]/c-own-profile[1]/ul[1]/li[1]/div[1]/div[1]/ul[1]/li[1]/a[1]/span[1]")
	WebElement profile;

	@FindBy(xpath = "//span[normalize-space()='Sign In']")
	WebElement dremshopLogin;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/nav[1]/div[1]/div[2]/p[2]")
	WebElement ahe_hhe_Login;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/header[1]/div[1]/div[1]/c-header-component[1]/section[1]/div[1]/div[3]/ul[1]/li[1]/a[1]")
	WebElement dremshopProfile;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/c-own-my-account[1]/lightning-layout[1]/slot[1]/lightning-layout-item[2]/slot[1]/div[1]/div[1]/div[1]/c-own-account-information[1]/div[1]/div[2]/div[1]/div[2]/button[1]")
	WebElement editButton;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		configReader = new ConfigReader();
		prop = configReader.init_prop();
		String excelFilePath = prop.getProperty("excelpath");
		excelUtils = new ExcelUtils(excelFilePath);
		serviceProviderurls = excelUtils.readExcelData("QA_Service_Providers_Data", "Url");
		serviceProviderUrl = serviceProviderurls.get(0);
	}

	public boolean verifyUsername(String name) throws InterruptedException {
		Thread.sleep(10000);
		if (serviceProviderUrl.contains("dreamshop")) {
			try {
				WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofMinutes(3));
				wait1.until(ExpectedConditions.visibilityOf(DreamshopuserNameField));
				String userName = DreamshopuserNameField.getText();
				System.out.println("Screen Name:" + userName);
				String actualName = name;
				if (userName.equals(actualName)) {
					return true;
				} else {
					return false;
				}
			} catch (StaleElementReferenceException e) {
				WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofMinutes(3));
				wait1.until(ExpectedConditions.visibilityOf(DreamshopuserNameField));
				String userName = DreamshopuserNameField.getText();
				String actualName = name;
				if (userName.equals(actualName)) {
					return true;
				} else {
					return false;
				}
			}
		} else if (serviceProviderUrl.contains("acuracertified")) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(3));
			wait.until(ExpectedConditions.visibilityOf(acuraCertifieduserIcon)).click();
			String userName = AcuraCertifieduserNameField.getText();
			System.out.println("Screen Name:" + userName);
			String actualName = "Hi, " + name;
			if (userName.equals(actualName)) {
				return true;
			} else {
				return false;
			}
		} else if (serviceProviderUrl.contains("acura.com")) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(3));
			wait.until(ExpectedConditions.visibilityOf(acurauserIcon)).click();
			String userName = AcurauserNameField.getText();
			System.out.println("Screen Name:" + userName);
			String actualName = "Hi, " + name;
			if (userName.equals(actualName)) {
				return true;
			} else {
				return false;
			}
		} else {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(3));
			wait.until(ExpectedConditions.elementToBeClickable(MygarageuserNameField));
			String userName = MygarageuserNameField.getText();
			System.out.println("Screen Name:" + userName);
			String actualName = "Hi, " + name;
			if (userName.equals(actualName)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public void clickLogout() throws InterruptedException {
		Thread.sleep(10000);
		if (serviceProviderUrl.contains("dreamshop")) {
			try {
				Thread.sleep(30000);
				WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofMinutes(5));
				wait1.until(ExpectedConditions.elementToBeClickable(DreamshopuserNameField)).click();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions.elementToBeClickable(dreamshoplogout)).click();
				Thread.sleep(30000);
				WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
				wait2.until(ExpectedConditions.elementToBeClickable(dremshopLogin)).click();
			} catch (StaleElementReferenceException e) {
				WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofMinutes(5));
				wait1.until(ExpectedConditions.elementToBeClickable(DreamshopuserNameField)).click();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions.elementToBeClickable(dreamshoplogout)).click();
				Thread.sleep(30000);
				WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
				wait2.until(ExpectedConditions.elementToBeClickable(dremshopLogin)).click();
			} catch (ElementClickInterceptedException ex) {
				System.out.println("full stacktrace of exception:" + ex);
				DreamshopuserNameField.click();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions.elementToBeClickable(dreamshoplogout)).click();
				Thread.sleep(30000);
				WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
				wait2.until(ExpectedConditions.elementToBeClickable(dremshopLogin)).click();
			}
		} else if (serviceProviderUrl.contains("prelive.electrum") || serviceProviderUrl.contains("acura.electrum")
				|| serviceProviderUrl.contains("home-uat") || serviceProviderUrl.contains("uat.electrum")) {
			try {
				WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofMinutes(2));
				wait1.until(ExpectedConditions.elementToBeClickable(ahe_hhe_logout)).click();
				WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(15));
				wait2.until(ExpectedConditions.elementToBeClickable(ahe_hhe_Login)).click();
			} catch (StaleElementReferenceException e) {
				WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofMinutes(2));
				wait1.until(ExpectedConditions.elementToBeClickable(ahe_hhe_logout)).click();
				WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(15));
				wait2.until(ExpectedConditions.elementToBeClickable(ahe_hhe_Login)).click();
			}
		} else if (serviceProviderUrl.contains("acuracertified")) {
			WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofMinutes(3));
			wait2.until(ExpectedConditions.elementToBeClickable(acuraCertifieduserIcon)).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(3));
			wait.until(ExpectedConditions.elementToBeClickable(acuraCertifiedLogout)).click();
//			Actions action = new Actions(driver);
//			action.moveToElement(acuraCertifieduserIcon).perform();
//			action.moveToElement(acuraCertifiedLogout);
//			action.click().build().perform();
			Thread.sleep(30000);
			WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait3.until(ExpectedConditions.elementToBeClickable(userIconAcuraCertified)).click();
			WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait4.until(ExpectedConditions.elementToBeClickable(loginButtonAcuraCertified)).click();
		} else if (serviceProviderUrl.contains("acura.com")) {
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofMinutes(3));
			wait1.until(ExpectedConditions.visibilityOf(acurauserIcon));
			Actions action = new Actions(driver);
			action.moveToElement(acurauserIcon).perform();
			action.moveToElement(acuraLogout);
			action.click().build().perform();
			Thread.sleep(30000);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(userIconAcura)).click();
			WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait2.until(ExpectedConditions.elementToBeClickable(loginButtonAcura)).click();
		} else {
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofMinutes(3));
			wait1.until(ExpectedConditions.visibilityOf(userIcon));
			Actions action = new Actions(driver);
			action.moveToElement(userIcon).perform();
			action.moveToElement(logout);
			action.click().build().perform();
			Thread.sleep(30000);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.elementToBeClickable(login)).click();
		}
	}

	public void clickProfileButton() throws TimeoutException, InterruptedException {
		Thread.sleep(30000);
		if (serviceProviderUrl.contains("dreamshop")) {
			try {
				Thread.sleep(30000);
				WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofMinutes(2));
//				wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName("c-about-car")));
				wait1.until(ExpectedConditions.visibilityOf(DreamshopuserNameField));
				DreamshopuserNameField.click();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
				wait.until(ExpectedConditions.elementToBeClickable(dremshopProfile)).click();
			} catch (StaleElementReferenceException e) {
				WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofMinutes(1));
				wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName("c-about-car")));
				wait1.until(ExpectedConditions.visibilityOf(DreamshopuserNameField));
				DreamshopuserNameField.click();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
				wait.until(ExpectedConditions.elementToBeClickable(dremshopProfile)).click();
			} catch (ElementClickInterceptedException ex) {
				System.out.println("full stacktrace of exception:" + ex);
				DreamshopuserNameField.click();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
				wait.until(ExpectedConditions.elementToBeClickable(dremshopProfile)).click();
			}

		} else if (serviceProviderUrl.contains("acuracertified")) {
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofMinutes(3));
			wait1.until(ExpectedConditions.elementToBeClickable(acuraCertifieduserIcon)).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(3));
			wait.until(ExpectedConditions.elementToBeClickable(acuraCertifiedProfile)).click();
//			Actions action = new Actions(driver);
//			action.moveToElement(acuraCertifieduserIcon).perform();
//			action.moveToElement(acuraCertifiedProfile);
//			action.click().build().perform();
		} else if (serviceProviderUrl.contains("acura.com")) {
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofMinutes(1));
			wait1.until(ExpectedConditions.visibilityOf(acurauserIcon));
			Actions action = new Actions(driver);
			action.moveToElement(acurauserIcon).click().moveToElement(acuraProfile).click().perform();
		} else {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("slds-spinner_container")));
			wait.until(ExpectedConditions.visibilityOf(userIcon));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofMinutes(5));
			wait1.until(ExpectedConditions.elementToBeClickable(userIcon)).click();
		}
	}

	public void clickEditPage() throws InterruptedException {
		Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();
	}
}
