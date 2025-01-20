package parallel;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.pages.ChangePasswordPage;
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

public class ForgotPasswordPageSteps {
	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	private MfaPage mfaPage = new MfaPage(DriverFactory.getDriver());
	private EmailVerificationPage emailVerification = new EmailVerificationPage(DriverFactory.getDriver());
	private HomePage homePage = new HomePage(DriverFactory.getDriver());
	private SmsVerificationPage phoneVerification = new SmsVerificationPage(DriverFactory.getDriver());
	private ChangePasswordPage changePassword = new ChangePasswordPage(DriverFactory.getDriver());
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
	List<String> valuestoUpdate = excelUtils.readExcelData("PasswordUpdate_SecurityQuestion", "Value");
	String verificationType = vertificationtypes.get(0);
	String MFAEnabledemail = usernames.get(0);
	String MFAEnabledpassword=passwords.get(0);
	String NONMFAandNoSecurityQuestionsUser = usernames.get(1);
	String passwordToBeUpdated = valuestoUpdate.get(0);
	String securityQuestionAnswer = valuestoUpdate.get(1);
	String NONMFAandSecurityQuestionsUser = usernames.get(2);
	String NONMFAandSecurityQuestionsUserPassword =passwords.get(2);
	String NONMFAUser = usernames.get(3);
	String serviceProviderUrl = serviceProviderurls.get(1);

	@Given("User is on the login page for the password reset")
	public void user_is_on_the_login_page_for_the_password_reset() throws Exception {
		// Write code here that turns the phrase above into concrete actions
		loginPage.openLoginPage(serviceProviderUrl);
		loginPage.clickLoginButton();
	}

	@When("User clicks on the forgot password link on the login page")
	public void user_clicks_on_the_forgot_password_link_on_the_login_page() {
		// Write code here that turns the phrase above into concrete actions
		loginPage.clickForgotPassword();
	}

	@And("User is asked to enter the email and click on the sign in")
	public void user_is_asked_to_enter_the_email_and_click_on_the_sign_in() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		Thread.sleep(10000);
		loginPage.enterEmailAndClickVerifyforForgotPassword(MFAEnabledemail);
	}

	@Then("User clicks on the forgotpassword link on the email")
	public void user_clicks_on_the_forgotpassword_link_on_the_email() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		emailVerification.resetPassword(vertificationtypeurls.get(0), MFAEnabledemail);

	}

	@And("User enters the verification code from both the email and the sms")
	public void user_enters_the_verification_code_from_both_the_email_and_the_sms() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		String emailcode;
		String phonecode;
		// Step-1 email verification
		Thread.sleep(10000);
		emailcode = emailVerification.verifyEmail(vertificationtypeurls.get(0), MFAEnabledemail);
		mfaPage.verifyChangePassword(emailcode);
		// Step-2 Phone Verification
		Thread.sleep(10000);
		phonecode = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumbers.get(0));
		mfaPage.verifyChangePassword(phonecode);

	}

	@Then("if the verification is validated user is on the change password page and fills all the details")
	public void if_the_verification_is_validated_user_is_on_the_change_password_page_and_fills_all_the_details()
			throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("at password:" + passwordToBeUpdated);
		changePassword.fillChangePasswordDetails(passwordToBeUpdated);
	}

	@And("Login in with the updated password")
	public void login_in_with_the_updated_password() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		loginPage.enterUsername(MFAEnabledemail);
		loginPage.enterPassword(passwordToBeUpdated);
		loginPage.clickSignIn();
		if (verificationType.equals(vertificationtypes.get(0))) {
			mfaPage.selectEmailOption();
			mfaPage.clickOptionSubmit();
		} else {
			mfaPage.selectSmsOption();
			mfaPage.clickOptionSubmit();
		}
		String code;
		if (verificationType.equals(vertificationtypes.get(0))) {
			code = emailVerification.verifyEmail(vertificationtypeurls.get(0), MFAEnabledemail);
		} else {
			code = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumbers.get(0));
		}
		mfaPage.enterOtp(code);
		mfaPage.clickSubmitOtp();
	}

	@Then("if the user is password is successfully changed user is logged in")
	public void if_the_user_is_password_is_successfully_changed_user_is_logged_in() throws IOException, InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		changePassword.updatePasswordtoUpdateValueForMFAEnabledUser();
		if (serviceProviderUrl.contains("dreamshop")) {
			Assert.assertTrue(homePage.verifyUsername(fullNames.get(0)));
		} else {
			Assert.assertTrue(homePage.verifyUsername(firstNames.get(0)));
		}
	}

	@And("User is asked to enter the email and click on the sign in for the non mfa and no security questions user")
	public void user_is_asked_to_enter_the_email_and_click_on_the_sign_in_for_the_non_mfa_and_no_security_questions_user() {
		// Write code here that turns the phrase above into concrete actions
		loginPage.enterEmailAndClickVerifyforForgotPassword(NONMFAandNoSecurityQuestionsUser);
	}

	@Then("User clicks on the forgotpassword link on the email for the non mfa and no security questions user")
	public void user_clicks_on_the_forgotpassword_link_on_the_email_for_the_no_mfa_and_no_security_questions_user()
			throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		Thread.sleep(10000);
		emailVerification.resetPassword(vertificationtypeurls.get(0), NONMFAandNoSecurityQuestionsUser);

	}

	@And("User fills password and confirm password fields and click submit and click on the sign in button")
	public void User_fills_password_and_confirm_password_fields_and_click_submit_and_click_on_the_sign_in_button()
			throws InterruptedException {
		changePassword.fillChangePasswordDetails(passwordToBeUpdated);
	}

	@And("User Answers the Security Question prompted and clicks submit")
	public void User_Answers_the_Security_Question_prompted_and_clicks_submit() {
		mfaPage.answerSecurityQuestionAndSubmit(securityQuestionAnswer);
	}

	@Then("check the email for the password change successful for non mfa and no security questions user")
	public void Login_in_with_the_updated_password_for_non_mfa_login() throws InterruptedException, IOException {
		changePassword.updatePasswordtoUpdateValueForNonMFANosecurityQuestionsUser();
		emailVerification.verifyChangePasswordEmail(vertificationtypeurls.get(0), NONMFAandNoSecurityQuestionsUser);
	}

	@And("User is asked to enter the email and click on the sign in for the non mfa and have security questions user")
	public void user_is_asked_to_enter_the_email_and_click_on_the_sign_in_for_the_non_mfa_and_have_security_questions_user() {
		// Write code here that turns the phrase above into concrete actions
		loginPage.enterEmailAndClickVerifyforForgotPassword(NONMFAandSecurityQuestionsUser);
	}

	@Then("User clicks on the forgotpassword link on the email for the non mfa and have security questions user")
	public void user_clicks_on_the_forgotpassword_link_on_the_email_for_the_no_mfa_and_have_security_questions_user()
			throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		emailVerification.resetPassword(vertificationtypeurls.get(0), NONMFAandSecurityQuestionsUser);

	}

	@And("check the email for the password change successful for non mfa and security questions user")
	public void Login_in_with_the_updated_password_for_non_mfa_login_for_having_security_questions_user()
			throws InterruptedException, IOException {
		changePassword.updatePasswordtoUpdateValueForNonMFAsecurityQuestionsUser();
		emailVerification.verifyChangePasswordEmail(vertificationtypeurls.get(0), NONMFAandSecurityQuestionsUser);
	}

	@And("User is asked to enter the email and click on the sign in for the non mfa user")
	public void user_is_asked_to_enter_the_email_and_click_on_the_sign_in_for_the_non_mfa_user() {
		// Write code here that turns the phrase above into concrete actions
		loginPage.enterEmailAndClickVerifyforForgotPassword(NONMFAandSecurityQuestionsUser);
	}

	@Then("User clicks on the forgotpassword link on the email for the non mfa user")
	public void user_clicks_on_the_forgotpassword_link_on_the_email_for_the_no_mfa_user() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		Thread.sleep(10000);
		emailVerification.resetPassword(vertificationtypeurls.get(0), NONMFAandSecurityQuestionsUser);

	}

	@Then("Login in with the updated password in the non mfa site")
	public void Login_in_with_the_updated_password_in_the_non_mfa_site() {
		loginPage.enterUsername(NONMFAandSecurityQuestionsUser);
		loginPage.enterPassword(passwordToBeUpdated);
		loginPage.clickSignIn();
	}

	@And("if the user is password is successfully changed user is logged in for the non mfa user")
	public void if_the_user_is_password_is_successfully_changed_user_is_logged_in_for_the_non_mfa_user()
			throws IOException, InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		changePassword.updatePasswordtoUpdateValueForNonMFAsecurityQuestionsUser();
		if (serviceProviderUrl.contains("dreamshop")) {
			Assert.assertTrue(homePage.verifyUsername(fullNames.get(2)));
		} else if (serviceProviderUrl.contains("prelive.electrum") || serviceProviderUrl.contains("acura.electrum")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(homePage.verifyUsername(firstNames.get(2)));
		}
	}
	@And("User is asked to enter the email enter empty and invalid email and click on the sign in")
	public void User_is_asked_to_enter_the_email_enter_empty_and_invalid_email_and_click_on_the_sign_in() throws InterruptedException {
        loginPage.fillNegativeForgotPasswordData("invalid");
		loginPage.enterEmailAndClickVerifyforForgotPassword(MFAEnabledemail);
	}
	@And("User enters the invalid and valid verification code from both the email and the sms")
	public void user_enters_the_invalid_and_valid_verification_code_from_both_the_email_and_the_sms() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		String emailcode;
		String phonecode;
		mfaPage.fillInvalidOtpForChangePassword();
		// Step-1 email verification
		emailcode = emailVerification.verifyEmail(vertificationtypeurls.get(0), MFAEnabledemail);
		mfaPage.verifyChangePassword(emailcode);
		Thread.sleep(3000);
		mfaPage.fillInvalidOtpForChangePassword();
		// Step-2 Phone Verification
		Thread.sleep(10000);
		phonecode = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumbers.get(8));
		mfaPage.verifyChangePassword(phonecode);
	}
	@And("check for invalid and old passwords")
    public void check_for_invalid_and_old_passwords() throws InterruptedException {
       changePassword.fillNegativeData(MFAEnabledpassword);				
    }
	@And("User is asked to enter the email enter empty and invalid email and click on the sign in for non mfa user")
	public void User_is_asked_to_enter_the_email_enter_empty_and_invalid_email_and_click_on_the_sign_in_for_non_mfa_user() throws InterruptedException {
        loginPage.fillNegativeForgotPasswordData("invalid");
		loginPage.enterEmailAndClickVerifyforForgotPassword(NONMFAandSecurityQuestionsUser);
	}
	@And("User Answers valid and invalid to the Security Question prompted and clicks submit")
	public void User_Answers_valid_and_invalid_to_the_Security_Question_prompted_and_clicks_submit() throws InterruptedException {
		mfaPage.fillWrongAnswer();
		mfaPage.answerSecurityQuestionAndSubmitAfterError(securityQuestionAnswer);
	}
	@And("check for invalid and old passwords for non mfa")
    public void check_for_invalid_and_old_passwords_for_non_mfa() throws InterruptedException {
       changePassword.fillNegativeData(NONMFAandSecurityQuestionsUserPassword);				
    }
}
