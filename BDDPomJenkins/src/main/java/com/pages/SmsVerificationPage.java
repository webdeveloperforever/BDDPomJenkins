package com.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SmsVerificationPage {
    private WebDriver driver;
	String windowId;
	String text;
    @FindBy(css = "body > div:nth-child(1) > section:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(3) > span:nth-child(1) > b:nth-child(1)")
    WebElement OTP;
    
    public SmsVerificationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
	public String verifyOtpFromPhone(String url,String phoneNumber) throws InterruptedException {
		windowId = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		driver.navigate().to(url+"1"+phoneNumber+"/");
		WebElement currentAddress =OTP;
		text = currentAddress.getText();
		System.out.println("the otp received is:" + text);
		driver.close();
		driver.switchTo().window(windowId);
		return text;
	}

}
