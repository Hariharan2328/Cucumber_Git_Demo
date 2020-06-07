Feature: TC001_CreateLead

Scenario: Create a new Lead

When Enter user_name "demosalesmanager" in Login page
And Enter pass_word "crmsfa" in Login page
When Click on Login_button
When Click on CRM-SFA_link
And Click on Leads_link
And Click on Create_Leads link
When Enter company_name "CTS" in CreateLeadPage
And Enter first_name "Hari" in CreateLeadPage
And Enter last_name "Haran" in CreateLeadPage
And click on Create_button
Then Verify View_Leads page