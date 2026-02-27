package cookproject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerProfileTest {

    @Test
    public void testConstructorAndGetters() {
        CustomerProfile profile = new CustomerProfile("123", "Vegetarian", "Peanuts");
        
        assertEquals("123", profile.getCustomerId());
        assertEquals("Vegetarian", profile.getDietaryRestriction());
        assertEquals("Peanuts", profile.getAllergies());
    }

    @Test
    public void testEquals_SameObject() {
        CustomerProfile profile = new CustomerProfile("123", "Vegetarian", "Peanuts");
        assertTrue(profile.equals(profile));
    }

    @Test
    public void testEquals_EqualObjects() {
        CustomerProfile profile1 = new CustomerProfile("123", "Vegetarian", "Peanuts");
        CustomerProfile profile2 = new CustomerProfile("456", "Vegetarian", "Peanuts");
        assertTrue(profile1.equals(profile2));
    }

    @Test
    public void testEquals_DifferentObjects() {
        CustomerProfile profile1 = new CustomerProfile("123", "Vegetarian", "Peanuts");
        CustomerProfile profile2 = new CustomerProfile("123", "Vegan", "Peanuts");
        assertFalse(profile1.equals(profile2));
    }

    @Test
    public void testEquals_NullObject() {
        CustomerProfile profile = new CustomerProfile("123", "Vegetarian", "Peanuts");
        assertFalse(profile.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        CustomerProfile profile = new CustomerProfile("123", "Vegetarian", "Peanuts");
        String notAProfile = "I'm not a CustomerProfile";
        assertFalse(profile.equals(notAProfile));
    }

    @Test
    public void testHashCode_EqualObjects() {
        CustomerProfile profile1 = new CustomerProfile("123", "Vegetarian", "Peanuts");
        CustomerProfile profile2 = new CustomerProfile("456", "Vegetarian", "Peanuts");
        assertEquals(profile1.hashCode(), profile2.hashCode());
    }

    @Test
    public void testHashCode_DifferentObjects() {
        CustomerProfile profile1 = new CustomerProfile("123", "Vegetarian", "Peanuts");
        CustomerProfile profile2 = new CustomerProfile("123", "Vegan", "Peanuts");
        assertNotEquals(profile1.hashCode(), profile2.hashCode());
    }

    @Test
    public void testHashCode_Consistency() {
        CustomerProfile profile = new CustomerProfile("123", "Vegetarian", "Peanuts");
        int initialHashCode = profile.hashCode();
        assertEquals(initialHashCode, profile.hashCode());
    }
}