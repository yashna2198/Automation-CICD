@tag
Feature: Error validation


  @errorvalidation
  Scenario Outline: Title of your scenario outline
  	Given Landing on the ecommerce website
    When login to the ecommerce website using username <name> password <password>
    Then "Incorrect email or password." message displays

    Examples: 
      	| name  							| password 	   |
	      | arjun2103@gmail.com | Arjun98@2103 |
