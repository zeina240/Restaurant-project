package cookproject;

import java.util.List;

public class Statistics {
    private final UserRepository userRepo;
    private final OrderService orderService;

    public Statistics(UserRepository userRepo, OrderService orderService) {
        this.userRepo = userRepo;
        this.orderService = orderService;
    }

    public void viewStatistics() {
        List<User> allUsers = userRepo.getAllUsers();
        List<Order> allOrders = orderService.getAllOrdersPublic(); // اجلب الطلبات من النظام الحقيقي

        long adminCount = allUsers.stream().filter(u -> u.getRole() == UserRole.ADMIN).count();
        long chefCount = allUsers.stream().filter(u -> u.getRole() == UserRole.CHEF).count();
        long managerCount = allUsers.stream().filter(u -> u.getRole() == UserRole.KITCHEN_MANAGER).count(); // أصلح Role.MANAGER

        long completed = allOrders.stream().filter(Order::isPaid).count();
        long pending = allOrders.size() - completed;

        System.out.println("\n--- User Statistics ---");
        System.out.println("Admins: " + adminCount);
        System.out.println("Chefs: " + chefCount);
        System.out.println("Kitchen Managers: " + managerCount);

        System.out.println("\n--- Order Statistics ---");
        System.out.println("Completed orders: " + completed);
        System.out.println("Pending orders: " + pending);
    }
}
