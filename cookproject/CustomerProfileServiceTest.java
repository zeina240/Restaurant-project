package cookproject;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerProfileServiceTest {

    private CustomerProfileService service;

    @BeforeEach
    public void setup() {
        service = new CustomerProfileService();
    }

    @Test
    public void testSaveAndGetPreferences() {
        String customerId = "cust123";
        String restriction = "gluten-free";
        String allergies = "peanuts";

        service.savePreferences(customerId, restriction, allergies);

        CustomerProfile profile = service.getPreferences(customerId);
        assertNotNull(profile);
        assertEquals(customerId, profile.getCustomerId());
        assertEquals(restriction, profile.getDietaryRestriction());
        assertEquals(allergies, profile.getAllergies());
    }

    @Test
    public void testGetPreferencesForUnknownCustomer_returnsNull() {
        CustomerProfile profile = service.getPreferences("unknownId");
        assertNull(profile);
    }

    @Test
    public void testOverwritePreferencesForSameCustomer() {
        String customerId = "cust456";

        service.savePreferences(customerId, "lactose", "shellfish");
        CustomerProfile profile1 = service.getPreferences(customerId);
        assertEquals("lactose", profile1.getDietaryRestriction());
        assertEquals("shellfish", profile1.getAllergies());

        // Overwrite with new preferences
        service.savePreferences(customerId, "vegan", "none");
        CustomerProfile profile2 = service.getPreferences(customerId);
        assertEquals("vegan", profile2.getDietaryRestriction());
        assertEquals("none", profile2.getAllergies());
    }
}
