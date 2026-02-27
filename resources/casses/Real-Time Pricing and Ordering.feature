Feature: Real-Time Pricing and Ordering

  As a kitchen manager,
  I want the system to fetch real-time ingredient prices from suppliers,
  So that I can make cost-effective purchasing decisions.

  Scenario: View real-time ingredient prices
    Given the kitchen manager logs into the inventory system2
    When the manager navigates to the "Supplier Pricing" page
    Then the system should display real-time prices for all available ingredients
    And show the supplier name, unit price, and last update timestamp

  Scenario: Compare prices from multiple suppliers
    Given the system displays prices from multiple suppliers
    When the manager selects an ingredient, like "Tomatoes"
    Then the system should display a comparison chart of prices from different suppliers
    And highlight the most cost-effective option

  Scenario: Place a purchase order based on real-time prices
    Given the kitchen manager selects the best price for "Olive Oil"
    When the manager places an order through the system
    Then the system should generate a purchase order with supplier details and pricing
    And send a confirmation message to the manager