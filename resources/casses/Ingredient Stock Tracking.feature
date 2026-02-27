Feature: Ingredient Stock Tracking
  As a kitchen manager,
  I want to track ingredient stock levels in real time,
  So that I can prevent shortages and ensure continuous operations.

  Scenario: View current ingredient stock levels
    Given the kitchen manager logs into the inventory system1
    When the manager navigates to the "Stock Overview" page
    Then the system should display a list of all ingredients with their current stock levels

  Scenario: Receive low stock alert
    Given the ingredient "Tomatoes" falls below the minimum threshold
    When the system detects the low stock level
    Then the kitchen manager should receive an alert notification