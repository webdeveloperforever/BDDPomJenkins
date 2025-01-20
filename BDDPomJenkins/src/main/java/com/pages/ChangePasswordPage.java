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

import com.qa.util.ConfigReader;
import com.qa.util.ExcelUtils;

public class ChangePasswordPage {
	private WebDriver driver;
	private ConfigReader configReader;
	private Properties prop;
	String Base_Password = "Honda_";
    private ExcelUtils excelUtils;
    List<String> valuestoUpdate;
	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[2]/form[1]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
	WebElement passwordField;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[2]/form[2]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
	WebElement confirmPasswordField;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[2]/form[2]/div[2]/button[1]")
	WebElement submitButton;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/button[1]")
	WebElement signInButton;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[4]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
	WebElement phoneField;
	
	@FindBy(xpath = "(//*[contains(text(),'In order to change your password you must provide a new password')])[1]")
	WebElement passwordFieldErrorMsg;

	@FindBy(xpath = "(//*[contains(text(),'In order to change your password you must confirm your new password')])[1]")
	WebElement confirmPasswordFieldErrorMsg;

	@FindBy(xpath = "(//*[contains(text(),'Your password does not meet the minimum password requirements.')])[1]")
	WebElement passwordFieldErrorMsg1;
	
	@FindBy(xpath = "(//*[contains(text(),'Passwords do not match.')])[1]")
	WebElement confirmPasswordFieldErrorMsg1;
	
	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]")
	WebElement oldPasswordUsedErrorMsg;
	
	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[2]/form[2]/div[2]/button[1]")
	WebElement submitButtonAfterError;
	
	public void updateExcelData(int rownumber) throws IOException {
		String passwordtoUpdate = generateNewPassword();
		FileInputStream fis = new FileInputStream(prop.getProperty("excelpath"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet2 = workbook.getSheetAt(1);
		sheet2.getRow(rownumber).getCell(1).setCellValue(valuestoUpdate.get(0));
		XSSFSheet sheet = workbook.getSheetAt(3);
		sheet.getRow(1).getCell(1).setCellValue(passwordtoUpdate);
		FileOutputStream output = new FileOutputStream(prop.getProperty("excelpath"));
		workbook.write(output);
		workbook.close();
		System.out.println("updated password:" + passwordtoUpdate);
		fis.close();
	}

	public ChangePasswordPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
        configReader = new ConfigReader();
        prop = configReader.init_prop();
        String excelFilePath = prop.getProperty("excelpath");
        excelUtils = new ExcelUtils(excelFilePath);
        valuestoUpdate = excelUtils.readExcelData("PasswordUpdate_SecurityQuestion", "Value");
	}

	public void fillChangePasswordDetails(String password) throws InterruptedException {
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(password);
		submitButton.click();
		Thread.sleep(10000);
		signInButton.click();
	}

	public void updatePasswordtoUpdateValueForNonMFANosecurityQuestionsUser() throws IOException {
		updateExcelData(2);
	}

	public void updatePasswordtoUpdateValueForMFAEnabledUser() throws IOException {
		updateExcelData(1);
	}

	public void updatePasswordtoUpdateValueForNonMFAsecurityQuestionsUser() throws IOException {
		updateExcelData(3);
	}

	public String generateNewPassword() {
		int counter = readCounter() + 1;
		writeCounter(counter);
		System.out.println("password:" + Base_Password + String.format("%03d", counter));
		return Base_Password + String.format("%02d", counter);
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
	public void fillNegativeData(String password) throws InterruptedException {
		passwordField.click();
		confirmPasswordField.click();
		Thread.sleep(3000);
		passwordFieldErrorMsg.isDisplayed();
		passwordField.click();
		Thread.sleep(2000);
		confirmPasswordFieldErrorMsg.isDisplayed();
		passwordField.sendKeys("honda123");
		Thread.sleep(2000);
		confirmPasswordField.click();
		Thread.sleep(2000);
		passwordFieldErrorMsg1.isDisplayed();
		passwordField.clear();
		passwordField.sendKeys("Honda_123");
		confirmPasswordField.sendKeys("Honda_12");
		passwordField.click();
		Thread.sleep(2000);
		confirmPasswordFieldErrorMsg1.isDisplayed();
		passwordField.clear();
		confirmPasswordField.clear();
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(password);
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.visibilityOf(oldPasswordUsedErrorMsg));
		String shownmsg = oldPasswordUsedErrorMsg.getText();
		System.out.println(shownmsg);
		String actualmsg = "You cannot reuse this old password.";
		Assert.assertEquals(shownmsg, actualmsg);
		passwordField.clear();
		confirmPasswordField.clear();
	}
	public void fillChangePasswordDetailsForNegativeData(String password) throws InterruptedException {
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(password);
		submitButton.click();
		Thread.sleep(10000);
		signInButton.click();
	}
}
