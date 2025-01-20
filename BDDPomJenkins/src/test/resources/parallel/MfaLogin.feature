Feature: Login Page Automation

  Background: 
    Given User is on the login page
   @MFA @Smoke
  Scenario: (MFA - Settings Test) Login Frequency Setting = 0 (Every Login) – in an MFA Enabled Site
    When User enters valid username and password that are of mfa enabled user
    And Clicks on the Sign In Button for Mfa Enabled user
    Then User is Asked to Select either the Email or the sms
    And User enters the verification code sent to user for Mfa Enabled user
    Then if the verification is validated user is logged in for mfa enabled user
    
   @MFA @Smoke
   Scenario: (MFA - Login Test) Existing User Not Enrolled in MFA Logging into an MFA Enabled Site
    When User enters valid username and password that are of not mfa enabled user
    And user is asked to check the mfa and sms terms and conditions and user enters phone number
    Then Clicks on the Sign In Button for non Mfa Enabled user
    And User enters the verification code sent to user for non Mfa Enabled user
    Then if the verification is validated user is logged in for non mfa enabled user

  @MFA @Smoke
  Scenario: Trusted Device (Login Frequency Setting = 0 (Every Login))
    When User enters valid username and password that are of mfa enabled user for trusted device
    And Clicks on the Sign In Button for Mfa Enabled user
    Then User is Asked to Select either the Email or the sms
    And User enters the verification code sent to user and checks trusted device
    Then if the verification is validated user is logged in for mfa enabled user in trusted device
    And Clicks on logout Button from profile and again click on the login button
    Then User again enters valid username and password and clicks login button for the trusted device
    And user gets logged into site without MFA
 
  @MFA @Smoke
  Scenario: Remember my Username functionality for MFA opted user
    When User Enters valid username and password and checks remember me
    And Clicks On the Login Button
    Then User is Asked to Select either the Email or the sms
    And User Enters the verification code sent to user
    Then if the Verification is validated user is logged in
    And Clicks on logout Button from profile and again click on the login button
    Then Check That the username will be auto populated
    
    @MFA @Regression
    Scenario: (MFA - Settings Test) Login Frequency Setting = 0 (Every Login) – in an MFA Enabled Site 
    When User enters empty or invalid username and password
    And Users enters valid username and invalid password
    Then User is Asked to Select either the Email or the sms
    And User enters the invalid and valid verification code sent to user
    Then if the verification is validated user is logged in for mfa enabled user

    
    @NonMFA @Regression
    Scenario: (MFA - Settings Test) Login Frequency Setting = 0 (Every Login) – in an MFA Enabled Site
    When User enters empty or invalid username and password
    And Users enters valid username and invalid password for non mfa
    Then if the Verification is validated user is logged in for non mfa user
    
  ### For the Non-MFA Enabled Site Start ############
   @NonMFA @Smoke
   Scenario: Remember my Username functionality for User who has security questions in Non-Mfa enabled site
    When User Enters valid username and password and checks remember me for nonmfa user
    And Clicks On the Login Button
    Then if the Verification is validated user is logged in for non mfa user
    And Clicks on logout Button from profile and again click on the login button
    Then Check That the username will be auto populated for non mfa

  @NonMFA @Smoke
  Scenario: Existing User that does not have Security Questions Logging into a site that is NOT MFA Enabled
    When User enters valid username and password that are of mfa enabled user
    And Clicks on the Sign In Button for Mfa Enabled user
    Then select the questions and click submit button
    And User is redirected to the login page

  ### End #################
  #Scenario: (MFA - Settings Test) Login Frequency Setting = Blank (Not Set; Never) – in an MFA Enabled Site
    #When User enters valid username and password that are of mfa enabled user
    #And Clicks on the Sign In Button for Mfa Enabled user
    #Then if the Verification is validated user is logged in
    #And Clicks On logout button from profile
    #Then Again Click on login from home page
    #And User enters the username and password and clicks login button for the mfa site
    #Then if the Verification is validated user is logged in
    #
   #Scenario: (MFA - Settings Test) Login Frequency Setting = 1 (1 Day) – in an MFA Enabled Site
    #When User enters valid username and password that are of mfa enabled user
    #And Clicks on the Sign In Button for Mfa Enabled user
    #Then User is Asked to Select either the Email or the sms
    #And User enters the verification code sent to user for Mfa Enabled user
    #Then if the verification is validated user is logged in for mfa enabled user
    #And Clicks on logout Button from profile and again click on the login button
    #Then User again enters valid username and password and clicks login button for the trusted device
    #And user gets logged into site without MFA