package com.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class MfaPage {
    WebDriver driver;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/c-ciam-m-f-a-verification-u-x[1]/div[1]/form[1]/div[1]/lightning-radio-group[1]/fieldset[1]/div[1]/span[1]/label[1]/span[1]")
    WebElement emailOption;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/c-ciam-m-f-a-verification-u-x[1]/div[1]/form[1]/div[1]/lightning-radio-group[1]/fieldset[1]/div[1]/span[2]/label[1]/span[1]")
    WebElement smsOption;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/c-ciam-m-f-a-verification-u-x[1]/div[1]/form[1]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
    WebElement otpField;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/c-ciam-m-f-a-verification-u-x[1]/div[1]/form[1]/div[4]/lightning-button[1]/button[1]")
    WebElement submitOtpButton;

    @FindBy(xpath="/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/c-ciam-m-f-a-verification-u-x[1]/div[1]/form[1]/div[2]/lightning-button[1]/button[1]")
    WebElement optionSubmitButton;
    
    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/section[1]/div[1]/div[1]/div[1]/c-ciam-m-f-a-verification-u-x[1]/div[1]/form[1]/div[1]/lightning-radio-group[1]/fieldset[1]/div[1]/span[1]/label[1]/span[1]")
    WebElement manageprofileEmailOption;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/section[1]/div[1]/div[1]/div[1]/c-ciam-m-f-a-verification-u-x[1]/div[1]/form[1]/div[1]/lightning-radio-group[1]/fieldset[1]/div[1]/span[2]/label[1]/span[1]")
    WebElement manageprofileSmsOption;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/section[1]/div[1]/div[1]/div[1]/c-ciam-m-f-a-verification-u-x[1]/div[1]/form[1]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
    WebElement manageprofileOtpField;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/section[1]/div[1]/div[1]/div[1]/c-ciam-m-f-a-verification-u-x[1]/div[1]/form[1]/div[3]/lightning-button[1]/button[1]")
    WebElement manageProfilesubmitOtpButton;

    @FindBy(xpath="/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/section[1]/div[1]/div[1]/div[1]/c-ciam-m-f-a-verification-u-x[1]/div[1]/form[1]/div[2]/lightning-button[1]/button[1]")
    WebElement manageprofileOptionSubmitButton;
    
    @FindBy(xpath="/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/c-ciam-m-f-a-verification-u-x[1]/div[1]/form[1]/div[3]/div[1]/div[1]/label[1]/span[1]")
    WebElement trustedDevice;
    
    @FindBy(xpath="/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
    WebElement registerEmailField;
    
    @FindBy(xpath="/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[4]/button[1]")
    WebElement registersubmitField;
    
    @FindBy(xpath="/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
    WebElement registerPhoneField;
    
    @FindBy(xpath="/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/c-ciam-m-f-a-verification-u-x[1]/div[1]/form[1]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
    WebElement changePasswordField;
    
    @FindBy(xpath="/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/c-ciam-m-f-a-verification-u-x[1]/div[1]/form[1]/div[3]/lightning-button[1]/button[1]")
    WebElement changePasswordVerficationSubmitButton;
    
    @FindBy(xpath="/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[2]/div[2]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
    WebElement securityQuestionField;
    
    @FindBy(xpath="/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[2]/div[3]/button[1]")     
    WebElement securityQuestionSubmitButton;
    
    @FindBy(xpath="/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[4]/div[2]/section[1]/div[1]/div[1]/div[1]/c-ciam-m-f-a-verification-u-x[1]/div[1]/form[1]/div[3]/lightning-button[1]/button[1]")
    WebElement changePassswordMFASubmitButton;
    
    @FindBy(xpath="/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[4]/div[2]/section[1]/div[1]/div[1]/div[1]/c-ciam-m-f-a-verification-u-x[1]/div[1]/form[1]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
    WebElement changePasswordOtpField;
    
    @FindBy(xpath="/html[1]/body[1]/div[7]/div[1]/div[2]/div[1]/div[2]/c-ciam-m-f-a-verification-u-x[1]/div[1]/form[1]/div[3]/lightning-button[1]/button[1]")
    WebElement changeEmailMFASubmitButton;
    
    @FindBy(xpath="/html[1]/body[1]/div[7]/div[1]/div[2]/div[1]/div[2]/c-ciam-m-f-a-verification-u-x[1]/div[1]/form[1]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
    WebElement changeEmailOtpField;
    
    @FindBy(xpath="/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[3]/div[2]/c-c-i-a-m-change-phone-u-x[1]/section[1]/div[1]/div[1]/div[1]/c-ciam-m-f-a-verification-u-x[1]/div[1]/form[1]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
    WebElement changePhoneOtpField;
    
    @FindBy(xpath="/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[3]/div[2]/c-c-i-a-m-change-phone-u-x[1]/section[1]/div[1]/div[1]/div[1]/c-ciam-m-f-a-verification-u-x[1]/div[1]/form[1]/div[3]/lightning-button[1]/button[1]")
    WebElement changePhoneMFASubmitButton;
    
    @FindBy(xpath="//*[text()='Please provide a valid verification code.']")
    WebElement invalidMFACodeErrorMsg;
    
    @FindBy(xpath="/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/lightning-input[1]/lightning-primitive-input-simple[1]/div[2]")
    WebElement invalidPhoneErrorMsg;
    
    @FindBy(xpath="/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[3]")
    WebElement invalidSecurityQuestionAnswerErrorMsg;
    
    @FindBy(xpath="/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[2]/div[3]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
    WebElement afterErrorAnswerField;
    
    @FindBy(xpath="/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[2]/div[4]/button[1]")
    WebElement afterErrorSubmitButton;
    
    public MfaPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void selectEmailOption() {
        emailOption.click();
    }
    public void clickOptionSubmit() {
    	optionSubmitButton.click();
    }
    public void selectSmsOption() {
        smsOption.click();
    }

    public void enterOtp(String otp) {
        otpField.sendKeys(otp);
    }

    public void clickSubmitOtp() {
        submitOtpButton.click();
    }
    public void selectEmailOptionManageProfile() {
    	manageprofileEmailOption.click();
    }
    public void clickOptionSubmitManageProfile() {
    	manageprofileOptionSubmitButton.click();
    }
    public void selectSmsOptionManageProfile() {
    	manageprofileSmsOption.click();
    }

    public void enterOtpManageProfile(String otp) {
    	manageprofileOtpField.sendKeys(otp);
    }

    public void clickSubmitOtpManageProfile() throws InterruptedException {
    	Thread.sleep(5000);
    	manageProfilesubmitOtpButton.click();
    }
    public void clickTrustedDevice() {
    	trustedDevice.click();
    }
    public void registerEmailField(String otp) {
    	registerEmailField.sendKeys(otp);
    }
    public void registerPhoneField(String otp) {
    	registerPhoneField.sendKeys(otp);
    }
    public void registerSubmitOtp() {
    	registersubmitField.click();
    }
    public void verifyChangePassword(String otp) {
    	changePasswordField.sendKeys(otp);
    	changePasswordVerficationSubmitButton.click();
    }
    public void answerSecurityQuestionAndSubmit(String answer) {
    	securityQuestionField.sendKeys(answer);
    	securityQuestionSubmitButton.click();
    }
    public void emailandPhoneVerificationforChangePassword(String otp) {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	changePasswordOtpField.sendKeys(otp);
    	changePassswordMFASubmitButton.click();
    } 
    public void phoneVerificationForChangeEmail(String otp) throws InterruptedException {
    	Thread.sleep(5000);
    	changeEmailOtpField.sendKeys(otp);
    	changeEmailMFASubmitButton.click();
    }
    public void emailVerificationForChangePhone(String otp) {
    	changePhoneOtpField.sendKeys(otp);
    	changePhoneMFASubmitButton.click();
    }
    public void fillEmailNegativeData() throws InterruptedException {
    	registerEmailField.sendKeys("12345");
    	registersubmitField.click();
    	invalidMFACodeErrorMsg.isDisplayed();
        registerEmailField.clear();
    }
    public void fillPhoneNegativeData() throws InterruptedException {
    	Thread.sleep(10000);
    	registerPhoneField.sendKeys("12345");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(registersubmitField)).click();
    	Thread.sleep(5000);
    	invalidMFACodeErrorMsg.isDisplayed();
    	registerPhoneField.clear();
    }
    public void fillInvaildOtp() throws InterruptedException {
    	Thread.sleep(5000);
    	otpField.sendKeys("12345");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(submitOtpButton)).click();
    	Thread.sleep(5000);
    	invalidMFACodeErrorMsg.isDisplayed();
    	otpField.clear();
    }
    public void fillInvalidOtpForChangePassword() throws InterruptedException {
    	Thread.sleep(5000);
    	changePasswordField.sendKeys("12345");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(changePasswordVerficationSubmitButton)).click();
    	Thread.sleep(5000);
    	invalidMFACodeErrorMsg.isDisplayed();
    	changePasswordField.clear();
    }
    public void fillWrongAnswer() throws InterruptedException {
    	securityQuestionField.sendKeys("Hello");
    	securityQuestionSubmitButton.click();
    	Thread.sleep(10000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.visibilityOf(invalidSecurityQuestionAnswerErrorMsg));
		String shownmsg = invalidSecurityQuestionAnswerErrorMsg.getText();
		System.out.println(shownmsg);
		String actualmsg = "The answer you provided was incorrect.";
		Assert.assertEquals(shownmsg, actualmsg);
    }
    public void answerSecurityQuestionAndSubmitAfterError(String answer) {
    	afterErrorAnswerField.sendKeys(answer);
    	afterErrorSubmitButton.click();
    }
    public void fillInvalidOtpManageProfile() throws InterruptedException{
    	Thread.sleep(5000);
    	manageprofileOtpField.sendKeys("12345");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(manageProfilesubmitOtpButton)).click();
    	Thread.sleep(5000);
    	invalidMFACodeErrorMsg.isDisplayed();
    	manageprofileOtpField.clear();
    }
    public void fillNegativeDataForChangeEmail() throws InterruptedException {
    	Thread.sleep(5000);
    	changeEmailOtpField.sendKeys("12345");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(changeEmailMFASubmitButton)).click();
    	Thread.sleep(5000);
    	invalidMFACodeErrorMsg.isDisplayed();
    	changeEmailOtpField.clear();
    }
    public void fillNegativeDataForChangePhone() throws InterruptedException {
    	Thread.sleep(5000);
    	changePhoneOtpField.sendKeys("12345");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(changePhoneMFASubmitButton)).click();
    	Thread.sleep(5000);
    	invalidMFACodeErrorMsg.isDisplayed();
    	changePhoneOtpField.clear();
    }
    public void fillInvalidOtpForChangePasswordManageProfile() throws InterruptedException{
    	Thread.sleep(5000);
    	changePasswordOtpField.sendKeys("12345");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(changePassswordMFASubmitButton)).click();
    	Thread.sleep(5000);
    	invalidMFACodeErrorMsg.isDisplayed();
    	changePasswordOtpField.clear();
    }
}
