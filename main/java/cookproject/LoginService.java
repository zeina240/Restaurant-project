package cookproject;

import java.util.HashMap;
import java.util.Map;

public class LoginService {

    private Map<String, User> users = new HashMap<>();

    public LoginService() {
        // إضافة المستخدمين الافتراضيين
     users.put("customer1", new User("customer1", "customer1@example.com", "pass123", UserRole.CUSTOMER));
users.put("chef1", new User("chef1", "chef1@example.com", "chefpass", UserRole.CHEF));
users.put("admin", new User("admin", "admin@example.com", "admin123", UserRole.ADMIN));
users.put("worker", new User("worker", "worker@example.com", "worker123", UserRole.WORKER));
users.put("delivery", new User("delivery", "delivery@example.com", "delivery123", UserRole.DELIVERY));
users.put("supplier", new User("supplier", "supplier@example.com", "supplier123", UserRole.SUPPLIER));
users.put("kitchenManager", new User("kitchenManager", "kitchenManager@example.com", "kitchen123", UserRole.KITCHEN_MANAGER));


    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;  // If login fails
    }
}
