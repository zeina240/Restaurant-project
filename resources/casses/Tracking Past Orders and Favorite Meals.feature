Feature: Tracking Past Orders and Favorite Meals

Scenario: Successfully viewing past orders and reordering meals
  Given the customer is logged in to the system2
  When the customer navigates to the "Past Orders" page1
  Then the system displays a list of the customer's past orders
  And the customer can see details of each order, including meal items, date, and total cost
  When the customer selects a past order they liked
  Then the system allows the customer to click "Reorder" and place the same order again
  And the system confirms the order is successfully added to the cart
Scenario: Chef viewing customer order history for personalized meal recommendations
  Given the chef is logged in to the system
  When the chef navigates to the "Customer Profiles" page1
  And the chef selects a customer profile with ID "1234"
  Then the system displays the customer's past orders
  And the chef can review the customer's preferences based on previous meals
  And the chef can suggest personalized meal plans based on past orders
Scenario: Storing and retrieving customer order history for trend analysis (Admin)
  Given the system administrator is logged in to the system
  When the administrator navigates to the "Order History" page
  Then the system displays a list of all customer orders with relevant details
  And the administrator can use this data to analyze trends and improve service offerings