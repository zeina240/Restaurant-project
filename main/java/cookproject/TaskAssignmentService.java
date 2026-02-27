package cookproject;

import java.util.List;
import java.util.Optional;

public class TaskAssignmentService {
    private boolean notificationSent;

    public Optional<Chef> findAvailableChef(List<Chef> chefs, String requiredExpertise) {
        return chefs.stream()
                .filter(c -> c.getExpertise().equalsIgnoreCase(requiredExpertise) && c.hasLowWorkload())
                .findFirst();
    }

    public boolean assignTaskToChef(Chef chef) {
        if (chef != null) {
            chef.assignTask();
            sendNotification(chef);
            return true;
        }
        return false;
    }

    private void sendNotification(Chef chef) {
        // simulate sending a notification
        System.out.println("Notification sent to: " + chef.getName());
        notificationSent = true;
    }

    public boolean isNotificationSent() {
        return notificationSent;
    }
}