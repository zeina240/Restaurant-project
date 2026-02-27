package cookproject;
import cookproject.Chef;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChefTest {

    @Test
    public void testConstructorWithAvailability() {
        Chef chef = new Chef("Ali", "Grill", 3, true);
        assertEquals("Ali", chef.getName());
        assertEquals("Grill", chef.getExpertise());
        assertEquals(3, chef.getCurrentWorkload());
        assertTrue(chef.isAvailable());
    }

    @Test
    public void testConstructorWithoutAvailability() {
        Chef chef = new Chef("Sara", "Pastry", 2);
        assertEquals("Sara", chef.getName());
        assertEquals("Pastry", chef.getExpertise());
        assertEquals(2, chef.getCurrentWorkload());
        // اختبر إذا الإتاحة false مبدئيًا أو حسب ما تريده
        // قد تحتاج لضبط default value في الكلاس الأصلي إذا كانت isAvailable غير مُهيئة
    }

    @Test
    public void testSetAvailable() {
        Chef chef = new Chef("Omar", "Soup", 1, false);
        chef.setAvailable(true);
        assertTrue(chef.isAvailable());
    }

    @Test
    public void testAssignTask() {
        Chef chef = new Chef("Nada", "Salad", 4, true);
        chef.assignTask();
        assertEquals(5, chef.getCurrentWorkload());
    }

    @Test
    public void testHasLowWorkloadTrue() {
        Chef chef = new Chef("Lina", "Dessert", 3, true);
        assertTrue(chef.hasLowWorkload());
    }

    @Test
    public void testHasLowWorkloadFalse() {
        Chef chef = new Chef("Khaled", "Steak", 5, true);
        assertFalse(chef.hasLowWorkload());
    }

    @Test
    public void testReceiveNotification() {
        Chef chef = new Chef("Amal", "Fish", 2, true);
        chef.receiveNotification("New order assigned");
        // لا يمكن التحقق من System.out بسهولة هنا إلا إذا استخدمت mock أو OutputStream
    }

    @Test
    public void testReceiveReminder() {
        Chef chef = new Chef("Tariq", "Sauce", 1, true);
        chef.receiveReminder("Start your shift");
        // مثل التعليق السابق، يمكنك تجاهل التحقق أو استخدام أدوات خاصة لاختبار الإخراج
    }
}
