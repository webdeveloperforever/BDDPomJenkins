Feature: Password Reset Automation
  Background: 
    Given User is on the login page for the password reset
  @MFA @Smoke
  Scenario: (MFA - Password Test) Forgot Password from an MFA enabled site and user is opted in to MFA
    When User clicks on the forgot password link on the login page
    And User is asked to enter the email and click on the sign in
    Then User clicks on the forgotpassword link on the email
    And User enters the verification code from both the email and the sms
    Then if the verification is validated user is on the change password page and fills all the details
    And Login in with the updated password
    Then if the user is password is successfully changed user is logged in
    
  @MFA @Smoke
  Scenario: (MFA - Password Test) Forgot Password from an MFA enabled site but user has NOT opted into MFA AND does NOT have security questions
    When User clicks on the forgot password link on the login page
    And User is asked to enter the email and click on the sign in for the non mfa and no security questions user
    Then User clicks on the forgotpassword link on the email for the non mfa and no security questions user
    And User fills password and confirm password fields and click submit and click on the sign in button
    Then check the email for the password change successful for non mfa and no security questions user
    
  @MFA @Smoke
  Scenario: (MFA - Password Test) Forgot Password from an MFA enabled site but user has NOT opted into MFA and does have security questions
    When User clicks on the forgot password link on the login page
    And User is asked to enter the email and click on the sign in for the non mfa and have security questions user
    Then User clicks on the forgotpassword link on the email for the non mfa and have security questions user
    And User Answers the Security Question prompted and clicks submit
    Then if the verification is validated user is on the change password page and fills all the details
    And check the email for the password change successful for non mfa and security questions user
    
  @MFA @Regression
  Scenario: (MFA - Password Test) Forgot Password from an MFA enabled site and user is opted in to MFA
    When User clicks on the forgot password link on the login page
    And User is asked to enter the email enter empty and invalid email and click on the sign in
    Then User clicks on the forgotpassword link on the email
    And User enters the invalid and valid verification code from both the email and the sms
    And check for invalid and old passwords
    Then if the verification is validated user is on the change password page and fills all the details 
    Then Login in with the updated password
    And if the user is password is successfully changed user is logged in
 
 #non-mfa start
 @NonMFA @Smoke
 	Scenario: (Password Test) Forgot Password from a site that is NOT MFA enabled
    When User clicks on the forgot password link on the login page
    And User is asked to enter the email and click on the sign in for the non mfa user
    Then User clicks on the forgotpassword link on the email for the non mfa user
    And User Answers the Security Question prompted and clicks submit
    Then if the verification is validated user is on the change password page and fills all the details
    And Login in with the updated password in the non mfa site
    Then if the user is password is successfully changed user is logged in for the non mfa user
      
  @NonMFA @Regression
 	Scenario: (Password Test) Forgot Password from a site that is NOT MFA enabled
    When User clicks on the forgot password link on the login page
    And User is asked to enter the email enter empty and invalid email and click on the sign in for non mfa user
    Then User clicks on the forgotpassword link on the email for the non mfa user
    And User Answers valid and invalid to the Security Question prompted and clicks submit
    And check for invalid and old passwords for non mfa
    Then if the verification is validated user is on the change password page and fills all the details
    And Login in with the updated password in the non mfa site
    Then if the user is password is successfully changed user is logged in for the non mfa user
  ##end
