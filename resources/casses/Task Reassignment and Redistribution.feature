Feature: Task Reassignment and Redistribution

As a kitchen manager,
I want to reassign tasks if a chef becomes unavailable or overloaded,
So that I can maintain efficient workflow and ensure task completion.

Scenario: Reassign a task when the chef becomes unavailable
Given a task "Grilling Steak" is assigned to Chef John
And Chef John becomes unavailable due to an emergency
When the kitchen manager decides to reassign the task
Then the system should display a list of available chefs with "Grilling" expertise
And suggest the chef with the lowest current workload
When the kitchen manager selects Chef Mike from the list
Then the task should be reassigned to Chef Mike
And Chef Mike should receive a notification about the reassigned task

Scenario: Add a chef when orders exceed capacity
Given the restaurant is at full capacity with a high volume of orders
And the system shows that the current chefs are overloaded
When the kitchen manager decides to add an extra chef
Then the system should suggest available chefs who are not yet assigned
And the kitchen manager can select and assign the new chef to a task
And the newly added chef should receive a notification about the new assignment
And the system should update the workload distribution to include the new chef

Scenario: Notify the kitchen manager when reassignment is successful
Given the kitchen manager has reassigned a task from Chef Mark to Chef Anna
When the reassignment is successfully completed
Then the system should display a confirmation message to the kitchen manager
And update the task list to reflect the new assignment