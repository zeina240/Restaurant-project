Feature: Manage Customer Dietary Preferences

  Scenario: Successfully storing dietary preferences and allergies
    Given the customer is logged in to the system1
    When the customer navigates to the "Dietary Preferences" page
    And the customer enters the following dietary preferences:
      | Dietary Restriction | Vegan       |
      | Allergy             | Nuts, Dairy |
    And the customer clicks "Save"
    Then the system validates the input
    And the system saves the dietary preferences and allergies
    And the system displays "Preferences saved successfully."

  Scenario: Viewing saved dietary preferences by chef
    Given the customer with ID "1234" has dietary preferences:
      | Dietary Restriction | Vegan       |
      | Allergy             | Nuts, Dairy |
    And the chef is logged in to the system1
    When the chef navigates to the "Customer Profiles" page
    And the chef selects the customer profile with ID "1234"
    Then the system displays the dietary preferences:
      | Dietary Restriction | Vegan       |
      | Allergy             | Nuts, Dairy |

  Scenario: Handling invalid input for dietary preferences
    Given the customer is logged in to the system1
    And the customer navigates to the "Dietary Preferences" page
    When the customer entered invalid values for dietary restrictions or allergies
    And the customer clicks "Save"
    Then the system validates the input
    And the system displays "Invalid input. Please check your entries."

  Scenario: Comparing two identical customer profiles
    Given two customer profiles with the same data
    Then the system confirms they are equal

  Scenario: Creating a customer with no user
    Given a customer is created without a user
    Then the customer has no user

  Scenario: Testing edge cases in equality
    Given two customer profiles with the same data
    Then the system tests equality edge cases

  Scenario: Comparing customer profiles with null fields
    Given two customer profiles with null data
    Then the system confirms they are equal