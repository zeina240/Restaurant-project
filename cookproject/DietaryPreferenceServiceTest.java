package cookproject;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class DietaryPreferenceServiceTest {

    private DietaryPreferenceService service;
    private Customer customer;

    @BeforeEach
    public void setup() {
        service = new DietaryPreferenceService();
        customer = new Customer(); // استخدم الكونستركتور البسيط للاختبارات
    }

    // اختبار validatePreferences مع مدخلات صحيحة
    @Test
    public void testValidatePreferences_ValidInput() {
        Set<String> restrictions = new HashSet<>();
        restrictions.add("Gluten");
        restrictions.add("Lactose");

        Set<String> allergies = new HashSet<>();
        allergies.add("Peanuts");
        allergies.add("Shellfish");

        assertTrue(service.validatePreferences(restrictions, allergies));
    }

    // اختبار validatePreferences مع null
    @Test
    public void testValidatePreferences_NullInput() {
        assertFalse(service.validatePreferences(null, new HashSet<>()));
        assertFalse(service.validatePreferences(new HashSet<>(), null));
        assertFalse(service.validatePreferences(null, null));
    }

    // اختبار validatePreferences مع قيود غير صالحة (مساحات فقط أو أرقام أو رموز)
    @Test
    public void testValidatePreferences_InvalidRestrictions() {
        Set<String> restrictions = new HashSet<>();
        restrictions.add("  ");  // مسافات فقط
        restrictions.add("Gluten1"); // يحتوي رقم

        Set<String> allergies = new HashSet<>();
        allergies.add("Peanuts");

        assertFalse(service.validatePreferences(restrictions, allergies));
    }

    // اختبار validatePreferences مع حساسية غير صالحة (رموز أو أرقام)
    @Test
    public void testValidatePreferences_InvalidAllergies() {
        Set<String> restrictions = new HashSet<>();
        restrictions.add("Gluten");

        Set<String> allergies = new HashSet<>();
        allergies.add("");    // فارغة
        allergies.add("Shellfish!");
        allergies.add("123");

        assertFalse(service.validatePreferences(restrictions, allergies));
    }

    // اختبار updatePreferences
    @Test
    public void testUpdatePreferences() {
        Set<String> restrictions = new HashSet<>();
        restrictions.add("Vegan");
        restrictions.add("Low Salt");

        Set<String> allergies = new HashSet<>();
        allergies.add("Peanuts");
        allergies.add("Dairy");

        // أضف بعض التفضيلات القديمة
        customer.addDietaryRestriction("Gluten");
        customer.addAllergy("Shellfish");

        // استدعاء التحديث
        service.updatePreferences(customer, restrictions, allergies);

        // التفضيلات القديمة يجب أن تُمسح
        assertFalse(customer.getDietaryRestrictions().contains("Gluten"));
        assertFalse(customer.getAllergies().contains("Shellfish"));

        // التفضيلات الجديدة يجب أن تكون موجودة
        assertTrue(customer.getDietaryRestrictions().contains("Vegan"));
        assertTrue(customer.getDietaryRestrictions().contains("Low Salt"));
        assertTrue(customer.getAllergies().contains("Peanuts"));
        assertTrue(customer.getAllergies().contains("Dairy"));
    }
}
