package cookproject;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> users = new ArrayList<>();

    public UserRepository() {
        users = new ArrayList<>();

        

        users.add(new User("customer@example.com", "SecurePass123!", UserRole.CUSTOMER));
        users.add(new User("chef@example.com", "ChefPass123!", UserRole.CHEF));
        users.add(new User("admin@example.com", "AdminPass123!", UserRole.ADMIN));
        users.add(new User("worker@example.com", "WorkerPass123!", UserRole.WORKER));
        users.add(new User("manager@example.com", "ManagerPass123!", UserRole.KITCHEN_MANAGER));
        users.add(new User("supplier@example.com", "SupplierPass123!", UserRole.SUPPLIER));
        users.add(new User("delivery@example.com", "DeliveryPass123!", UserRole.DELIVERY));
    }

    public static User getUserByEmail(String enteredEmail) {
        UserRepository repository = new UserRepository();
        User user = repository.findByEmail(enteredEmail);
        if (user != null) {
            return user;
        }
        return null;
    }

    public User findByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean removeUserByEmail(String email) {
        return users.removeIf(u -> u.getEmail().equals(email));
    }

}