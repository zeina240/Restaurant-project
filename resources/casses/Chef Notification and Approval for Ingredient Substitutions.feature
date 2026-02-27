Feature: Chef Notification and Approval for Ingredient Substitutions
As a chef,
I want to receive an alert when an ingredient substitution is applied,
So that I can approve or adjust the final recipe accordingly.

Scenario: Chef is notified about an ingredient substitution
Given the system has suggested an ingredient substitution for a meal,
And the customer has confirmed the substitution,
When the order is sent to the kitchen,
Then the chef should receive an alert about the substitution,
And the system should display "Ingredient X has been substituted with Ingredient Y for this order."

Scenario: Chef manually approves or adjusts a suggested substitution
Given the system has suggested an ingredient substitution for a meal,
And the customer has confirmed the substitution,
When the chef reviews the order in the kitchen system,
Then the chef should have an option to approve or adjust the suggested substitution,
And any adjustments should be updated in the customer's order summary.