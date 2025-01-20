package com.pages;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
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

public class RegistrationPage {
    private WebDriver driver;
	private ConfigReader configReader;
	private Properties prop;
    @FindBy(xpath = "/html[1]/body[1]/div[3]/header[1]/div[1]/div[1]/c-header-component[1]/header[1]/div[1]/div[2]/div[1]/div[1]/a[2]")
    WebElement registerButton;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[4]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
    WebElement firstNameField;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[4]/div[2]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
    WebElement lastNameField;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[4]/form[1]/div[1]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
    WebElement phoneNumberField;
    
    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[4]/form[2]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
    WebElement emailField;
    
    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[4]/form[3]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
    WebElement passwordField;
    
    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[4]/form[4]/div[1]/lightning-input[1]/lightning-primitive-input-simple[1]/div[1]/div[1]/input[1]")
    WebElement confirmPasswordField;
    
    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[4]/div[3]/c-ciam-registration-checkbox-u-x[1]/div[1]/div[1]/div[1]/label[1]/span[1]")
    WebElement registrationCheckbox1;
    
    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[4]/div[4]/c-ciam-registration-checkbox-u-x[1]/div[1]/div[1]/div[1]/label[1]/span[1]")
    WebElement registrationCheckbox2;
    
    @FindBy(xpath = "//*[@name='firstname']")
    WebElement firstNameField1;
    
    @FindBy(xpath = "//*[@name='lastname']")
    WebElement lastNameField1;
    
    @FindBy(xpath = "//*[@name='email']")
    WebElement emailField1;
    
    @FindBy(xpath="//*[@name='phone']")
    WebElement phoneField1;
    
    @FindBy(xpath = "//*[@name='password']")
    WebElement passwordField1;
    
    @FindBy(xpath = "//*[@name='confirmpassword']")
    WebElement confirmPasswordField1;
    
    @FindBy(xpath = "//*[@title='Create Account']")
    WebElement createAccountButton;
    
    @FindBy(xpath="/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/b[1]")
    WebElement loginInfoText;
    
    @FindBy(xpath = "//*[text()='You must provide a valid phone number (10 digits).']")
    WebElement phoneFieldErrorMsg2;
    
    @FindBy(xpath="//*[text()='You must provide a First Name to register.']")
    WebElement firstNameFieldErrorMsg;
    
    @FindBy(xpath="//*[text()='You must provide a Last Name to register.']")
    WebElement lastNameFieldErrorMsg;
    
    @FindBy(xpath = "//*[text()='Please provide a valid email in order to register.']")
    WebElement emailFieldErrorMsg1;
    
    @FindBy(xpath="//*[text()='You must provide a phone number in order to register.']")
    WebElement phoneFieldErrorMsg1;
    
    @FindBy(xpath="//*[text()='Your password does not meet the minimum password requirements.']")
    WebElement passwordFieldErrorMsg1;
    
    @FindBy(xpath = "//*[text()='You must provide a password in order to register.']")
    WebElement passwordFieldErrorMsg2;
    
    @FindBy(xpath = "//*[text()='Passwords do not match.']")
    WebElement passwordFieldErrorMsg3;
    
    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[4]/form[2]/div[1]/div[1]")
    WebElement duplicateEmailErrorMsg;
    
    @FindBy(xpath="/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/div[4]/form[1]/div[1]/div[1]")
    WebElement duplicateEmailErrorMsg1;
     
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        configReader = new ConfigReader();
        prop = configReader.init_prop();
    }
    public void openRegistrationPage(String url) {
        driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    public void cilckRegisterButton() {
    	registerButton.click();
    }
    public void fillMFAregsitrationData(String firstName,String lastName,String phoneNumber,String email,String Password) {
    	firstNameField.sendKeys(firstName);
    	lastNameField.sendKeys(lastName);
    	phoneNumberField.sendKeys(phoneNumber);
    	emailField.sendKeys(email);
    	passwordField.sendKeys(Password);
    	confirmPasswordField.sendKeys(Password);
    }
    public void checkMfaCheckboxes() {
    	registrationCheckbox1.click();
    	registrationCheckbox2.click();
    }
    public void clickCreateAccountButton() throws IOException {
    	int counter=readCounter()+1;
    	writeCounter(counter);
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    	wait.until(ExpectedConditions.elementToBeClickable(createAccountButton)).click();
    	FileInputStream fis=new FileInputStream(prop.getProperty("excelpath"));
    	XSSFWorkbook workbook=new XSSFWorkbook(fis);
    	XSSFSheet sheet=workbook.getSheetAt(2);
    	sheet.getRow(3).getCell(1).setCellValue("devcheckout"+counter+"@yopmail.com");
    	FileOutputStream output=new FileOutputStream(prop.getProperty("excelpath"));
    	System.out.println("updated email:"+counter);
    	workbook.write(output);
    	workbook.close();
    	fis.close();
    }
    public void fillNonMFAregsitrationData(String firstName,String lastName,String email,String Password) {
    	firstNameField1.sendKeys(firstName);
    	lastNameField1.sendKeys(lastName);
    	emailField1.sendKeys(email);
    	passwordField1.sendKeys(Password);
    	confirmPasswordField1.sendKeys(Password);
    }
    public void checkNonMFACheckbox() {
    	registrationCheckbox1.click();
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
    public void fillNegativeDataForMFA() throws InterruptedException {
		firstNameField1.sendKeys("");
		lastNameField1.sendKeys("");
		phoneField1.sendKeys("");
		emailField1.click();
        phoneFieldErrorMsg1.isDisplayed();
        phoneField1.sendKeys("40525");
        emailField1.click();
        phoneFieldErrorMsg2.isDisplayed();
        emailField1.sendKeys("test");
        phoneField1.click();
		boolean displayed=firstNameFieldErrorMsg.isDisplayed();
		Assert.assertTrue(displayed, "You must provide a phone number in order to register is present");
		lastNameFieldErrorMsg.isDisplayed();
		Thread.sleep(2000);
		emailFieldErrorMsg1.isDisplayed();
		passwordField1.sendKeys("Wi123");
		emailField1.click();
		passwordFieldErrorMsg1.isDisplayed();
		Thread.sleep(2000);
		passwordField1.clear();
		emailField1.click();
		passwordFieldErrorMsg2.isDisplayed();
		Thread.sleep(2000);
		passwordField1.sendKeys("Wipro@123");
		confirmPasswordField1.sendKeys("wipro");
		emailField1.click();
		passwordFieldErrorMsg3.isDisplayed();
		passwordField1.sendKeys("");
		confirmPasswordField1.sendKeys("");
		emailField1.clear();
		emailField1.sendKeys("testbdd21@yopmail.com");
		passwordField1.click();
		String shownmsg = duplicateEmailErrorMsg.getText();
		String actualmsg = "You already have an account with us. Sign in";
		Assert.assertEquals(shownmsg, actualmsg);
	    emailField1.clear();
		phoneField1.clear();
		passwordField1.clear();
		confirmPasswordField1.clear();
    }
    public void fillNegativeDataForNonMFA() throws InterruptedException {
		firstNameField1.sendKeys("");
		lastNameField1.sendKeys("");
		emailField1.click();
		firstNameFieldErrorMsg.isDisplayed();
		lastNameFieldErrorMsg.isDisplayed();
        emailField1.sendKeys("test");
        passwordField1.click();
        emailFieldErrorMsg1.isDisplayed();
		Thread.sleep(2000);
		passwordField1.sendKeys("Wi123");
		emailField1.click();
		passwordFieldErrorMsg1.isDisplayed();
		Thread.sleep(2000);
		passwordField1.clear();
		emailField1.click();
		Thread.sleep(2000);
		passwordField1.sendKeys("Wipro@123");
		confirmPasswordField1.sendKeys("wipro");
		emailField1.click();
		passwordFieldErrorMsg3.isDisplayed();
		passwordField1.sendKeys("");
		confirmPasswordField1.sendKeys("");
		emailField1.clear();
		emailField1.sendKeys("testbdd21@yopmail.com");
		passwordField1.click();
		Thread.sleep(5000);
		String shownmsg = duplicateEmailErrorMsg1.getText();
		String actualmsg = "You already have an account with us. Sign in";
		Assert.assertEquals(shownmsg, actualmsg);
		firstNameField1.clear();
		lastNameField1.clear();
	    emailField1.clear();
		passwordField1.clear();
		confirmPasswordField1.clear();
    }
}
