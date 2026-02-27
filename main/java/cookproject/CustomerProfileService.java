package cookproject;

import cookproject.CustomerProfile;

import java.util.HashMap;
import java.util.Map;

public class CustomerProfileService {
    private Map<String, CustomerProfile> customerData = new HashMap<>();

    public void savePreferences(String customerId, String restriction, String allergies) {
        customerData.put(customerId, new CustomerProfile(customerId, restriction, allergies));
    }

    public CustomerProfile getPreferences(String customerId) {
        return customerData.get(customerId);
    }
}
