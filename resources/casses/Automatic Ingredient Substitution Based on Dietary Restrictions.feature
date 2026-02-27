Feature: Automatic Ingredient Substitution Based on Dietary Restrictions

As a customer,
I want the system to automatically suggest alternative ingredients if an ingredient is unavailable or restricted,
So that I can still enjoy my meal without compromising my dietary needs.

Scenario: System suggests alternative ingredient due to dietary restriction
  Given the customer has dietary restrictions saved in their profile,
  And the customer selects a meal that contains a restricted ingredient,
  When the system suggests a substitution,
  Then the system should suggest an alternative ingredient,
  And the system should display substitution message "Ingredient X has been substituted with Ingredient Y for this order."

Scenario: System suggests alternative ingredient due to unavailability
  Given the menu has a list of ingredients with availability status,
  And the customer selects a meal that contains an unavailable ingredient,
  When the system suggests a substitution,
  Then the system should suggest an alternative ingredient,
  And the system should display substitution message "Ingredient X has been substituted with Ingredient Y for this order."
