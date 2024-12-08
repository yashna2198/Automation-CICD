
@tag
Feature: Purchase Order from ecommerce website

	Background: 
		Given Landing on the ecommerce website
		
	 @Regression
	  Scenario Outline: Positive test of submitting the order
	    Given login to the ecommerce website using username <name> password <password>
	    When select the product <productname>
	    And go to the cart
	    And verify the selected <productname> 
	    And check the order providing the <countryname>
	    Then Confirm the message "THANKYOU FOR THE ORDER."
	
	    Examples: 
	      | name  							| password 		| productname  | countryname   |
	      | arjun2103@gmail.com | Arjun@2103	| ZARA COAT 3	 | India         |
	      | karan2103@gmail.com | Karan@2103  | IPHONE 13 PRO| India         |
