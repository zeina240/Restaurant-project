package cookproject;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ManageMealOrdersSteps {

    private String scheduledDeliveryTime;
    private String customerName;
    private boolean reminderSent = false;

    @Given("the customer is on the online ordering platform")
    public void the_customer_is_on_the_online_ordering_platform() {
        System.out.println("Customer is on the ordering platform.");
    }

    @When("the customer submits an order with the following details:")
    public void the_customer_submits_an_order_with_the_following_details(DataTable dataTable) {
        Map<String, String> orderDetails = dataTable.asMap(String.class, String.class);
        customerName = orderDetails.get("Customer Name");
        scheduledDeliveryTime = orderDetails.get("Delivery Time");
        System.out.println("Customer submitted order: " + orderDetails);
    }

    @Then("the system should send a confirmation message to the customer")
    public void the_system_should_send_a_confirmation_message_to_the_customer() {
        System.out.println("Confirmation message sent to customer.");
    }

    @Then("the system should notify the order receiver about the new order")
    public void the_system_should_notify_the_order_receiver_about_the_new_order() {
        System.out.println("Order receiver notified about new order.");
    }

    @Given("the chef has received the order details from the order receiver")
    public void the_chef_has_received_the_order_details_from_the_order_receiver() {
        System.out.println("Chef has received order details.");
    }

    @When("the chef finishes preparing the meal")
    public void the_chef_finishes_preparing_the_meal() {
        System.out.println("Chef finished preparing meal.");
    }

    @Then("the chef should notify the order receiver that the meal is ready")
    public void the_chef_should_notify_the_order_receiver_that_the_meal_is_ready() {
        System.out.println("Chef notified order receiver that meal is ready.");
    }

    @Then("the order receiver should assign the delivery task to the delivery person with the following details:")
    public void the_order_receiver_should_assign_the_delivery_task_to_the_delivery_person_with_the_following_details(
            DataTable dataTable) {
        Map<String, String> deliveryDetails = dataTable.asMap(String.class, String.class);
        System.out.println("Delivery task assigned with details: " + deliveryDetails);
    }

    @Given("the customer has an upcoming delivery scheduled for {string}")
    public void the_customer_has_an_upcoming_delivery_scheduled_for(String deliveryTime) {
        this.scheduledDeliveryTime = deliveryTime;
        System.out.println("Customer has delivery scheduled for: " + deliveryTime);
    }

    @When("the system sends a reminder notification {int} hour before the delivery time")
    public void the_system_sends_a_reminder_notification_hour_before_the_delivery_time(Integer hoursBefore) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime deliveryTime = LocalDateTime.parse(scheduledDeliveryTime, formatter);
        LocalDateTime fakeNow = deliveryTime.minusHours(hoursBefore); 

        if (ReminderService.shouldSendReminder(scheduledDeliveryTime, hoursBefore, fakeNow)) {
            ReminderService.sendReminder(customerName != null ? customerName : "Customer");
            reminderSent = true;
        } else {
            System.out.println("Reminder not sent: Not yet the correct time.");
            reminderSent = false;
        }
    }

    @Then("the customer should receive the reminder notification")
    public void the_customer_should_receive_the_reminder_notification() {
        assert reminderSent : "Expected reminder to be sent, but it wasn't.";
        System.out.println("Customer received reminder notification.");
    }

    @Then("the customer should not receive the reminder yet")
    public void the_customer_should_not_receive_the_reminder_yet() {
        System.out.println("No reminder sent. It's not yet time.");
    }
}