package cookproject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReminderService {

    public static boolean shouldSendReminder(String deliveryTimeStr, int hoursBefore, LocalDateTime currentTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime deliveryTime = LocalDateTime.parse(deliveryTimeStr, formatter);
        LocalDateTime reminderTime = deliveryTime.minusHours(hoursBefore);

        boolean isTime = currentTime.isAfter(reminderTime.minusMinutes(1)) && currentTime.isBefore(reminderTime.plusMinutes(1));

        if (isTime) {
            System.out.println("It's time to send the reminder.");
        } else {
            System.out.println("It's NOT time to send the reminder.");
        }

        return isTime;
    }

    public static void sendReminder(String customerName) {
        System.out.println("Reminder: Hello " + customerName + ", your meal will be delivered soon!");
    }
}
