package parallel;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.testng.Assert;

import com.pages.EmailVerificationPage;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.MfaPage;
import com.pages.SmsVerificationPage;
import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;
import com.qa.util.ExcelUtils;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageSteps {
	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	private MfaPage mfaPage = new MfaPage(DriverFactory.getDriver());
	private EmailVerificationPage emailVerification = new EmailVerificationPage(DriverFactory.getDriver());
	private HomePage homePage = new HomePage(DriverFactory.getDriver());
	private SmsVerificationPage phoneVerification = new SmsVerificationPage(DriverFactory.getDriver());
    private ConfigReader configReader = new ConfigReader();
    private Properties prop = configReader.init_prop();
	private ExcelUtils excelUtils = new ExcelUtils(prop.getProperty("excelpath"));
	List<String> usernames = excelUtils.readExcelData("QA_User_Credentials", "Username");
	List<String> firstNames = excelUtils.readExcelData("QA_User_Credentials", "First Name");
	List<String> lastNames = excelUtils.readExcelData("QA_User_Credentials", "Last Name");
	List<String> fullNames = excelUtils.readExcelData("QA_User_Credentials", "Full Name");
	List<String> phoneNumbers = excelUtils.readExcelData("QA_User_Credentials", "Phone number");
	List<String> serviceProviderurls = excelUtils.readExcelData("QA_Service_Providers_Data", "Url");
	List<String> passwords = excelUtils.readExcelData("QA_User_Credentials", "Password");
	List<String> vertificationtypes = excelUtils.readExcelData("SMS_Phone_Verifications_URLS", "Verification");
	List<String> vertificationtypeurls = excelUtils.readExcelData("SMS_Phone_Verifications_URLS", "Url");
    List<String> valuestoUpdate=excelUtils.readExcelData("PasswordUpdate_SecurityQuestion", "Value");
	String verificationType = vertificationtypes.get(0);
	String MFAUserEmail = usernames.get(0);
	String MFAUserPassword =passwords.get(0);	
	String MFAUserEmail1 = usernames.get(5);
	String MFAUserPassword1 = passwords.get(5);
	String MFAUserEmail2 = usernames.get(6);
	String MFAUserPassword2 = passwords.get(6);
	String NonMFAEmail = usernames.get(1);
	String NonMFAPassword = passwords.get(1);
	String serviceProviderUrl=serviceProviderurls.get(1);
	String SecurityQuestionsUserEmail =usernames.get(2);
	String SecurityQuestionsUserPassword =passwords.get(2);
	String AnswerSecurityQuestion=valuestoUpdate.get(1);

	@Given("User is on the login page")
	public void user_is_on_the_login_page() throws Exception {
		loginPage.openLoginPage(serviceProviderUrl);
		loginPage.clickLoginButton();
	}

	@When("User enters valid username and password that are of mfa enabled user")
	public void user_enters_valid_username_and_password_that_are_of_mfa_enabled_user() {
		// Write code here that turns the phrase above into concrete actions
		loginPage.enterUsername(MFAUserEmail);
		loginPage.enterPassword(MFAUserPassword);
	}

	@And("Clicks on the Sign In Button for Mfa Enabled user")
	public void clicks_on_the_Sign_In_Button_for_Mfa_Enabled_user() {
		// Write code here that turns the phrase above into concrete actions
		loginPage.clickSignIn();
	}

	@Then("User is Asked to Select either the Email or the sms")
	public void user_is_asked_to_select_either_the_email_or_the_sms() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		if (verificationType.equals(vertificationtypes.get(0))) {
			mfaPage.selectEmailOption();
			mfaPage.clickOptionSubmit();
		} else {
			mfaPage.selectSmsOption();
			mfaPage.clickOptionSubmit();
		}
	}

	@And("User enters the verification code sent to user for Mfa Enabled user")
	public void user_enters_the_verification_code_sent_to_user_for_Mfa_Enabled_user()
			throws IOException, InterruptedException {
		String code;
		if (verificationType.equals(vertificationtypes.get(0))) {
			code = emailVerification.verifyEmail(vertificationtypeurls.get(0), MFAUserEmail);
		} else {
			code = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumbers.get(0));
		}
		mfaPage.enterOtp(code);
		mfaPage.clickSubmitOtp();
	}

	@Then("if the verification is validated user is logged in for mfa enabled user")
	public void if_the_verification_is_validated_user_is_logged_in_for_mfa_enabled_user() throws InterruptedException {
		if (serviceProviderUrl.contains("dreamshop")) {
			Assert.assertTrue(homePage.verifyUsername(fullNames.get(0)));
		} else {
			Assert.assertTrue(homePage.verifyUsername(firstNames.get(0)));
		}
	}

	@When("User enters valid username and password that are of not mfa enabled user")
	public void user_enters_valid_username_and_password_that_are_of_not_mfa_enabled_user() {
		loginPage.enterUsername(NonMFAEmail);
		loginPage.enterPassword(NonMFAPassword);
		loginPage.clickSignIn();
	}

	@And("user is asked to check the mfa and sms terms and conditions and user enters phone number")
	public void user_is_asked_to_check_the_mfa_and_sms_terms_and_conditions_and_user_enters_phone_number() {
		// Write code here that turns the phrase above into concrete actions
		loginPage.enterPhoneValue(phoneNumbers.get(6));
		loginPage.acceptMfaTerms();

	}

	@Then("Clicks on the Sign In Button for non Mfa Enabled user")
	public void clicks_on_the_Sign_In_Button_for_non_Mfa_Enabled_user() {
		// Write code here that turns the phrase above into concrete actions
		loginPage.nonMfaUserLogin();
	}

	@And("User enters the verification code sent to user for non Mfa Enabled user")
	public void user_enters_the_verification_code_sent_to_user_for_non_Mfa_Enabled_user() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		Thread.sleep(10000);
		String code = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumbers.get(6));
		mfaPage.enterOtp(code);
		mfaPage.clickSubmitOtp();
	}

	@Then("if the verification is validated user is logged in for non mfa enabled user")
	public void if_the_verification_is_validated_user_is_logged_in_for_non_mfa_enabled_user()
			throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		if (serviceProviderUrl.contains("dreamshop")) {
			Assert.assertTrue(homePage.verifyUsername(fullNames.get(1)));
		} else {
			Assert.assertTrue(homePage.verifyUsername(firstNames.get(1)));
		}
	}
	@When("User enters valid username and password that are of mfa enabled user for trusted device")
	public void user_enters_valid_username_and_password_that_are_of_mfa_enabled_user_for_trusted_device() throws Exception {
		loginPage.enterUsername(MFAUserEmail1);
		loginPage.enterPassword(MFAUserPassword1);
	}
	@And("User enters the verification code sent to user and checks trusted device")
	public void User_enters_the_verification_code_sent_to_user_and_checks_trusted_device() throws InterruptedException {
		String code;
		if (verificationType.equals(vertificationtypes.get(0))) {
			code = emailVerification.verifyEmail(vertificationtypeurls.get(0), MFAUserEmail1);
		} else {
			code = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumbers.get(5));
		}
		mfaPage.enterOtp(code);
		mfaPage.clickTrustedDevice();
		mfaPage.clickSubmitOtp();
	}
	@Then("if the verification is validated user is logged in for mfa enabled user in trusted device")
	public void if_the_verification_is_validated_user_is_logged_in_for_mfa_enabled_user_in_trusted_device() throws InterruptedException {
		if (serviceProviderUrl.contains("dreamshop")) {
			Assert.assertTrue(homePage.verifyUsername(fullNames.get(5)));
		} else {
			Assert.assertTrue(homePage.verifyUsername(firstNames.get(5)));
		}
	}
	@And("Clicks on logout Button from profile and again click on the login button")
	public void clicks_on_logout_button_from_profileand_again_click_on_the_login_button() throws InterruptedException {
		homePage.clickLogout();
	}

	@Then("User again enters valid username and password and clicks login button for the trusted device")
	public void user_again_enters_valid_username_and_password_and_clicks_login_button_for_the_trusted_device() {
		loginPage.enterUsername(MFAUserEmail1);
		loginPage.enterPassword(MFAUserPassword1);
		loginPage.clickSignIn();

	}

	@And("user gets logged into site without MFA")
	public void user_gets_logged_into_site_without_mfa() throws InterruptedException {
		if (serviceProviderUrl.contains("dreamshop")) {
			Assert.assertTrue(homePage.verifyUsername(fullNames.get(5)));
		} else {
			Assert.assertTrue(homePage.verifyUsername(firstNames.get(5)));
		}
	}

	@When("User Enters valid username and password and checks remember me")
	public void user_enters_valid_username_and_password_and_checks_remember_me() throws Exception {
		loginPage.enterUsername(MFAUserEmail2);
		loginPage.enterPassword(MFAUserPassword2);
		loginPage.checkRememberMe();
	}
	@And("Clicks On the Login Button")
	public void Clicks_On_the_Login_Button() {
		loginPage.clickSignIn();
	}

	@And("User Enters the verification code sent to user")
	public void user_enters_the_verification_code_sent_to_user() throws IOException, InterruptedException {
		String code;
		if (verificationType.equals(vertificationtypes.get(0))) {
			code = emailVerification.verifyEmail(vertificationtypeurls.get(0), MFAUserEmail2);
		} else {
			code = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumbers.get(6));
		}
		mfaPage.enterOtp(code);
		mfaPage.clickSubmitOtp();

	}

	@Then("if the Verification is validated user is logged in")
	public void if_the_verification_is_validated_user_is_logged_in() throws InterruptedException {
		if (serviceProviderUrl.contains("dreamshop")) {
			Assert.assertTrue(homePage.verifyUsername(fullNames.get(6)));
		} else {
			Assert.assertTrue(homePage.verifyUsername(firstNames.get(6)));
		}
	}

	@Then("Clicks On logout button from profile")
	public void clicks_On_logout_button_from_profile() throws InterruptedException {
		homePage.clickLogout();
	}

	@Then("Check That the username will be auto populated")
	public void check_that_the_username_will_be_auto_populated() throws InterruptedException {
		Assert.assertTrue(loginPage.verifyEmailValue(MFAUserEmail2));
	}
	@When("User Enters valid username and password and checks remember me for nonmfa user")
	public void User_Enters_valid_username_and_password_and_checks_remember_me_for_nonmfa_user() {
        loginPage.enterUsername(SecurityQuestionsUserEmail);
        loginPage.enterPassword(SecurityQuestionsUserPassword);
        loginPage.checkRememberMe();
	}
	@Then("if the Verification is validated user is logged in for non mfa user")
	public void if_the_Verification_is_validated_user_is_logged_in_for_non_mfa_user() throws InterruptedException {
		if (serviceProviderUrl.contains("prelive.electrum") || serviceProviderUrl.contains("acura.electrum")) {
			Assert.assertTrue(true);
		}else if (serviceProviderUrl.contains("dreamshop")) {
			Assert.assertTrue(homePage.verifyUsername(fullNames.get(2)));
		}else if(serviceProviderUrl.contains("acuracertified") || serviceProviderUrl.contains("acura")){
			Assert.assertTrue(homePage.verifyUsername(firstNames.get(2)));
		}
		else {
			Assert.assertTrue(homePage.verifyUsername(firstNames.get(2)));
		}
	}
	@Then("Check That the username will be auto populated for non mfa")
	public void check_that_the_username_will_be_auto_populated_for_non_mfa() throws InterruptedException {
		Assert.assertTrue(loginPage.verifyEmailValue(SecurityQuestionsUserEmail));
	}
	@Then("select the questions and click submit button")
	public void select_the_questions_and_click_submit_button() throws InterruptedException{
       loginPage.setSecurityQuestions(AnswerSecurityQuestion, AnswerSecurityQuestion, AnswerSecurityQuestion);
	}
	@And("User is redirected to the login page")
	public void User_is_redirected_to_the_login_page() {
		String actualName = "One sign-in to access Honda and Acura websites and apps!";
		Assert.assertTrue(loginPage.checkLoginInfoText(actualName));
	}
	@And("User enters the username and password and clicks login button for the mfa site")
	public void User_enters_the_username_and_password_and_clicks_login_button_for_the_mfa_site() {
        loginPage.enterUsername(MFAUserEmail);
        loginPage.enterPassword(MFAUserPassword);
        loginPage.clickSignIn();
	}
	@When("User enters empty or invalid username and password")
	public void user_enters_empty_or_invalid_username_and_password() {		
        loginPage.fillNegativeLoginData();
	}
	@And("Users enters valid username and invalid password")
	public void users_enters_valid_username_and_invalid_password(){
        loginPage.fillInvalidPassword(MFAUserEmail2);
		loginPage.enterUsername(MFAUserEmail2);
		loginPage.enterPassword(MFAUserPassword2);
		loginPage.clickSignInAfterInvalidPassword();
	}
	@And("User enters the invalid and valid verification code sent to user")
	public void user_enters_the_invalid_and_valid_verification_code_sent_to_user()
			throws IOException, InterruptedException {
		mfaPage.fillInvaildOtp();
		String code;
		if (verificationType.equals(vertificationtypes.get(0))) {
			code = emailVerification.verifyEmail(vertificationtypeurls.get(0), MFAUserEmail2);
		} else {
			code = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumbers.get(0));
		}
		mfaPage.enterOtp(code);
		mfaPage.clickSubmitOtp();
	}
	@And("Users enters valid username and invalid password for non mfa")
	public void users_enters_valid_username_and_invalid_password_for_non_mfa(){
        loginPage.fillInvalidPassword("testuser@yopmail.com");
		loginPage.enterUsername(SecurityQuestionsUserEmail);
		loginPage.enterPassword(SecurityQuestionsUserPassword);
		loginPage.clickSignInAfterInvalidPassword();
	}
}
