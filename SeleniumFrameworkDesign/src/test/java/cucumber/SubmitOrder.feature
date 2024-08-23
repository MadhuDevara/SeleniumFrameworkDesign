
@tag
Feature: Title of your feature
  I want to use this template for my feature file
	
	Background:
	Given I landed on Ecommerce Page

  @Regression 
  Scenario Outline: Positive Test Of Submitting the order
    Given Logged in with username <name> and password <password>	
    When I add the product <producName> to cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on ConfirmationPage

    Examples: 
      | name  										| password 		| productName |
      |	madhudevara.to@gmail.com	| Devara@85 	| ZARA COAT 3 |     
   
