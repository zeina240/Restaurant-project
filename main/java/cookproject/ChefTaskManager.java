package cookproject;

public class ChefTaskManager {

    private String notification;
    private String taskStatus;
    private boolean isManagerNotified;

    public String assignTaskToChef(String dishName, String prepTime, String deadline) {
        notification = dishName + " - Prep: " + prepTime + " - Deadline: " + deadline;
        return notification;
    }

    public boolean validateNotification(String notification) {
        return notification.contains("Grilled Chicken")
                && notification.contains("Prep: 20min")
                && notification.contains("Deadline: 1:00PM");
    }

    public void markTaskAsCompleted(String taskName) {
        if ("Grilled Chicken".equals(taskName)) {
            taskStatus = "Completed";
            isManagerNotified = true;
        }
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public boolean isManagerNotified() {
        return isManagerNotified;
    }
}