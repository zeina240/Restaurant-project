package cookproject;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChefNotificationServiceTest {

    @Test
    public void testSendSubstitutionAlert() {
        ChefNotificationService service = new ChefNotificationService();

        Chef dummyChef = new Chef("Ali", "Desserts", 2, true);
        service.sendSubstitutionAlert(dummyChef, "Please substitute Chef for next order.");
        // لا يُتوقع استثناء — تم بنجاح
    }

    @Test
    public void testUpdateOrderWithChefAdjustment_throwsException() {
        ChefNotificationService service = new ChefNotificationService();

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Chocolate", 2));

        List<Meal> dummyMeals = new ArrayList<>();
        dummyMeals.add(new Meal("Chocolate Cake", ingredients, 20));

        Order dummyOrder = new Order("O001", "C123", dummyMeals, "2025-05-22", 20);

        assertThrows(UnsupportedOperationException.class, () -> {
            service.updateOrderWithChefAdjustment(dummyOrder, "Chef adjusted the order.");
        });
    }

    @Test
    public void testGetChefNotificationService_throwsException() {
        assertThrows(UnsupportedOperationException.class, () -> {
            ChefNotificationService.getChefNotificationService("Some input");
        });
    }

    @Test
    public void testGetAllTaskNotifications_throwsException() {
        assertThrows(UnsupportedOperationException.class, () -> {
            ChefNotificationService.getAllTaskNotifications();
        });
    }

    @Test
    public void testGetCustomerReminders_throwsException() {
        assertThrows(UnsupportedOperationException.class, () -> {
            ChefNotificationService.getCustomerReminders("Remind me");
        });
    }

    @Test
    public void testGetUpcomingOrderReminders_throwsException() {
        assertThrows(UnsupportedOperationException.class, () -> {
            ChefNotificationService.getUpcomingOrderReminders();
        });
    }
}
