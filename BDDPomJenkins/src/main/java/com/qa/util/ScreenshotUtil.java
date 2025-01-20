package com.qa.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
public String captureScreenshot(WebDriver driver,String scenarioName) {
	String screenshotPath=System.getProperty("user.dir")+"/screenshots/"+scenarioName+".png";
	try {
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(screenshotPath));
	}catch(IOException e) {
		e.printStackTrace();
	}
	return screenshotPath;
}
}
