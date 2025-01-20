Feature: Manage profile Automation
   
  ######### MFA Start ###############
  @MFA @Smoke
  Scenario: (MFA - Manage Profile Test) Manage Profile page in an MFA Enabled Site
  Given User is logged in of an mfa enabled user for manageprofile
  When User clicks on the myprofile and the edit for the manageprofile page
  Then Confirm that there is no Change Security Question Button
  
  @MFA @Smoke
  Scenario: (MFA - Manage Profile Test) Change Name (Manage Profile Frequency  = 0 (Every action)) - in an MFA Enabled Site
  Given User is logged in of an mfa enabled user for changename
  When User clicks on the myprofile and the edit for the manageprofile page
  Then click on the change name button
  And User is asked to select either the verification by the email or the sms
  Then if the verification either by email or sms is valid then user is asked to enter the updated values and click on the submit
  And if the change name is valid then the updated name is seen on the userdetail page
  
  @MFA @Regression
  Scenario: (MFA - Manage Profile Test) Change Name validation(Manage Profile Frequency  = 0 (Every action)) - in an MFA Enabled Site
  Given User is logged in of an mfa enabled user for changename
  When User clicks on the myprofile and the edit for the manageprofile page
  Then click on the change name button
  And User is asked to select either the verification by the email or the sms of invalid and valid otp
  Then if the verification either by email or sms is valid then user is asked to enter the updated values, if user removed the First Name,Last Name value and click on the submit
  And if the change name is valid then the updated name is seen on the userdetail page
  
  @MFA @Smoke
  Scenario: (MFA - Manage Profile Test) Change Password in an MFA Enabled Site
  Given User is logged in of an mfa enabled user for changepassword
  When User clicks on the myprofile and the edit for the manageprofile page
  Then click on the change password button
  And enter the verification from email
  Then enter the verification from phone
  And if verification is successful then the user is asked to enter the new password and confirmpassword and click submit
  Then again login with the updated password
  And if the change password is successful then the user is logged in successful
  
  @MFA @Regression
  Scenario: (MFA - Manage Profile Test) Change Password with negative scenarios in an MFA Enabled Site
  Given User is logged in of an mfa enabled user for changepassword
  When User clicks on the myprofile and the edit for the manageprofile page
  Then click on the change password button
  And enter the valid and invalid verification from email
  Then enter the valid and invalid verification from phone
  And if verification is successful then the user is asked to enter the new password with negative scenario and confirmpassword and click submit
  Then again login with the updated password
  And if the change password is successful then the user is logged in successful
  
  @MFA @Smoke
  Scenario: (MFA - Manage Profile Test) Change Email in an MFA Enabled Site (Manage Profile Frequency  = 0 (Every action))
  Given User is logged in of an mfa enabled user for the change email
  When User clicks on the myprofile and the edit for the manageprofile page
  Then click on the change email button
  And enter the verification from phone for change email
  Then if the verification is successful user is asked is asked to enter the email to be updated and click submit
  And user goes to the new email and verifies it
  Then user logsout from the site
  And User logs in again with the updated email
  Then if the change email is successful user is logged in
  
  @MFA @Regression
  Scenario: (MFA - Manage Profile Test) Change Email in an MFA Enabled Site with Negative Scenarios(Manage Profile Frequency  = 0 (Every action))
  Given User is logged in of an mfa enabled user for the change email
  When User clicks on the myprofile and the edit for the manageprofile page
  Then click on the change email button
  And enter the invalid and valid verification from phone for change email
  Then if the verification is successful user is asked is asked to enter the existing email and new email to be updated and click submit
  And user goes to the new email and verifies it
  Then user logsout from the site
  And User logs in again with the updated email
  Then if the change email is successful user is logged in
  
  @MFA @Smoke
  Scenario: (MFA - Manage Profile Test) Change Phone Number  (Manage Profile Frequency  = 0 (Every action)) - in an MFA Enabled Site
  Given User is logged in of an mfa enabled user for the change phone
  When User clicks on the myprofile and the edit for the manageprofile page
  Then click on the change phone button
  And user is asked to enter the verification by the email
  Then if the verification is success user is asked to enter the new phone number and click on the submit
  And a new verification code is sent to the new number and check the email if the phone is updated or not
  Then login and verify the phone
  
  @MFA @Regression
  Scenario: (MFA - Manage Profile Test) Change Phone Number with Negative Scenarios (Manage Profile Frequency  = 0 (Every action)) - in an MFA Enabled Site
  Given User is logged in of an mfa enabled user for the change phone
  When User clicks on the myprofile and the edit for the manageprofile page
  Then click on the change phone button
  And user is asked to enter the vaild and invalid verification by the email
  Then if the verification is success user is asked to enter the new phone number with the wrong data and click on the submit
  And a new verification code is sent to the new number and check the email if the phone is updated or not
  Then login and verify the phone
  

  ###### MFA End######################
  ##### NON-MFA START ###############
  @NonMFA @Smoke
  Scenario: (Manage Profile Test) Manage Profile page in a site that is NOT MFA Enabled 
  Given User is logged in of an nonmfa enabled user
  When User clicks on the myprofile and the edit for the manageprofile page
  Then Confirm that there is no Change Phone Number

  @NonMFA @Smoke
  Scenario: (MFA - Manage Profile Test) Change Name (Manage Profile Frequency  = 0 (Every action)) - in an MFA Enabled Site
  Given User is logged in of an nonmfa enabled user
  When User clicks on the myprofile and the edit for the manageprofile page
  Then click on the change name button for the non mfa
  And User is asked to update the values in the change name and click on the submit
  Then if the change name is valid then the updated name is seen on the userdetail page for non mfa
  
  @NonMFA @Regression
  Scenario: (MFA - Manage Profile Test) Change Name with Negative Scenarios(Manage Profile Frequency  = 0 (Every action)) - in an MFA Enabled Site
  Given User is logged in of an nonmfa enabled user
  When User clicks on the myprofile and the edit for the manageprofile page
  Then click on the change name button for the non mfa
  And User is asked to update the values in the change name with the negative data and click on the submit
  Then if the change name is valid then the updated name is seen on the userdetail page for non mfa
  
  @NonMFA @Smoke
  Scenario: (Manage Profile Test) Change Password in a site that is NOT MFA Enabled
  Given User is logged in of an nonmfa enabled user for change password
  When User clicks on the myprofile and the edit for the manageprofile page
  Then click on the change password button for the nonmfa
  And the user is asked to enter the current password and new password and confirmpassword and click submit for the nonmfa
  Then agin login with the updated password for the nonmfa user
  And if the change password is successful then the user is logged in successful for non mfa
  
  @NonMFA @Regression
  Scenario: (Manage Profile Test) Change Password with Negative Scenarios in a site that is NOT MFA Enabled
  Given User is logged in of an nonmfa enabled user for change password
  When User clicks on the myprofile and the edit for the manageprofile page
  Then click on the change password button for the nonmfa
  And the user is asked to enter the current password and new password and confirmpassword with the negative scenarios and click submit for the nonmfa
  Then agin login with the updated password for the nonmfa user
  And if the change password is successful then the user is logged in successful for non mfa
  
  @NonMFA @Smoke1
  Scenario: (Manage Profile Test) Change Email in a site that is NOT MFA Enabled 
  Given User is logged in of an nonmfa enabled user for change email
  When User clicks on the myprofile and the edit for the manageprofile page
  Then click on the change email button
  And user is asked to enter the email to be updated and click on the submit button
  Then User goes to the new emai and verifies it for the nonmfa
  And user logs out from the site 
  Then user logs in again with the updated email
  And if the change email is successful user is logged in for the nonmfa
  
  @NonMFA @Regression
  Scenario: (Manage Profile Test) Change Email with Negative Scenarios in a site that is NOT MFA Enabled 
  Given User is logged in of an nonmfa enabled user for change email
  When User clicks on the myprofile and the edit for the manageprofile page
  Then click on the change email button
  And user is asked to enter the invalid and valid email to be updated and click on the submit button
  Then User goes to the new emai and verifies it for the nonmfa
  And user logs out from the site 
  Then user logs in again with the updated email
  And if the change email is successful user is logged in for the nonmfa
  
  @NonMFA @Smoke
  Scenario: (Manage Profile Test) Change Security Questions in a site that is NOT MFA Enabled 
  Given User is logged in of an nonmfa enabled user for change security questions
  When User clicks on the myprofile and the edit for the manageprofile page
  Then click on the chnage security questions button
  And select the question and answer it and then click on the submit button
  Then check the email if the security questions are updated or not
  
  @NonMFA @Regression
  Scenario: (Manage Profile Test) Change Security Questions with Negative Scenario in a site that is NOT MFA Enabled 
  Given User is logged in of an nonmfa enabled user for change security questions
  When User clicks on the myprofile and the edit for the manageprofile page
  Then click on the chnage security questions button
  And select the question and answer it wrongly and then click on the submit button
  Then check the email if the security questions are updated or not

  ##### NON-MFA END ############### 