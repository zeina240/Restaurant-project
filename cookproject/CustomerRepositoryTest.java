package cookproject;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerRepositoryTest {

    private CustomerRepository repository;

    @BeforeEach
    public void setup() {
        repository = new CustomerRepository();
    }

    @Test
    public void testInitialCustomersExist() {
        assertNotNull(repository.findByEmail("alice@example.com"));
        assertNotNull(repository.findByEmail("bob@example.com"));
        assertNotNull(repository.findByEmail("carol@example.com"));
    }

    @Test
    public void testFindByEmail_existing() {
        Customer customer = repository.findByEmail("alice@example.com");
        assertNotNull(customer);
        assertTrue(customer.getDietaryRestrictions().contains("gluten"));
        assertTrue(customer.getAllergies().contains("peanuts"));
    }

    @Test
    public void testFindByEmail_nonExisting() {
        assertNull(repository.findByEmail("nonexistent@example.com"));
    }

    @Test
    public void testAddCustomer() {
        User newUser = new User("dave@example.com", "mypassword", UserRole.CUSTOMER);
        Customer newCustomer = new Customer(newUser);
        newCustomer.addDietaryRestriction("vegan");
        repository.addCustomer(newCustomer);

        Customer retrieved = repository.findByEmail("dave@example.com");
        assertNotNull(retrieved);
        assertTrue(retrieved.getDietaryRestrictions().contains("vegan"));
    }

    @Test
    public void testUpdateCustomerEmail_success() {
        boolean updated = repository.updateCustomerEmail("alice@example.com", "alice.new@example.com");
        assertTrue(updated);
        assertNull(repository.findByEmail("alice@example.com"));
        Customer updatedCustomer = repository.findByEmail("alice.new@example.com");
        assertNotNull(updatedCustomer);
        assertEquals("alice.new@example.com", updatedCustomer.getEmail());
    }

    @Test
    public void testUpdateCustomerEmail_fail_existingNewEmail() {
        // Trying to update alice@example.com to bob@example.com (already exists)
        boolean updated = repository.updateCustomerEmail("alice@example.com", "bob@example.com");
        assertFalse(updated);
        assertNotNull(repository.findByEmail("alice@example.com"));
    }

    @Test
    public void testUpdateCustomerEmail_fail_nonExistingOldEmail() {
        boolean updated = repository.updateCustomerEmail("nonexistent@example.com", "new@example.com");
        assertFalse(updated);
    }

    @Test
    public void testRemoveCustomerByEmail_success() {
        boolean removed = repository.removeCustomerByEmail("bob@example.com");
        assertTrue(removed);
        assertNull(repository.findByEmail("bob@example.com"));
    }

    @Test
    public void testRemoveCustomerByEmail_fail() {
        boolean removed = repository.removeCustomerByEmail("nonexistent@example.com");
        assertFalse(removed);
    }

    @Test
    public void testGetAllCustomers() {
        var allCustomers = repository.getAllCustomers();
        assertTrue(allCustomers.containsKey("alice@example.com"));
        assertTrue(allCustomers.containsKey("bob@example.com"));
        assertTrue(allCustomers.containsKey("carol@example.com"));
    }
}
