package cookproject;

import java.time.LocalDateTime;

public class Task {
    private String taskType;
    private Chef assignedChef;
    private LocalDateTime startTime;  // تم التغيير هنا من String إلى LocalDateTime
    private String notificationMessage;
    private Order order;

    public Task(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskType() {
        return taskType;
    }

    public Chef getAssignedChef() {
        return assignedChef;
    }

    public void assignChef(Chef chef) {
        this.assignedChef = chef;
    }

    public void reassignTask(Chef newChef) {
        this.assignedChef = newChef;
    }

public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
}


    public LocalDateTime getStartTime() {  // تعديل الميثود
        return startTime;
    }

    public void setNotificationMessage(String message) {
        this.notificationMessage = message;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
