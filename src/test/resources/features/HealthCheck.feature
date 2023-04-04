@ui @healthCheck

Feature: E-commerce project web site health check

@Search
Scenario: User is able to open the application and able to performe search for table
#Given User open the browser
Given User navigated to the landing page of the application
When User search for product "table"
Then Search result is displayed "table"
#And browser is closed

@Searche
Scenario: User is able to open the application and able to performe search for mobile
#Given User open the browser
Given User navigated to the landing page of the application
When User search for product "mobile"
Then Search result is displayed "mobile"
#And browser is closed

@ProductDetails
Scenario: User is click on the Product and check the Product Details
#Given User open the browser
Given User navigated to the landing page of the application
And User search for product "laptop"
When User click on any product
Then Product Description is displayed in new tab
#And browser is closed