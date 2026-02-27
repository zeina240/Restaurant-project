Feature: Update Customer Dietary Preferences
 Scenario: Successfully updating dietary preferences and allergies
  Given the customer is logged in to the system3
  When the customer navigates to the "Dietary Preferences" page2
  And the customer updates the following dietary preferences:
    | Dietary Restriction | Vegetarian  |
    | Allergy             | Gluten, Soy |
  And the customer clicks "Save"
  Then the system validates update input
  And the system updates the dietary preferences and allergies
  And the system displays1 "Preferences updated successfully."
Scenario: Handling invalid input while updating dietary preferences
  Given the customer is logged in to the system3
  When the customer navigates to the "Dietary Preferences" page2
  And the customer enters invalid values for dietary restrictions or allergies (e.g., special characters or empty fields)
  And the customer clicks "Save"
  Then the system displays an error message "Invalid input. Please check your entries."