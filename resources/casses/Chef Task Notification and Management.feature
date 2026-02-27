Feature: Chef Task Notification and Management
  As a chef,
  I want to receive notifications about my assigned cooking tasks,
  So that I can prepare meals on time and stay organized.

  Scenario: Receive notification when a new task is assigned
    Given the kitchen manager assigns a new cooking task to Chef Alex
    When the task is successfully assigned
    Then Chef Alex should receive a notification with task details
    And the notification should include the dish name, preparation time, and deadline

  Scenario: Mark task as completed
    Given Chef Alex has completed the assigned task "Grilled Chicken"
    When Chef Alex marks the task as completed in the system
    Then the system should update the task status to "Completed"
    And notify the kitchen manager about the task completion