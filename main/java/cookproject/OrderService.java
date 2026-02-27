package cookproject;

import java.util.*;
import java.util.stream.Collectors;

public class OrderService {

    private Map<String, List<Order>> ordersDB = new HashMap<>();

    public OrderService() {
        initializeDummyOrders();
    }

    private void initializeDummyOrders() {
        // إنشاء وجبات
        Meal pizza = new Meal("Pizza", null, 20.0);
        Meal salad = new Meal("Salad", null, 5.0);
        Meal burger = new Meal("Burger", null, 15.0);
        Meal sandwich = new Meal("Sandwich", null, 12.5);

        // طلبات alice
        Order o1 = new Order("O001", "alice@example.com", List.of(pizza), "2025-05-15", pizza.getPrice());
        o1.setPaid(true);

        Order o2 = new Order("O002", "alice@example.com", List.of(burger), "2025-05-16", burger.getPrice());
        o2.setPaid(false);

        ordersDB.put("alice@example.com", new ArrayList<>(Arrays.asList(o1, o2)));

        // طلب جاهز للتوصيل
        Order o3 = new Order("O010", "delivery@example.com", List.of(sandwich), "2025-05-16", sandwich.getPrice());
        o3.setPaid(true);
        o3.setReady(true);
        o3.setDeliveryStatus("READY_FOR_DELIVERY");

        ordersDB.put("delivery@example.com", new ArrayList<>(Arrays.asList(o3)));
    }

    public Order reorder(String orderId) {
        for (List<Order> orders : ordersDB.values()) {
            for (Order order : orders) {
                if (order.getOrderId().equals(orderId)) {
                    Order newOrder = new Order(
                            generateOrderId(),
                            order.getCustomerId(),
                            order.getMealItems(),
                            getCurrentDate(),
                            order.getTotalCost()
                    );

                    List<Order> customerOrders = ordersDB.get(order.getCustomerId());
                    if (customerOrders == null) {
                        customerOrders = new ArrayList<>();
                        ordersDB.put(order.getCustomerId(), customerOrders);
                    }
                    customerOrders.add(newOrder);
                    return newOrder;
                }
            }
        }
        throw new RuntimeException("Order not found for reorder: " + orderId);
    }

    private String generateOrderId() {
        return "O" + (int) (Math.random() * 10000);
    }

    private String getCurrentDate() {
        return java.time.LocalDate.now().toString();
    }

    public List<Order> getAllOrders() {
        List<Order> allOrders = new ArrayList<>();
        for (List<Order> customerOrders : ordersDB.values()) {
            allOrders.addAll(customerOrders);
        }
        return allOrders;
    }

    public Order completeOrder(String customerId) {
        Meal customMeal = new Meal("Custom Meal", null, 50.0); // وجبة مخصصة
        List<Meal> customMeals = new ArrayList<>();
        customMeals.add(customMeal);

        Order newOrder = new Order(
                generateOrderId(),
                customerId,
                customMeals,
                getCurrentDate(),
                50.0
        );
        ordersDB.computeIfAbsent(customerId, k -> new ArrayList<>()).add(newOrder);
        return newOrder;
    }

    private void showOrderStatistics() {
        List<Order> allOrders = getAllOrders();

        long completed = allOrders.stream().filter(Order::isPaid).count();
        long pending = allOrders.size() - completed;

        System.out.println("\n--- Order Statistics ---");
        System.out.println("Completed orders: " + completed);
        System.out.println("Pending orders: " + pending);
    }

    public List<Order> getAllOrdersPublic() {
        return getAllOrders();
    }

    public void addOrder(String customerId, Order newOrder) {
        ordersDB.computeIfAbsent(customerId, k -> new ArrayList<>()).add(newOrder);
    }

    public List<Order> getPastOrders(String customerId) {
        return ordersDB.getOrDefault(customerId, Collections.emptyList());
    }

    public List<Order> getPendingDeliveries() {
        return getAllOrders().stream()
                .filter(order -> "READY_FOR_DELIVERY".equalsIgnoreCase(order.getDeliveryStatus()))
                .collect(Collectors.toList());
    }

    public boolean markOrderAsDelivered(String orderId) {
        for (Order order : getAllOrders()) {
            if (order.getOrderId().equals(orderId) && !"DELIVERED".equalsIgnoreCase(order.getDeliveryStatus())) {
                order.setDeliveryStatus("DELIVERED");
                return true;
            }
        }
        return false;
    }

    public Order getOrderById(String id) {
        for (Order order : getAllOrders()) {
            if (order.getOrderId().equalsIgnoreCase(id)) {
                return order;
            }
        }
        return null;
    }
}
