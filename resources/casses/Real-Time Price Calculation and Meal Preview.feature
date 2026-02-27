Feature: Real-Time Price Calculation and Meal Preview
  As a customer,
  I want to see the price of my meal as I add ingredients
  and a preview of how the final dish will look,
  So that I can make informed decisions about my meal and budget.

  Scenario: Customer views real-time price and meal preview as ingredients are added
    Given the menu contains a list of ingredients with prices and images
    And the customer selects "Chicken" and "Rice" as ingredients for their meal
    When the customer adds "Chicken" and "Rice" to their order
    Then the system should calculate the total price of the meal in real-time
    And the system should display a visual preview of the meal with "Chicken" and "Rice"

  Scenario: Customer views a warning if the meal price exceeds budget
    Given the menu contains a list of ingredients with prices
    And the customer has a budget of $20 for their meal
    When the customer adds ingredients to their order and the total price exceeds $20
    Then the system should display a warning that the meal price exceeds the budget
    And the customer should be notified about the exceeding cost