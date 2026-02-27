package cookproject;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChefTaskManagerTest {

    private ChefTaskManager manager;

    @BeforeEach
    public void setup() {
        manager = new ChefTaskManager();
    }

    @Test
    public void testAssignTaskToChef_returnsCorrectNotification() {
        String dishName = "Grilled Chicken";
        String prepTime = "20min";
        String deadline = "1:00PM";

        String notification = manager.assignTaskToChef(dishName, prepTime, deadline);

        assertNotNull(notification);
        assertTrue(notification.contains(dishName));
        assertTrue(notification.contains(prepTime));
        assertTrue(notification.contains(deadline));
    }

    @Test
    public void testValidateNotification_validNotification() {
        String validNotification = "Grilled Chicken - Prep: 20min - Deadline: 1:00PM";

        assertTrue(manager.validateNotification(validNotification));
    }

    @Test
    public void testValidateNotification_invalidNotification_missingGrilledChicken() {
        String invalidNotification = "Roasted Beef - Prep: 20min - Deadline: 1:00PM";

        assertFalse(manager.validateNotification(invalidNotification));
    }

    @Test
    public void testValidateNotification_invalidNotification_wrongPrepTime() {
        String invalidNotification = "Grilled Chicken - Prep: 15min - Deadline: 1:00PM";

        assertFalse(manager.validateNotification(invalidNotification));
    }

    @Test
    public void testValidateNotification_invalidNotification_wrongDeadline() {
        String invalidNotification = "Grilled Chicken - Prep: 20min - Deadline: 2:00PM";

        assertFalse(manager.validateNotification(invalidNotification));
    }

    @Test
    public void testMarkTaskAsCompleted_withGrilledChicken_setsStatusAndNotifies() {
        assertNull(manager.getTaskStatus());
        assertFalse(manager.isManagerNotified());

        manager.markTaskAsCompleted("Grilled Chicken");

        assertEquals("Completed", manager.getTaskStatus());
        assertTrue(manager.isManagerNotified());
    }

    @Test
    public void testMarkTaskAsCompleted_withOtherTask_doesNothing() {
        manager.markTaskAsCompleted("Roasted Beef");

        assertNull(manager.getTaskStatus());
        assertFalse(manager.isManagerNotified());
    }

    @Test
    public void testInitialValues() {
        assertNull(manager.getTaskStatus());
        assertFalse(manager.isManagerNotified());
    }
}
