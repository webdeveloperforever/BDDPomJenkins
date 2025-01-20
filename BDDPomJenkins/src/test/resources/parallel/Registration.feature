Feature: Registration Page Automation

@MFA @Smoke
Scenario: Check registration is successful with the valid emails and phone in devcheckoutsite when the mfa is enabled for the community
    Given User is on the dev checkout page
    When User click on the Registration Link
    And User enters firstName and lastName and emailAddress and phone and createPassword and confirmPassword
		Then User select the all terms and conditions
		And Clicks on the CreatAccount Button
		Then User is asked to enters the email verification code
		And if the verification is validated User is asked to enters the phone verification code
		Then if the verification is validated user is redirected to login page
		
@NonMFA @Smoke
Scenario: Check Non MFA registration is successful with the valid emails and phone in devcheckoutsite when the mfa is not enabled for the community
    Given User is on the dev checkout page
    When User click on the Registration Link
	  And User enters firstName and lastName and emailAddress and createPassword and confirmPassword
		Then User select the terms and conditions
		And Clicks on the CreatAccount Button
		Then User is asked to enters the email verification code
		And if the verification is validated User is asked to enters the Security Questions
		Then if the verification is validated user is redirected to login page
 
@MFA @Regression
Scenario: Check registration validation is successful with the valid inavlid emails and invalid phone in devcheckoutsite when the mfa is enabled for the community
    Given User is on the dev checkout page
    When User click on the Registration Link
	  And User enters empty firstName and empty lastName and invalid emailAddress and invalid phone and createPassword and confirmPassword
		And User enters firstName and lastName and emailAddress and phone and createPassword and confirmPassword
		Then User select the all terms and conditions
		And Clicks on the CreatAccount Button
		And User enters invalid email verification code
		Then User is asked to enters the email verification code
		And User enters invalid phone verification code
		And if the verification is validated User is asked to enters the phone verification code
		Then if the verification is validated user is redirected to login page
		
@NonMFA @Regression
Scenario: Check Non MFA registration Validations is successful with the valid emails and phone in devcheckoutsite when the mfa is enabled for the community
    Given User is on the dev checkout page
    When User click on the Registration Link
    And User enters empty firstName and empty lastName and invalid emailAddress and createPassword and confirmPassword
	  And User enters firstName and lastName and emailAddress and createPassword and confirmPassword
		Then User select the terms and conditions
		And Clicks on the CreatAccount Button
		And User enters invalid email verification code
		Then User is asked to enters the email verification code
		And Verify User is not able to create account without entering all the Security Question
		Then if the verification is validated user is redirected to login page

		
		