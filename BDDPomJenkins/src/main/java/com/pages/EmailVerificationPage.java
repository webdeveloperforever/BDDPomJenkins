package com.pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EmailVerificationPage {
	private WebDriver driver;
	String windowId;
	String text;
	@FindBy(xpath = "/html[1]/body[1]/div[1]/header[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")
	WebElement emailField;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/header[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]")
	WebElement emailSubmitButton;

	@FindBy(xpath = "//td[normalize-space()='Sandbox: Your Honda Verification Code']")
	WebElement verifyEmailXpath;

	@FindBy(xpath = "/html[1]/body[1]/div[2]/p[3]/strong[1]")
	WebElement otpXpath;

	@FindBy(xpath = "//td[contains(text(),'Sandbox: Verify your new account in DreamshopCheck')]")
	WebElement registerEmailXpath;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/main[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[1]/input[1]")
	WebElement yopmailEmailField;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/main[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[4]/button[1]/i[1]")
	WebElement yopmailArrow;

	@FindBy(xpath = "/html[1]/body[1]/main[1]/div[1]/div[1]/div[1]/div[2]/p[3]/strong[1]")
	WebElement yopMailOtp;

	@FindBy(xpath = "/html[1]/body[1]")
	WebElement registerOtpXpath;

	@FindBy(xpath = "/html[1]/body[1]/main[1]/div[1]/div[1]/div[1]/div[2]/p[3]/a[1]")
	WebElement resetPasswordLink;

	@FindBy(xpath = "//input[@title='Reset Password']")
	WebElement resetPasswordButton;

	@FindBy(xpath = "//pre")
	WebElement emailText;

	@FindBy(xpath = "/html[1]/body[1]/main[1]/div[1]/div[1]/div[1]/div[2]/p[3]")
	WebElement changeNameEmail;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/lightning-output-field[1]/div[1]/lightning-formatted-name[1]")
	WebElement changeNameExpectedValue;

	@FindBy(xpath = "/html[1]/body[1]/main[1]/div[1]/div[1]/div[1]/div[2]/p[4]/a[1]")
	WebElement changeEmailVerifyLink;

	@FindBy(xpath = "/html[1]/body[1]/main[1]/div[1]/div[1]/div[1]/div[2]")
	WebElement changeSecurityQuestionEmail;
	
	@FindBy(xpath = "/html[1]/body[1]/main[1]/div[1]/div[1]/div[1]/div[2]/p[2]")
	WebElement changePasswordEmail;
	
	@FindBy(xpath ="/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[2]/div[1]/div[1]/div[1]/div[6]/button[1]")
	WebElement refreshButton;
	
	public EmailVerificationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String verifyEmail(String url, String email) throws InterruptedException {
		Thread.sleep(5000);
		windowId = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		driver.navigate().to(url);
		String emailfieldValue = yopmailEmailField.getAttribute("value");
		if (!emailfieldValue.isBlank() || emailfieldValue != null) {
			yopmailEmailField.clear();
		}
		yopmailEmailField.sendKeys(email);
		yopmailArrow.click();
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.elementToBeClickable(refreshButton)).click();
		driver.switchTo().frame("ifmail");
		WebElement currentAddress = yopMailOtp;
		text = currentAddress.getText();
		System.out.println("the otp received is:" + text);
		driver.close();
		driver.switchTo().window(windowId);
		return text;

	}

	public String verifyRegisterEmail(String url, String email) throws InterruptedException {
		windowId = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		driver.navigate().to(url);
		String emailfieldValue = yopmailEmailField.getAttribute("value");
		if (!emailfieldValue.isBlank() || emailfieldValue != null) {
			yopmailEmailField.clear();
		}
		yopmailEmailField.sendKeys(email);
		yopmailArrow.click();
		driver.switchTo().frame("ifmail");
		WebElement currentAddress = emailText;
		text = currentAddress.getText();
		String numberOnly = text.replaceAll("[^0-9]", "");
		System.out.println("the otp received is:" + numberOnly);
		driver.close();
		driver.switchTo().window(windowId);
		return numberOnly;
	}

	public void resetPassword(String url, String email) throws InterruptedException {
		Thread.sleep(10000);
		windowId = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		driver.navigate().to(url);
		String emailfieldValue = yopmailEmailField.getAttribute("value");
		if (!emailfieldValue.isBlank() || emailfieldValue != null) {
			yopmailEmailField.clear();
		}
		yopmailEmailField.sendKeys(email);
		yopmailArrow.click();
		driver.switchTo().frame("ifmail");
		// open the link in new tab, Keys.Chord string passed to sendKeys
		String forgotPasswordLink = resetPasswordLink.getAttribute("href");
		System.out.println(forgotPasswordLink);
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(windowId)) {
				// Switch to the opened tab
				driver.switchTo().window(actual);
				// opening the URL saved.
				driver.get(forgotPasswordLink);
			}
		}
		resetPasswordButton.click();
	}

	public void verifyChangeNameEmail(String url, String Email) {
		windowId = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		driver.navigate().to(url);
		String emailfieldValue = yopmailEmailField.getAttribute("value");
		if (!emailfieldValue.isBlank() || emailfieldValue != null) {
			yopmailEmailField.clear();
		}
		yopmailEmailField.sendKeys(Email);
		yopmailArrow.click();
		driver.switchTo().frame("ifmail");
		WebElement emailContent = changeNameEmail;
		String emailMessage = emailContent.getText();
		System.out.println("emailmessage:" + emailMessage);
		String actualFullName = extractValue(emailMessage, "New name:");
		System.out.println("Actual Full Name: " + actualFullName);
		driver.close();
		driver.switchTo().window(windowId);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("location.reload()");
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait1.until(ExpectedConditions.visibilityOf(changeNameExpectedValue));
		String expectedFullNameValue = changeNameExpectedValue.getText();
		System.out.println(expectedFullNameValue);
		Assert.assertEquals(expectedFullNameValue, actualFullName);
	}

	public void verifyChangeEmail(String url, String Email) {
		windowId = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		driver.navigate().to(url);
		String emailfieldValue = yopmailEmailField.getAttribute("value");
		if (!emailfieldValue.isBlank() || emailfieldValue != null) {
			yopmailEmailField.clear();
		}
		yopmailEmailField.sendKeys(Email);
		yopmailArrow.click();
		driver.switchTo().frame("ifmail");
		changeEmailVerifyLink.click();
		driver.close();
		driver.switchTo().window(windowId);
	}

	public void verifyChangePhone(String url, String Email) {
		windowId = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		driver.navigate().to(url);
		String emailfieldValue = yopmailEmailField.getAttribute("value");
		if (!emailfieldValue.isBlank() || emailfieldValue != null) {
			yopmailEmailField.clear();
		}
		yopmailEmailField.sendKeys(Email);
		yopmailArrow.click();
		driver.switchTo().frame("ifmail");
		WebElement emailContent = changeNameEmail;
		String emailMessage = emailContent.getText();
		System.out.println("emailmessage:" + emailMessage);
		String previousPhone = extractValue(emailMessage, "Previous phone number:");
		System.out.println("Previous phone: " + previousPhone);
		String newPhone = extractValue(emailMessage, "New phone number:");
		System.out.println("New phone: " + newPhone);
		Assert.assertNotEquals(previousPhone, newPhone);
		driver.close();
		driver.switchTo().window(windowId);
	}

	public void verifyChangeSecurityQuestionsEmail(String url, String Email,String serviceProviderurl) {
		windowId = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		driver.navigate().to(url);
		String emailfieldValue = yopmailEmailField.getAttribute("value");
		if (!emailfieldValue.isBlank() || emailfieldValue != null) {
			yopmailEmailField.clear();
		}
		yopmailEmailField.sendKeys(Email);
		yopmailArrow.click();
		driver.switchTo().frame("ifmail");
		WebElement emailContent = changeSecurityQuestionEmail;
		String emailMessage = emailContent.getText();
		String actualContent;
		if(serviceProviderurl.contains("acura.com")|| serviceProviderurl.contains("acuracertified")) {
			actualContent="Hello Test BDD,\n"
	    		+ "We recently received a request to update the security questions associated with your Acura account.\n"
	    		+ "If you didn't request this change, please contact us.\n"
	    		+ "Thank you,\n"
	    		+ "The Acura Team";
		}else {
			actualContent = "Hi Test BDD,\n" + "\n"
				+ "We recently received a request to change your MyGarage account’s security questions.\n" + "\n"
				+ "If you didn’t request this change, contact us.\n" + "\n" + "Thank you,\n" + "MyGarage";
		}
		Assert.assertEquals(emailMessage, actualContent);
		driver.close();
		driver.switchTo().window(windowId);
	}
	public void verifyChangePasswordEmail(String url,String Email) {
		windowId = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		driver.navigate().to(url);
		String emailfieldValue = yopmailEmailField.getAttribute("value");
		if (!emailfieldValue.isBlank() || emailfieldValue != null) {
			yopmailEmailField.clear();
		}
		yopmailEmailField.sendKeys(Email);
		yopmailArrow.click();
		driver.switchTo().frame("ifmail");
		WebElement emailContent = changePasswordEmail;
		String emailMessage = emailContent.getText();
		Assert.assertTrue(emailMessage.contains("account has been changed."));
		driver.close();
		driver.switchTo().window(windowId);
	}

	private static String extractValue(String input, String key) {
		int startIndex = input.indexOf(key);
		if (startIndex != -1) {
			startIndex += key.length(); // Move to the end of the key
			int endIndex = input.indexOf("\n", startIndex); // Look for the end of the line
			return (endIndex == -1) ? input.substring(startIndex).trim() // If no newline, take till the end of the
																			// string
					: input.substring(startIndex, endIndex).trim();
		}
		return "Not found"; // Return a default message if the key is not found
	}
}
