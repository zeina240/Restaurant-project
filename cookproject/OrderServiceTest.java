package cookproject;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        orderService = new OrderService();
    }

    @Test
    public void testInitializeDummyOrders() {
        List<Order> allOrders = orderService.getAllOrdersPublic();
        assertFalse(allOrders.isEmpty());
        // تأكد أنه فيه طلبات لعميل معين
        assertNotNull(orderService.getPastOrders("alice@example.com"));
    }

    @Test
    public void testReorderExistingOrder() {
        List<Order> aliceOrders = orderService.getPastOrders("alice@example.com");
        assertFalse(aliceOrders.isEmpty());
        Order firstOrder = aliceOrders.get(0);
        Order newOrder = orderService.reorder(firstOrder.getOrderId());

        assertEquals(firstOrder.getCustomerId(), newOrder.getCustomerId());
        assertEquals(firstOrder.getMealItems(), newOrder.getMealItems());
        assertNotEquals(firstOrder.getOrderId(), newOrder.getOrderId());
        assertEquals(java.time.LocalDate.now().toString(), newOrder.getDate());
    }

    @Test
    public void testReorderNonExistingOrderThrows() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            orderService.reorder("INVALID_ID");
        });
        assertTrue(exception.getMessage().contains("Order not found"));
    }

    @Test
    public void testCompleteOrderAddsOrder() {
        String customerId = "newcustomer@example.com";
        Order newOrder = orderService.completeOrder(customerId);

        assertEquals(customerId, newOrder.getCustomerId());
        assertEquals(1, newOrder.getMealItems().size());
        assertEquals("Custom Meal", newOrder.getMealItems().get(0).getName());

        List<Order> customerOrders = orderService.getPastOrders(customerId);
        assertTrue(customerOrders.contains(newOrder));
    }

    @Test
    public void testGetPendingDeliveries() {
        List<Order> pending = orderService.getPendingDeliveries();
        assertFalse(pending.isEmpty());
        for (Order order : pending) {
            assertEquals("READY_FOR_DELIVERY", order.getDeliveryStatus());
        }
    }

    @Test
    public void testMarkOrderAsDelivered() {
        List<Order> pending = orderService.getPendingDeliveries();
        assertFalse(pending.isEmpty());
        Order order = pending.get(0);

        boolean result = orderService.markOrderAsDelivered(order.getOrderId());
        assertTrue(result);
        assertEquals("DELIVERED", order.getDeliveryStatus());

        // Try marking again should return false because already delivered
        boolean secondResult = orderService.markOrderAsDelivered(order.getOrderId());
        assertFalse(secondResult);
    }

    @Test
    public void testGetOrderById() {
        List<Order> allOrders = orderService.getAllOrdersPublic();
        assertFalse(allOrders.isEmpty());
        Order order = allOrders.get(0);

        Order found = orderService.getOrderById(order.getOrderId());
        assertNotNull(found);
        assertEquals(order.getOrderId(), found.getOrderId());

        Order notFound = orderService.getOrderById("NON_EXISTING_ID");
        assertNull(notFound);
    }

    @Test
    public void testAddOrder() {
        String customerId = "test@example.com";
        Order order = new Order("O999", customerId, List.of(new Meal("Test Meal", null, 10.0)), "2025-05-20", 10.0);
        orderService.addOrder(customerId, order);

        List<Order> orders = orderService.getPastOrders(customerId);
        assertTrue(orders.contains(order));
    }
}
