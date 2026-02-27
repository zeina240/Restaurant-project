Feature: Role-Based Redirection After Login
  As a registered user
  I want to be redirected to my personalized dashboard based on my role
  So that I can access the correct area of the system based on my responsibilities

  Scenario: Role-Based Redirection After Login
    Given I am a registered user
    When I log in with my correct credentials
    Then I should be redirected based on my role:
      | User Role            | Redirected To             |  
      | CUSTOMER           | /customer/dashboard        |  
      | CHEF               | /chef/dashboard            |  
      | ADMIN              | /admin/control-panel       |  
      | WORKER             | /worker/dashboard          |  
      | DELIVERY_PERSONNEL | /delivery/dashboard        |  
      | SUPPLIER           | /supplier/management-panel |  
      | KITCHEN_MANAGER    | /kitchen/management-panel  |  

