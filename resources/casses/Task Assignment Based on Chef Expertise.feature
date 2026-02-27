Feature: Task Assignment Based on Chef Expertise
  As a kitchen manager,
  I want to assign tasks to chefs based on their workload and expertise,
  So that I can ensure balanced workloads and efficiency in the kitchen.

  Scenario: Assign task to chef based on expertise
    Given the kitchen manager wants to assign a "Grilling" task
    And the system shows a list of chefs with their expertise and current workload
    When the kitchen manager selects a chef with "Grilling" expertise and low workload
    Then the system should assign the task to the selected chef
    And the chef should receive a notification about the new assignment

  Scenario: Display summary of assigned and pending tasks
    Given the kitchen manager wants to view the task distribution
    When the manager opens the task assignment dashboard
    Then the system should display a summary of assigned tasks per chef
    And show pending tasks that still need assignment

  Scenario: No available chef with required expertise
    Given the kitchen manager wants to assign a "Sushi" task
    And the system shows a list of chefs with their expertise and current workload
    When the kitchen manager selects a chef with "Sushi" expertise and low workload
    Then the system should not assign the task to the selected chef
    And the chef should not receive a notification