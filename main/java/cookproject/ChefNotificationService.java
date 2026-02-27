
  package cookproject;
 
 import java.util.List;
 
  import javax.management.Notification;
  
  public class ChefNotificationService {
  public void sendSubstitutionAlert(Chef chef, String message) {

  System.out.println("Sending alert to chef: " + message);
  }
  
  public void updateOrderWithChefAdjustment(Order order, String newMessage) {
  
  throw new
  UnsupportedOperationException("Unimplemented method 'updateOrderWithChefAdjustment'"
  );
  }
  
  public static List<Notification> getChefNotificationService(String input) {

  throw new
  UnsupportedOperationException("Unimplemented method 'getChefNotificationService'"
  );
  }
  
  public static List<Notification> getAllTaskNotifications() {
 
 throw new
  UnsupportedOperationException("Unimplemented method 'getAllTaskNotifications'"
 );
  }
 
  public static List<Notification> getCustomerReminders(String input) {
 
 throw new
  UnsupportedOperationException("Unimplemented method 'getCustomerReminders'");
  }
  
  public static List<Notification> getUpcomingOrderReminders() {

  throw new
 UnsupportedOperationException("Unimplemented method 'getUpcomingOrderReminders'"
  );
 }
  }
 