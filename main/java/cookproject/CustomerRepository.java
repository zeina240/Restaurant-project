package cookproject;

import java.util.HashMap;
import java.util.Map;

public class CustomerRepository {
    private Map<String, Customer> customerMap = new HashMap<>();

    // Constructor - Initialize with some default data
    public CustomerRepository() {
        User user1 = new User("alice@example.com", "pass123", UserRole.CUSTOMER);
        Customer customer1 = new Customer(user1);
        customer1.addDietaryRestriction("gluten");
        customer1.addAllergy("peanuts");

        User user2 = new User("bob@example.com", "password", UserRole.CUSTOMER);
        Customer customer2 = new Customer(user2);
        customer2.addDietaryRestriction("lactose");
        customer2.addAllergy("shellfish");

        User user3 = new User("carol@example.com", "abc123", UserRole.CUSTOMER);
        Customer customer3 = new Customer(user3);

        customerMap.put(user1.getEmail(), customer1);
        customerMap.put(user2.getEmail(), customer2);
        customerMap.put(user3.getEmail(), customer3);
    }

    // Retrieve a customer by email
    public Customer findByEmail(String email) {
        return customerMap.get(email);
    }

    // Add a new customer
    public void addCustomer(Customer customer) {
        customerMap.put(customer.getEmail(), customer);
    }

    // Update a customer's email
    public boolean updateCustomerEmail(String oldEmail, String newEmail) {
        if (customerMap.containsKey(oldEmail) && !customerMap.containsKey(newEmail)) {
            Customer customer = customerMap.remove(oldEmail);
            customer.setEmail(newEmail);  // Ensure Customer supports setEmail()
            customerMap.put(newEmail, customer);
            return true;
        }
        return false;
    }

    // Remove a customer by email
    public boolean removeCustomerByEmail(String email) {
        return customerMap.remove(email) != null;
    }

    // Get all customers
    public Map<String, Customer> getAllCustomers() {
        return customerMap;
    }
}
