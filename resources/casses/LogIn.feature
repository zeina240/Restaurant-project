Feature: User Login  
  As a registered user (Customer, Chef, Admin, Worker, Delivery Personnel, Supplier, or Kitchen Manager)  
  I want to log into my account  
  So that I can access my personalized dashboard based on my role  

  Scenario: Successful Login for Any User  
    Given I am on the login page  
    When I enter my valid email "user@example.com"  
    And I enter my correct password "SecurePass123!"  
    And I click the "Login" button  
    Then I should be redirected to my respective dashboard  
    And I should see a welcome message "Welcome, [User Role]!"  

  Scenario: Login with Incorrect Password  
    Given I am on the login page  
    When I enter my valid email "user@example.com"  
    And I enter an incorrect password "WrongPass456"  
    And I click the "Login" button  
    Then I should see an error message "Invalid email or password. Please try again."  

  Scenario: Login with Unregistered Email  
    Given I am on the login page  
    When I enter an unregistered email "unknown@example.com"  
    And I enter any password "Test1234!"  
    And I click the "Login" button  
    Then I should see an error message "User not found. Please sign up."  

  