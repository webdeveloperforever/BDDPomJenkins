package parallel;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.pages.EmailVerificationPage;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.MfaPage;
import com.pages.ProfilePage;
import com.pages.SmsVerificationPage;
import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;
import com.qa.util.ExcelUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ManageProfileStepDefination {
	static WebDriver driver;
	String text;
	String windowId;
	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	private MfaPage mfaPage = new MfaPage(DriverFactory.getDriver());
	private EmailVerificationPage emailVerification = new EmailVerificationPage(DriverFactory.getDriver());
	private HomePage homePage = new HomePage(DriverFactory.getDriver());
	private SmsVerificationPage phoneVerification = new SmsVerificationPage(DriverFactory.getDriver());
	private ProfilePage profilePage = new ProfilePage(DriverFactory.getDriver());
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
	String MFAUserEmail = usernames.get(0);
	String MFAUserPassword = passwords.get(0);
	String MFAUserEmail1 = usernames.get(5);
	String MFAUserPassword1 = passwords.get(5);
	String MFAUserEmail2 = usernames.get(6);
	String MFAUserPassword2 = passwords.get(6);
	String MFAUserEmail3 = usernames.get(7);
	String MFAUserPassword3 = passwords.get(7);
	String updatedpasswords = passwords.get(0);
	String NonMFAEmail = usernames.get(1);
	String NonMFAPassword = passwords.get(1);
	String SecurityQuestionsUserEmail = usernames.get(2);
	String SecurityQuestionsUserPassword = passwords.get(2);
	String AnswerSecurityQuestion = valuestoUpdate.get(1);
	String ChangePasswordToUpdate = valuestoUpdate.get(0);
	String NewEmailToBeUpdated = valuestoUpdate.get(2);
	String fieldToUpdateInChangeName = valuestoUpdate.get(3);
	String firstNameValuetobeUpdated = valuestoUpdate.get(4);
	String lastNameValuetobeUpdated = valuestoUpdate.get(5);
	String phoneNumbertoUpdate = valuestoUpdate.get(6);
	String SecurityQuestionsUserEmail1 = usernames.get(9);
	String SecurityQuestionsUserPassword1 = passwords.get(9);
	String SecurityQuestionsUserEmail2 = usernames.get(10);
	String SecurityQuestionsUserPassword2 = passwords.get(10);
	String serviceProviderUrl = serviceProviderurls.get(0);

	@Given("User is logged in of an mfa enabled user for manageprofile")
	public void user_is_logged_in_of_an_mfa_enabled_user_for_manageprofile() throws Exception {
		loginPage.openLoginPage(serviceProviderUrl);
		loginPage.clickLoginButton();
		loginPage.enterUsername(MFAUserEmail);
		loginPage.enterPassword(MFAUserPassword);
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
			code = emailVerification.verifyEmail(vertificationtypeurls.get(0), MFAUserEmail);
		} else {
			code = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumbers.get(0));
		}
		mfaPage.enterOtp(code);
		mfaPage.clickSubmitOtp();
	}

	@When("User clicks on the myprofile and the edit for the manageprofile page")
	public void user_clicks_on_the_myprofile_and_the_edit_for_the_manageprofile_page()
			throws InterruptedException, TimeoutException {
		// Write code here that turns the phrase above into concrete actions
		if (serviceProviderUrl.contains("dreamshop")) {
			try {
				homePage.clickProfileButton();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				homePage.clickProfileButton();
				e.printStackTrace();
			}
		} else if (serviceProviderUrl.contains("acuracertified") || serviceProviderUrl.contains("acura.com")) {
			homePage.clickProfileButton();
		} else {
			try {
				homePage.clickProfileButton();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				homePage.clickProfileButton();
				e.printStackTrace();
			}
			homePage.clickEditPage();
		}
	}

	@Then("Confirm that there is no Change Security Question Button")
	public void confirm_that_there_is_no_change_security_question_button() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		Thread.sleep(10000);
		profilePage.checkNoChangeSecurityQuestionsButton();
	}

	@Given("User is logged in of an mfa enabled user for changename")
	public void user_is_logged_in_of_an_mfa_enabled_user_for_changename() throws Exception {
		loginPage.openLoginPage(serviceProviderUrl);
		loginPage.clickLoginButton();
		loginPage.enterUsername(MFAUserEmail1);
		loginPage.enterPassword(MFAUserPassword1);
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
			code = emailVerification.verifyEmail(vertificationtypeurls.get(0), MFAUserEmail1);
		} else {
			code = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumbers.get(5));
		}
		mfaPage.enterOtp(code);
		mfaPage.clickSubmitOtp();
	}

	@Then("click on the change name button")
	public void click_on_the_change_name_button() {
		profilePage.clickChangeNameButton();
	}

	@And("User is asked to select either the verification by the email or the sms")
	public void User_is_asked_to_select_either_the_verification_by_the_email_or_the_sms() throws InterruptedException {
		if (verificationType.equals(vertificationtypes.get(0))) {
			mfaPage.selectEmailOptionManageProfile();
			mfaPage.clickOptionSubmitManageProfile();
		} else {
			mfaPage.selectSmsOptionManageProfile();
			mfaPage.clickOptionSubmitManageProfile();
		}
		String code;
		if (verificationType.equals(vertificationtypes.get(0))) {
			code = emailVerification.verifyEmail(vertificationtypeurls.get(0), MFAUserEmail1);
		} else {
			code = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumbers.get(5));
		}
		mfaPage.enterOtpManageProfile(code);
		mfaPage.clickSubmitOtpManageProfile();
	}

	@Then("if the verification either by email or sms is valid then user is asked to enter the updated values and click on the submit")
	public void Then_if_the_verification_either_by_email_or_sms_is_valid_then_user_is_asked_to_enter_the_updated_values_and_click_on_the_submit()
			throws IOException {
		if (fieldToUpdateInChangeName.equals("FirstName")) {
			profilePage.updateFirstName(firstNameValuetobeUpdated);
		} else {
			profilePage.updateLastName(lastNameValuetobeUpdated);
		}
	}

	@And("if the change name is valid then the updated name is seen on the userdetail page")
	public void if_the_change_name_is_valid_then_the_updated_name_is_seen_on_the_userdetail_page()
			throws InterruptedException {
		emailVerification.verifyChangeNameEmail(vertificationtypeurls.get(0), MFAUserEmail1);
	}

	@Given("User is logged in of an mfa enabled user for changepassword")
	public void user_is_logged_in_of_an_mfa_enabled_user_for_changepassword() throws Exception {
		loginPage.openLoginPage(serviceProviderUrl);
		loginPage.clickLoginButton();
		loginPage.enterUsername(MFAUserEmail2);
		loginPage.enterPassword(MFAUserPassword2);
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
			code = emailVerification.verifyEmail(vertificationtypeurls.get(0), MFAUserEmail2);
		} else {
			code = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumbers.get(6));
		}
		mfaPage.enterOtp(code);
		mfaPage.clickSubmitOtp();
	}

	@Then("click on the change password button")
	public void click_on_the_change_password_button() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		Thread.sleep(10000);
		profilePage.clickChangePasswordButton();
	}

	@Then("enter the verification from email")
	public void enter_the_verification_from_email() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		String code = emailVerification.verifyEmail(vertificationtypeurls.get(0), MFAUserEmail2);
		mfaPage.emailandPhoneVerificationforChangePassword(code);
	}

	@Then("enter the verification from phone")
	public void enter_the_verification_from_phone() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		Thread.sleep(10500);
		String code = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumbers.get(6));
		mfaPage.emailandPhoneVerificationforChangePassword(code);
	}

	@Then("if verification is successful then the user is asked to enter the new password and confirmpassword and click submit")
	public void if_verification_is_successful_then_the_user_is_asked_to_enter_the_new_password_and_confirmpassword_and_click_submit()
			throws IOException {
		// Write code here that turns the phrase above into concrete actions
		profilePage.changePassword(MFAUserPassword2, ChangePasswordToUpdate);
	}

	@Then("again login with the updated password")
	public void again_login_with_the_updated_password() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		ExcelUtils excelUtilsupdated = new ExcelUtils(prop.getProperty("excelpath"));
		List<String> updatedpasswords = excelUtilsupdated.readExcelData("QA_User_Credentials", "Password");
		Thread.sleep(5000);
		loginPage.enterUsername(MFAUserEmail2);
		loginPage.enterPassword(updatedpasswords.get(6));
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
			code = emailVerification.verifyEmail(vertificationtypeurls.get(0), MFAUserEmail2);
		} else {
			code = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumbers.get(6));
		}
		mfaPage.enterOtp(code);
		mfaPage.clickSubmitOtp();
	}

	@And("if the change password is successful then the user is logged in successful")
	public void if_the_change_password_is_successful_then_the_user_is_logged_in_successful()
			throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(profilePage.verifyUsername(fullNames.get(6)));
	}

	@Given("User is logged in of an mfa enabled user for the change email")
	public void user_is_logged_in_of_an_mfa_enabled_user_for_the_change_email() throws Exception {
		ExcelUtils excelUtilsupdated = new ExcelUtils(prop.getProperty("excelpath"));
		List<String> updatedpasswords = excelUtilsupdated.readExcelData("QA_User_Credentials", "Password");
		loginPage.openLoginPage(serviceProviderUrl);
		loginPage.clickLoginButton();
		loginPage.enterUsername(MFAUserEmail3);
		loginPage.enterPassword(updatedpasswords.get(7));
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
			code = emailVerification.verifyEmail(vertificationtypeurls.get(0), MFAUserEmail3);
		} else {
			code = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumbers.get(7));
		}
		mfaPage.enterOtp(code);
		mfaPage.clickSubmitOtp();
	}

	@Then("click on the change email button")
	public void click_on_the_change_email_button() throws InterruptedException {
		profilePage.clickChangeEmailButton();
	}

	@And("enter the verification from phone for change email")
	public void enter_the_verification_from_phone_for_change_email() throws InterruptedException {
		Thread.sleep(10000);
		String code = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumbers.get(7));
		mfaPage.phoneVerificationForChangeEmail(code);
	}

	@Then("if the verification is successful user is asked is asked to enter the email to be updated and click submit")
	public void if_the_verification_is_successful_user_is_asked_is_asked_to_enter_the_email_to_be_updated_and_click_submit()
			throws IOException {
		profilePage.changeEmail(NewEmailToBeUpdated);
	}

	@And("user goes to the new email and verifies it")
	public void user_goes_to_the_new_email_and_verifies_it() throws InterruptedException {
		Thread.sleep(10000);
		emailVerification.verifyChangeEmail(vertificationtypeurls.get(0), NewEmailToBeUpdated);
	}

	@Then("user logsout from the site")
	public void user_logsout_from_the_site() throws InterruptedException {
		Thread.sleep(20000);
		profilePage.clickReturntoEditPage(serviceProviderUrl);
		homePage.clickLogout();
	}

	@And("User logs in again with the updated email")
	public void User_logs_in_again_with_the_updated_email() throws InterruptedException {
		ExcelUtils excelUtilsupdated = new ExcelUtils(prop.getProperty("excelpath"));
		List<String> updatedpasswords = excelUtilsupdated.readExcelData("QA_User_Credentials", "Password");
		List<String> updatedUsernames = excelUtilsupdated.readExcelData("QA_User_Credentials", "Username");
		loginPage.enterUsername(updatedUsernames.get(7));
		loginPage.enterPassword(updatedpasswords.get(7));
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
			code = emailVerification.verifyEmail(vertificationtypeurls.get(0), updatedUsernames.get(7));
		} else {
			code = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumbers.get(7));
		}
		mfaPage.enterOtp(code);
		mfaPage.clickSubmitOtp();

	}

	@Then("if the change email is successful user is logged in")
	public void if_the_change_email_is_successful_user_is_logged_in() throws InterruptedException {
		if (serviceProviderUrl.contains("dreamshop")) {
			Assert.assertTrue(homePage.verifyUsername(fullNames.get(7)));
		} else {
			Assert.assertTrue(homePage.verifyUsername(firstNames.get(7)));
		}
	}

	@Given("User is logged in of an mfa enabled user for the change phone")
	public void user_is_logged_in_of_an_mfa_enabled_user_for_the_change_phone() throws Exception {
		ExcelUtils excelUtilsupdated = new ExcelUtils(prop.getProperty("excelpath"));
		List<String> updatedpasswords = excelUtilsupdated.readExcelData("QA_User_Credentials", "Password");
		List<String> updatedUsernames = excelUtilsupdated.readExcelData("QA_User_Credentials", "Username");
		loginPage.openLoginPage(serviceProviderUrl);
		loginPage.clickLoginButton();
		loginPage.enterUsername(updatedUsernames.get(8));
		loginPage.enterPassword(updatedpasswords.get(8));
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
			code = emailVerification.verifyEmail(vertificationtypeurls.get(0), updatedUsernames.get(8));
		} else {
			code = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumbers.get(8));
		}
		mfaPage.enterOtp(code);
		mfaPage.clickSubmitOtp();
	}

	@Then("click on the change phone button")
	public void click_on_the_change_phone_button() throws InterruptedException {
		Thread.sleep(10000);
		profilePage.clickChangePhoneButton();
	}

	@And("user is asked to enter the verification by the email")
	public void user_is_asked_to_enter_the_verification_by_the_email() throws InterruptedException {
		ExcelUtils excelUtilsupdated = new ExcelUtils(prop.getProperty("excelpath"));
		List<String> updatedUsernames = excelUtilsupdated.readExcelData("QA_User_Credentials", "Username");
		String code = emailVerification.verifyEmail(vertificationtypeurls.get(0), updatedUsernames.get(8));
		mfaPage.emailVerificationForChangePhone(code);
	}

	@Then("if the verification is success user is asked to enter the new phone number and click on the submit")
	public void if_the_verification_is_success_user_is_asked_to_enter_the_new_phone_number_and_click_on_the_submit() {
		profilePage.changePhone(phoneNumbertoUpdate);
	}

	@And("a new verification code is sent to the new number and check the email if the phone is updated or not")
	public void a_new_verification_code_is_sent_to_the_new_number_and_check_the_email_if_the_phone_is_updated_or_not()
			throws InterruptedException {
		ExcelUtils excelUtilsupdated = new ExcelUtils(prop.getProperty("excelpath"));
		List<String> updatedUsernames = excelUtilsupdated.readExcelData("QA_User_Credentials", "Username");
		Thread.sleep(10000);
		String code = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumbertoUpdate);
		mfaPage.emailVerificationForChangePhone(code);
		Thread.sleep(10000);
		emailVerification.verifyChangePhone(vertificationtypeurls.get(0), updatedUsernames.get(8));
		Thread.sleep(20000);
		profilePage.clickReturntoEditPage(serviceProviderUrl);
		homePage.clickLogout();
	}

	@Then("login and verify the phone")
	public void login_and_verify_the_phone() throws InterruptedException {
		ExcelUtils excelUtilsupdated = new ExcelUtils(prop.getProperty("excelpath"));
		List<String> updatedpasswords = excelUtilsupdated.readExcelData("QA_User_Credentials", "Password");
		List<String> updatedUsernames = excelUtilsupdated.readExcelData("QA_User_Credentials", "Username");
		loginPage.enterUsername(updatedUsernames.get(8));
		loginPage.enterPassword(updatedpasswords.get(8));
		loginPage.clickSignIn();
		loginPage.enterPhoneValue(phoneNumbers.get(8));
		loginPage.acceptMfaTerms();
		loginPage.nonMfaUserLogin();
		Thread.sleep(20000);
		String code = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumbers.get(8));
		mfaPage.enterOtp(code);
		mfaPage.clickSubmitOtp();
		if (serviceProviderUrl.contains("dreamshop")) {
			Assert.assertTrue(homePage.verifyUsername(fullNames.get(8)));
		} else {
			Assert.assertTrue(homePage.verifyUsername(firstNames.get(8)));
		}
	}

	@Given("User is logged in of an nonmfa enabled user")
	public void User_is_logged_in_of_an_nonmfa_enabled_user() throws Exception {
		loginPage.openLoginPage(serviceProviderUrl);
		loginPage.clickLoginButton();
		loginPage.enterUsername(SecurityQuestionsUserEmail);
		loginPage.enterPassword(SecurityQuestionsUserPassword);
		loginPage.clickSignIn();
	}

	@Then("Confirm that there is no Change Phone Number")
	public void Confirm_that_there_is_no_Change_Phone_Number() throws InterruptedException {
		Thread.sleep(10000);
		profilePage.checkNoChangePhoneButton();
	}

	@Then("click on the change name button for the non mfa")
	public void click_on_the_change_name_button_for_the_non_mfa() throws InterruptedException {
		Thread.sleep(5000);
		profilePage.clickChangeNameButton();
		Thread.sleep(10000);
	}

	@And("User is asked to update the values in the change name and click on the submit")
	public void User_is_asked_to_update_the_values_in_the_change_name_and_click_on_the_submit() throws IOException {
		if (fieldToUpdateInChangeName.equals("FirstName")) {
			profilePage.updateFirstNameNonMfa(firstNameValuetobeUpdated);
		} else {
			profilePage.updateLastNameNonMfa(lastNameValuetobeUpdated);
		}
	}

	@Then("if the change name is valid then the updated name is seen on the userdetail page for non mfa")
	public void if_the_change_name_is_valid_then_the_updated_name_is_seen_on_the_userdetail_page_for_non_mfa()
			throws InterruptedException {
		emailVerification.verifyChangeNameEmail(vertificationtypeurls.get(0), SecurityQuestionsUserEmail);
	}

	@Given("User is logged in of an nonmfa enabled user for change password")
	public void User_is_logged_in_of_an_nonmfa_enabled_user_for_change_password() throws Exception {
		loginPage.openLoginPage(serviceProviderUrl);
		loginPage.clickLoginButton();
		loginPage.enterUsername(SecurityQuestionsUserEmail1);
		loginPage.enterPassword(SecurityQuestionsUserPassword1);
		loginPage.clickSignIn();
	}

	@Then("click on the change password button for the nonmfa")
	public void click_on_the_change_password_button_for_the_nonmfa() throws InterruptedException {
		Thread.sleep(5000);
		profilePage.clickChangePasswordButtonNonMfa();
		Thread.sleep(10000);
	}

	@And("the user is asked to enter the current password and new password and confirmpassword and click submit for the nonmfa")
	public void the_user_is_asked_to_enter_the_current_password_and_new_password_and_confirmpassword_and_click_submit_for_the_nonmfa()
			throws IOException, InterruptedException {
		profilePage.changePasswordNonMfa(SecurityQuestionsUserPassword1, ChangePasswordToUpdate);
		Thread.sleep(10000);
	}

	@Then("agin login with the updated password for the nonmfa user")
	public void agin_login_with_the_updated_password_for_the_nonmfa_user() {
		ExcelUtils excelUtilsupdated = new ExcelUtils(prop.getProperty("excelpath"));
		List<String> updatedpasswords = excelUtilsupdated.readExcelData("QA_User_Credentials", "Password");
		loginPage.enterUsername(SecurityQuestionsUserEmail1);
		loginPage.enterPassword(updatedpasswords.get(9));
		loginPage.clickSignIn();
	}

	@And("if the change password is successful then the user is logged in successful for non mfa")
	public void if_the_change_password_is_successful_then_the_user_is_logged_in_successful_for_non_mfa()
			throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(profilePage.verifyUsername(fullNames.get(9)));
	}

	@Given("User is logged in of an nonmfa enabled user for change email")
	public void User_is_logged_in_of_an_nonmfa_enabled_user_for_change_email() throws Exception {
		loginPage.openLoginPage(serviceProviderUrl);
		loginPage.clickLoginButton();
		loginPage.enterUsername(SecurityQuestionsUserEmail2);
		loginPage.enterPassword(SecurityQuestionsUserPassword2);
		loginPage.clickSignIn();
	}

	@And("user is asked to enter the email to be updated and click on the submit button")
	public void user_is_asked_to_enter_the_email_to_be_updated_and_click_on_the_submit_button() throws IOException, InterruptedException {
		Thread.sleep(5000);
		profilePage.changeEmailNonMfa(NewEmailToBeUpdated);
		Thread.sleep(10000);
	}

	@Then("User goes to the new emai and verifies it for the nonmfa")
	public void User_goes_to_the_new_emai_and_verifies_it_for_the_nonmfa() throws InterruptedException {
		Thread.sleep(10000);
		emailVerification.verifyChangeEmail(vertificationtypeurls.get(0), NewEmailToBeUpdated);
	}

	@And("user logs out from the site")
	public void user_logs_out_from_the_site() throws InterruptedException {
		Thread.sleep(10000);
		profilePage.clickReturntoEditPage(serviceProviderUrl);
		homePage.clickLogout();
	}

	@Then("user logs in again with the updated email")
	public void user_logs_in_again_with_the_updated_email() {
		ExcelUtils excelUtilsupdated = new ExcelUtils(prop.getProperty("excelpath"));
		List<String> updatedpasswords = excelUtilsupdated.readExcelData("QA_User_Credentials", "Password");
		List<String> updatedUsernames = excelUtilsupdated.readExcelData("QA_User_Credentials", "Username");
		loginPage.enterUsername(updatedUsernames.get(10));
		loginPage.enterPassword(updatedpasswords.get(10));
		loginPage.clickSignIn();
	}

	@And("if the change email is successful user is logged in for the nonmfa")
	public void if_the_change_email_is_successful_user_is_logged_in_for_the_nonmfa() throws InterruptedException {
		if (serviceProviderUrl.contains("dreamshop")) {
			Assert.assertTrue(homePage.verifyUsername(fullNames.get(10)));
		} else {
			Assert.assertTrue(homePage.verifyUsername(firstNames.get(10)));
		}
	}

	@Given("User is logged in of an nonmfa enabled user for change security questions")
	public void User_is_logged_in_of_an_nonmfa_enabled_user_for_change_security_questions() throws Exception {
		ExcelUtils excelUtilsupdated = new ExcelUtils(prop.getProperty("excelpath"));
		List<String> updatedpasswords = excelUtilsupdated.readExcelData("QA_User_Credentials", "Password");
		List<String> updatedUsernames = excelUtilsupdated.readExcelData("QA_User_Credentials", "Username");
		loginPage.openLoginPage(serviceProviderUrl);
		loginPage.clickLoginButton();
		loginPage.enterUsername(updatedUsernames.get(2));
		loginPage.enterPassword(updatedpasswords.get(2));
		loginPage.clickSignIn();
	}

	@Then("click on the chnage security questions button")
	public void click_on_the_chnage_security_questions_button() throws InterruptedException {
		Thread.sleep(20000);
		profilePage.clickChangeSecurityQuestionButton();
		Thread.sleep(5000);
	}

	@And("select the question and answer it and then click on the submit button")
	public void select_the_question_and_answer_it_and_then_click_on_the_submit_button() throws InterruptedException {
		loginPage.setSecurityQuestions(AnswerSecurityQuestion, AnswerSecurityQuestion, AnswerSecurityQuestion);
	}

	@Then("check the email if the security questions are updated or not")
	public void check_the_email_if_the_security_questions_are_updated_or_not() throws InterruptedException {
		ExcelUtils excelUtilsupdated = new ExcelUtils(prop.getProperty("excelpath"));
		List<String> updatedUsernames = excelUtilsupdated.readExcelData("QA_User_Credentials", "Username");
		Thread.sleep(10000);
		emailVerification.verifyChangeSecurityQuestionsEmail(vertificationtypeurls.get(0), updatedUsernames.get(2),serviceProviderUrl);
	}

	@Then("if the verification either by email or sms is valid then user is asked to enter the updated values, if user removed the First Name,Last Name value and click on the submit")
	public void Then_if_the_verification_either_by_email_or_sms_is_valid_then_user_is_asked_to_enter_the_updated_values_if_user_removed_the_First_Name_and_did_not_entered_the_updated_First_Name_value_and_click_on_the_submit()
			throws IOException, InterruptedException {
		if (fieldToUpdateInChangeName.equals("FirstName")) {
			profilePage.fillNegativeDataforChangeFirstName();
			profilePage.updateFirstName(firstNameValuetobeUpdated);
		} else {
			profilePage.fillNegativeDataforChangeLastName();
			profilePage.updateLastName(lastNameValuetobeUpdated);
		}
	}

	@And("User is asked to select either the verification by the email or the sms of invalid and valid otp")
	public void User_is_asked_to_select_either_the_verification_by_the_email_or_the_sms_of_invaild_and_valid_otp()
			throws InterruptedException {
		if (verificationType.equals(vertificationtypes.get(0))) {
			mfaPage.selectEmailOptionManageProfile();
			mfaPage.clickOptionSubmitManageProfile();
		} else {
			mfaPage.selectSmsOptionManageProfile();
			mfaPage.clickOptionSubmitManageProfile();
		}
		String code;
		if (verificationType.equals(vertificationtypes.get(0))) {
			mfaPage.fillInvalidOtpManageProfile();
			code = emailVerification.verifyEmail(vertificationtypeurls.get(0), MFAUserEmail1);
		} else {
			mfaPage.fillInvalidOtpManageProfile();
			code = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumbers.get(5));
		}
		mfaPage.enterOtpManageProfile(code);
		mfaPage.clickSubmitOtpManageProfile();
	}

	@And("enter the invalid and valid verification from phone for change email")
	public void enter_the_invalid_and_valid_verification_from_phone_for_change_email() throws InterruptedException {
		Thread.sleep(10000);
		mfaPage.fillNegativeDataForChangeEmail();
		String code = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumbers.get(7));
		mfaPage.phoneVerificationForChangeEmail(code);
	}

	@Then("if the verification is successful user is asked is asked to enter the existing email and new email to be updated and click submit")
	public void if_the_verification_is_successful_user_is_asked_is_asked_to_enter_the_existing_email_and_new_email_to_be_updated_and_click_submit()
			throws IOException, InterruptedException {
		profilePage.fillNegativeDataforChangeEmail(MFAUserEmail3);
		Thread.sleep(5000);
		profilePage.changeEmail(NewEmailToBeUpdated);
	}

	@And("user is asked to enter the vaild and invalid verification by the email")
	public void user_is_asked_to_enter_the_valid_and_invalid_verification_by_the_email() throws InterruptedException {
		ExcelUtils excelUtilsupdated = new ExcelUtils(prop.getProperty("excelpath"));
		List<String> updatedUsernames = excelUtilsupdated.readExcelData("QA_User_Credentials", "Username");
		Thread.sleep(10000);
		mfaPage.fillNegativeDataForChangePhone();
		String code = emailVerification.verifyEmail(vertificationtypeurls.get(0), updatedUsernames.get(8));
		mfaPage.emailVerificationForChangePhone(code);
	}

	@Then("if the verification is success user is asked to enter the new phone number with the wrong data and click on the submit")
	public void if_the_verification_is_success_user_is_asked_to_enter_the_new_phone_number_with_the_wrong_data_and_click_on_the_submit()
			throws InterruptedException {
		profilePage.changePhoneNegativeData();
		Thread.sleep(5000);
		profilePage.changePhone(phoneNumbertoUpdate);
	}

	@Then("enter the valid and invalid verification from email")
	public void enter_the_vlaid_and_invalid_verification_from_email() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		Thread.sleep(10000);
		mfaPage.fillInvalidOtpForChangePasswordManageProfile();
		String code = emailVerification.verifyEmail(vertificationtypeurls.get(0), MFAUserEmail2);
		mfaPage.emailandPhoneVerificationforChangePassword(code);
	}

	@Then("enter the valid and invalid verification from phone")
	public void enter_the_valid_and_invalid_verification_from_phone() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		Thread.sleep(10000);
		mfaPage.fillInvalidOtpForChangePasswordManageProfile();
		String code = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumbers.get(6));
		mfaPage.emailandPhoneVerificationforChangePassword(code);
	}

	@Then("if verification is successful then the user is asked to enter the new password with negative scenario and confirmpassword and click submit")
	public void if_verification_is_successful_then_the_user_is_asked_to_enter_the_new_password_with_negative_scenario_and_confirmpassword_and_click_submit()
			throws IOException, InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		profilePage.fillNegativeData(MFAUserPassword2);
		Thread.sleep(10000);
		profilePage.changePassword(MFAUserPassword2, ChangePasswordToUpdate);
	}

	@And("select the question and answer it wrongly and then click on the submit button")
	public void select_the_question_and_answer_it_wrongly_and_then_click_on_the_submit_button()
			throws InterruptedException {
		Thread.sleep(10000);
		loginPage.fillNegativeSecurityQuestionsDataNonMFA(AnswerSecurityQuestion, AnswerSecurityQuestion,
				AnswerSecurityQuestion);
	}

	@And("User is asked to update the values in the change name with the negative data and click on the submit")
	public void User_is_asked_to_update_the_values_in_the_change_name_with_the_negative_data_and_click_on_the_submit()
			throws IOException, InterruptedException {
		if (fieldToUpdateInChangeName.equals("FirstName")) {
			profilePage.fillNegativeDataforChangeFirstName();
			profilePage.updateFirstNameNonMfa(firstNameValuetobeUpdated);
		} else {
			profilePage.fillNegativeDataforChangeLastName();
			profilePage.updateLastNameNonMfa(lastNameValuetobeUpdated);
		}
	}

	@And("the user is asked to enter the current password and new password and confirmpassword with the negative scenarios and click submit for the nonmfa")
	public void the_user_is_asked_to_enter_the_current_password_and_new_password_and_confirmpassword_with_the_negative_scenarios_and_click_submit_for_the_nonmfa()
			throws IOException, InterruptedException {
		profilePage.fillNegativeDataNonMFA(SecurityQuestionsUserPassword);
		Thread.sleep(10000);
		profilePage.changePasswordNonMfa(SecurityQuestionsUserPassword, ChangePasswordToUpdate);
	}

	@And("user is asked to enter the invalid and valid email to be updated and click on the submit button")
	public void user_is_asked_to_enter_the_invalid_and_valid_email_to_be_updated_and_click_on_the_submit_button()
			throws IOException, InterruptedException {
		profilePage.fillNegativeDataforChangeEmail(SecurityQuestionsUserEmail);
		Thread.sleep(10000);
		profilePage.changeEmailNonMfa(NewEmailToBeUpdated);
	}
}
