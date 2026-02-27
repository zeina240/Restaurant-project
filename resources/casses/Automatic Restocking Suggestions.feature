Feature: Automatic Restocking Suggestions

  As a system,
  I want to automatically suggest restocking when ingredients are low,
  So that kitchen managers can take action promptly.

  Scenario: Suggest restocking when stock level is low
    Given the stock level of "Olive Oil" drops below 10 units
    When the system detects the low level
    Then the system should suggest a restock quantity based on average usage
    And display a restock suggestion message to the kitchen manager

  Scenario: Generate a restock order
    Given the manager approves the restock suggestion
    When the manager confirms the order
    Then the system should generate a restock order form
    And automatically send it to the preferred supplier

  Scenario: Update stock levels after restocking
    Given a restock order of "Flour" has been delivered
    When the manager updates the inventory with the received quantity
    Then the system should update the stock level accordingly
    And mark the restock order as "Completed"
