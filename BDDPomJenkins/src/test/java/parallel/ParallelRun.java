package parallel;

import java.util.Properties;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;

import com.qa.util.ConfigReader;
import com.qa.util.SendEmail;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
@CucumberOptions(
		features = {"src/test/resources/parallel/MfaLogin.feature"},
		glue = {"parallel"},
		plugin = {"pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-thread/",
				"rerun:target/failedrerun.txt"},
		tags="@MFA and @Smoke"
		)

public class ParallelRun extends AbstractTestNGCucumberTests{
	private SendEmail sendemail;
	private ConfigReader configReader;
	Properties prop;
	@Override
	@DataProvider(parallel=true)
	public Object[][] scenarios(){
		return super.scenarios();
	}
	@AfterSuite
	public void sendReport() {
		configReader = new ConfigReader();
		prop = configReader.init_prop();
		String ServiceProvider=prop.getProperty("serviceprovider");
		String environment;
		if(prop.getProperty("excelpath").contains("QA")) {
			environment="QA";
		}else {
			environment="UAT";
		}
		if(prop.getProperty("sendexecutionreportemail").equals("true")) {
		String toEmail="qatesthonda001@gmail.com";
		String subject="Test Execution Report in "+ServiceProvider+" "+environment;;
		String body="Please find the attached test automation execution report in "+ServiceProvider+" "+environment;
		String filePath="test output/PdfReport/ExtentPdf.pdf";
		sendemail=new SendEmail();
		sendemail.sendEmailWithAttachment(toEmail, subject, body, filePath);
	}
	}
}
