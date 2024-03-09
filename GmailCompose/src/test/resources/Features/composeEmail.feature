Feature: Compose and send an email in Gmail


  Scenario: Successful email composition and sending
    Given I am logged in to my Gmail account
    When I click on the "Compose" button
    And And I enter the recipient's email address "sushantjha39@gmail.com"
    And I enter the subject and body of the email
    And I click on the "Send" button
    Then I should see a confirmation message that the email was sent "Message sent"
