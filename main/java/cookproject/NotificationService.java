package cookproject;

public class NotificationService {

    public String sendTaskNotification(Task task) {
        return "Dish: " + task.getOrder().getDishName() + ", Deadline: " + task.getOrder().getDeadline();
    }

    public String sendReminder(Task task) {
        return "Reminder - Dish: " + task.getOrder().getDishName() + ", Starts at: " + task.getStartTime();
    }

    public void sendConfirmation(String message) {
        System.out.println("Confirmation: " + message);
    }
}
