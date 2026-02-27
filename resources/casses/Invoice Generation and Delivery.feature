Feature: Invoice Generation and Delivery

  As a customer,
  I want to receive an invoice after completing my order,
  So that I can have a record of my purchase and payment details.

  Scenario: Generate an invoice for a customer
  Given the customer has completed a purchase at the restaurant
  When the payment is processed successfully
  Then the system should automatically generate an invoice
  And the invoice should include the order details, total amount, taxes, and payment method
  And the customer should receive the invoice via email or printed copy