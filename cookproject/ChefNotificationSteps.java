package cookproject;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class ChefNotificationSteps {

    private Order order;
    private Chef chef;
    private Task task;
    private NotificationService notificationService = new NotificationService();

    private String taskNotification;
    private String reminderNotification;

    @Given("a customer places an order online")
    public void a_customer_places_an_order_online() {
        order = new Order("ORD001", "CUST123", 
            Arrays.asList(new Meal("Spaghetti Bolognese")),  // ✅ إصلاح النوع
            "2025-05-10", 25.0);
        order.setDishName("Spaghetti Bolognese");
        order.setDeadline(parseTime("2025-05-10 14:00")); // 2:00PM
    }

    @Given("the online worker receives the order request")
    public void the_online_worker_receives_the_order_request() {
        assertNotNull("Order should be received", order);
    }

    @When("the online worker assigns the cooking task to a chef")
    public void the_online_worker_assigns_the_cooking_task_to_a_chef() {
        chef = new Chef("Chef Mario", "Italian", 2, true);

        task = new Task("Cooking");
        task.setOrder(order);
        task.setStartTime(parseTime("2025-05-10 13:30")); // 1:30PM
        task.assignChef(chef);

        taskNotification = notificationService.sendTaskNotification(task);
        chef.receiveNotification(taskNotification);
    }

    @Then("the system should send a notification to the assigned chef")
    public void the_system_should_send_a_notification_to_the_assigned_chef() {
        assertNotNull("Task must be assigned", task.getAssignedChef());
        assertNotNull("Notification must be sent", taskNotification);
    }

    @Then("the notification should include the dish name, deadline")
    public void the_notification_should_include_the_dish_name_deadline() {
        assertTrue(taskNotification.contains("Spaghetti Bolognese"));
        assertTrue(taskNotification.contains("Deadline: 2:00PM"));
    }

    @Given("a chef has a scheduled cooking task")
    public void a_chef_has_a_scheduled_cooking_task() {
        order = new Order("ORD002", "CUST789", 
            Arrays.asList(new Meal("Tacos")),  // ✅ إصلاح النوع
            "2025-05-10", 18.0);
        order.setDishName("Tacos");
        order.setDeadline(parseTime("2025-05-10 15:30")); // 3:30PM

        chef = new Chef("Chef Juan", "Mexican", 1, true);

        task = new Task("Cooking");
        task.setOrder(order);
        task.setStartTime(parseTime("2025-05-10 15:00")); // 3:00PM
        task.assignChef(chef);
    }

    @Given("the task start time is approaching \\(e.g., {int} minutes before)")
    public void the_task_start_time_is_approaching_e_g_minutes_before(Integer minutesBefore) {
        if (minutesBefore == 10) {
            reminderNotification = notificationService.sendReminder(task);
            chef.receiveReminder(reminderNotification);
        }
    }

    @When("the system detects the upcoming task")
    public void the_system_detects_the_upcoming_task() {
        assertNotNull("Reminder should be generated", reminderNotification);
    }

    @Then("the system should send a reminder notification to the chef")
    public void the_system_should_send_a_reminder_notification_to_the_chef() {
        assertNotNull(reminderNotification);
    }

    @Then("display the task details")
    public void display_the_task_details() {
        System.out.println("Reminder Task Details: " + reminderNotification);
        assertTrue(reminderNotification.contains("Tacos"));
    }

    private LocalDateTime parseTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateTimeStr, formatter);
    }
}
