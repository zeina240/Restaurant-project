Feature: Chef Task Notifications  

  As a chef,  
  I want to get notified of scheduled cooking tasks,  
  So that I can prepare meals on time.  

  Scenario: Notify a chef of a newly assigned cooking task  
    Given a customer places an order online  
    And the online worker receives the order request  
    When the online worker assigns the cooking task to a chef  
    Then the system should send a notification to the assigned chef  
    And the notification should include the dish name, deadline  

  Scenario: Reminder notification for an upcoming task  
    Given a chef has a scheduled cooking task  
    And the task start time is approaching (e.g., 10 minutes before)  
    When the system detects the upcoming task  
    Then the system should send a reminder notification to the chef  
    And display the task details  

  