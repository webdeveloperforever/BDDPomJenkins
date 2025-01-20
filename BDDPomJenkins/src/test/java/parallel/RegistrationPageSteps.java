package parallel;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.testng.Assert;

import com.pages.EmailVerificationPage;
import com.pages.LoginPage;
import com.pages.MfaPage;
import com.pages.RegistrationPage;
import com.pages.SmsVerificationPage;
import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;
import com.qa.util.ExcelUtils;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistrationPageSteps {
	private RegistrationPage registrationPage = new RegistrationPage(DriverFactory.getDriver());
	private EmailVerificationPage emailVerification = new EmailVerificationPage(DriverFactory.getDriver());
	private MfaPage mfaPage = new MfaPage(DriverFactory.getDriver());
	private SmsVerificationPage phoneVerification = new SmsVerificationPage(DriverFactory.getDriver());
	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	private ConfigReader configReader = new ConfigReader();
	private Properties prop = configReader.init_prop();
	private ExcelUtils excelUtils = new ExcelUtils(prop.getProperty("excelpath"));
	List<String> registrationData = excelUtils.readExcelData("QA_Registration_Details", "Value");
	List<String> serviceProviderurls = excelUtils.readExcelData("QA_Service_Providers_Data", "Url");
	List<String> vertificationtypeurls = excelUtils.readExcelData("SMS_Phone_Verifications_URLS", "Url");
	String FirstName = registrationData.get(0);
	String LastName = registrationData.get(1);
	String phoneNumber = registrationData.get(3);
	String email = registrationData.get(2);
	String password = registrationData.get(4);
	String securityQuestionsAnswer = registrationData.get(5);

	@Given("User is on the dev checkout page")
	public void user_is_on_the_dev_checkout_page() throws Exception {
		// start screen recording
		registrationPage.openRegistrationPage(serviceProviderurls.get(8));
	}

	@When("User click on the Registration Link")
	public void user_click_on_the_registration_link() {
		// Write code here that turns the phrase above into concrete actions
		registrationPage.cilckRegisterButton();
	}

	@And("User enters firstName and lastName and emailAddress and phone and createPassword and confirmPassword")
	public void user_enters_first_name_and_last_name_and_email_address_and_phone_and_create_password_and_confirm_password() {
		// Write code here that turns the phrase above into concrete actions
		registrationPage.fillMFAregsitrationData(FirstName, LastName, phoneNumber, email, password);
	}

	@Then("User select the all terms and conditions")
	public void user_select_the_all_terms_and_conditions() {
		// Write code here that turns the phrase above into concrete actions
		registrationPage.checkMfaCheckboxes();
	}

	@And("Clicks on the CreatAccount Button")
	public void clicks_on_the_creat_account_button() throws IOException {
		// Write code here that turns the phrase above into concrete actions
		registrationPage.clickCreateAccountButton();
	}

	@Then("User is asked to enters the email verification code")
	public void user_is_asked_to_enters_the_email_verification_code() throws IOException, InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		// throw new io.cucumber.java.PendingException();
		String code = emailVerification.verifyRegisterEmail(vertificationtypeurls.get(0), email);
		mfaPage.registerEmailField(code);
		mfaPage.registerSubmitOtp();
	}

	@And("if the verification is validated User is asked to enters the phone verification code")
	public void if_the_verification_is_validated_user_is_asked_to_enters_the_phone_verification_code()
			throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		// throw new io.cucumber.java.PendingException();
		String code = phoneVerification.verifyOtpFromPhone(vertificationtypeurls.get(1), phoneNumber);
		mfaPage.registerPhoneField(code);
		mfaPage.registerSubmitOtp();
	}

	@Then("if the verification is validated user is redirected to login page")
	public void if_the_verification_is_validated_user_is_redirected_to_login_page() throws Exception {
		// Write code here that turns the phrase above into concrete actions
		// throw new io.cucumber.java.PendingException();
		String actualName = "One sign-in to access Honda and Acura websites and apps!";
		Assert.assertTrue(loginPage.checkLoginInfoText(actualName));
	}

	@And("User enters firstName and lastName and emailAddress and createPassword and confirmPassword")
	public void User_enters_firstName_and_lastName_and_emailAddress_and_createPassword_and_confirmPassword() {
		registrationPage.fillNonMFAregsitrationData(FirstName, LastName, email, password);
	}

	@Then("User select the terms and conditions")
	public void user_select_the_terms_and_conditions() {
		// Write code here that turns the phrase above into concrete actions
		registrationPage.checkNonMFACheckbox();
	}

	@And("if the verification is validated User is asked to enters the Security Questions")
	public void if_the_verification_is_validated_User_is_asked_to_enters_the_Security_Questions()
			throws InterruptedException {
		loginPage.setSecurityQuestions(securityQuestionsAnswer, securityQuestionsAnswer, securityQuestionsAnswer);
	}

	@And("User enters empty firstName and empty lastName and invalid emailAddress and invalid phone and createPassword and confirmPassword")
	public void user_enters_empty_first_name_and_empty_last_name_and_inavlida_email_address_and_invalid_phone_and_create_password_and_confirm_password()
			throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		registrationPage.fillNegativeDataForMFA();
	}

	@And("User enters invalid email verification code")
	public void user_enters_invalid_email_verification_code() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		mfaPage.fillEmailNegativeData();
	}

	@And("User enters empty firstName and empty lastName and invalid emailAddress and createPassword and confirmPassword")
	public void user_enters_empty_first_name_and_empty_last_name_and_inavlida_email_address_and_create_password_and_confirm_password()
			throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		registrationPage.fillNegativeDataForNonMFA();
	}

	@And("Verify User is not able to create account without entering all the Security Question")
	public void verify_user_is_not_able_to_create_account_without_entering_all_the_security_question()
			throws InterruptedException {
		loginPage.fillNegativeSecurityQuestionsData(securityQuestionsAnswer, securityQuestionsAnswer, securityQuestionsAnswer);

	}

	@And("User enters invalid phone verification code")
	public void user_enters_invalid_phone_verification_code() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		mfaPage.fillPhoneNegativeData();
	}
}
