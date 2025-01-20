package com.pages;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v126.network.Network;
import org.openqa.selenium.devtools.v126.network.model.Headers;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.util.ConfigReader;
import com.qa.util.ExcelUtils;

public class LoginPage {
	private WebDriver driver;
	private ConfigReader configReader;
	private Properties prop;
	private ExcelUtils excelUtils;
	List<String> serviceProviderurls;
	List<String> credentials;
	String serviceProviderUrl;
	DevTools chromeDevTools;
	@FindBy(xpath = "//button[@class='slds-button slds-button_outline-brand slds-m-left_x-small login-button']")
	WebElement loginButtonMyGarage;

	@FindBy(xpath = "//span[normalize-space()='Sign In']")
	WebElement loginButtonDreamShop;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/nav[1]/div[1]/div[2]/div[1]/div[1]/button[1]/span[1]/*[name()='svg'][1]")
	WebElement userIconAcuraCertified;

	@FindBy(xpath = "//a[normalize-space()='Sign In']")
	WebElement loginButtonAcuraCertified;

	@FindBy(xpath = "//body/div/nav[@aria-label='Main Menu']/div/div/div/div/div/div/ul/li[1]/a[1]")
	WebElement loginButtonAcura;

	@FindBy(xpath = "(//*[name()='svg'][@aria-label='profile'])[2]")
	WebElement userIconAcura;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/nav[1]/div[1]/div[2]/p[2]")
	WebElement loginButtonAhe_Hhe;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/span[1]/div[2]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
	WebElement usernameField;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/span[1]/form[1]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
	WebElement passwordField;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/button[1]")
	WebElement signInButton;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[4]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
	WebElement phoneField;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[5]/div[1]/div[1]/label[1]/span[1]")
	WebElement mfaTermsAndConditions;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[6]/button[1]")
	WebElement nonMfaSignButton;

	@FindBy(xpath = "(//*[contains(@id,'combobox-button')])[1]")
	WebElement firstComboBox;

	@FindBy(xpath = "(//*[contains(text(),'In which city were you born? Enter city name only.')])[1]")
	WebElement firstDropDownSelection;

	@FindBy(xpath = "(//input)[2]")
	WebElement firstAnswerField;

	@FindBy(xpath = "(//*[contains(text(),'Choose a question*')])[1]")
	WebElement secondComboBox;

	@FindBy(xpath = "(//*[contains(text(),'What is the first name of your oldest niece?')])[1]")
	WebElement secondDropDownSelection;

	@FindBy(xpath = "(//input)[3]")
	WebElement secondAnswerField;

	@FindBy(xpath = "(//*[contains(text(),'Choose a question*')])[1]")
	WebElement thirdComboBox;

	@FindBy(xpath = "(//*[contains(text(),'What was your favorite childhood toy?')])[1]")
	WebElement thirdDropDownSelection;

	@FindBy(xpath = "(//input)[4]")
	WebElement thirdAnswerField;

	@FindBy(xpath = "//*[@title='Submit']")
	WebElement securityQuestionSubmitButton;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/span[1]/div[4]/div[1]/div[1]/label[1]/span[1]")
	WebElement rememberMe;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/b[1]")
	WebElement loginInfoText;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/span[1]/div[3]/a[1]/span[1]")
	WebElement forgotPasswordLink;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
	WebElement forgotPasswordEmailField;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[2]/button[1]")
	WebElement forgotPasswordVerifyButton;

	@FindBy(xpath = "//input[@inputmode='email']")
	WebElement emailUsernameValue;

	@FindBy(xpath = "(//*[contains(text(),'Please enter an answer.')])[1]")
	WebElement firstAnswerFieldErrorMsg;

	@FindBy(xpath = "(//*[contains(text(),'Please enter an answer.')])[2]")
	WebElement secondAnswerFieldErrorMsg;

	@FindBy(xpath = "(//*[contains(text(),'Please enter an answer.')])[3]")
	WebElement thirdAnswerFieldErrorMsg;

	@FindBy(xpath = "(//*[contains(text(),'You must provide an answer to your security question before you can change your password.')])[1]")
	WebElement firstAnswerFieldErrorMsgNonMFA;

	@FindBy(xpath = "(//*[contains(text(),'You must provide an answer to your security question before you can change your password.')])[2]")
	WebElement secondAnswerFieldErrorMsgNonMFA;

	@FindBy(xpath = "(//*[contains(text(),'You must provide an answer to your security question before you can change your password.')])[3]")
	WebElement thirdAnswerFieldErrorMsgNonMFA;

	@FindBy(xpath = "(//*[contains(text(),'Please provide an email address in order to sign in.')])[1]")
	WebElement usernameFieldErrorMsg;

	@FindBy(xpath = "(//*[contains(text(),'Please provide a password in order to sign in.')])[1]")
	WebElement passwordFieldErrorMsg;

	@FindBy(xpath = " (//*[contains(text(),'Please provide a valid email address when signing in.')])[1]")
	WebElement usernameFieldErrorMsg1;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]")
	WebElement invalidPasswordErrorMsg;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[4]/button[1]")
	WebElement signButtonAfterInvalidPassword;

	@FindBy(xpath = "//*[text()='Please provide a valid email address.']")
	WebElement forgotPasswordEmailFieldErrorMsg;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[2]")
	WebElement forgotPasswordEmailFieldErrorMsg1;

	@FindBy(xpath = "//span[normalize-space()='An internal server error has occurred']")
	WebElement interServerError;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		chromeDevTools = ((HasDevTools) driver).getDevTools();
		PageFactory.initElements(driver, this);
		configReader = new ConfigReader();
		prop = configReader.init_prop();
		String excelFilePath = prop.getProperty("excelpath");
		excelUtils = new ExcelUtils(excelFilePath);
		serviceProviderurls = excelUtils.readExcelData("QA_Service_Providers_Data", "Url");
		credentials = excelUtils.readExcelData("SiteCredential", "Credentials");
		serviceProviderUrl = serviceProviderurls.get(0);
	}

	public void openLoginPage(String url) {
		if (serviceProviderUrl.contains("acuracertified") || serviceProviderUrl.contains("acura.com")) {
			handleAcuraLogin();
		} else {
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
	}

	public void clickLoginButton() {
		if (serviceProviderUrl.contains("dreamshop")) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
				wait.until(ExpectedConditions.elementToBeClickable(loginButtonDreamShop)).click();
		} else if (serviceProviderUrl.contains("prelive.electrum") || serviceProviderUrl.contains("acura.electrum")) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
			wait.until(ExpectedConditions.elementToBeClickable(loginButtonAhe_Hhe)).click();
		} else if (serviceProviderUrl.contains("acuracertified")) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(userIconAcuraCertified)).click();
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait1.until(ExpectedConditions.elementToBeClickable(loginButtonAcuraCertified)).click();
		} else if (serviceProviderUrl.contains("acura.com")) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(userIconAcura)).click();
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait1.until(ExpectedConditions.elementToBeClickable(loginButtonAcura)).click();
		} else {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
			wait.until(ExpectedConditions.elementToBeClickable(loginButtonMyGarage)).click();
		}
	}

	public void enterUsername(String username) {
		usernameField.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}

	public void clickSignIn() {
		signInButton.click();
	}

	public void enterPhoneValue(String phonenumber) {
		phoneField.sendKeys(phonenumber);
	}

	public void acceptMfaTerms() {
		mfaTermsAndConditions.click();
	}

	public void nonMfaUserLogin() {
		nonMfaSignButton.click();
	}

	public void checkRememberMe() {
		rememberMe.click();
	}

	public boolean verifyEmailValue(String name) throws InterruptedException {
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait1.until(ExpectedConditions.visibilityOf(emailUsernameValue));
		Thread.sleep(10000);
		String userName = emailUsernameValue.getAttribute("value");
		System.out.println("Screen Name:" + userName);
		String actualName = name;
		if (userName.equals(actualName)) {
			return true;
		} else {
			return false;
		}
	}

	public void setSecurityQuestions(String firstanswerfield, String secondanswerfield, String thirdanswerfield)
			throws InterruptedException {
		Thread.sleep(10000);
		firstComboBox.click();
		firstDropDownSelection.click();
		firstAnswerField.sendKeys(firstanswerfield);
		secondComboBox.click();
		Thread.sleep(2000);
		secondDropDownSelection.click();
		secondAnswerField.sendKeys(secondanswerfield);
		thirdComboBox.click();
		Thread.sleep(2000);
		thirdDropDownSelection.click();
		thirdAnswerField.sendKeys(thirdanswerfield);
		securityQuestionSubmitButton.click();
	}

	public boolean checkLoginInfoText(String value) {
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
		String actualValue = loginInfoText.getText();
		String expectedValue = value;
		if (actualValue.equals(expectedValue)) {
			return true;
		} else {
			return false;
		}
	}

	public void clickForgotPassword() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
	}

	public void enterEmailAndClickVerifyforForgotPassword(String email) {
		forgotPasswordEmailField.sendKeys(email);
		forgotPasswordVerifyButton.click();
	}

	public void fillNegativeSecurityQuestionsData(String firstanswerfield, String secondanswerfield,
			String thirdanswerfield) throws InterruptedException {
		Thread.sleep(1000);
		firstComboBox.click();
		Thread.sleep(1000);
		firstDropDownSelection.click();
		firstAnswerField.sendKeys("");
		secondAnswerField.click();
		firstAnswerFieldErrorMsg.isDisplayed();
		secondComboBox.click();
		Thread.sleep(1000);
		secondDropDownSelection.click();
		secondAnswerField.sendKeys("");
		thirdAnswerField.click();
		secondAnswerFieldErrorMsg.isDisplayed();
		thirdComboBox.click();
		Thread.sleep(2000);
		thirdDropDownSelection.click();
		thirdAnswerField.sendKeys("");
		secondAnswerField.click();
		Thread.sleep(3000);
		thirdAnswerFieldErrorMsg.isDisplayed();
		firstAnswerField.sendKeys(firstanswerfield);
		secondAnswerField.sendKeys(secondanswerfield);
		thirdAnswerField.sendKeys(thirdanswerfield);
		secondAnswerField.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(securityQuestionSubmitButton)).click();
	}

	public void handleAcuraLogin() {
		chromeDevTools.createSession();
		chromeDevTools.send(Network.enable(Optional.of(0), Optional.of(0), Optional.of(0)));
		Map<String, Object> header = new HashMap<>();
		String basicAuth = "Basic " + new String(
				new Base64().encode(String.format("%s:%s", credentials.get(0), credentials.get(1)).getBytes()));
		header.put("Authorization", basicAuth);
		chromeDevTools.send(Network.setExtraHTTPHeaders(new Headers(header)));
		if (serviceProviderUrl.contains("acura.com")) {
			driver.get("https://akamai-staging2.acura.com/");
		} else {
			driver.get("https://akamai-staging3.acuracertified.com/");
		}
	}

	public void fillNegativeLoginData() {
		usernameField.sendKeys("");
		usernameFieldErrorMsg.isDisplayed();
		passwordField.sendKeys("");
		usernameField.click();
		passwordFieldErrorMsg.isDisplayed();
		usernameField.sendKeys("invalidemail");
		passwordField.click();
		usernameFieldErrorMsg1.isDisplayed();
		usernameField.clear();
	}

	public void fillInvalidPassword(String Username) {
		usernameField.sendKeys(Username);
		passwordField.sendKeys("WrongPassword");
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
		signInButton.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(10));
		wait.until(ExpectedConditions.visibilityOf(invalidPasswordErrorMsg)).click();
		String shownmsg = invalidPasswordErrorMsg.getText();
		System.out.println(shownmsg);
		String actualmsg = "Invalid Login\n"
				+ "Sorry, we didn't recognize your email or password you entered. Please try again.";
		Assert.assertEquals(shownmsg, actualmsg);
		usernameField.clear();
		passwordField.clear();
	}

	public void clickSignInAfterInvalidPassword() {
		signButtonAfterInvalidPassword.click();
	}

	public void fillNegativeForgotPasswordData(String username) throws InterruptedException {
		Thread.sleep(5000);
		forgotPasswordEmailField.sendKeys(username);
		forgotPasswordEmailFieldErrorMsg.isDisplayed();
		forgotPasswordEmailField.clear();
		Thread.sleep(3000);
		forgotPasswordEmailField.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.visibilityOf(forgotPasswordEmailFieldErrorMsg1));
		Thread.sleep(5000);
		String shownmsg = forgotPasswordEmailFieldErrorMsg1.getText();
		System.out.println(shownmsg);
		String actualmsg = "Email\n" + "Email is required.";
		Assert.assertEquals(shownmsg, actualmsg);
	}

	public void fillNegativeSecurityQuestionsDataNonMFA(String firstanswerfield, String secondanswerfield,
			String thirdanswerfield) throws InterruptedException {
		Thread.sleep(1000);
		firstComboBox.click();
		Thread.sleep(1000);
		firstDropDownSelection.click();
		firstAnswerField.sendKeys("");
		secondAnswerField.click();
		firstAnswerFieldErrorMsgNonMFA.isDisplayed();
		secondComboBox.click();
		Thread.sleep(1000);
		secondDropDownSelection.click();
		secondAnswerField.sendKeys("");
		WebElement element = thirdAnswerField;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		thirdAnswerField.click();
		secondAnswerFieldErrorMsgNonMFA.isDisplayed();
		thirdComboBox.click();
		Thread.sleep(2000);
		thirdDropDownSelection.click();
		thirdAnswerField.sendKeys("");
		secondAnswerField.click();
		Thread.sleep(3000);
		thirdAnswerFieldErrorMsgNonMFA.isDisplayed();
		firstAnswerField.sendKeys(firstanswerfield);
		secondAnswerField.sendKeys(secondanswerfield);
		thirdAnswerField.sendKeys(thirdanswerfield);
		secondAnswerField.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(securityQuestionSubmitButton)).click();
	}
}
