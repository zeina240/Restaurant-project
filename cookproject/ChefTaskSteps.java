package cookproject;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class ChefTaskSteps {

    private ChefTaskManager manager = new ChefTaskManager();
    private String notification;
    private String taskName;

    @Given("the kitchen manager assigns a new cooking task to Chef Alex")
    public void the_kitchen_manager_assigns_a_new_cooking_task_to_chef_alex() {
        notification = manager.assignTaskToChef("Grilled Chicken", "20min", "1:00PM");
        taskName = "Grilled Chicken";
    }

    @When("the task is successfully assigned")
    public void the_task_is_successfully_assigned() {
        assertNotNull("Notification should have been generated", notification);
    }

    @Then("Chef Alex should receive a notification with task details")
    public void chef_alex_should_receive_a_notification_with_task_details() {
        assertNotNull("Chef should receive a notification", notification);
    }

    @Then("the notification should include the dish name, preparation time, and deadline")
    public void the_notification_should_include_the_dish_name_preparation_time_and_deadline() {
        assertTrue("Notification missing dish name", notification.contains("Grilled Chicken"));
        assertTrue("Notification missing preparation time", notification.contains("Prep: 20min"));
        assertTrue("Notification missing deadline", notification.contains("Deadline: 1:00PM"));
        assertTrue("Notification format invalid", manager.validateNotification(notification));
    }

    @Given("Chef Alex has completed the assigned task {string}")
    public void chef_alex_has_completed_the_assigned_task(String task) {
        taskName = task;
        assertEquals("Unexpected task name", "Grilled Chicken", taskName);
    }

    @When("Chef Alex marks the task as completed in the system")
    public void chef_alex_marks_the_task_as_completed_in_the_system() {
        manager.markTaskAsCompleted(taskName);
    }

    @Then("the system should update the task status to {string}")
    public void the_system_should_update_the_task_status_to(String expectedStatus) {
        assertEquals("Task status mismatch", expectedStatus, manager.getTaskStatus());
    }

    @Then("notify the kitchen manager about the task completion")
    public void notify_the_kitchen_manager_about_the_task_completion() {
        assertTrue("Kitchen manager should be notified", manager.isManagerNotified());
    }
}