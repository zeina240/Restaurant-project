package cookproject;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    public void setup() {
        customer = new Customer("john_doe", "john@example.com", "Vegan", "Peanuts", "123 Street");
    }

    @Test
    public void testConstructors() {
        // Constructor كامل
        assertEquals("john_doe", customer.getUsername());
        assertEquals("john@example.com", customer.getEmail());
        assertEquals("Vegan", customer.getDietaryPreference());
        assertEquals("Peanuts", customer.getAllergyInfo());
        assertEquals("123 Street", customer.getAddress());

        // Constructor بسيط
        Customer simpleCustomer = new Customer();
        assertEquals("testuser", simpleCustomer.getUsername());
        assertEquals("test@example.com", simpleCustomer.getEmail());
        assertEquals("None", simpleCustomer.getDietaryPreference());
        assertEquals("None", simpleCustomer.getAllergyInfo());
        assertEquals("Test Address", simpleCustomer.getAddress());

        // Constructor مع User
        User user = new User("alice", "alice@example.com", "pass123", UserRole.CUSTOMER);
        Customer fromUser = new Customer(user);
        assertEquals("alice", fromUser.getUsername());
        assertEquals("alice@example.com", fromUser.getEmail());
        assertEquals("None", fromUser.getDietaryPreference());
        assertEquals("None", fromUser.getAllergyInfo());
        assertEquals("Unknown", fromUser.getAddress());
    }

    @Test
    public void testAddDietaryRestriction() {
        customer.clearPreferences();
        assertEquals("None", customer.getDietaryPreference());

        customer.addDietaryRestriction("Gluten-Free");
        assertEquals("Gluten-Free", customer.getDietaryPreference());
        assertTrue(customer.getDietaryRestrictions().contains("Gluten-Free"));

        customer.addDietaryRestriction("Low-Carb");
        assertEquals("Gluten-Free, Low-Carb", customer.getDietaryPreference());
        assertTrue(customer.getDietaryRestrictions().contains("Low-Carb"));
        assertEquals(2, customer.getDietaryRestrictions().size());
    }

    @Test
    public void testAddAllergy() {
        customer.clearPreferences();
        assertEquals("None", customer.getAllergyInfo());

        customer.addAllergy("Peanuts");
        assertEquals("Peanuts", customer.getAllergyInfo());
        assertTrue(customer.getAllergies().contains("Peanuts"));

        customer.addAllergy("Shellfish");
        assertEquals("Peanuts, Shellfish", customer.getAllergyInfo());
        assertTrue(customer.getAllergies().contains("Shellfish"));
        assertEquals(2, customer.getAllergies().size());
    }

    @Test
    public void testClearPreferences() {
        customer.addDietaryRestriction("Vegan");
        customer.addAllergy("Peanuts");
        assertFalse(customer.getDietaryRestrictions().isEmpty());
        assertFalse(customer.getAllergies().isEmpty());

        customer.clearPreferences();

        assertEquals("None", customer.getDietaryPreference());
        assertEquals("None", customer.getAllergyInfo());
        assertTrue(customer.getDietaryRestrictions().isEmpty());
        assertTrue(customer.getAllergies().isEmpty());
    }

    @Test
    public void testIsRestricted() {
        customer.clearPreferences();
        customer.addDietaryRestriction("Gluten-Free");
        customer.addDietaryRestriction("Vegan");

        assertTrue(customer.isRestricted("Gluten-Free"));
        assertTrue(customer.isRestricted("Vegan"));
        assertFalse(customer.isRestricted("Low-Carb"));
    }

    @Test
    public void testGetUserReturnsSelf() {
        User user = customer.getUser();
        assertSame(customer, user);
    }
}
