package com.pages;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;
import com.qa.util.ExcelUtils;

public class ProfilePage {
	private WebDriver driver;
	String Base_Password = "Honda_";
	String Base_Email = "dreamshopqa";
	private ConfigReader configReader;
	private Properties prop;
	private ExcelUtils excelUtils;
	List<String> firstNames;
	List<String> lastNames;
	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/button[1]")
	WebElement changeNameButton;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/button[1]")
	WebElement chnageEmailButton;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[3]/div[2]")
	WebElement changePhoneButton;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[4]/div[2]")
	WebElement changePasswordButton;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[3]/div[2]")
	WebElement changePasswordNonMfa;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[4]/div[2]")
	WebElement changeSecurityQuestionButtonNonMfa;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/c-own-global-header[1]/div[2]/div[1]/header[1]/div[1]/div[3]/lightning-layout[1]/slot[1]/c-own-profile[1]/ul[1]/li[1]/div[1]/div[1]/ul[1]/li[1]/a[1]/span[1]")
	WebElement profile;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/c-own-my-account[1]/lightning-layout[1]/slot[1]/lightning-layout-item[2]/slot[1]/div[1]/div[1]/div[1]/c-own-account-information[1]/div[1]/div[2]/div[1]/div[2]/button[1]")
	WebElement editButton;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/section[1]/div[1]/div[1]/div[1]/span[1]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
	WebElement firstNameField;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/section[1]/div[1]/div[1]/div[1]/span[3]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
	WebElement lastNameField;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/button[1]")
	WebElement changeNameSubmitButton;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[4]/div[2]/section[1]/div[1]/div[1]/div[1]/form[1]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
	WebElement passwordField;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[4]/div[2]/section[1]/div[1]/div[1]/div[1]/form[2]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
	WebElement newPasswordField;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[4]/div[2]/section[1]/div[1]/div[1]/div[1]/form[3]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
	WebElement confirmPasswordField;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[3]/div[2]/section[1]/div[1]/div[1]/div[1]/form[1]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
	WebElement passwordFieldNonMfa;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[3]/div[2]/section[1]/div[1]/div[1]/div[1]/form[2]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
	WebElement newPasswordFieldNonMfa;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[3]/div[2]/section[1]/div[1]/div[1]/div[1]/form[3]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
	WebElement confirmPasswordFieldNonMfa;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[4]/div[2]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/button[1]")
	WebElement changePasswordSubmitButton;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[3]/div[2]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/button[1]")
	WebElement changePasswordSubmitButtonNonMfa;

	@FindBy(xpath = "/html[1]/body[1]/div[7]/div[1]/div[2]/div[1]/div[2]/div[2]/span[1]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
	WebElement newEmailField;

	@FindBy(xpath = "/html[1]/body[1]/div[7]/div[1]/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/button[1]")
	WebElement changeEmailSubmitButton;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[5]/div[1]/a[1]")
	WebElement returntoEditPage;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[3]/div[2]/c-c-i-a-m-change-phone-u-x[1]/section[1]/div[1]/div[1]/div[2]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
	WebElement newPhoNeField;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[3]/div[2]/c-c-i-a-m-change-phone-u-x[1]/section[1]/div[1]/div[1]/div[3]/div[1]/button[1]")
	WebElement changePhoneSubmitButton;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/lightning-output-field[1]/div[1]/lightning-formatted-name[1]")
	WebElement nameValue;

	@FindBy(xpath = "//*[text()='Please enter a first name.']")
	WebElement firstNameFieldErrorMsg;

	@FindBy(xpath = "//*[text()='Please enter a last name.']")
	WebElement lastNameFieldErrorMsg;

	@FindBy(xpath = "//*[text()='The new email address you have entered is the same as your current email address. Please try a different email address.']")
	WebElement changeEmailErrorMsg;

	@FindBy(xpath = "//*[text()='You must provide a valid Phone No.']")
	WebElement changePhoneErrorMsg;

	@FindBy(xpath = "(//*[contains(text(),'In order to change your password you must provide your current password.')])[1]")
	WebElement passwordFieldErrorMsg;

	@FindBy(xpath = "(//*[contains(text(),'In order to change your password you must provide a new password')])[1]")
	WebElement passwordFieldErrorMsg2;

	@FindBy(xpath = "(//*[contains(text(),'In order to change your password you must confirm your new password')])[1]")
	WebElement passwordFieldErrorMsg3;

	@FindBy(xpath = "(//*[contains(text(),'Your password does not meet the minimum password requirements.')])[1]")
	WebElement passwordFieldErrorMsg1;

	@FindBy(xpath = "(//*[contains(text(),'Passwords do not match.')])[1]")
	WebElement confirmPasswordFieldErrorMsg1;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[4]/div[2]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/h4[1]")
	WebElement oldPasswordUsedErrorMsg;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[2]/form[2]/div[2]/button[1]")
	WebElement submitButtonAfterError;
	
	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[3]/div[2]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/h4[1]")
	WebElement oldPasswordUsedErrorMsgNonMfa;

	public ProfilePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		configReader = new ConfigReader();
		prop = configReader.init_prop();
		String excelFilePath = prop.getProperty("excelpath");
		excelUtils = new ExcelUtils(excelFilePath);
		firstNames = excelUtils.readExcelData("QA_User_Credentials", "First Name");
		lastNames = excelUtils.readExcelData("QA_User_Credentials", "Last Name");
	}

	public void updateExcelDataForChangeName(int rownumber, int cellnumber, String valuetoUpdate, String updateField)
			throws IOException {
		FileInputStream fis = new FileInputStream(prop.getProperty("excelpath"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = workbook.getSheetAt(1);
		sheet1.getRow(rownumber).getCell(cellnumber).setCellValue(valuetoUpdate);
		if (updateField.equals("FirstNameField")) {
			XSSFSheet sheet2 = workbook.getSheetAt(1);
			sheet2.getRow(rownumber).getCell(5).setCellValue(valuetoUpdate + " " + lastNames.get(5));
		} else {
			XSSFSheet sheet2 = workbook.getSheetAt(1);
			sheet2.getRow(rownumber).getCell(5).setCellValue(firstNames.get(5) + " " + valuetoUpdate);
		}
		FileOutputStream output = new FileOutputStream(prop.getProperty("excelpath"));
		workbook.write(output);
		workbook.close();
		fis.close();
	}

	public void updateExcelDataForChangeNameNonMfa(int rownumber, int cellnumber, String valuetoUpdate,
			String updateField) throws IOException {
		FileInputStream fis = new FileInputStream(prop.getProperty("excelpath"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = workbook.getSheetAt(1);
		sheet1.getRow(rownumber).getCell(cellnumber).setCellValue(valuetoUpdate);
		if (updateField.equals("FirstNameField")) {
			XSSFSheet sheet2 = workbook.getSheetAt(1);
			sheet2.getRow(rownumber).getCell(5).setCellValue(valuetoUpdate + " " + lastNames.get(2));
		} else {
			XSSFSheet sheet2 = workbook.getSheetAt(1);
			sheet2.getRow(rownumber).getCell(5).setCellValue(firstNames.get(2) + " " + valuetoUpdate);
		}
		FileOutputStream output = new FileOutputStream(prop.getProperty("excelpath"));
		workbook.write(output);
		workbook.close();
		fis.close();
	}

	public void updateExcelDataForChangePassword(int rownumber, int cellnumber, String valuetoUpdate)
			throws IOException {
		String passwordtoUpdate = generateNewPassword();
		FileInputStream fis = new FileInputStream(prop.getProperty("excelpath"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = workbook.getSheetAt(1);
		sheet1.getRow(rownumber).getCell(cellnumber).setCellValue(valuetoUpdate);
		XSSFSheet sheet2 = workbook.getSheetAt(3);
		sheet2.getRow(1).getCell(1).setCellValue(passwordtoUpdate);
		FileOutputStream output = new FileOutputStream(prop.getProperty("excelpath"));
		workbook.write(output);
		workbook.close();
		fis.close();
	}

	public void updateExcelDataForChangeEmail(int rownumber, int cellnumber, String valuetoUpdate) throws IOException {
		String emailtoUpdate = generateNewEmail();
		FileInputStream fis = new FileInputStream(prop.getProperty("excelpath"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = workbook.getSheetAt(1);
		sheet1.getRow(rownumber).getCell(cellnumber).setCellValue(valuetoUpdate);
		XSSFSheet sheet2 = workbook.getSheetAt(3);
		sheet2.getRow(3).getCell(1).setCellValue(emailtoUpdate);
		FileOutputStream output = new FileOutputStream(prop.getProperty("excelpath"));
		workbook.write(output);
		workbook.close();
		fis.close();
	}

	public void checkNoChangeSecurityQuestionsButton() {
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
		String name = changeNameButton.getText();
		String actualName = "Change Name";
		Assert.assertEquals(name, actualName);
		String changeEmailButtonName = chnageEmailButton.getText();
		String changeEmailButtonActualName = "Change Email";
		Assert.assertEquals(changeEmailButtonName, changeEmailButtonActualName);
		String changePhoneButtonName = changePhoneButton.getText();
		String changePhoneButtonActualName = "Change Phone Number";
		Assert.assertEquals(changePhoneButtonName, changePhoneButtonActualName);
		String changePasswordButtonName = changePasswordButton.getText();
		String changePasswordButtonActualName = "Change Password";
		Assert.assertEquals(changePasswordButtonName, changePasswordButtonActualName);
	}

	public void checkNoChangePhoneButton() {
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
		String name = changeNameButton.getText();
		String actualName = "Change Name";
		Assert.assertEquals(name, actualName);
		String changeEmailButtonName = chnageEmailButton.getText();
		String changeEmailButtonActualName = "Change Email";
		Assert.assertEquals(changeEmailButtonName, changeEmailButtonActualName);
		String changePasswordButtonName = changePasswordNonMfa.getText();
		String changePasswordButtonActualName = "Change Password";
		Assert.assertEquals(changePasswordButtonName, changePasswordButtonActualName);
		String changeSecurityQuestionsButtonName = changeSecurityQuestionButtonNonMfa.getText();
		String changeSecurityQuestionsButtonActualName = "Change Security Questions";
		Assert.assertEquals(changeSecurityQuestionsButtonName, changeSecurityQuestionsButtonActualName);
	}

	public void clickChangeNameButton() {
		changeNameButton.click();
	}

	public void updateFirstName(String nametoUpdate) throws IOException {
		firstNameField.clear();
		firstNameField.sendKeys(nametoUpdate);
		changeNameSubmitButton.click();
		updateExcelDataForChangeName(6, 3, nametoUpdate, "FirstNameField");
	}

	public void updateLastName(String nametoupdate) throws IOException {
		lastNameField.clear();
		lastNameField.sendKeys(nametoupdate);
		changeNameSubmitButton.click();
		updateExcelDataForChangeName(6, 4, nametoupdate, "LastNameField");
	}

	public void updateFirstNameNonMfa(String nametoUpdate) throws IOException {
		firstNameField.clear();
		firstNameField.sendKeys(nametoUpdate);
		changeNameSubmitButton.click();
		updateExcelDataForChangeNameNonMfa(3, 3, nametoUpdate, "FirstNameField");
	}

	public void updateLastNameNonMfa(String nametoupdate) throws IOException {
		lastNameField.clear();
		lastNameField.sendKeys(nametoupdate);
		changeNameSubmitButton.click();
		updateExcelDataForChangeNameNonMfa(3, 4, nametoupdate, "LastNameField");
	}

	public void clickChangePasswordButton() {
		changePasswordButton.click();
	}

	public void clickChangePasswordButtonNonMfa() {
		changePasswordNonMfa.click();
	}

	public void changePassword(String password, String passwordtoUpdate) throws IOException {
		passwordField.sendKeys(password);
		newPasswordField.sendKeys(passwordtoUpdate);
		confirmPasswordField.sendKeys(passwordtoUpdate);
		changePasswordSubmitButton.click();
		updateExcelDataForChangePassword(7, 1, passwordtoUpdate);
	}

	public void changePasswordNonMfa(String password, String passwordtoUpdate) throws IOException {
		passwordFieldNonMfa.sendKeys(password);
		newPasswordFieldNonMfa.sendKeys(passwordtoUpdate);
		confirmPasswordFieldNonMfa.sendKeys(passwordtoUpdate);
		changePasswordSubmitButtonNonMfa.click();
		updateExcelDataForChangePassword(10, 1, passwordtoUpdate);
	}

	public void clickChangeEmailButton() throws InterruptedException {
		Thread.sleep(10000);
		chnageEmailButton.click();
	}

	public void changeEmail(String newEmailtobeUpdated) throws IOException {
		newEmailField.sendKeys(newEmailtobeUpdated);
		changeEmailSubmitButton.click();
		updateExcelDataForChangeEmail(8, 0, newEmailtobeUpdated);
	}

	public void changeEmailNonMfa(String newEmailtobeUpdated) throws IOException {
		newEmailField.sendKeys(newEmailtobeUpdated);
		changeEmailSubmitButton.click();
		updateExcelDataForChangeEmail(11, 0, newEmailtobeUpdated);
	}

	public void clickReturntoEditPage(String serviceProviderUrl) throws InterruptedException {
		if (serviceProviderUrl.contains("acuracertified") || serviceProviderUrl.contains("acura.com")) {
			returntoEditPage.click();
			Thread.sleep(5000);
			LoginPage lp=new LoginPage(DriverFactory.getDriver());
			lp.handleAcuraLogin();
		}
		else {
		returntoEditPage.click();
		}
	}

	public void clickChangePhoneButton() {
		changePhoneButton.click();
	}

	public void changePhone(String phoneNumber) {
		newPhoNeField.sendKeys(phoneNumber);
		changePhoneSubmitButton.click();
	}

	public void clickChangeSecurityQuestionButton() {
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait1.until(ExpectedConditions.elementToBeClickable(changeSecurityQuestionButtonNonMfa)).click();
	}

	public boolean verifyUsername(String name) {
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
		String userName = nameValue.getText();
		String actualName = name;
		if (userName.equals(actualName)) {
			return true;
		} else {
			return false;
		}
	}

	public String generateNewPassword() {
		int counter = readCounter() + 1;
		writeCounter(counter);
		System.out.println("password:" + Base_Password + String.format("%02d", counter));
		return Base_Password + String.format("%02d", counter);
	}

	public String generateNewEmail() {
		int counter = readCounter() + 1;
		writeCounter(counter);
		System.out.println("email:" + Base_Email + counter);
		return Base_Email + counter + "@yopmail.com";
	}

	public int readCounter() {
		configReader = new ConfigReader();
		prop = configReader.init_prop();
		return Integer.parseInt(prop.getProperty("counter", "0"));
	}

	public void writeCounter(int updatedcounter) {
		configReader = new ConfigReader();
		prop = configReader.write_data(updatedcounter);
	}

	public void fillNegativeDataforChangeFirstName() throws IOException, InterruptedException {
		firstNameField.clear();
		firstNameField.sendKeys("");
		changeNameSubmitButton.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(firstNameFieldErrorMsg));
		Thread.sleep(5000);
		firstNameFieldErrorMsg.isDisplayed();
	}

	public void fillNegativeDataforChangeLastName() throws IOException, InterruptedException {
		lastNameField.clear();
		lastNameField.sendKeys("");
		changeNameSubmitButton.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(lastNameFieldErrorMsg));
		Thread.sleep(5000);
		lastNameFieldErrorMsg.isDisplayed();
	}

	public void fillNegativeDataforChangeEmail(String Email) throws InterruptedException {
		newEmailField.sendKeys(Email);
		changeEmailSubmitButton.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(changeEmailErrorMsg));
		Thread.sleep(5000);
		changeEmailErrorMsg.isDisplayed();
		newEmailField.clear();
	}

	public void changePhoneNegativeData() throws InterruptedException {
		newPhoNeField.sendKeys("12345");
		changePhoneSubmitButton.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(changePhoneErrorMsg));
		Thread.sleep(5000);
		changePhoneErrorMsg.isDisplayed();
		newPhoNeField.clear();
	}

	public void fillNegativeData(String password) throws InterruptedException {
		passwordField.click();
		newPasswordField.click();
		Thread.sleep(3000);
		passwordFieldErrorMsg.isDisplayed();
		confirmPasswordField.click();
		newPasswordField.click();
		Thread.sleep(2000);
		passwordFieldErrorMsg2.isDisplayed();
		passwordFieldErrorMsg3.isDisplayed();
		passwordField.sendKeys(password);
		newPasswordField.sendKeys("honda123");
		Thread.sleep(2000);
		confirmPasswordField.click();
		Thread.sleep(2000);
		passwordFieldErrorMsg1.isDisplayed();
		newPasswordField.clear();
		newPasswordField.sendKeys("Honda_123");
		confirmPasswordField.sendKeys("Honda_12");
		newPasswordField.click();
		Thread.sleep(2000);
		confirmPasswordFieldErrorMsg1.isDisplayed();
		newPasswordField.clear();
		confirmPasswordField.clear();
		newPasswordField.sendKeys(password);
		confirmPasswordField.sendKeys(password);
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.elementToBeClickable(changePasswordSubmitButton)).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.visibilityOf(oldPasswordUsedErrorMsg));
		String shownmsg = oldPasswordUsedErrorMsg.getText();
		System.out.println(shownmsg);
		String actualmsg = "You cannot reuse this old password.";
		Assert.assertEquals(shownmsg, actualmsg);
		passwordField.clear();
		newPasswordField.clear();
		confirmPasswordField.clear();
	}
	public void fillNegativeDataNonMFA(String password) throws InterruptedException{
		passwordFieldNonMfa.click();
		newPasswordFieldNonMfa.click();
		Thread.sleep(3000);
		passwordFieldErrorMsg.isDisplayed();
		confirmPasswordFieldNonMfa.click();
		newPasswordFieldNonMfa.click();
		Thread.sleep(2000);
		passwordFieldErrorMsg2.isDisplayed();
		passwordFieldErrorMsg3.isDisplayed();
		passwordFieldNonMfa.sendKeys(password);
		newPasswordFieldNonMfa.sendKeys("honda123");
		Thread.sleep(2000);
		confirmPasswordFieldNonMfa.click();
		Thread.sleep(2000);
		passwordFieldErrorMsg1.isDisplayed();
		newPasswordFieldNonMfa.clear();
		newPasswordFieldNonMfa.sendKeys("Honda_123");
		confirmPasswordFieldNonMfa.sendKeys("Honda_12");
		newPasswordFieldNonMfa.click();
		Thread.sleep(2000);
		confirmPasswordFieldErrorMsg1.isDisplayed();
		newPasswordFieldNonMfa.clear();
		confirmPasswordFieldNonMfa.clear();
		newPasswordFieldNonMfa.sendKeys(password);
		confirmPasswordFieldNonMfa.sendKeys(password);
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.elementToBeClickable(changePasswordSubmitButtonNonMfa)).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.visibilityOf(oldPasswordUsedErrorMsgNonMfa));
		String shownmsg = oldPasswordUsedErrorMsgNonMfa.getText();
		System.out.println(shownmsg);
		String actualmsg = "You cannot reuse this old password.";
		Assert.assertEquals(shownmsg, actualmsg);
		passwordFieldNonMfa.clear();
		newPasswordFieldNonMfa.clear();
		confirmPasswordFieldNonMfa.clear();
	}
}
