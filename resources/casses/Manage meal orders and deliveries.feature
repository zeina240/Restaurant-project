Feature: Manage meal orders and deliveries
  As a customer, I want to receive reminders for my upcoming meal deliveries so that I can be prepared to receive them.
  Additionally, the system should ensure smooth coordination between the online order receiver, chef, and delivery person.

  Scenario: Customer places an online order with address details
    Given the customer is on the online ordering platform
    When the customer submits an order with the following details:
      | Field         | Value            |
      | Customer Name | John Doe         |
      | Meal Type     | Grilled Chicken  |
      | Delivery Time | 2023-10-15 18:00 |
      | Address       |  123 Main Street |
    Then the system should send a confirmation message to the customer
    And the system should notify the order receiver about the new order

  Scenario: Chef prepares the meal and notifies the order receiver
    Given the chef has received the order details from the order receiver
    When the chef finishes preparing the meal
    Then the chef should notify the order receiver that the meal is ready
    And the order receiver should assign the delivery task to the delivery person with the following details:
      | Field         | Value           |
      | Customer Name | John Doe        |
      | Address       | 123 Main Street |
      | Meal Price    | $15.00          |

  Scenario: Customer receives a reminder for the upcoming delivery
    Given the customer has an upcoming delivery scheduled for "2023-10-15 18:00"
    When the system sends a reminder notification 1 hour before the delivery time
    Then the customer should receive the reminder notification

  Scenario: Reminder should not be sent when it is too early
    Given the customer has an upcoming delivery scheduled for "2099-12-31 18:00"
    When the system sends a reminder notification 1 hour before the delivery time
    Then the customer should not receive the reminder yet