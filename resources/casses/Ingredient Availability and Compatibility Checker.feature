Feature: Ingredient Availability and Compatibility Checker
  As a customer,
  I want to know if an ingredient I select is available,
  and if it's compatible with the other ingredients I choose,
  So that I can customize my meal based on what is available and feasible.

  Scenario:
    Given the menu has a list of ingredients with availability and compatibility data
    And the customer selects "Chicken" and "Rice" as ingredients for their meal
    When the customer attempts to add the selected ingredients to their order
    Then the system should confirm that both "Chicken" and "Rice" are available and compatible
    And the customer should be notified that their selection is valid

  Scenario: Customer selects an unavailable ingredient
    Given the menu has a list of ingredients with availability and compatibility data
    And the customer selects "Chicken" and "Unicorn Meat" as ingredients for their meal
    When the customer attempts to add the selected ingredients to their order
    Then the system should check the availability of "Unicorn Meat"
    And the system should notify the customer that "Unicorn Meat" is unavailable

  Scenario: Customer selects incompatible ingredients
    Given the menu has a list of ingredients with availability and compatibility data
    And the customer selects "Chicken" and "Chocolate" as ingredients for their meal
    When the customer attempts to add the selected ingredients to their order
    Then the system should check the compatibility of "Chicken" and "Chocolate"
    And the system should notify the customer that "Chicken" and "Chocolate" are incompatible and cannot be combined